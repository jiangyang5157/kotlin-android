package com.gmail.jiangyang5157.example_router_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.android.router.core.route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.setup.RouterFragment
import com.gmail.jiangyang5157.core.ext.fromJson
import com.gmail.jiangyang5157.example_router_app.*
import com.gmail.jiangyang5157.example_router_app.vm.ContactListViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : Fragment(), RouterFragment {

    override val router: FragmentRouter<*> = Dependency.router
    private val route: UriRoute by route()
    private val contacts by lazy {
        route.query("contacts")?.let {
            Gson().fromJson<List<Contact>>(it)
        } ?: emptyList()
    }

    private lateinit var viewModel: ContactListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[ContactListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_contact_list.layoutManager = LinearLayoutManager(context)
        rv_contact_list.adapter = Adapter()
    }

    override fun onResume() {
        super.onResume()
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.supportActionBar?.title = "Contacts"
        appCompatActivity.supportActionBar?.subtitle = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvPhone: TextView = view.findViewById(R.id.tv_phone)
        val tvEmail: TextView = view.findViewById(R.id.tv_email)
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun getItemCount(): Int = contacts.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_contact,
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val contact = contacts[position]
            holder.tvName.text = contact.name
            holder.tvPhone.text = "Phone: ${contact.phone}"
            holder.tvEmail.text = "Email: ${contact.email}"
            holder.itemView.setOnClickListener { viewModel.onContactClicked(contact) }
        }
    }
}
