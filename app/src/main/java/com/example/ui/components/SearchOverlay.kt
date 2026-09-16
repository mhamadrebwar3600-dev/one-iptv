package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IptvRepository
import com.example.data.model.PlayableMedia
import com.example.ui.theme.AccentGold
import com.example.ui.theme.BackgroundDark
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SearchOverlay(
  searchQuery: String,
  isUnlocked: Boolean,
  onOpenSettings: () -> Unit,
  onMediaClick: (PlayableMedia) -> Unit,
  modifier: Modifier = Modifier
) {
  if (!isUnlocked) {
    Box(
      modifier = modifier
        .fillMaxSize()
        .background(BackgroundDark)
        .padding(24.dp),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.Lock,
          contentDescription = null,
          tint = CrimsonLight,
          modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          text = "Search is unavailable while content is locked.",
          color = TextPrimary,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "تکایە سەرەتا کۆدی دەستپێگەیشتن لە ڕێکخستنەکان بنووسە.",
          color = TextSecondary,
          fontSize = 13.sp
        )
      }
    }
    return
  }

  val matchedChannels = IptvRepository.liveTvChannels.filter {
    it.title.contains(searchQuery, ignoreCase = true) ||
      it.titleKurdish.contains(searchQuery, ignoreCase = true) ||
      it.category.contains(searchQuery, ignoreCase = true)
  }

  val matchedMovies = IptvRepository.movies.filter {
    it.title.contains(searchQuery, ignoreCase = true) ||
      it.titleKurdish.contains(searchQuery, ignoreCase = true) ||
      it.genre.contains(searchQuery, ignoreCase = true)
  }

  val matchedSports = IptvRepository.sportsMatches.filter {
    it.title.contains(searchQuery, ignoreCase = true) ||
      it.teamHome.contains(searchQuery, ignoreCase = true) ||
      it.teamAway.contains(searchQuery, ignoreCase = true)
  }

  val totalMatches = matchedChannels.size + matchedMovies.size + matchedSports.size

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark)
      .testTag("search_results_list"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Text(
        text = if (searchQuery.isEmpty()) "Suggested Searches / گەڕانی پێشنیارکراو" else "Results ($totalMatches found)",
        color = TextSecondary,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(4.dp))
    }

    if (totalMatches == 0 && searchQuery.isNotEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "No results found for '$searchQuery'",
            color = TextMuted,
            fontSize = 14.sp
          )
        }
      }
    }

    // Channels
    items(matchedChannels) { channel ->
      SearchResultItem(
        media = channel,
        typeLabel = "Live TV • CH ${channel.channelNumber}",
        icon = Icons.Default.Tv,
        onClick = { onMediaClick(channel) }
      )
    }

    // Movies
    items(matchedMovies) { movie ->
      SearchResultItem(
        media = movie,
        typeLabel = "Movie • ${movie.year} • ${movie.genre}",
        icon = Icons.Default.Movie,
        onClick = { onMediaClick(movie) }
      )
    }

    // Sports
    items(matchedSports) { sport ->
      SearchResultItem(
        media = sport,
        typeLabel = "Sports • ${sport.league}",
        icon = Icons.Default.SportsSoccer,
        onClick = { onMediaClick(sport) }
      )
    }
  }
}

@Composable
private fun SearchResultItem(
  media: PlayableMedia,
  typeLabel: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceDark)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF22222A)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CrimsonLight,
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = media.title,
            color = TextPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "${media.titleKurdish} • $typeLabel",
            color = TextMuted,
            fontSize = 11.sp
          )
        }
      }

      Icon(
        imageVector = Icons.Default.PlayArrow,
        contentDescription = "Watch",
        tint = CrimsonLight,
        modifier = Modifier.size(20.dp)
      )
    }
  }
}
