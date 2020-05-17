package com.gmail.jiangyang5157.example_core.example.router.fragmentroute

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.example_core.R
import com.gmail.jiangyang5157.example_core.example.router.RouterFragmentGuest
import kotlinx.android.synthetic.main.example_fragment_fragmentroute.*

class ExampleFragment1 : Fragment(),
    RouterFragmentGuest<ExampleRoute> {

    private val route: ExampleRoute by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.example_fragment_fragmentroute, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "ExampleFragment1\n\n" +
            "# route=${route.data}\n"

        btn_1.setOnClickListener {
            router push ExampleRoute1(
                "Push by ExampleFragment1"
            )
        }

        btn_2.setOnClickListener {
            router push ExampleRoute2(
                "Push by ExampleFragment1"
            )
        }

        btn_3.setOnClickListener {
            router replaceTopWith ExampleRoute1(
                "replaceTopWith ExampleRoute1 by ExampleFragment1"
            )
        }

        btn_4.setOnClickListener {
            router replaceTopWith ExampleRoute2(
                "replaceTopWith ExampleRoute2 by ExampleFragment1"
            )
        }
    }
}
