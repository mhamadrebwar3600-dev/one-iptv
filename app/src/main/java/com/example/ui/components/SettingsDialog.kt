package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
  isUnlocked: Boolean,
  accessCodeInput: String,
  accessCodeError: String?,
  accessCodeSuccess: Boolean,
  streamQuality: String,
  hardwareAcceleration: Boolean,
  onCodeChanged: (String) -> Unit,
  onSubmitCode: () -> Unit,
  onLockContent: () -> Unit,
  onQualityChanged: (String) -> Unit,
  onToggleHardwareAcceleration: () -> Unit,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = SurfaceDark,
    dragHandle = null,
    modifier = Modifier.testTag("settings_modal")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
      // Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(CrimsonContainer),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = null,
              tint = CrimsonLight,
              modifier = Modifier.size(22.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "Settings & Activation",
              color = TextPrimary,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "ڕێکخستن و کۆدی بینینی کەناڵەکان",
              color = TextMuted,
              fontSize = 12.sp
            )
          }
        }

        IconButton(
          onClick = onDismiss,
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xFF22222A))
            .testTag("close_settings_button")
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // ACTIVATION CODE SECTION CARD
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .border(
            width = 1.dp,
            color = if (isUnlocked) AccentLiveGreen.copy(alpha = 0.5f) else CrimsonPrimary.copy(alpha = 0.6f),
            shape = RoundedCornerShape(16.dp)
          )
          .testTag("activation_code_section"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = Color(0xFF191920)
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
        ) {
          // Section Title
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = if (isUnlocked) Icons.Default.LockOpen else Icons.Default.Lock,
                contentDescription = null,
                tint = if (isUnlocked) AccentLiveGreen else CrimsonLight,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Channel Access Code",
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }

            // Status Badge
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                  if (isUnlocked) AccentLiveGreen.copy(alpha = 0.15f) else CrimsonPrimary.copy(alpha = 0.15f)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
              Text(
                text = if (isUnlocked) "UNLOCKED" else "LOCKED",
                color = if (isUnlocked) AccentLiveGreen else CrimsonLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }

          Text(
            text = "کۆدی بینینی کەناڵەکان",
            color = TextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Input field labeled "Channel Access Code" / "کۆدی بینینی کەناڵەکان"
          OutlinedTextField(
            value = accessCodeInput,
            onValueChange = onCodeChanged,
            label = {
              Text(
                text = "Channel Access Code / کۆدی بینینی کەناڵەکان",
                fontSize = 13.sp
              )
            },
            placeholder = {
              Text(
                text = "Enter code (e.g. 6565)",
                color = TextMuted,
                fontSize = 13.sp
              )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.NumberPassword,
              imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
              onDone = { onSubmitCode() }
            ),
            leadingIcon = {
              Icon(
                imageVector = Icons.Default.VpnKey,
                contentDescription = null,
                tint = CrimsonLight,
                modifier = Modifier.size(20.dp)
              )
            },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = CrimsonPrimary,
              unfocusedBorderColor = SurfaceBorder,
              focusedLabelColor = CrimsonLight,
              unfocusedLabelColor = TextSecondary,
              focusedTextColor = TextPrimary,
              unfocusedTextColor = TextPrimary,
              cursorColor = CrimsonLight,
              focusedContainerColor = Color(0xFF141418),
              unfocusedContainerColor = Color(0xFF141418)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("access_code_input")
          )

          // Error Message Banner: "Invalid Access Code"
          AnimatedVisibility(visible = accessCodeError != null) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF380E12))
                .border(1.dp, CrimsonPrimary, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("access_code_error_banner"),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                tint = CrimsonLight,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = accessCodeError ?: "Invalid Access Code",
                color = CrimsonLight,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
              )
            }
          }

          // Success Message Banner
          AnimatedVisibility(visible = accessCodeSuccess) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF0C2B18))
                .border(1.dp, AccentLiveGreen, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("access_code_success_banner"),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = AccentLiveGreen,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Channels Unlocked Successfully! / کەناڵەکان کرانەوە",
                color = AccentLiveGreen,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Submit/Save Button
          Button(
            onClick = onSubmitCode,
            colors = ButtonDefaults.buttonColors(
              containerColor = CrimsonPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(46.dp)
              .testTag("submit_code_button")
          ) {
            Text(
              text = "Submit / Save (تەواوکردن)",
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
          }

          // If unlocked: provide relock option
          if (isUnlocked) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
              onClick = onLockContent,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextSecondary
              ),
              modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .testTag("lock_content_button")
            ) {
              Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Lock Channels Again / قفڵکردنەوەی کەناڵەکان",
                fontSize = 12.sp
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // IPTV STREAM PREFERENCES
      Text(
        text = "STREAM PREFERENCES / ڕێکخستنی پەخش",
        color = TextMuted,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = Color(0xFF191920)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          // Stream Quality Selector
          Text(
            text = "Default Streaming Quality",
            color = TextPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
          )
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf("Auto", "4K HDR", "FHD 1080p", "HD 720p").forEach { quality ->
              val isSelected = streamQuality == quality || (quality == "4K HDR" && streamQuality.contains("4K"))
              Box(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(8.dp))
                  .background(if (isSelected) CrimsonPrimary else Color(0xFF24242D))
                  .clickable { onQualityChanged(quality) }
                  .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = quality,
                  color = if (isSelected) Color.White else TextSecondary,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Hardware Acceleration Toggle
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "Hardware Decoder (HW+)",
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
              )
              Text(
                text = "Accelerates 4K 60FPS playback",
                color = TextMuted,
                fontSize = 12.sp
              )
            }
            Switch(
              checked = hardwareAcceleration,
              onCheckedChange = { onToggleHardwareAcceleration() },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = CrimsonPrimary,
                uncheckedThumbColor = TextMuted,
                uncheckedTrackColor = Color(0xFF282832)
              )
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // APP VERSION INFO
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "ONE IPTV v1.0.0 • Modern Streaming Engine",
          color = TextMuted,
          fontSize = 12.sp
        )
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}
