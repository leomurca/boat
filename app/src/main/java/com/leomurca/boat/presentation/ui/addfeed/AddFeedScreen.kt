package com.leomurca.boat.presentation.ui.addfeed

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.presentation.ui.components.*

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
                    FeedCard(
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
