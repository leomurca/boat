package com.leomurca.boat.presentation.ui.addfeed

import android.app.Application
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.leomurca.boat.data.model.Feed
import com.leomurca.boat.data.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFeedViewModel @Inject constructor(
    app: Application,
    private val feedRepository: FeedRepository
) : AndroidViewModel(app) {

    private val _uiState = MutableStateFlow<UIState>(UIState.Empty)
    val uiState: StateFlow<UIState> get() = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val feed = feedRepository.feedWithURL("nat")
            _uiState.value = UIState.Success(feed = feed)
        }
    }

    sealed class UIState {
        data class Success(val feed: Feed) : UIState()
        object Empty : UIState()
        object Loading : UIState()
    }
}