package com.leomurca.boat.data.model

sealed class ResultOf<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultOf<T>()
    data class Error(val error: String) : ResultOf<Nothing>()
    data class ExceptionOf(val error: String) : ResultOf<Nothing>()
}