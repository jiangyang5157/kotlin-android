package com.gmail.jiangyang5157.example_router_app.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.UriRoute

class LoginViewModel : ViewModel() {

    private val router = Dependency.router

    var email: String? = null
    var password: String? = null
    var alertText = MutableLiveData<String>()

    fun onLoginClicked() {
        val email = this.email
        val password = this.password
        if (email == null || password == null) {
            alertText.value = "Please enter email and password."
        } else {
            router push UriRoute("app://com.example_router_app/login_processing" +
                "?email=$email" +
                "&password=$password")
        }
    }
}
