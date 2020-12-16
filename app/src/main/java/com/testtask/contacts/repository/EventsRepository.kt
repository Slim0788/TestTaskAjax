package com.testtask.contacts.repository

import com.testtask.contacts.api.dto.Result
import com.testtask.contacts.network.ResultWrapper

interface EventsRepository {

    suspend fun getData(): ResultWrapper<List<Result>>

    fun deleteCachedData()

    fun getByPhone(phone: String)
}