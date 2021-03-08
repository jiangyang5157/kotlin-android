package com.gmail.jiangyang5157.android.example_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class EnterDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_enter_details, container, false)

        val etName = rootView.findViewById<EditText>(R.id.et_name)
        val etMobile = rootView.findViewById<EditText>(R.id.et_mobile)

        val btnVerifyDetails = rootView.findViewById<Button>(R.id.btn_verify)
        btnVerifyDetails.setOnClickListener {
            findNavController().navigate(
                EnterDetailsFragmentDirections.actionEnterDetailsFragmentToVerifyDetailsFragment(
                    name = etName.text.toString(),
                    mobile = etMobile.text.toString().toLongOrNull() ?: -1
                )
            )
        }

        return rootView
    }
}
