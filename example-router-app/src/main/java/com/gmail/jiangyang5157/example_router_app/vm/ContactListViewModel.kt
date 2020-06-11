package com.gmail.jiangyang5157.example_router_app.vm

import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.example_router_app.Contact
import com.gmail.jiangyang5157.example_router_app.Dependency
import com.gmail.jiangyang5157.example_router_app.UriRoute
import com.google.gson.Gson

class ContactListViewModel : ViewModel() {

    private val router = Dependency.router

    fun onContactClicked(contact: Contact) {
        router push UriRoute(
            "app://com.example_router_app/chat" +
                "?contact=${Gson().toJson(contact)}" +
                "&lastSeenTime=${System.currentTimeMillis()}"
        )
    }
}
