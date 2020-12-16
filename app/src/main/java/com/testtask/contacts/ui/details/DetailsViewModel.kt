package com.testtask.contacts.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testtask.contacts.data.Person
import com.testtask.contacts.db.ResponseDao
import com.testtask.contacts.dependency.Dependencies
import com.testtask.contacts.repository.EventsMapper
import com.testtask.contacts.repository.EventsRepository
import com.testtask.contacts.utils.SingleEventLiveData

class DetailsViewModel(
    private val eventsRepository: EventsRepository,
) : ViewModel() {

   val nameLiveData = MutableLiveData<String>()
   val surnameLiveData = MutableLiveData<String>()

    fun getPerson(id: String) {
        val db = Dependencies.db.responseDao()
        val person = db.getByPhone(id)

        nameLiveData.value = person.name.first
        surnameLiveData.value = person.name.last
    }

    fun updatePerson() {

    }


}