package com.leomurca.boat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.leomurca.boat.presentation.navigation.BottomNavigationBar
import com.leomurca.boat.presentation.navigation.NavigationHost
import com.leomurca.boat.presentation.navigation.Screen
import com.leomurca.boat.ui.theme.BoatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoatTheme {
                val navController = rememberNavController()
                val screens = listOf(Screen.Home, Screen.ReadLater, Screen.Settings)

                Scaffold(
                    bottomBar = { BottomNavigationBar(screens, navController) }
                ) { innerPadding ->
                    NavigationHost(navHostController = navController, innerPadding = innerPadding)
                }
            }
        }
    }
}