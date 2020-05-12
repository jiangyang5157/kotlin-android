package com.gmail.jiangyang5157.kotlin.example.router.ui.uri

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.replaceTopWith
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import com.gmail.jiangyang5157.kotlin.example.router.RouterData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_urirouter1.*

class UriRouterFragment1 : Fragment(), RouterFragment {

    @Parcelize
    data class Route(val uriString: String) : RouterData.UriRoute {

        val param1
            get() = Uri.parse(uriString).getQueryParameter(KEY_PARAM1)

        companion object {
            const val ADDRESS = "https://com.gmail.jiangyang5157/example/urirouter/page1"
            const val KEY_PARAM1 = "param1"
        }
    }

    override val router: FragmentRouter<RouterData.UriRoute> = RouterApi.router
    private val route: Route by route()

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
            "I am= ${route}\n\n" +
                "My Address is= ${Route.ADDRESS}\n\n" +
                "From= ${route.uriString}\n\n" +
                "param1= ${route.param1}\n"

        btn_1.setOnClickListener {
            RouterApi.router push RouterApi.route(
                "https://com.gmail.jiangyang5157/example/urirouter/page1?param1=Push by Page 1"
            )
        }

        btn_2.setOnClickListener {
            RouterApi.router replaceTopWith RouterApi.route(
                "https://com.gmail.jiangyang5157/example/urirouter/page1?param1=Replace with Page 1"
            )
        }
    }
}
