package com.leomurca.boat.presentation.ui.addfeed

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
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

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)
    val uiState: StateFlow<UIState> get() = _uiState

    private val _url = mutableStateOf("")
    val url: State<String> get() = _url

    fun onFetchFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            feedRepository.feedWithURL(_url.value)?.let { feed ->
                _uiState.value = UIState.Success(feed = feed)
            } ?: run {
                _uiState.value = UIState.Empty
            }
        }
    }

    fun onChangeURL(value: String) {
        _url.value = value
    }

    sealed class UIState {
        data class Success(val feed: Feed) : UIState()
        object Empty : UIState()
        object Loading : UIState()
        object Initial : UIState()
    }
}