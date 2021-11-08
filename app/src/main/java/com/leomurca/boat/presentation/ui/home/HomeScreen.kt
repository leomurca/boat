package com.leomurca.boat.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leomurca.boat.presentation.ui.components.FeedCard

@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = { viewModel.refreshFeedList() },
    ) {
        when (val state = uiState.value) {
            is HomeViewModel.UIState.FeedsLoaded -> {
                LazyColumn(
                    modifier = Modifier.lazyColumnModifiers(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(state.feeds) { feed -> FeedCard(feed = feed, onClick = {}) }
                }
            }
            is HomeViewModel.UIState.Empty -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.columnModifiers()
                ) { Text(text = "There are no feeds added yet!") }
            }
            is HomeViewModel.UIState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.columnModifiers()
                ) { /* Nothing to Display*/ }
            }
        }
    }
}

@Composable
private fun Modifier.columnModifiers() = this
    .fillMaxWidth()
    .fillMaxHeight()
    .verticalScroll(rememberScrollState())

private fun Modifier.lazyColumnModifiers() = this
    .fillMaxWidth()
    .fillMaxHeight()
