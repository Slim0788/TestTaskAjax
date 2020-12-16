package com.testtask.contacts.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testtask.contacts.repository.EventsMapper
import com.testtask.contacts.repository.EventsRepository

class DetailsViewModelFactory(
    private val eventsRepository: EventsRepository,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == DetailsViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            DetailsViewModel(
                eventsRepository
            ) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}