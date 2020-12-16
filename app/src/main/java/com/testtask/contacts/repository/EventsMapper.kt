package com.testtask.contacts.repository

import com.testtask.contacts.api.dto.Result
import com.testtask.contacts.data.Person

class EventsMapper {
    fun map(responseList: List<Result>): List<Person> {
        return responseList.map(::map)
    }

    private fun map(result: Result) =
        Person(
            id = "",
            name = result.name.first,
            surname = result.name.last,
            email = result.email,
            avatarUrl = result.picture.large,
            phoneNumber = result.phone,
            gender = result.gender
        )
}