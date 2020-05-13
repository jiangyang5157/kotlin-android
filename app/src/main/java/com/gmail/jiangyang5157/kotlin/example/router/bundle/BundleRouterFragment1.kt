package com.gmail.jiangyang5157.kotlin.example.router.bundle

import android.annotation.SuppressLint
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
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_bundlerouter1.*

class BundleRouterFragment1 : Fragment(), RouterFragment {

    @Parcelize
    data class Route(val data: Bundle) :
        BundleRoute {

        val param1
            get() = data.get(KEY_PARAM1) as String

        companion object {
            const val ID = "Page 1"
            const val KEY_PARAM1 = "param1"
        }
    }

    override val router: FragmentRouter<BundleRoute> = RouterApi.bundleRouter
    private val route: Route by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bundlerouter1, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "My data:\n${route}\n\n" +
                "My id:\n${Route.ID}\n\n" +
                "param1= ${route.param1}\n"

        btn_1.setOnClickListener {
            router push RouterApi.bundleRoute(
                BundleRouteData(
                    "Page 1",
                    Bundle().apply { putString("param1", "Push by Page 1: ${Route.ID}") })
            )
        }

        btn_2.setOnClickListener {
            router replaceTopWith RouterApi.bundleRoute(
                BundleRouteData(
                    "Page 1",
                    Bundle().apply { putString("param1", "Replace with Page 1: ${Route.ID}") })
            )
        }
    }
}
