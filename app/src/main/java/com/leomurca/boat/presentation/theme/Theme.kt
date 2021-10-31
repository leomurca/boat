package com.leomurca.boat.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.leomurca.boat.ui.theme.*

private val DarkColorPalette = darkColors(
    primary = Orange,
    primaryVariant = DarkerOrange,
    secondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = DarkerOrange,
    secondary = Color.White,
    secondaryVariant = DarkerOrange,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BoatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}