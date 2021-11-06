package com.leomurca.boat.presentation.ui.addfeed

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.leomurca.boat.data.adapter.Feed
import com.leomurca.boat.data.network.NetworkResult
import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.extension.toNetworkResult
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

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)
    val uiState: StateFlow<UIState> get() = _uiState

    private val _url = mutableStateOf("")
    val url: State<String> get() = _url

    fun onFetchFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UIState.Loading
            when (val result = feedRepository.feedWithURL(_url.value).toNetworkResult()) {
                is NetworkResult.Success -> {
                    _uiState.value = UIState.FeedFound(feed = result.data)
                }
                else -> {
                    _uiState.value = UIState.FeedNotFound
                }
            }
        }
    }

    fun onChangeURL(value: String) {
        _url.value = value
    }

    sealed class UIState {
        data class FeedFound(val feed: Feed) : UIState()
        object FeedNotFound : UIState()
        object Loading : UIState()
        object Initial : UIState()
    }
}