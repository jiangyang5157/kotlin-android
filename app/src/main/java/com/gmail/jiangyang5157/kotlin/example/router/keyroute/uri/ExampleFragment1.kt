package com.gmail.jiangyang5157.kotlin.example.router.keyroute.uri

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.popUntilPredicate
import com.gmail.jiangyang5157.android.router.core.push
import com.gmail.jiangyang5157.android.router.core.replaceTopWith
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.RouterFragmentGuest
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.synthetic.main.example_fragment_keyroute_uri.*

class ExampleFragment1 : Fragment(),
    RouterFragmentGuest<UriRoute> {

    private val route: UriRoute by route()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.example_fragment_keyroute_uri, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = "ExampleFragment1\n\n" +
            "# route=${route.data}\n\n" +
            "# param1=${route.parameter("param1")}\n"

        btn_1.setOnClickListener {
            router push UriRoute(
                "http://example.router.uri/page1?param1=Push by ExampleFragment1"
            )
        }

        btn_2.setOnClickListener {
            router push UriRoute(
                "http://example.router.uri/page2?param1=Push by ExampleFragment1"
            )
        }

        btn_3.setOnClickListener {
            router replaceTopWith UriRoute(
                "http://example.router.uri/page1?param1=replaceTopWith by ExampleFragment1"
            )
        }

        btn_4.setOnClickListener {
            router replaceTopWith UriRoute(
                "http://example.router.uri/page2?param1=replaceTopWith by ExampleFragment1"
            )
        }

        btn_5.setOnClickListener {
            router popUntilPredicate {
                it.route.key == Key("http://example.router.uri/page1")
            }
        }
    }
}
