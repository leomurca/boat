package com.leomurca.boat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leomurca.boat.presentation.ui.home.HomeScreen
import com.leomurca.boat.presentation.ui.readlater.ReadLaterScreen
import com.leomurca.boat.presentation.ui.settings.SettingsScreen
import com.leomurca.boat.ui.theme.BoatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoatTheme {
                val navController = rememberNavController()
                val screens = listOf(Screen.Home, Screen.ReadLater, Screen.Settings)

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) { HomeScreen(navController) }
                        composable(Screen.ReadLater.route) { ReadLaterScreen(navController) }
                        composable(Screen.Settings.route) { SettingsScreen(navController) }
                    }
                }
            }
        }
    }

    sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
        object Home : Screen("home", R.string.home, Icons.Filled.Home)
        object ReadLater : Screen("readlater", R.string.read_later, Icons.Filled.Favorite)
        object Settings : Screen("settings", R.string.settings, Icons.Filled.Settings)
    }
}