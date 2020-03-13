package com.gmail.jiangyang5157.feature_color.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gmail.jiangyang5157.core.Injectable
import com.gmail.jiangyang5157.core.data.Resource
import com.gmail.jiangyang5157.core.data.Status
import com.gmail.jiangyang5157.feature_color.R
import com.gmail.jiangyang5157.feature_color.vm.ColorViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_color.tv_info

class ColorFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vm = ViewModelProviders.of(this, viewModelFactory)[ColorViewModel::class.java]

        view.findViewById<Button>(R.id.btn_fetch_color).setOnClickListener {
            vm.loadColor(forceFetch = true)
                .observe(viewLifecycleOwner, Observer { data ->
                    printInfo(data)
                })
        }

        view.findViewById<Button>(R.id.btn_fetch_colors).setOnClickListener {
            vm.loadColors(forceFetch = true)
                .observe(viewLifecycleOwner, Observer { data ->
                    printInfo(data)
                })
        }

        view.findViewById<Button>(R.id.btn_load_color).setOnClickListener {
            vm.loadColor().observe(viewLifecycleOwner, Observer { data ->
                printInfo(data)
            })
        }

        view.findViewById<Button>(R.id.btn_load_colors).setOnClickListener {
            vm.loadColors().observe(viewLifecycleOwner, Observer { data ->
                printInfo(data)
            })
        }
    }

    private fun <T> printInfo(data: Resource<T>) {
        when (data.status) {
            Status.LOADING -> {
                tv_info.text = "Loading......\n"
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
