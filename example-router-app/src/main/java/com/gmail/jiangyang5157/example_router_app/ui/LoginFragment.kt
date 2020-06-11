package com.gmail.jiangyang5157.example_router_app.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gmail.jiangyang5157.example_router_app.R
import com.gmail.jiangyang5157.example_router_app.vm.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        viewModel.alertText.observe({ lifecycle }) { alertText ->
            alertText?.let { Toast.makeText(context, alertText, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener { viewModel.onLoginClicked() }
        til_email.editText?.setText(viewModel.email, TextView.BufferType.EDITABLE)
        til_password.editText?.setText(viewModel.password, TextView.BufferType.EDITABLE)
        til_email.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                viewModel.email = editable.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        til_password.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                viewModel.password = editable.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onStart() {
        super.onStart()
        activity?.title = "Login"
    }
}
