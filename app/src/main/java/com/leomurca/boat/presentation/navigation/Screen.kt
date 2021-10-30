package com.leomurca.boat.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.leomurca.boat.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen(Route.HOME.value, R.string.home, Icons.Filled.Home)
    object ReadLater : Screen(Route.READ_LATER.value, R.string.read_later, Icons.Filled.Favorite)
    object Settings : Screen(Route.SETTINGS.value, R.string.settings, Icons.Filled.Settings)
}
