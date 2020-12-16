package com.testtask.contacts.repository

import com.testtask.contacts.api.AjaxServiceApi
import com.testtask.contacts.api.dto.Result
import com.testtask.contacts.network.NetworkSource
import com.testtask.contacts.network.ResultWrapper
import com.testtask.contacts.repository.cache.EventsCache

private const val NUMBER_OF_PERSONS = 20

class EventsRepositoryImpl(
    private val ajaxServiceApi: AjaxServiceApi,
    private val networkSource: NetworkSource,
    private val cache: EventsCache
) : EventsRepository {

    override suspend fun getData(): ResultWrapper<List<Result>> {
        if (cache.isCached()) {
            return ResultWrapper.Success(cache.getResponse())
        }
        val result = networkSource.safeApiCall {
            ajaxServiceApi.getPersons(NUMBER_OF_PERSONS).results
        }

        if (result is ResultWrapper.Success) {
            cache.insertResponse(result.value)
        }

        return result
    }



    override fun deleteCachedData() {
        cache.clearCache()
    }

    override fun getByPhone(phone: String) {
        TODO("Not yet implemented")
    }

}