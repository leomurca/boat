package com.leomurca.boat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leomurca.boat.presentation.theme.DarkerOrange

@Composable
fun ScaffoldWithTopBar(title: String, onBackPressed: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.columnModifiers()) {
                Row(
                    modifier = Modifier.rowModifiers(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onBackPressed() },
                        content = {
                            Icon(
                                Icons.Outlined.ArrowBack,
                                contentDescription = "Back to previous screen",
                                tint = Color.White
                            )
                        },
                    )
                    Text(
                        text = title,
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
        }) {
        content()
    }
}

private fun Modifier.columnModifiers() = this
    .fillMaxWidth()
    .background(DarkerOrange)

private fun Modifier.rowModifiers() = this
    .fillMaxWidth()
    .height(55.dp)
    .padding(10.dp)
