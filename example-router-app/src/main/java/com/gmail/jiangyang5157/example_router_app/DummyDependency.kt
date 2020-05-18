package com.gmail.jiangyang5157.example_router_app

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.example_router_app.fragment.*
import com.gmail.jiangyang5157.example_router_app.transition.*
import com.gmail.jiangyang5157.kotlin_kit.model.Key

object Dependency {

    val router: FragmentRouter<UriRoute> =
        FragmentRouter {
            transition {
                register(DefaultTransition())
                register(LoginToLoginProcessingTransition())
                register(LoginProcessingToNextTransition())
                register(LoginFailedToLoginTransition())
                register(ContactListToChatTransition())
            }
            fragment {
                map(Key("app://com.example_router_app/login")) { LoginFragment::class }
                map(Key("app://com.example_router_app/login_processing")) { LoginProcessingFragment::class }
                map(Key("app://com.example_router_app/login_failed")) { LoginFailedFragment::class }
                map(Key("app://com.example_router_app/contact_list")) { ContactListFragment::class }
                map(Key("app://com.example_router_app/chat")) { ChatFragment::class }
            }
        }
}
