package com.gmail.jiangyang5157.architecture.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gmail.jiangyang5157.architecture.network.ApiEmptyResponse
import com.gmail.jiangyang5157.architecture.network.ApiErrorResponse
import com.gmail.jiangyang5157.architecture.network.ApiResponse
import com.gmail.jiangyang5157.architecture.network.ApiSuccessResponse
import com.gmail.jiangyang5157.architecture.util.AppExecutor

/**
 * Created by Yang Jiang on July 11, 2019
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(private val appExecutor: AppExecutor) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(
                        Resource.success(
                            newData
                        )
                    )
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        // We dispatch the latest dbSource when fetching networkSource
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        val networkSource = createCall()
        result.addSource(networkSource) { apiResponse ->
            result.removeSource(networkSource)
            result.removeSource(dbSource)
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    appExecutor.diskIO.execute {
                        saveCallResult(processResponse(apiResponse))
                        appExecutor.mainThread.execute {
                            // We dispatch a new dbSource with the latest results just received from network and saved in [saveCallResult]
                            result.addSource(loadFromDb()) { newData ->
                                setValue(
                                    Resource.success(
                                        newData
                                    )
                                )
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutor.mainThread.execute {
                        // We dispatch a new dbSource when received an empty networkSource
                        result.addSource(loadFromDb()) { newData ->
                            setValue(
                                Resource.success(
                                    newData
                                )
                            )
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed(apiResponse.errorMessage)
                    // We dispatch the latest dbSource when failed to receive networkSource
                    result.addSource(dbSource) { newData ->
                        setValue(
                            Resource.error(
                                newData,
                                apiResponse.errorMessage
                            )
                        )
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected open fun shouldFetch(data: ResultType?): Boolean = true

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.responseBody

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    protected open fun onFetchFailed(errorMessage: String?) {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>
}
