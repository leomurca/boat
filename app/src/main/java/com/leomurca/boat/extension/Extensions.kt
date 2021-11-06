package com.leomurca.boat.extension

import com.leomurca.boat.data.adapter.Feed
import com.leomurca.boat.data.network.NetworkResult
import retrofit2.Response

fun Response<Feed>.toNetworkResult(): NetworkResult<Feed> {
    return try {
        if (this.isSuccessful) {
            NetworkResult.Success(this.body()!!)
        } else NetworkResult.Error(this.message())
    } catch (error: Exception) {
        NetworkResult.NetworkException(error.message!!)
    }
}