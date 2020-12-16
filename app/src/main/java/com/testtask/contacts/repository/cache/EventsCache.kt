package com.testtask.contacts.repository.cache

import com.testtask.contacts.api.dto.Result

interface EventsCache {

    fun isCached(): Boolean

    fun getResponse(): List<Result>

    fun insertResponse(response: List<Result>)

    fun clearCache()
}