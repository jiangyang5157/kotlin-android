package com.gmail.jiangyang5157.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvModules = findViewById(R.id.rv_modules) as RecyclerView
        rvModules.setHasFixedSize(true)
        rvModules.layoutManager = LinearLayoutManager(this)
        rvModules.adapter = ModuleAdapter()
    }

    class ModuleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class ModuleAdapter : RecyclerView.Adapter<ModuleViewHolder>() {

        private val mModules = arrayOf(
                "Flip View"
        )

        private val mModuleOnClickListener = View.OnClickListener { v ->
            when {
                v.tag == "Flip View" -> v.context.startActivity(Intent(v.context, FlipViewActivity::class.java))
            }
        }

        override fun getItemCount(): Int {
            return mModules.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ModuleViewHolder {
            return ModuleViewHolder(TextView(parent?.context))
        }

        override fun onBindViewHolder(holder: ModuleViewHolder?, position: Int) {
            with(holder?.itemView as TextView) {
                text = mModules[position]
                tag = mModules[position]
                setOnClickListener(mModuleOnClickListener)
            }
        }
    }
}
