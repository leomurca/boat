package com.leomurca.boat.presentation.ui.addfeeddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leomurca.boat.R
import com.leomurca.boat.presentation.ui.addfeed.Screen
import com.leomurca.boat.presentation.ui.components.*

@Composable
fun AddFeedDetailsScreen(
    navController: NavController,
    viewModel: AddFeedDetailsViewModel,
    onInitViewModel: () -> Unit
) {
    onInitViewModel.invoke()
    val uiState = viewModel.uiState.collectAsState()
    val feedData = viewModel.feedData.collectAsState()

    CustomScaffold(
        scaffoldType = ScaffoldType.SecondaryScreens(
            topBarType = TopBarType.Editing(
                screenName = Screen.AddFeedDetails.screeName,
                onBackPressed = { navController.popBackStack() },
                onEditSaved = {}
            )
        )
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp)
            ) {
                RemoteImage(
                    url = feedData.value.imagePath,
                    contentDescription = feedData.value.title,
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

            TextField(
                value = feedData.value.title,
                onValueChange = { viewModel.onChangeTitle(it) },
                label = { Text("Title") },
                modifier = Modifier.textFieldModifiers()
            )

            TextField(
                value = feedData.value.description ?: "",
                onValueChange = { viewModel.onChangeDescription(it) },
                label = { Text("Description") },
                placeholder = { Text(text = "Enter a description for your feed...") },
                modifier = Modifier.textFieldModifiers()
            )

            when (uiState.value) {
                is AddFeedDetailsViewModel.UIState.Editing -> {
                    // Do Nothing
                }
                is AddFeedDetailsViewModel.UIState.Loading -> {
                    Text(text = "Loading...")
                }
                is AddFeedDetailsViewModel.UIState.Error -> {
                    // Show Error message
                }
            }
        }
    }
}

private fun Modifier.imageModifiers() = this
    .width(100.dp)
    .height(100.dp)
    .background(Color.LightGray)

private fun Modifier.noImageModifiers() = this
    .width(60.dp)
    .height(60.dp)

private fun Modifier.textFieldModifiers() = this
    .fillMaxWidth()
    .padding(horizontal = 20.dp, vertical = 10.dp)
