package com.leomurca.boat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leomurca.boat.presentation.ui.home.HomeScreen
import com.leomurca.boat.presentation.ui.readlater.ReadLaterScreen
import com.leomurca.boat.presentation.ui.settings.SettingsScreen
import com.leomurca.boat.presentation.theme.BoatTheme
import com.leomurca.boat.ui.theme.TopBar

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoatTheme {
                val navController = rememberNavController()
                val screens = listOf(Screen.Home, Screen.ReadLater, Screen.Settings)

                Scaffold(
                    bottomBar = { BottomNavigationBar(screens, navController) },
                    topBar = { TopBar() }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding),
                        builder = {
                            composable(Screen.Home.route) { HomeScreen(this@MainActivity) }
                            composable(Screen.ReadLater.route) { ReadLaterScreen(navController) }
                            composable(Screen.Settings.route) { SettingsScreen(navController) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBar)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_horizontal_logo),
            contentDescription = "Boat",
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(10.dp)
        )
        Divider()
    }
}

@Composable
private fun BottomNavigationBar(screens: List<Screen>, navController: NavController) {
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

private sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : Screen(Route.HOME.value, R.string.home, Icons.Filled.Home)
    object ReadLater : Screen(Route.READ_LATER.value, R.string.read_later, Icons.Filled.Favorite)
    object Settings : Screen(Route.SETTINGS.value, R.string.settings, Icons.Filled.Settings)
}

private enum class Route(val value: String) {
    HOME("home"),
    READ_LATER("read_later"),
    SETTINGS("settings")
}
