package com.testtask.contacts.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testtask.contacts.repository.EventsMapper
import com.testtask.contacts.repository.EventsRepository

class ContactsViewModelFactory(
    private val eventsRepository: EventsRepository,
    private val eventsMapper: EventsMapper
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == ContactsViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            ContactsViewModel(
                eventsRepository,
                eventsMapper
            ) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}