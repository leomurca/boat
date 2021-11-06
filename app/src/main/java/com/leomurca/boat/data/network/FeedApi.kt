package com.leomurca.boat.data.network

import com.leomurca.boat.data.model.adapters.FeedAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface FeedApi {
    @GET
    suspend fun feed(@Url url: String): Response<FeedAdapter>
}