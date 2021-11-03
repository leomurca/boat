package com.leomurca.boat.presentation.ui.addfeed

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leomurca.boat.presentation.ui.addfeeddetails.AddFeedDetailsScreen
import com.leomurca.boat.presentation.theme.BoatTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class AddFeedActivity : AppCompatActivity() {

    private val viewModel: AddFeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            BoatTheme {
                val navController = rememberNavController()

                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AddFeed.route,
                        modifier = Modifier.padding(innerPadding),
                        builder = {
                            composable(Screen.AddFeed.route) {
                                AddFeedScreen(
                                    navController = navController,
                                    viewModel = viewModel,
                                    onBackPressed = { finish() }
                                )
                            }
                            composable(Screen.AddFeedDetails.route) {
                                AddFeedDetailsScreen(
                                    navController = navController
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

sealed class Screen(val route: String, val screeName: String) {
    object AddFeed : Screen(Route.ADD_FEED.value, "Add Feed")
    object AddFeedDetails : Screen(Route.ADD_FEED_DETAILS.value, "Add Feed Details")
}

private enum class Route(val value: String) {
    ADD_FEED("add_feed"),
    ADD_FEED_DETAILS("add_feed_details")
}