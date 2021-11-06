package com.leomurca.boat.presentation.ui.addfeeddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leomurca.boat.R
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.presentation.ui.addfeed.Screen
import com.leomurca.boat.presentation.ui.components.*

@Composable
fun AddFeedDetailsScreen(navController: NavController, feed: Feed) {
    CustomScaffold(
        scaffoldType = ScaffoldType.SecondaryScreens(
            topBarType = TopBarType.Default(
                screenName = Screen.AddFeedDetails.screeName,
                onBackPressed = { navController.popBackStack() }
            )
        )
    ) {
        Column {
            RemoteImage(
                url = feed.imagePath,
                contentDescription = feed.title,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier.imageModifiers(),
                onError = {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_image_white),
                        contentDescription = "No image provided!",
                        modifier = Modifier.noImageModifiers(),
                    )
                }
            )
        }
    }
}

private fun Modifier.imageModifiers() = this
    .width(100.dp)
    .height(100.dp)
    .background(Color.LightGray)
    .fillMaxWidth()

private fun Modifier.noImageModifiers() = this
    .width(60.dp)
    .height(60.dp)
