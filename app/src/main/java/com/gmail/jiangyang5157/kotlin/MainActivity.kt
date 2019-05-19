package com.gmail.jiangyang5157.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvModules = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_modules)
        rvModules.setHasFixedSize(true)
        rvModules.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rvModules.adapter = ModuleAdapter()
    }

    class ModuleViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

    class ModuleAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<ModuleViewHolder>() {

        private val mModules = arrayOf(
                "todo"
        )

        private val mModuleOnClickListener = View.OnClickListener { v ->
                // to check view.tag
        }

        override fun getItemCount(): Int {
            return mModules.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
            return ModuleViewHolder(TextView(parent.context))
        }

        override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
            with(holder.itemView as TextView) {
                text = mModules[position]
                tag = mModules[position]
                setOnClickListener(mModuleOnClickListener)
            }
        }
    }
}
