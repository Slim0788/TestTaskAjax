package com.testtask.contacts.api.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "gender")
    val gender: String,
    @Json(name = "name")
    @Embedded
    val name: Name,
    @Json(name = "email")
    val email: String,

    @PrimaryKey
    @Json(name = "phone")
    val phone: String,
    @Json(name = "picture")
    @Embedded
    val picture: Picture
)