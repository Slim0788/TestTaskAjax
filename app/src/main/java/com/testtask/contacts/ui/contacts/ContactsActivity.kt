package com.testtask.contacts.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.testtask.contacts.R
import com.testtask.contacts.databinding.ActivityContactsBinding
import com.testtask.contacts.dependency.Dependencies
import com.testtask.contacts.ui.details.DetailsActivity

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding

    private val eventsAdapter = ContactsAdapter(emptyList())

    private val viewModel: ContactsViewModel by viewModels() {
        ContactsViewModelFactory(
            Dependencies.eventsRepository,
            Dependencies.eventsMapper
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts)

        binding.apply {
            lifecycleOwner = this@ContactsActivity
            viewmodel = viewModel

            refreshLayout.apply {
                setOnRefreshListener {
                    updateData()
                }
                setColorSchemeResources(
                    android.R.color.holo_blue_dark,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
            }

            recyclerView.apply {
                setHasFixedSize(true)
                adapter = eventsAdapter.apply {
                    setOnItemClickListener {
                        redirectToDetails(it)
                    }
                }
            }
        }

        viewModel.apply {
            eventsList.observe(this@ContactsActivity, Observer { items ->
                eventsAdapter.items = items
                eventsAdapter.notifyDataSetChanged()
            })
            error.observe(this@ContactsActivity, Observer { errorMessage ->
                Toast.makeText(this@ContactsActivity, errorMessage, Toast.LENGTH_LONG).show()
            })
        }

        if (savedInstanceState == null) {
            getData()
        }
    }

    private fun updateData() {
        viewModel.updatePersons()
    }

    private fun getData() {
        viewModel.getPersons()
    }

    private fun redirectToDetails(position: String) {
        Intent(this@ContactsActivity, DetailsActivity::class.java).apply {
            putExtras(DetailsActivity.getBundle(position))
            startActivity(this)
        }
    }

}