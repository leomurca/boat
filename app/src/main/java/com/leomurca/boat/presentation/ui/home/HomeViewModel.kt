package com.leomurca.boat.presentation.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.domain.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val feedRepository: FeedRepository
) : AndroidViewModel(app) {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> get() = _uiState

    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    init {
        refreshFeedList()
    }

    fun refreshFeedList() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(4000L) // Just for demonstrations purposes
            when (val result = feedRepository.feeds()) {
                is ResultOf.Success -> {
                    _uiState.value = handleSuccess(result.data)
                }
                is ResultOf.Error -> {
                    _uiState.value = UIState.Error(message = result.error)
                }
            }
            _isRefreshing.value = false
        }
    }

    private fun handleSuccess(feeds: List<Feed>): UIState {
        return if (feeds.isEmpty()) {
            UIState.Empty
        } else {
            UIState.FeedsLoaded(feeds)
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Empty : UIState()
        data class FeedsLoaded(val feeds: List<Feed>) : UIState()
        data class Error(val message: String) : UIState()
    }
}