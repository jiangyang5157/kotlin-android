package com.gmail.jiangyang5157.example_router_app.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.android.router.core.clear
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.replaceTopWith
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.DummyService
import com.gmail.jiangyang5157.example_router_app.UriRoute
import com.google.gson.Gson

class LoginProcessingViewModel : ViewModel() {

    private val router = Dependency.router

    private val handler = Handler(Looper.getMainLooper())

    private var email: String? = null
    private var password: String? = null

    fun process(email: String?, password: String?) {
        this.email = email
        this.password = password
        handler.postDelayed(loggedIn, 2500)
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(loggedIn)
    }

    private val loggedIn = Runnable {
        if (password == "asd") {
            onLoginSuccess()
        } else {
            onLoginFailed()
        }
    }

    private fun onLoginSuccess() {
        DummyService.isLoggedIn = true
        router {
            clear() push UriRoute("app://com.example_router_app/contact_list" +
                "?contacts=${Gson().toJson(DummyService.contacts)}")
        }
    }

    private fun onLoginFailed() {
        router replaceTopWith UriRoute("app://com.example_router_app/login_failed")
    }
}
