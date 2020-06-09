package com.gmail.jiangyang5157.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Yang Jiang on 13 March, 2019
 */
@Deprecated("use viewModel() or activityViewModel() to manage ViewModel scope")
class ViewModelMapFactory @Inject constructor(
  private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = providers[modelClass]
            ?: providers.asIterable().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value
            ?: throw IllegalArgumentException("Unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
