package com.leomurca.boat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leomurca.boat.R
import com.leomurca.boat.domain.model.Feed

@ExperimentalMaterialApi
@Composable
fun FeedCard(feed: Feed, onClick: () -> Unit) {
    Card(elevation = 5.dp, modifier = Modifier.cardModifiers(), onClick = { onClick() }) {
        Box(modifier = Modifier.boxModifiers()) {
            Row {
                RemoteImage(
                    url = feed.imagePath,
                    contentDescription = feed.title,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.imageModifiers(),
                    onError = {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_image_white),
                            contentDescription = "No image provided!",
                            modifier = Modifier.noImageModifiers()
                        )
                    }
                )
                Column {
                    Text(
                        text = feed.title,
                        fontWeight = FontWeight.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.textModifiers()
                    )
                    feed.description?.let {
                        Text(
                            text = it,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.textModifiers()
                        )
                    }
                    feed.language?.let {
                        Text(
                            text = it,
                            modifier = Modifier.textModifiers()
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.cardModifiers() = this
    .fillMaxWidth()
    .padding(horizontal = 20.dp, vertical = 10.dp)

private fun Modifier.boxModifiers() = this.padding(10.dp)

private fun Modifier.imageModifiers() = this
    .width(100.dp)
    .height(100.dp)
    .background(Color.LightGray)

private fun Modifier.noImageModifiers() = this
    .width(60.dp)
    .height(60.dp)

private fun Modifier.textModifiers() = this.padding(start = 10.dp)
