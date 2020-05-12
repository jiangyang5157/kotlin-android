package com.gmail.jiangyang5157.kotlin.example.router.ui.uri

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_urirouter0.*

class UriRouterFragment0 : Fragment(), RouterFragment {

    @Parcelize
    data class Route(val uriString: String) : UriRoute {

        val param1
            get() = Uri.parse(uriString).getQueryParameter(KEY_PARAM1)

        val param2
            get() = Uri.parse(uriString).getQueryParameter(KEY_PARAM2)

        companion object {
            const val ADDRESS = "https://com.gmail.jiangyang5157/example/urirouter/page0"
            const val KEY_PARAM1 = "param1"
            const val KEY_PARAM2 = "param2"
        }
    }

    override val router: FragmentRouter<UriRoute> = RouterApi.uriRouter
    private val route: Route by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urirouter0, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "My data:\n${route}\n\n" +
                "My id:\n${Route.ADDRESS}\n\n" +
                "param1= ${route.param1}\n" +
                "param2= ${route.param2}\n"

        btn_1.setOnClickListener {
            router push RouterApi.uriRoute(
                "https://com.gmail.jiangyang5157/example/urirouter/page1?param1=Push by Page 0: ${Route.ADDRESS}"
            )
        }

        btn_2.setOnClickListener {
            router push RouterApi.uriRoute(
                "https://com.gmail.jiangyang5157/example/urirouter/page2?param1=Push by Page 0: ${Route.ADDRESS}"
            )
        }

        btn_3.setOnClickListener {
            router push RouterApi.uriRoute(
                "https://com.gmail.jiangyang5157/example/urirouter/page3?param1=Push by Page 0: ${Route.ADDRESS}"
            )
        }
    }
}
