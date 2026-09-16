package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.PlayableMedia
import com.example.ui.theme.AccentGold
import com.example.ui.theme.AccentLiveGreen
import com.example.ui.theme.BackgroundDark
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun StreamPlayerOverlay(
  media: PlayableMedia?,
  isPlaying: Boolean,
  onTogglePlayback: () -> Unit,
  onClose: () -> Unit,
  modifier: Modifier = Modifier
) {
  if (media == null) return

  var selectedTrack by remember { mutableStateOf("Kurdish / کوردی") }
  var aspectRatio by remember { mutableStateOf("16:9") }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color.Black.copy(alpha = 0.96f))
      .statusBarsPadding()
      .testTag("stream_player_overlay")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Top Navigation / Controls Bar
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = onClose,
            modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
              .background(Color(0xFF202026))
              .testTag("close_player_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close Player",
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = media.title,
              color = TextPrimary,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = media.titleKurdish,
              color = CrimsonLight,
              fontSize = 12.sp
            )
          }
        }

        // Live Badge
        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(CrimsonPrimary)
            .padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(Color.White)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "STREAMING",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      // Main Video Player Viewport Container
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(16f / 9f)
          .border(1.dp, CrimsonPrimary.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
          .testTag("video_player_screen"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A0A0C))
      ) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.radialGradient(
                listOf(Color(0xFF25060A), Color(0xFF09090C))
              )
            ),
          contentAlignment = Alignment.Center
        ) {
          // Center Screen Artwork & Streaming Equalizer
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Box(
              modifier = Modifier
                .size(68.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.6f))
                .border(2.dp, CrimsonLight, CircleShape)
                .clickable { onTogglePlayback() }
                .testTag("toggle_play_button"),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = if (isPlaying) "● BUFFERING STABLE • 60 FPS" else "PAUSED",
              color = if (isPlaying) AccentLiveGreen else TextMuted,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
          }

          // Top Overlay Details
          Row(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(horizontal = 6.dp, vertical = 3.dp)
            ) {
              Text(
                text = "${media.streamQuality} • H.265 HW+",
                color = AccentGold,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }

      // Stream Details & Interactive Controls
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          // Audio Track & Subtitles Selector
          Text(
            text = "AUDIO & SUBTITLES / دەنگ و ژێرنووس",
            color = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
          )
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf("Kurdish / کوردی", "Original 5.1", "English").forEach { track ->
              val isSelected = selectedTrack == track
              Box(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(8.dp))
                  .background(if (isSelected) CrimsonPrimary else Color(0xFF22222A))
                  .clickable { selectedTrack = track }
                  .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = track,
                  color = if (isSelected) Color.White else TextSecondary,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Player Quick Tools: Aspect Ratio & Quality
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.AspectRatio,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Aspect Ratio: $aspectRatio",
                color = TextSecondary,
                fontSize = 12.sp
              )
            }

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF22222A))
                .clickable {
                  aspectRatio = if (aspectRatio == "16:9") "FIT / FULL" else "16:9"
                }
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
              Text(
                text = "Switch Ratio",
                color = CrimsonLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}
