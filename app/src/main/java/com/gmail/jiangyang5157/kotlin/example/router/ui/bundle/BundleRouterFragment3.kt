package com.gmail.jiangyang5157.kotlin.example.router.ui.bundle

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_bundlerouter3.*

class BundleRouterFragment3 : Fragment(), RouterFragment {

    @Parcelize
    data class Route(val data: Bundle) : BundleRoute {

        val param1
            get() = data.get(KEY_PARAM1)

        companion object {
            const val KEY = "Page 3"
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
        return inflater.inflate(R.layout.fragment_bundlerouter3, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text =
            "I am= ${route}\n\n" +
                "My Key is= ${Route.KEY}\n\n" +
                "Data= ${route.data}\n\n" +
                "param1= ${route.param1}\n"

        btn_1.setOnClickListener {
            // todo
        }
    }
}
