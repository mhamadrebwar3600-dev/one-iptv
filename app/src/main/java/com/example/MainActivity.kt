package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.IptvRepository
import com.example.data.model.IptvTab
import com.example.ui.IptvUiState
import com.example.ui.IptvViewModel
import com.example.ui.components.BottomNavBar
import com.example.ui.components.IptvHeader
import com.example.ui.components.LiveTvView
import com.example.ui.components.LockedStateView
import com.example.ui.components.MoviesView
import com.example.ui.components.SearchOverlay
import com.example.ui.components.SettingsDialog
import com.example.ui.components.SportsView
import com.example.ui.components.StreamPlayerOverlay
import com.example.ui.theme.BackgroundDark
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  private val viewModel: IptvViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        OneIptvApp(
          uiState = uiState,
          viewModel = viewModel
        )
      }
    }
  }
}

@Composable
fun OneIptvApp(
  uiState: IptvUiState,
  viewModel: IptvViewModel,
  modifier: Modifier = Modifier
) {
  Scaffold(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark),
    topBar = {
      IptvHeader(
        isSearchActive = uiState.isSearchActive,
        searchQuery = uiState.searchQuery,
        isUnlocked = uiState.isUnlocked,
        onOpenSettings = { viewModel.openSettings(true) },
        onToggleSearch = { active -> viewModel.toggleSearch(active) },
        onSearchQueryChanged = { query -> viewModel.onSearchQueryChanged(query) }
      )
    },
    bottomBar = {
      // Bottom navigation is always available or prominent so user can switch tabs
      BottomNavBar(
        selectedTab = uiState.selectedTab,
        onTabSelected = { tab -> viewModel.selectTab(tab) }
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BackgroundDark)
    ) {
      // Content Area: Either Locked State or Unlocked Channel Streams/Banners
      if (!uiState.isUnlocked) {
        // Locked / Placeholder state when not authorized (Default State)
        LockedStateView(
          onOpenSettings = { viewModel.openSettings(true) }
        )
      } else {
        // Unlocked State: Display channels, streams, and slider banners
        when (uiState.selectedTab) {
          IptvTab.LIVE_TV -> {
            LiveTvView(
              channels = IptvRepository.liveTvChannels,
              selectedCategory = uiState.selectedCategory,
              onCategorySelected = { cat -> viewModel.selectCategory(cat) },
              onChannelClick = { media -> viewModel.playMedia(media) }
            )
          }

          IptvTab.MOVIES -> {
            MoviesView(
              movies = IptvRepository.movies,
              selectedCategory = uiState.selectedCategory,
              onCategorySelected = { cat -> viewModel.selectCategory(cat) },
              onMovieClick = { media -> viewModel.playMedia(media) }
            )
          }

          IptvTab.SPORTS -> {
            SportsView(
              matches = IptvRepository.sportsMatches,
              onMatchClick = { media -> viewModel.playMedia(media) }
            )
          }
        }
      }

      // Search overlay when search is active
      if (uiState.isSearchActive) {
        SearchOverlay(
          searchQuery = uiState.searchQuery,
          isUnlocked = uiState.isUnlocked,
          onOpenSettings = { viewModel.openSettings(true) },
          onMediaClick = { media ->
            viewModel.toggleSearch(false)
            viewModel.playMedia(media)
          }
        )
      }

      // Settings Modal / Screen
      if (uiState.isSettingsOpen) {
        SettingsDialog(
          isUnlocked = uiState.isUnlocked,
          accessCodeInput = uiState.accessCodeInput,
          accessCodeError = uiState.accessCodeError,
          accessCodeSuccess = uiState.accessCodeSuccess,
          streamQuality = uiState.streamQualitySelected,
          hardwareAcceleration = uiState.hardwareAccelerationEnabled,
          onCodeChanged = { code -> viewModel.onCodeInputChanged(code) },
          onSubmitCode = { viewModel.submitAccessCode() },
          onLockContent = { viewModel.lockContent() },
          onQualityChanged = { q -> viewModel.setStreamQuality(q) },
          onToggleHardwareAcceleration = { viewModel.toggleHardwareAcceleration() },
          onDismiss = { viewModel.openSettings(false) }
        )
      }

      // Interactive Stream Player Overlay (when channel/movie/sports is tapped)
      AnimatedVisibility(
        visible = uiState.activePlayingMedia != null,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
      ) {
        StreamPlayerOverlay(
          media = uiState.activePlayingMedia,
          isPlaying = uiState.isPlaying,
          onTogglePlayback = { viewModel.togglePlayback() },
          onClose = { viewModel.closePlayer() }
        )
      }
    }
  }
}
