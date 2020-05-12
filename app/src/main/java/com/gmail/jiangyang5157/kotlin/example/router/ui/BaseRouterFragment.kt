package com.gmail.jiangyang5157.kotlin.example.router.ui

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi
import com.gmail.jiangyang5157.kotlin.example.router.RouterData

open class BaseRouterFragment : Fragment(), RouterFragment {

    override val router: FragmentRouter<RouterData.UriRoute> =
        RouterApi.router
}
