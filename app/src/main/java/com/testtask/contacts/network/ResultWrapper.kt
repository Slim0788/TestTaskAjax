package com.testtask.contacts.network

sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(
        val code: Int? = null,
        val error: Error? = null
    ) : ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$value]"
            is GenericError -> "GenericError[code: $code, error: $error]"
            is NetworkError -> "HttpError[IOException]"
        }
    }
}