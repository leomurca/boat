package com.leomurca.boat.data.network

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val error: String) : NetworkResult<Nothing>()
    data class NetworkException(val error: String) : NetworkResult<Nothing>()
}