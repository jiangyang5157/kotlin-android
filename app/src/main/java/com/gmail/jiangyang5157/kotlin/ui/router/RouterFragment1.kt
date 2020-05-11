package com.gmail.jiangyang5157.kotlin.ui.router

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.jiangyang5157.android.router.route
import com.gmail.jiangyang5157.kotlin.R
import kotlinx.android.synthetic.main.fragment_router1.*

class RouterFragment1 : BaseRouterFragment() {

    private val route: UriRoute.Fragment1 by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_router1, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "1: ${route.info()}"
    }
}
