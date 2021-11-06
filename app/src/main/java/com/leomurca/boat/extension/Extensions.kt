package com.leomurca.boat.extension

import com.leomurca.boat.data.model.adapters.FeedAdapter
import com.leomurca.boat.data.mapper.FeedAdapterToFeedMapper
import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.domain.model.Feed
import retrofit2.Response

fun Response<FeedAdapter>.toResultOf(mapper: FeedAdapterToFeedMapper): ResultOf<Feed> {
    return try {
        if (this.isSuccessful) {
            ResultOf.Success(mapper.map(this.body()!!))
        } else ResultOf.Error(this.message())
    } catch (error: Exception) {
        ResultOf.ExceptionOf(error.message!!)
    }
}