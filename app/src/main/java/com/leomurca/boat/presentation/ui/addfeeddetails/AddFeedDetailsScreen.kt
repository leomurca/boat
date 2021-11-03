package com.leomurca.boat.presentation.ui.addfeeddetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.leomurca.boat.presentation.ui.addfeed.Screen
import com.leomurca.boat.presentation.ui.components.CustomScaffold
import com.leomurca.boat.presentation.ui.components.ScaffoldType
import com.leomurca.boat.presentation.ui.components.TopBarType

@Composable
fun AddFeedDetailsScreen(navController: NavController) {
    CustomScaffold(
        scaffoldType = ScaffoldType.SecondaryScreens(
            topBarType = TopBarType.Default(
                screenName = Screen.AddFeedDetails.screeName,
                onBackPressed = { navController.popBackStack() }
            )
        )
    ) {
        Text(text = "Add Feed Details Screen")
    }
}