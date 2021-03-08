package com.gmail.jiangyang5157.android.example_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class VerifyDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_verify_details, container, false)

        val args: VerifyDetailsFragmentArgs by navArgs()
        val name = args.name
        val mobile = args.mobile

        val tvName = rootView.findViewById<TextView>(R.id.tv_name)
        val tvMobile = rootView.findViewById<TextView>(R.id.tv_mobile)

        tvName.text = name
        tvMobile.text = mobile.toString()

        return rootView
    }
}
