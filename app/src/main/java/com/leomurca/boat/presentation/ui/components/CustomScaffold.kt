package com.leomurca.boat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leomurca.boat.R
import com.leomurca.boat.presentation.theme.DarkerOrange
import com.leomurca.boat.presentation.theme.DarkerOrange

@Composable
fun CustomScaffold(
    scaffoldType: ScaffoldType,
    content: @Composable (PaddingValues) -> Unit
) {
    when (scaffoldType) {
        is ScaffoldType.MainScreens -> {
            Scaffold(
                bottomBar = { scaffoldType.bottomNavigation.invoke() },
                topBar = { TopBarResolver(topBarType = scaffoldType.topBarType) }
            ) { content(it) }
        }

        is ScaffoldType.SecondaryScreens -> {
            Scaffold(
                topBar = { TopBarResolver(topBarType = scaffoldType.topBarType) }
            ) { content(it) }
        }
    }
}

@Composable
private fun TopBarResolver(topBarType: TopBarType) {
    when (topBarType) {
        is TopBarType.LogoOnly -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(com.leomurca.boat.presentation.theme.TopBar)
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
        is TopBarType.Default -> {
            Column(modifier = Modifier.columnModifiers()) {
                Row(
                    modifier = Modifier.rowModifiers(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { topBarType.onBackPressed.invoke() },
                        content = {
                            Icon(
                                Icons.Outlined.ArrowBack,
                                contentDescription = "Back to previous screen",
                                tint = Color.White
                            )
                        },
                    )
                    Text(
                        text = topBarType.screenName,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )

                    IconButton(
                        onClick = {},
                        content = { Text("") },
                        enabled = false
                    )
                }
                Divider()
            }
        }
        is TopBarType.Editing -> {
            Column(modifier = Modifier.columnModifiers()) {
                Row(
                    modifier = Modifier.rowModifiers(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { topBarType.onBackPressed.invoke() },
                        content = {
                            Icon(
                                Icons.Outlined.ArrowBack,
                                contentDescription = "Back to previous screen",
                                tint = Color.White
                            )
                        },
                    )
                    Text(
                        text = topBarType.screenName,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )

                    Text(
                        text = "Save",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable { topBarType.onEditSaved.invoke() }
                    )
                }
                Divider()
            }
        }
    }
}

sealed class ScaffoldType {
    data class MainScreens(
        val bottomNavigation: @Composable () -> Unit,
        val topBarType: TopBarType
    ) : ScaffoldType()

    data class SecondaryScreens(
        val topBarType: TopBarType
    ) : ScaffoldType()
}

sealed class TopBarType {
    object LogoOnly : TopBarType()
    data class Default(val screenName: String, val onBackPressed: () -> Unit) : TopBarType()
    data class Editing(
        val screenName: String,
        val onBackPressed: () -> Unit,
        val onEditSaved: () -> Unit
    ) : TopBarType()
}

private fun Modifier.columnModifiers() = this
    .fillMaxWidth()
    .background(DarkerOrange)

private fun Modifier.rowModifiers() = this
    .fillMaxWidth()
    .height(55.dp)
    .padding(10.dp)
