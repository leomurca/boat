package com.leomurca.boat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val title: String? = null,
    val description: String? = null,
    val link: String? = null,
    val pubDate: String? = null,
) : Parcelable