package com.gmail.jiangyang5157.kotlin.example.router.ui.bundle

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_bundlerouter0.*

class BundleRouterFragment0 : Fragment(), RouterFragment {

    @Parcelize
    data class Route(val data: Bundle) : BundleRoute {

        val param1
            get() = data.get(KEY_PARAM1) as String

        val param2
            get() = data.get(KEY_PARAM2) as String

        companion object {
            const val KEY = "Page 0"
            const val KEY_PARAM1 = "param1"
            const val KEY_PARAM2 = "param2"
        }
    }

    override val router: FragmentRouter<BundleRoute> = RouterApi.bundleRouter
    private val route: Route by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bundlerouter0, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "I am= ${route}\n\n" +
                "My Key is= ${Route.KEY}\n\n" +
                "Data= ${route.data}\n\n" +
                "param1= ${route.param1}\n" +
                "param2= ${route.param2}\n"

        btn_1.setOnClickListener {
            router push RouterApi.bundleRoute(
                BundleData(
                    "Page 1",
                    Bundle().apply { putString("param1", "Push by Page 0: ${Route.KEY}") })
            )
        }

        btn_2.setOnClickListener {
            router push RouterApi.bundleRoute(
                BundleData(
                    "Page 2",
                    Bundle().apply { putString("param1", "Push by Page 0: ${Route.KEY}") })
            )
        }

        btn_3.setOnClickListener {
            router push RouterApi.bundleRoute(
                BundleData(
                    "Page 3",
                    Bundle().apply { putString("param1", "Push by Page 0: ${Route.KEY}") })
            )
        }
    }
}
