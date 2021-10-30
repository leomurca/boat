package com.leomurca.boat.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leomurca.boat.presentation.ui.home.HomeScreen
import com.leomurca.boat.presentation.ui.readlater.ReadLaterScreen
import com.leomurca.boat.presentation.ui.settings.SettingsScreen

@Composable
fun NavigationHost(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) { HomeScreen(navHostController) }
        composable(Screen.ReadLater.route) { ReadLaterScreen(navHostController) }
        composable(Screen.Settings.route) { SettingsScreen(navHostController) }
    }
}