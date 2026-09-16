package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SportsSoccer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.PlayableMedia
import com.example.data.model.SportsItem
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
fun SportsView(
  matches: List<SportsItem>,
  onMatchClick: (PlayableMedia) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark)
      .testTag("sports_view"),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
  ) {
    // Top Sports Header
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 12.dp),
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
            text = "Live Matches / یارییە ڕاستەوخۆکان",
            color = TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(AccentLiveGreen)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "LIVE ARENA",
            color = AccentLiveGreen,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    // Match Cards
    items(matches.size) { index ->
      val match = matches[index]
      SportsMatchCard(
        match = match,
        onClick = { onMatchClick(match) },
        modifier = Modifier.padding(bottom = 14.dp)
      )
    }

    // Dedicated Sports Channels Grid Section
    item {
      Spacer(modifier = Modifier.height(10.dp))
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .width(3.dp)
            .height(16.dp)
            .background(CrimsonPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Sports Channels / کەناڵە وەرزشییەکان",
          color = TextPrimary,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    item {
      val sportsChannels = listOf(
        Pair("ONE Sport 1 4K", "خولی ئینگلیزی و پاڵەوانەکان"),
        Pair("ONE Sport 2 FHD", "خولی ئیسپانیا و ئیتاڵیا"),
        Pair("ONE Sport Arena", "پێشبڕکێی فۆرمولا وە تێنس"),
        Pair("ONE Sport News", "هەواڵی وەرزشی ٢٤ کاتژمێر")
      )

      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        sportsChannels.forEachIndexed { idx, (name, desc) ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .border(1.dp, SurfaceBorder, RoundedCornerShape(14.dp))
              .clickable {
                if (matches.isNotEmpty()) onMatchClick(matches[idx % matches.size])
              },
            shape = RoundedCornerShape(14.dp),
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
                    .size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF8A0009)),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.SportsSoccer,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                  )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                  Text(
                    text = name,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                  )
                  Text(
                    text = desc,
                    color = TextSecondary,
                    fontSize = 11.sp
                  )
                }
              }

              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(CrimsonPrimary)
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "4K 60FPS",
                  color = Color.White,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

@Composable
fun SportsMatchCard(
  match: SportsItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .shadow(8.dp, RoundedCornerShape(18.dp))
      .border(1.dp, SurfaceBorder, RoundedCornerShape(18.dp))
      .clickable(onClick = onClick)
      .testTag("sports_card_${match.id}"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceCard)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.verticalGradient(
            colors = listOf(Color(0xFF1E1E26), Color(0xFF121216))
          )
        )
        .padding(16.dp)
    ) {
      // League and Status Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = match.league,
            color = CrimsonLight,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = match.leagueKurdish,
            color = TextMuted,
            fontSize = 10.sp
          )
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (match.isLive) Color(0xFF2E090D) else Color(0xFF1A1A22))
            .border(
              1.dp,
              if (match.isLive) CrimsonPrimary else SurfaceBorder,
              RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = match.matchStatus,
            color = if (match.isLive) CrimsonLight else TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Teams and Score Board
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Home Team
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.weight(1f)
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(Color(0xFF242430)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = match.teamHome.take(3).uppercase(),
              color = TextPrimary,
              fontSize = 12.sp,
              fontWeight = FontWeight.ExtraBold
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = match.teamHome,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1
          )
        }

        // Score / VS
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.padding(horizontal = 12.dp)
        ) {
          if (match.isLive) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = match.homeScore,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
              )
              Text(
                text = " : ",
                color = CrimsonLight,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
              )
              Text(
                text = match.awayScore,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
              )
            }
          } else {
            Text(
              text = "VS",
              color = TextMuted,
              fontSize = 18.sp,
              fontWeight = FontWeight.Black
            )
          }
        }

        // Away Team
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.weight(1f)
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(Color(0xFF242430)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = match.teamAway.take(3).uppercase(),
              color = TextPrimary,
              fontSize = 12.sp,
              fontWeight = FontWeight.ExtraBold
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = match.teamAway,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Footer: Channel + Watch action
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint = CrimsonLight,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = match.channelName,
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
          )
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CrimsonPrimary)
            .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
          Text(
            text = "Watch Live / بینین",
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}
