package com.leomurca.boat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    val title: String,
    val description: String? = null,
    val imagePath: String? = null,
    val language: String? = null,
    val url: String,
    val items: List<Post>? = null
) : Parcelable