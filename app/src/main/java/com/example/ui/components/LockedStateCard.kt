package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BackgroundDark
import com.example.ui.theme.CrimsonDark
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun LockedStateView(
  onOpenSettings: () -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark)
      .testTag("locked_state_view"),
    contentPadding = PaddingValues(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    item {
      Spacer(modifier = Modifier.height(12.dp))

      // Main Locked Card Widget
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .shadow(16.dp, RoundedCornerShape(20.dp), spotColor = CrimsonPrimary)
          .border(
            width = 1.dp,
            brush = Brush.verticalGradient(
              listOf(CrimsonPrimary.copy(alpha = 0.8f), SurfaceBorder)
            ),
            shape = RoundedCornerShape(20.dp)
          )
          .testTag("locked_message_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
          containerColor = SurfaceDark
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Lock Icon Badge with Glowing Ring
          Box(
            modifier = Modifier
              .size(72.dp)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  listOf(CrimsonPrimary.copy(alpha = 0.35f), Color.Transparent)
                )
              )
              .border(2.dp, CrimsonPrimary, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Lock,
              contentDescription = "Content Locked",
              tint = CrimsonLight,
              modifier = Modifier.size(36.dp)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Lock Status Badge
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(Color(0xFF2B0B0E))
              .border(1.dp, CrimsonPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
              .padding(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.Security,
                contentDescription = null,
                tint = CrimsonLight,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "ACCESS RESTRICTED • ناوەڕۆک قفڵکراوە",
                color = CrimsonLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
              )
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          // English Message
          Text(
            text = "Content is currently locked. To view channels, please enter the Channel Access Code in Settings.",
            color = TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Divider with subtle red accent
          Box(
            modifier = Modifier
              .width(80.dp)
              .height(2.dp)
              .background(
                Brush.horizontalGradient(
                  listOf(Color.Transparent, CrimsonPrimary, Color.Transparent)
                )
              )
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Kurdish Message
          Text(
            text = "کەناڵەکان لە ئێستادا بەردەست نین. بۆ بینینی کەناڵەکان، تکایە کۆدی بینین لە سیپتینگ بنووسە.",
            color = Color(0xFFE2E2EA),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(24.dp))

          // Action Button to Settings
          Button(
            onClick = onOpenSettings,
            colors = ButtonDefaults.buttonColors(
              containerColor = CrimsonPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("open_settings_button")
          ) {
            Icon(
              imageVector = Icons.Default.VpnKey,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Enter Access Code in Settings / داخڵکردنی کۆد",
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }

    item {
      // Locked Placeholders Header
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "LOCKED CATEGORIES / بەشە داخراوەکان",
          color = TextMuted,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Icon(
          imageVector = Icons.Default.Lock,
          contentDescription = null,
          tint = TextMuted,
          modifier = Modifier.size(14.dp)
        )
      }
    }

    // Locked Grid Placeholders (Mock categories showing locked state)
    item {
      Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        val placeholderCategories = listOf(
          Pair("Live TV Channels (500+)", "کەناڵە ڕاستەوخۆکان"),
          Pair("VOD Movies & Cinema (1200+)", "فیلم و زنجیرەکان"),
          Pair("Sports & Arena Broadcasts (80+)", "یارییە وەرزشییەکان")
        )

        placeholderCategories.forEach { (catEn, catKu) ->
          LockedCategoryPlaceholderCard(titleEn = catEn, titleKu = catKu)
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}

@Composable
private fun LockedCategoryPlaceholderCard(
  titleEn: String,
  titleKu: String
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(72.dp)
      .border(1.dp, SurfaceBorder.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color(0xFF121215)
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E24)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(18.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = titleEn,
            color = TextSecondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
          )
          Text(
            text = titleKu,
            color = TextMuted,
            fontSize = 11.sp
          )
        }
      }

      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(6.dp))
          .background(Color(0xFF1E1E24))
          .padding(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text(
          text = "LOCKED",
          color = TextMuted,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
      }
    }
  }
}
