package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.IptvTab
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

@Composable
fun BottomNavBar(
  selectedTab: IptvTab,
  onTabSelected: (IptvTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    color = SurfaceDark.copy(alpha = 0.98f),
    shadowElevation = 16.dp,
    modifier = modifier
      .fillMaxWidth()
      .border(
        width = 1.dp,
        color = SurfaceBorder.copy(alpha = 0.5f),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
      )
      .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
      .testTag("bottom_nav_bar")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .navigationBarsPadding()
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // 1. Live TV / ڕاستەوخۆ (Default selected tab with TV icon)
      BottomNavItem(
        tab = IptvTab.LIVE_TV,
        titleEn = "Live TV",
        titleKu = "ڕاستەوخۆ",
        icon = Icons.Default.Tv,
        isSelected = selectedTab == IptvTab.LIVE_TV,
        onClick = { onTabSelected(IptvTab.LIVE_TV) },
        testTag = "tab_live_tv"
      )

      // 2. Movies / فیلمەکان (Clapperboard icon)
      BottomNavItem(
        tab = IptvTab.MOVIES,
        titleEn = "Movies",
        titleKu = "فیلمەکان",
        icon = Icons.Default.Movie,
        isSelected = selectedTab == IptvTab.MOVIES,
        onClick = { onTabSelected(IptvTab.MOVIES) },
        testTag = "tab_movies"
      )

      // 3. Sports / وەرزش (Football icon)
      BottomNavItem(
        tab = IptvTab.SPORTS,
        titleEn = "Sports",
        titleKu = "وەرزش",
        icon = Icons.Default.SportsSoccer,
        isSelected = selectedTab == IptvTab.SPORTS,
        onClick = { onTabSelected(IptvTab.SPORTS) },
        testTag = "tab_sports"
      )
    }
  }
}

@Composable
private fun BottomNavItem(
  tab: IptvTab,
  titleEn: String,
  titleKu: String,
  icon: ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit,
  testTag: String
) {
  val tintColor by animateColorAsState(
    targetValue = if (isSelected) CrimsonLight else TextMuted,
    animationSpec = tween(durationMillis = 200),
    label = "nav_tint"
  )

  val interactionSource = remember { MutableInteractionSource() }

  Column(
    modifier = Modifier
      .clip(RoundedCornerShape(12.dp))
      .clickable(
        interactionSource = interactionSource,
        indication = ripple(bounded = true, color = CrimsonPrimary),
        onClick = onClick
      )
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .testTag(testTag),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(if (isSelected) Color(0xFF2C0B0E) else Color.Transparent)
        .padding(horizontal = 14.dp, vertical = 4.dp),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = "$titleEn / $titleKu",
        tint = tintColor,
        modifier = Modifier.size(24.dp)
      )
    }

    Spacer(modifier = Modifier.height(3.dp))

    Text(
      text = "$titleEn • $titleKu",
      color = tintColor,
      fontSize = 11.sp,
      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
    )

    // Active Indicator Bar
    Box(
      modifier = Modifier
        .padding(top = 4.dp)
        .width(if (isSelected) 18.dp else 0.dp)
        .height(3.dp)
        .clip(CircleShape)
        .background(if (isSelected) CrimsonPrimary else Color.Transparent)
    )
  }
}
