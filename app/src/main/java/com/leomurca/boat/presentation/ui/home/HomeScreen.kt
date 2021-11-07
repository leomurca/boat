package com.leomurca.boat.presentation.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leomurca.boat.presentation.ui.addfeed.AddFeedActivity
import com.leomurca.boat.presentation.ui.addfeed.Feed

@ExperimentalMaterialApi
@Composable
fun HomeScreen(context: Context, viewModel: HomeViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is HomeViewModel.UIState.FeedsLoaded -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(state.feeds) { feed ->
                    Feed(feed = feed, onClick = {})
                }
            }
        }
        is HomeViewModel.UIState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }

}
