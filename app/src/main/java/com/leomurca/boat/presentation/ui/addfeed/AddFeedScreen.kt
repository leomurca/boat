package com.leomurca.boat.presentation.ui.addfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leomurca.boat.R
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.presentation.ui.components.CustomScaffold
import com.leomurca.boat.presentation.ui.components.RemoteImage
import com.leomurca.boat.presentation.ui.components.ScaffoldType
import com.leomurca.boat.presentation.ui.components.TopBarType

@ExperimentalMaterialApi
@Composable
fun AddFeedScreen(
    navController: NavController,
    viewModel: AddFeedViewModel,
    onBackPressed: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val urlState = viewModel.url

    CustomScaffold(
        scaffoldType = ScaffoldType.SecondaryScreens(
            topBarType = TopBarType.Default(
                screenName = Screen.AddFeed.screeName,
                onBackPressed = onBackPressed
            )
        )
    ) {
        Column {
            TextField(
                value = urlState.value,
                onValueChange = { viewModel.onChangeURL(it) },
                label = { Text("Feed URL") },
                placeholder = { Text(text = "Enter the feed url you want to subscribe to...") },
                modifier = Modifier.textFieldModifiers()
            )

            Button(
                onClick = { viewModel.onFetchFeed() },
                content = { Text("Add ") },
                modifier = Modifier.buttonModifiers()
            )

            when (val state = uiState.value) {
                is AddFeedViewModel.UIState.FeedFound -> {
                    Feed(
                        feed = state.feed,
                        onClick = {
                            navigateToAddFeedDetails(
                                navController,
                                state.feed.copy(url = urlState.value)
                            )
                        }
                    )
                }
                is AddFeedViewModel.UIState.FeedNotFound -> {
                    Text(
                        text = "No feeds were found!",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.textFieldModifiers()
                    )
                }
                is AddFeedViewModel.UIState.Loading -> {
                    Text(
                        text = "Loading...",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.textFieldModifiers()
                    )
                }
                is AddFeedViewModel.UIState.Initial -> {
                    // Do nothing
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Feed(feed: Feed, onClick: () -> Unit) {
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

private fun navigateToAddFeedDetails(navController: NavController, feed: Feed) {
    // I hope that the jetpack compose team solve this one day...
    navController.currentBackStackEntry?.savedStateHandle?.set("feed", feed)
    navController.navigate(Screen.AddFeedDetails.route)
}

private fun Modifier.textFieldModifiers() = this
    .fillMaxWidth()
    .padding(20.dp)

private fun Modifier.buttonModifiers() = this
    .fillMaxWidth()
    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)

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
