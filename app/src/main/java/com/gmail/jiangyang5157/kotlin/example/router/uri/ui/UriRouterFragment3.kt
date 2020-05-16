package com.gmail.jiangyang5157.kotlin.example.router.uri.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.usecase.RouteData
import com.gmail.jiangyang5157.kotlin.example.router.usecase.RouterFragmentSupport
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_urirouter3.*
import kotlin.reflect.KClass

class UriRouterFragment3 : Fragment(), RouterFragmentSupport<String> {

    @Parcelize
    data class Route(override val data: String) : RouteData<String> {

        override val fragment: KClass<out Fragment>
            get() = UriRouterFragment3::class

        val param1
            get() = Uri.parse(data).getQueryParameter(KEY_PARAM1)

        companion object {
            const val ID = "https://com.gmail.jiangyang5157/example/urirouter/page3"
            const val KEY_PARAM1 = "param1"
        }
    }

    private val route: Route by route()

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
            "My data:\n${route}\n\n" +
                "My id:\n${Route.ID}\n\n" +
                "param1= ${route.param1}\n"
    }
}
