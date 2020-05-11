package com.gmail.jiangyang5157.kotlin.ui.router

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.route
import com.gmail.jiangyang5157.kotlin.R
import kotlinx.android.synthetic.main.fragment_router.*

class RouterFragment0 : BaseRouterFragment() {

    private val route: UriRoute.Fragment0 by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_router, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "0: ${route.info()}"

        btn_1.setOnClickListener {
            RouterApi.router push RouterApi.route("https://com.gmail.jiangyang5157/RouterActivity/router1?info=From 0")
        }

        btn_2.setOnClickListener {
            RouterApi.router push RouterApi.route("https://com.gmail.jiangyang5157/RouterActivity/router2?info=From 0")
        }

        btn_3.setOnClickListener {
            RouterApi.router push RouterApi.route("https://com.gmail.jiangyang5157/RouterActivity/router3?info=From 0")
        }
    }
}
