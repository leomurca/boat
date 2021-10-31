package com.leomurca.boat.presentation.ui.addfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leomurca.boat.R
import com.leomurca.boat.data.model.Feed

@ExperimentalMaterialApi
@Composable
fun AddFeedScreen(navController: NavController, viewModel: AddFeedViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is AddFeedViewModel.UIState.Success -> {
            Feed(
                feed = state.feed,
                onClick = { navController.navigate(Screen.AddFeedDetails.route) }
            )
        }
        is AddFeedViewModel.UIState.Empty -> {
            Text("No feeds found!")
        }
        is AddFeedViewModel.UIState.Loading -> {
            // Do nothing
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun Feed(feed: Feed, onClick: () -> Unit) {
    Card(elevation = 5.dp, modifier = Modifier.cardModifiers(), onClick = { onClick() }) {
        Box(modifier = Modifier.boxModifiers()) {
            Row {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.nat),
                    contentDescription = feed.title,
                    modifier = Modifier.imageModifiers(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = feed.title,
                        fontWeight = FontWeight.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.textModifiers()
                    )
                    Text(
                        text = feed.description,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.textModifiers()
                    )
                    Text(
                        text = feed.language ?: "",
                        modifier = Modifier.textModifiers()
                    )
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
    .background(Color.Black)

private fun Modifier.textModifiers() = this.padding(start = 10.dp)
