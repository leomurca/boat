package com.leomurca.boat.presentation.ui.addfeeddetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.leomurca.boat.domain.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddFeedDetailsViewModel @Inject constructor(
    app: Application,
) : AndroidViewModel(app) {

    private val _uiState = MutableStateFlow(UIState.Editing)
    val uiState: StateFlow<UIState> get() = _uiState

    private val _feedData = MutableStateFlow(Feed(title = ""))
    val feedData: StateFlow<Feed> get() = _feedData

    fun init(feed: Feed) {
        _feedData.value = feed
    }

    fun onChangeTitle(title: String) {
        _feedData.value = _feedData.value.copy(title = title)
    }

    fun onChangeDescription(description: String) {
        _feedData.value = _feedData.value.copy(description = description)
    }

    sealed class UIState {
        object Editing : UIState()
        object Error : UIState()
        object Loading : UIState()
    }
}