package com.example.data.model

import androidx.compose.ui.graphics.Color

enum class IptvTab(val titleEn: String, val titleKu: String) {
  LIVE_TV("Live TV", "ڕاستەوخۆ"),
  MOVIES("Movies", "فیلمەکان"),
  SPORTS("Sports", "وەرزش")
}

sealed interface PlayableMedia {
  val title: String
  val titleKurdish: String
  val categoryName: String
  val streamQuality: String
  val logoTag: String
}

data class ChannelItem(
  val id: String,
  override val title: String,
  override val titleKurdish: String,
  val channelNumber: String,
  val category: String,
  val categoryKurdish: String,
  override val logoTag: String,
  val badgeColor: Long,
  override val streamQuality: String = "4K",
  val currentProgram: String,
  val nextProgram: String,
  val isLive: Boolean = true,
  val viewersCount: String = "14.2K",
  val bitrate: String = "8.6 Mbps"
) : PlayableMedia {
  override val categoryName: String get() = category
}

data class MovieItem(
  val id: String,
  override val title: String,
  override val titleKurdish: String,
  val genre: String,
  val genreKurdish: String,
  val rating: String,
  val year: String,
  val duration: String,
  val description: String,
  val descriptionKurdish: String,
  override val logoTag: String,
  val gradientStart: Long,
  val gradientEnd: Long,
  override val streamQuality: String = "4K HDR"
) : PlayableMedia {
  override val categoryName: String get() = genre
}

data class SportsItem(
  val id: String,
  override val title: String,
  override val titleKurdish: String,
  val league: String,
  val leagueKurdish: String,
  val teamHome: String,
  val teamAway: String,
  val homeScore: String,
  val awayScore: String,
  val matchStatus: String, // e.g. "68'", "LIVE", "FT", "20:45"
  val isLive: Boolean,
  val channelName: String,
  override val logoTag: String,
  override val streamQuality: String = "FHD 60FPS"
) : PlayableMedia {
  override val categoryName: String get() = league
}

data class HeroBannerItem(
  val id: String,
  val tag: String,
  val tagKurdish: String,
  val title: String,
  val titleKurdish: String,
  val subtitle: String,
  val quality: String = "4K ULTRA HD",
  val gradientStart: Long,
  val gradientEnd: Long,
  val media: PlayableMedia? = null
)
