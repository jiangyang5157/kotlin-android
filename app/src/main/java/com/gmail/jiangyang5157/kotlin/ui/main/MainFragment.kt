package com.gmail.jiangyang5157.kotlin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.jiangyang5157.architecture.Injectable
import com.gmail.jiangyang5157.kotlin.R
import com.gmail.jiangyang5157.kotlin.repo.color.ColorRepository
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * Created by Yang Jiang on May 08, 2019
 */
class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var colorRepository: ColorRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_color.setOnClickListener {
            colorRepository.loadColor().observe(viewLifecycleOwner, Observer { data ->
                Log.d("####", "data=$data")
            })
        }
        btn_colors.setOnClickListener {
            colorRepository.loadColors().observe(viewLifecycleOwner, Observer { data ->
                Log.d("####", "data=$data")
            })
        }
    }
}