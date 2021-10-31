package com.leomurca.boat.data.model

data class Feed(
    val title: String,
    val description: String,
    val imagePath: String? = null,
    val language: String? = null,
    val items: List<Post>? = null
)