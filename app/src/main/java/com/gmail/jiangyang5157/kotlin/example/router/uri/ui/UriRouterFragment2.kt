package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.popUntil
import com.gmail.jiangyang5157.android.router.core.popUntilKey
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.usecase.*
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_urirouter2.*
import kotlin.reflect.KClass

class UriRouterFragment2 : Fragment(), RouterFragmentSupport<UriRouteData> {

    private val route: UriRouteData by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urirouter2, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "My route:\n${route.data}\n\n" +
                "param1= ${route.getParam("param1")}\n" +
                "param2= ${route.getParam("param2")}\n"

        btn_1.setOnClickListener {
            router push UriRouteElement(
                "https://com.gmail.jiangyang5157/example/urirouter/page2?param1=Push by Page 2"
            )
        }

        btn_2.setOnClickListener {
            router popUntilKey Key(
                "https://com.gmail.jiangyang5157/example/urirouter/page1"
            )
        }
    }
}

