package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrimsonContainer
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun IptvHeader(
  isSearchActive: Boolean,
  searchQuery: String,
  isUnlocked: Boolean,
  onOpenSettings: () -> Unit,
  onToggleSearch: (Boolean) -> Unit,
  onSearchQueryChanged: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val focusRequester = remember { FocusRequester() }

  Surface(
    color = SurfaceDark.copy(alpha = 0.95f),
    modifier = modifier.fillMaxWidth(),
    shadowElevation = 8.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
      if (isSearchActive) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF202026))
            .border(1.dp, CrimsonPrimary.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = CrimsonLight,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          BasicTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            modifier = Modifier
              .weight(1f)
              .focusRequester(focusRequester)
              .testTag("search_input_field"),
            textStyle = TextStyle(
              color = TextPrimary,
              fontSize = 15.sp,
              fontFamily = FontFamily.SansSerif
            ),
            cursorBrush = SolidColor(CrimsonLight),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            decorationBox = { innerTextField ->
              if (searchQuery.isEmpty()) {
                Text(
                  text = "Search channels, movies, sports... / گەڕان",
                  color = TextMuted,
                  fontSize = 14.sp
                )
              }
              innerTextField()
            }
          )
          IconButton(
            onClick = {
              if (searchQuery.isNotEmpty()) {
                onSearchQueryChanged("")
              } else {
                onToggleSearch(false)
              }
            },
            modifier = Modifier
              .size(36.dp)
              .testTag("close_search_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close search",
              tint = TextSecondary,
              modifier = Modifier.size(20.dp)
            )
          }
        }
      } else {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Top-left Settings Gear Icon
          IconButton(
            onClick = onOpenSettings,
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(Color(0xFF1E1E24))
              .border(1.dp, SurfaceBorder, CircleShape)
              .testTag("settings_button")
          ) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = "Settings",
              tint = if (isUnlocked) CrimsonLight else TextSecondary,
              modifier = Modifier.size(22.dp)
            )
          }

          // Futuristic Title: "ONE IPTV"
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.testTag("app_title_header")
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                  Brush.linearGradient(
                    colors = listOf(CrimsonPrimary, CrimsonLight)
                  )
                )
                .padding(horizontal = 7.dp, vertical = 2.dp)
            ) {
              Text(
                text = "ONE",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 1.5.sp
              )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "IPTV",
              color = TextPrimary,
              fontSize = 20.sp,
              fontWeight = FontWeight.ExtraBold,
              fontFamily = FontFamily.SansSerif,
              letterSpacing = 3.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            // Futuristic pulse dot
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(if (isUnlocked) Color(0xFF00E676) else CrimsonPrimary)
            )
          }

          // Top-right Search Icon
          IconButton(
            onClick = { onToggleSearch(true) },
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(Color(0xFF1E1E24))
              .border(1.dp, SurfaceBorder, CircleShape)
              .testTag("search_button")
          ) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = TextSecondary,
              modifier = Modifier.size(22.dp)
            )
          }
        }
      }
    }
  }
}
