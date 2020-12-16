package com.testtask.contacts.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.testtask.contacts.R
import com.testtask.contacts.databinding.ActivityDetailsBinding
import com.testtask.contacts.dependency.Dependencies

private const val ARG_POSITION = ""

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: DetailsViewModel by viewModels() {
        DetailsViewModelFactory(
            Dependencies.eventsRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.apply {
            lifecycleOwner = this@DetailsActivity
            viewmodel = viewModel
        }

        val position = intent.getStringExtra(ARG_POSITION)
        binding.phoneEdit.setText(position.toString())
        viewModel.getPerson(position!!)

    }

    companion object {
        fun getBundle(position: String): Bundle {
            val bundle = Bundle()
            bundle.putString(ARG_POSITION, position)
            return bundle
        }
    }
}