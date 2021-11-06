package com.leomurca.boat.presentation.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

@Composable
fun RemoteImage(
    url: String?,
    contentDescription: String,
    alignment: Alignment,
    contentScale: ContentScale = ContentScale.None,
    modifier: Modifier,
    onError: @Composable (() -> Unit)? = null,
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        when (val state = loadImage(url = url)) {
            is ImageState.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            is ImageState.Loaded -> {
                Image(
                    bitmap = state.image.asImageBitmap(),
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    alignment = alignment,
                    modifier = Modifier.fillMaxSize()
                )
            }
            is ImageState.LoadError -> {
                onError?.let { onError() } ?: run {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "Could not load image")
                    }
                }
            }
        }
    }
}

@Composable
private fun loadImage(url: String?): ImageState {
    var state by remember(url) { mutableStateOf<ImageState>(ImageState.Loading) }

    if (url.isNullOrEmpty()) {
        state = ImageState.LoadError
    } else {
        Picasso.get().load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let { state = ImageState.Loaded(bitmap) } ?: run {
                    onBitmapFailed(Exception("Incorrect url"), null)
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                e?.printStackTrace()
                state = ImageState.LoadError
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // Do Nothing
            }
        })
    }

    return state
}

private sealed class ImageState {
    data class Loaded(val image: Bitmap) : ImageState()
    object Loading : ImageState()
    object LoadError : ImageState()
}
