package com.leomurca.boat.domain.model

data class Feed(
    val title: String,
    val description: String? = null,
    val imagePath: String? = null,
    val language: String? = null,
    val items: List<Post>? = null
)