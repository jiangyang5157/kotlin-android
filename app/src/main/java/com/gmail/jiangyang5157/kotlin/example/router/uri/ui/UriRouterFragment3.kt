package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.usecase.RouterFragmentSupport
import com.gmail.jiangyang5157.kotlin.example.router.usecase.UriRouteData
import kotlinx.android.synthetic.main.fragment_urirouter3.*

class UriRouterFragment3 : Fragment(), RouterFragmentSupport<UriRouteData> {

    private val route: UriRouteData by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urirouter3, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "My route:\n${route.data}\n\n" +
                "param1= ${route.getParam("param1")}\n" +
                "param2= ${route.getParam("param2")}\n"
    }
}
