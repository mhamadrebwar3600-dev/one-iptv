package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.AccessPreferences
import com.example.data.IptvRepository
import com.example.data.model.ChannelItem
import com.example.data.model.IptvTab
import com.example.data.model.MovieItem
import com.example.data.model.PlayableMedia
import com.example.data.model.SportsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class IptvUiState(
  val isUnlocked: Boolean = false,
  val selectedTab: IptvTab = IptvTab.LIVE_TV,
  val selectedCategory: String = "All",
  val searchQuery: String = "",
  val isSearchActive: Boolean = false,
  val isSettingsOpen: Boolean = false,
  val accessCodeInput: String = "",
  val accessCodeError: String? = null,
  val accessCodeSuccess: Boolean = false,
  val activePlayingMedia: PlayableMedia? = null,
  val isPlaying: Boolean = true,
  val streamQualitySelected: String = "4K Ultra HD",
  val hardwareAccelerationEnabled: Boolean = true,
  val favoriteIds: Set<String> = emptySet()
)

class IptvViewModel(application: Application) : AndroidViewModel(application) {
  private val accessPrefs = AccessPreferences(application)

  private val _uiState = MutableStateFlow(
    IptvUiState(isUnlocked = accessPrefs.isUnlocked())
  )
  val uiState: StateFlow<IptvUiState> = _uiState.asStateFlow()

  fun onCodeInputChanged(newCode: String) {
    _uiState.update {
      it.copy(
        accessCodeInput = newCode,
        accessCodeError = null,
        accessCodeSuccess = false
      )
    }
  }

  fun submitAccessCode() {
    val input = _uiState.value.accessCodeInput
    val success = accessPrefs.verifyAndUnlock(input)
    if (success) {
      _uiState.update {
        it.copy(
          isUnlocked = true,
          accessCodeError = null,
          accessCodeSuccess = true,
          accessCodeInput = ""
        )
      }
    } else {
      _uiState.update {
        it.copy(
          accessCodeError = "Invalid Access Code",
          accessCodeSuccess = false
        )
      }
    }
  }

  fun lockContent() {
    accessPrefs.lock()
    _uiState.update {
      it.copy(
        isUnlocked = false,
        accessCodeSuccess = false,
        activePlayingMedia = null
      )
    }
  }

  fun openSettings(open: Boolean) {
    _uiState.update {
      it.copy(
        isSettingsOpen = open,
        accessCodeError = null,
        accessCodeSuccess = false,
        accessCodeInput = ""
      )
    }
  }

  fun selectTab(tab: IptvTab) {
    _uiState.update {
      it.copy(
        selectedTab = tab,
        selectedCategory = "All"
      )
    }
  }

  fun selectCategory(category: String) {
    _uiState.update {
      it.copy(selectedCategory = category)
    }
  }

  fun toggleSearch(active: Boolean) {
    _uiState.update {
      it.copy(
        isSearchActive = active,
        searchQuery = if (!active) "" else it.searchQuery
      )
    }
  }

  fun onSearchQueryChanged(query: String) {
    _uiState.update {
      it.copy(searchQuery = query)
    }
  }

  fun playMedia(media: PlayableMedia?) {
    _uiState.update {
      it.copy(
        activePlayingMedia = media,
        isPlaying = true
      )
    }
  }

  fun togglePlayback() {
    _uiState.update {
      it.copy(isPlaying = !it.isPlaying)
    }
  }

  fun closePlayer() {
    _uiState.update {
      it.copy(activePlayingMedia = null)
    }
  }

  fun setStreamQuality(quality: String) {
    _uiState.update {
      it.copy(streamQualitySelected = quality)
    }
  }

  fun toggleHardwareAcceleration() {
    _uiState.update {
      it.copy(hardwareAccelerationEnabled = !it.hardwareAccelerationEnabled)
    }
  }

  fun toggleFavorite(id: String) {
    _uiState.update { current ->
      val newFavorites = if (current.favoriteIds.contains(id)) {
        current.favoriteIds - id
      } else {
        current.favoriteIds + id
      }
      current.copy(favoriteIds = newFavorites)
    }
  }
}
