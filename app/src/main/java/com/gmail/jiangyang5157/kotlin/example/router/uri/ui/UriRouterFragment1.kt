package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.replaceTopWith
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterFragmentGuest
import com.gmail.jiangyang5157.kotlin.example.router.uri.UriRoute
import kotlinx.android.synthetic.main.fragment_urirouter1.*

class UriRouterFragment1 : Fragment(),
    RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urirouter1, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "My route:\n${route.data}\n\n" +
                "param1= ${route.parameter("param1")}\n" +
                "param2= ${route.parameter("param2")}\n"

        btn_1.setOnClickListener {
            router push UriRoute(
                "http://com.gmail.jiangyang5157/uri/page1?param1=Push by Page 1"
            )
        }

        btn_2.setOnClickListener {
            router replaceTopWith UriRoute(
                "http://com.gmail.jiangyang5157/uri/page1?param1=Replace with Page 1"
            )
        }

        btn_3.setOnClickListener {
            router push UriRoute(
                "http://com.gmail.jiangyang5157/uri/page2?param1=Push by Page 1"
            )
        }
    }
}
