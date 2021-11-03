package com.leomurca.boat.presentation.ui.addfeeddetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.leomurca.boat.presentation.ui.addfeed.Screen
import com.leomurca.boat.presentation.ui.components.ScaffoldWithTopBar

@Composable
fun AddFeedDetailsScreen(navController: NavController) {
    ScaffoldWithTopBar(
        title = Screen.AddFeedDetails.screeName,
        onBackPressed = { navController.popBackStack() }) {
        Text(text = "Add Feed Details Screen")
    }
}