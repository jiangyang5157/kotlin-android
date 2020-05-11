package com.gmail.jiangyang5157.kotlin.ui.router

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.core.Injectable
import javax.inject.Inject

open class BaseRouterFragment : Fragment(), Injectable, RouterFragment {

    @Inject
    override lateinit var router: FragmentRouter<RouterActivityRoute>
}
