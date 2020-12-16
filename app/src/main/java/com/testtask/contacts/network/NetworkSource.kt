package com.testtask.contacts.network

interface NetworkSource {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T>

}