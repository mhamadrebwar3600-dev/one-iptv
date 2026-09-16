package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IptvRepository
import com.example.data.model.ChannelItem
import com.example.data.model.PlayableMedia
import com.example.ui.theme.AccentGold
import com.example.ui.theme.AccentLiveGreen
import com.example.ui.theme.BackgroundDark
import com.example.ui.theme.CrimsonContainer
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun LiveTvView(
  channels: List<ChannelItem>,
  selectedCategory: String,
  onCategorySelected: (String) -> Unit,
  onChannelClick: (PlayableMedia) -> Unit,
  modifier: Modifier = Modifier
) {
  val categories = listOf(
    Pair("All", "هەموو"),
    Pair("News", "هەواڵ"),
    Pair("Entertainment", "کات بەسەربردن"),
    Pair("Documentary", "بەڵگەنامەیی"),
    Pair("Kids", "منداڵان"),
    Pair("Music", "مۆسیقا")
  )

  val filteredChannels = if (selectedCategory == "All") {
    channels
  } else {
    channels.filter { it.category.equals(selectedCategory, ignoreCase = true) }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark)
      .testTag("live_tv_view"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Top Featured Slider Banner
    item {
      Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        HeroSlider(
          banners = IptvRepository.heroBanners,
          onBannerClick = onChannelClick
        )
      }
    }

    // Category Selector Chips
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState())
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        categories.forEach { (catEn, catKu) ->
          val isSelected = selectedCategory == catEn
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isSelected) CrimsonPrimary else SurfaceDark)
              .border(
                width = 1.dp,
                color = if (isSelected) CrimsonLight else SurfaceBorder,
                shape = RoundedCornerShape(20.dp)
              )
              .clickable { onCategorySelected(catEn) }
              .padding(horizontal = 14.dp, vertical = 8.dp)
              .testTag("cat_chip_$catEn")
          ) {
            Text(
              text = "$catEn • $catKu",
              color = if (isSelected) Color.White else TextSecondary,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
          }
        }
      }
    }

    // Category Section Title
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .width(3.dp)
              .height(16.dp)
              .background(CrimsonPrimary)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "$selectedCategory Channels",
            color = TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Text(
          text = "${filteredChannels.size} Available",
          color = CrimsonLight,
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold
        )
      }
    }

    // Channels Grid inside LazyColumn
    item {
      val chunkedChannels = filteredChannels.chunked(2)
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        chunkedChannels.forEach { rowItems ->
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            rowItems.forEach { channel ->
              Box(modifier = Modifier.weight(1f)) {
                ChannelGridCard(
                  channel = channel,
                  onClick = { onChannelClick(channel) }
                )
              }
            }
            if (rowItems.size == 1) {
              Spacer(modifier = Modifier.weight(1f))
            }
          }
        }
      }
    }
  }
}

@Composable
fun ChannelGridCard(
  channel: ChannelItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .testTag("channel_card_${channel.id}"),
    horizontalAlignment = Alignment.Start
  ) {
    // Card Widget with Channel Logo / Emblem
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(115.dp)
        .shadow(6.dp, RoundedCornerShape(16.dp))
        .border(1.dp, SurfaceBorder, RoundedCornerShape(16.dp))
        .clickable(onClick = onClick),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceCard)
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color(channel.badgeColor).copy(alpha = 0.25f),
                Color(0xFF141418)
              )
            )
          )
          .padding(10.dp)
      ) {
        // Top Badges: Channel Number & Quality
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(Color.Black.copy(alpha = 0.6f))
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = channel.channelNumber,
              color = TextSecondary,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold
            )
          }

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(CrimsonPrimary)
              .padding(horizontal = 5.dp, vertical = 2.dp)
          ) {
            Text(
              text = channel.streamQuality,
              color = Color.White,
              fontSize = 9.sp,
              fontWeight = FontWeight.Black
            )
          }
        }

        // Center Emblem / Logo
        Box(
          modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color(channel.badgeColor))
            .align(Alignment.Center)
            .border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = channel.logoTag,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 0.5.sp
          )
        }

        // Bottom Live Indicator
        Row(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(horizontal = 6.dp, vertical = 2.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(5.dp)
              .clip(CircleShape)
              .background(AccentLiveGreen)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "LIVE",
            color = AccentLiveGreen,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Title and Info BELOW the card
    Text(
      text = channel.title,
      color = TextPrimary,
      fontSize = 13.sp,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = channel.titleKurdish,
        color = CrimsonLight,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.weight(1f)
      )
      Text(
        text = channel.viewersCount,
        color = TextMuted,
        fontSize = 10.sp
      )
    }

    Text(
      text = channel.currentProgram,
      color = TextMuted,
      fontSize = 10.sp,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}
