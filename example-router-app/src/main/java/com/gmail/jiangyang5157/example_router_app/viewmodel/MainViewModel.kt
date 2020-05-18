package com.gmail.jiangyang5157.example_router_app.viewmodel

import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.DummyService
import com.gmail.jiangyang5157.example_router_app.UriRoute
import com.google.gson.Gson

class MainViewModel : ViewModel() {

    private val router = Dependency.router

    fun checkIfLoggedIn() {
        val route = if (!DummyService.isLoggedIn) {
            UriRoute("app://com.example_router_app/login")
        } else {
            UriRoute(
                "app://com.example_router_app/contact_list" +
                    "?contacts=${Gson().toJson(DummyService.contacts)}"
            )
        }

        router { clear() push route }
    }
}
