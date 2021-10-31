package com.leomurca.boat.presentation.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.leomurca.boat.presentation.ui.addfeed.AddFeedActivity

@ExperimentalMaterialApi
@Composable
fun HomeScreen(context: Context) {
    Button(
        onClick = { context.startActivity(Intent(context, AddFeedActivity::class.java)) },
        content = { Text("Add Feed") }
    )
}
