package com.testtask.contacts.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.testtask.contacts.R
import com.testtask.contacts.data.Person
import com.testtask.contacts.databinding.ActivityContactsItemBinding

class ContactsAdapter(var items: List<Person>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var onItemClickListener: ((phone: String) -> Unit)? = null

    fun setOnItemClickListener(listener: (phone: String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.activity_contacts_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ActivityContactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.model = items[adapterPosition]

            itemView.setOnClickListener {
                onItemClickListener?.invoke(items[adapterPosition].phoneNumber)
            }
        }
    }
}