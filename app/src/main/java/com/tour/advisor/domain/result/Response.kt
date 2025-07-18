package com.tour.advisor.domain.result

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val errorMessage: String?, val throwable: Throwable? = null) : Response<Nothing>()
}