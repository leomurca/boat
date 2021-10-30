package com.leomurca.boat.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leomurca.boat.R

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn {
        items(10) {
            Feed()
        }
    }
}

@Composable
private fun Feed() {
    Card(elevation = 5.dp, modifier = Modifier.cardModifiers()) {
        Box(modifier = Modifier.boxModifiers()) {
            Row {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.nat),
                    contentDescription = "Nat King Cole",
                    modifier = Modifier.imageModifiers(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Nat King Cole RSS Feed Titleeeeeeeee",
                        fontWeight = FontWeight.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.textModifiers()
                    )
                    Text(
                        text = "There was a boy. A very enchanted boy. They say he wandered very far.",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.textModifiers()
                    )
                    Text(
                        text = "en-us",
                        modifier = Modifier.textModifiers()
                    )
                }
            }
        }
    }
}

private fun Modifier.cardModifiers() = this
    .fillMaxWidth()
    .padding(horizontal = 20.dp, vertical = 10.dp)

private fun Modifier.boxModifiers() = this.padding(10.dp)

private fun Modifier.imageModifiers() = this
    .width(100.dp)
    .height(100.dp)
    .background(Color.Black)

private fun Modifier.textModifiers() = this.padding(start = 10.dp)
