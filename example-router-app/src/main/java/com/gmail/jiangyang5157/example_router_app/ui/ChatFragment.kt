package com.gmail.jiangyang5157.example_router_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragment
import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.example_router_app.Contact
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.R
import com.gmail.jiangyang5157.example_router_app.UriRoute
import com.google.gson.Gson
import java.util.*

class ChatFragment : Fragment(), RouterFragment {

    override val router: FragmentRouter<*> = Dependency.router
    private val route: UriRoute by route()
    private val contact by lazy {
        route.query("contact").let { Gson().fromJson<Contact>(it!!) }
    }
    private val lastSeenTime by lazy {
        route.query("lastSeenTime").let { it!!.toLong() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat, container, false)

    override fun onResume() {
        super.onResume()
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.supportActionBar?.title = contact.name
        appCompatActivity.supportActionBar?.subtitle = Date(lastSeenTime).toString()
    }
}
