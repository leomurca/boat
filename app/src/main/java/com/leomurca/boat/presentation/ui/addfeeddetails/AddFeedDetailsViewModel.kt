package com.leomurca.boat.presentation.ui.addfeeddetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.domain.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFeedDetailsViewModel @Inject constructor(
    app: Application,
    private val feedRepository: FeedRepository
) : AndroidViewModel(app) {

    private val _uiState = MutableStateFlow<UIState>(UIState.Editing)
    val uiState: StateFlow<UIState> get() = _uiState

    private val _feedData = MutableStateFlow(Feed(title = "", url = ""))
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

    fun onSaveFeed() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            when (val result = feedRepository.saveFeed(_feedData.value)) {
                is ResultOf.Success -> {
                    _uiState.value = UIState.Success
                }
                is ResultOf.Error -> {
                    _uiState.value = UIState.Error(result.error)
                }
            }
        }
    }

    sealed class UIState {
        object Editing : UIState()
        object Success : UIState()
        data class Error(val message: String = "Something went wrong :(") : UIState()
        object Loading : UIState()
    }
}