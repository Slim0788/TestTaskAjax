package com.testtask.contacts.api

import com.testtask.contacts.api.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AjaxServiceApi {

    @GET("api/")
    suspend fun getPersons(
        @Query("results") results: Int
    ): Response

}