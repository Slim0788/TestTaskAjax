package com.testtask.contacts.repository.cache

import com.testtask.contacts.api.dto.Result
import com.testtask.contacts.db.ResponseDao

private const val EXPIRATION_TIME = (10 * 60 * 1000).toLong() // 10 minutes

class EventsCacheImpl constructor(
    private val responseDao: ResponseDao,
    private val preferenceHelper: SharedPreferencesApi
) : EventsCache {

    override fun isCached(): Boolean {
        return responseDao.getAll().isNotEmpty() && !isExpired()
    }

    private fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferenceHelper.lastCacheTime
    }

    override fun getResponse(): List<Result> {
        return responseDao.getAll()
    }

    override fun insertResponse(response: List<Result>) {
        responseDao.deleteAll()
        responseDao.insertAll(response)

        preferenceHelper.lastCacheTime = System.currentTimeMillis()
    }

    override fun clearCache() {
        responseDao.deleteAll()
    }
}