package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HeroBannerItem
import com.example.data.model.PlayableMedia
import com.example.ui.theme.AccentGold
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun HeroSlider(
  banners: List<HeroBannerItem>,
  onBannerClick: (PlayableMedia) -> Unit,
  modifier: Modifier = Modifier
) {
  if (banners.isEmpty()) return

  var currentIndex by remember { mutableIntStateOf(0) }

  // Auto advance banner every 5 seconds
  LaunchedEffect(banners.size) {
    while (true) {
      delay(5000)
      currentIndex = (currentIndex + 1) % banners.size
    }
  }

  val item = banners[currentIndex]

  Card(
    modifier = modifier
      .fillMaxWidth()
      .height(190.dp)
      .shadow(12.dp, RoundedCornerShape(18.dp), spotColor = CrimsonPrimary)
      .border(1.dp, SurfaceBorder, RoundedCornerShape(18.dp))
      .clickable {
        item.media?.let { onBannerClick(it) }
      }
      .testTag("hero_slider_card"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Color(item.gradientEnd))
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.linearGradient(
            colors = listOf(
              Color(item.gradientStart),
              Color(item.gradientEnd),
              Color(0xFF0D0D0D)
            )
          )
        )
        .padding(16.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        // Top row: Tag + Quality badge
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(CrimsonPrimary)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = item.tag,
              color = Color.White,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.5.sp
            )
          }

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(Color.Black.copy(alpha = 0.6f))
              .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
              .padding(horizontal = 7.dp, vertical = 3.dp)
          ) {
            Text(
              text = item.quality,
              color = AccentGold,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        // Middle: Titles
        Column {
          Text(
            text = item.title,
            color = TextPrimary,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = item.titleKurdish,
            color = CrimsonLight,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = item.subtitle,
            color = TextSecondary,
            fontSize = 11.sp,
            maxLines = 1
          )
        }

        // Bottom row: Watch action + indicators
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(CrimsonPrimary)
              .padding(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.PlayArrow,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "Watch Stream / سەیرکردن",
              color = Color.White,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }

          // Indicator Dots
          Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            banners.indices.forEach { index ->
              val isCurrent = index == currentIndex
              Box(
                modifier = Modifier
                  .width(if (isCurrent) 16.dp else 6.dp)
                  .height(6.dp)
                  .clip(CircleShape)
                  .background(
                    if (isCurrent) CrimsonPrimary else Color.White.copy(alpha = 0.3f)
                  )
              )
            }
          }
        }
      }
    }
  }
}
