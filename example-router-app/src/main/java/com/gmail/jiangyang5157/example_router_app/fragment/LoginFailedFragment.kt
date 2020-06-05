package com.gmail.jiangyang5157.example_router_app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragment
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.R
import com.gmail.jiangyang5157.example_router_app.UriRoute
import kotlinx.android.synthetic.main.fragment_login_failed.*

class LoginFailedFragment : Fragment(), RouterFragment {

    override val router: FragmentRouter<*> = Dependency.router
    private val route: UriRoute by route()
    private val email by lazy { route.query("email") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login_failed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_email.text = email
    }
}
