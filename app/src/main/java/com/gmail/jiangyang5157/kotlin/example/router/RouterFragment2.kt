package com.gmail.jiangyang5157.kotlin.example.router

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_router2.*

class RouterFragment2 : BaseRouterFragment() {

    @Parcelize
    data class Route(val uriString: String) : RouterData.UriRoute {

        val info
            get() = Uri.parse(uriString).getQueryParameter("info")

        companion object {
            const val ADDRESS = "https://com.gmail.jiangyang5157/RouterActivity/router2"
        }
    }

    private val route: Route by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_router2, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "2: ${route.info}"
    }
}