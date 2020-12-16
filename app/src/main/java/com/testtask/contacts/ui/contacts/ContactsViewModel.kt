package com.testtask.contacts.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.contacts.data.Person
import com.testtask.contacts.network.ResultWrapper
import com.testtask.contacts.repository.EventsMapper
import com.testtask.contacts.repository.EventsRepository
import com.testtask.contacts.utils.SingleEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(
    private val eventsRepository: EventsRepository,
    private val eventsMapper: EventsMapper
) : ViewModel() {

    private val eventsListLiveData = MutableLiveData<List<Person>>()
    private val isProgressLiveData = MutableLiveData<Boolean>()
    private val errorMutableLiveData = SingleEventLiveData<String>()

    val eventsList: LiveData<List<Person>> = eventsListLiveData
    val isProgress: LiveData<Boolean> = isProgressLiveData
    val error: LiveData<String> = errorMutableLiveData

    fun getPersons() {
        getData()
    }

    fun updatePersons() {
        deleteAllDataFromDatabase()
        getData()
    }

    private fun getData() {
        isProgressLiveData.value = true
        viewModelScope.launch {
            val events = withContext(Dispatchers.IO) {
                eventsRepository.getData()
            }
            when (events) {
                is ResultWrapper.Success ->
                    eventsListLiveData.value = eventsMapper.map(events.value)
                is ResultWrapper.NetworkError -> errorMutableLiveData.value = events.toString()
                is ResultWrapper.GenericError -> errorMutableLiveData.value = events.toString()
            }
            isProgressLiveData.value = false
        }
    }

    private fun deleteAllDataFromDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                eventsRepository.deleteCachedData()
            }
        }
    }

}