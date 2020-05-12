package com.gmail.jiangyang5157.kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.example.router.ui.RouterActivity0
import com.gmail.jiangyang5157.kotlin.example.router.RouterApi

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_color).setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_ColorFragment)
        }

        view.findViewById<Button>(R.id.btn_router).setOnClickListener {
            RouterApi.setupRouterForRouterActivity()
            startActivity(Intent(requireContext(), RouterActivity0::class.java))
        }
    }
}
