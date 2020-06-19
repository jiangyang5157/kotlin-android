package com.gmail.jiangyang5157.feature_color.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.core.data.Status
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.feature_color.R
import com.gmail.jiangyang5157.feature_color.vm.ColorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_color.*
import javax.inject.Inject

@AndroidEntryPoint
class ColorFragment : Fragment() {

    @Inject
    lateinit var appExecutor: AppExecutor

    // viewModels: fragment scope
    // activityViewModels: activity scope
    private val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        assert(this::appExecutor.isInitialized)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_fetch_color).setOnClickListener {
            colorViewModel.loadColor(forceFetch = true)
                .observe(viewLifecycleOwner, Observer { data ->
                    printInfo(data)
                })
        }

        view.findViewById<Button>(R.id.btn_fetch_colors).setOnClickListener {
            colorViewModel.loadColors(forceFetch = true)
                .observe(viewLifecycleOwner, Observer { data ->
                    printInfo(data)
                })
        }

        view.findViewById<Button>(R.id.btn_load_color).setOnClickListener {
            colorViewModel.loadColor().observe(viewLifecycleOwner, Observer { data ->
                printInfo(data)
            })
        }

        view.findViewById<Button>(R.id.btn_load_colors).setOnClickListener {
            colorViewModel.loadColors().observe(viewLifecycleOwner, Observer { data ->
                printInfo(data)
            })
        }
    }

    private fun <T> printInfo(data: Resource<T>) {
        when (data.status) {
            Status.LOADING -> {
                tv_info.text = "Loading....\n"
            }
            Status.SUCCESS -> {
                tv_info.text = "Success!\n${data.data}\n"
            }
            Status.ERROR -> {
                tv_info.text = "Error!\n${data.message}\n"
            }
        }
    }
}
