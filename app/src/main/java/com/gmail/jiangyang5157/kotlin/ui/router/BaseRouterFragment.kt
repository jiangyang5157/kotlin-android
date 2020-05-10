package com.gmail.jiangyang5157.kotlin.ui.router

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment

open class BaseRouterFragment : Fragment(), RouterFragment {

    override val router: FragmentRouter<AppRoute> = Dependencies.router
}
