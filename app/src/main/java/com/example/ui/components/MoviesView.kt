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
import androidx.compose.foundation.rememberScrollState
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
import com.example.data.model.MovieItem
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
fun MoviesView(
  movies: List<MovieItem>,
  selectedCategory: String,
  onCategorySelected: (String) -> Unit,
  onMovieClick: (PlayableMedia) -> Unit,
  modifier: Modifier = Modifier
) {
  val genres = listOf(
    Pair("All", "هەموو"),
    Pair("Action", "ئاکشن"),
    Pair("Sci-Fi", "خەیاڵی"),
    Pair("Drama", "دراما")
  )

  val filteredMovies = if (selectedCategory == "All") {
    movies
  } else {
    movies.filter { it.genre.contains(selectedCategory, ignoreCase = true) }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BackgroundDark)
      .testTag("movies_view"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Genre Filter Chips
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState())
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        genres.forEach { (genreEn, genreKu) ->
          val isSelected = selectedCategory == genreEn
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isSelected) CrimsonPrimary else SurfaceDark)
              .border(
                width = 1.dp,
                color = if (isSelected) CrimsonLight else SurfaceBorder,
                shape = RoundedCornerShape(20.dp)
              )
              .clickable { onCategorySelected(genreEn) }
              .padding(horizontal = 14.dp, vertical = 8.dp)
              .testTag("genre_chip_$genreEn")
          ) {
            Text(
              text = "$genreEn • $genreKu",
              color = if (isSelected) Color.White else TextSecondary,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
          }
        }
      }
    }

    // Movies Header
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 6.dp),
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
            text = "Latest Blockbuster Movies / نوێترین فیلمەکان",
            color = TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    // Grid of Movies (2 columns)
    item {
      val chunked = filteredMovies.chunked(2)
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        chunked.forEach { rowItems ->
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            rowItems.forEach { movie ->
              Box(modifier = Modifier.weight(1f)) {
                MovieGridCard(
                  movie = movie,
                  onClick = { onMovieClick(movie) }
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
fun MovieGridCard(
  movie: MovieItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .testTag("movie_card_${movie.id}"),
    horizontalAlignment = Alignment.Start
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .shadow(8.dp, RoundedCornerShape(16.dp))
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
                Color(movie.gradientStart),
                Color(movie.gradientEnd),
                Color(0xFF0F0F12)
              )
            )
          )
          .padding(10.dp)
      ) {
        // Top Badges: Rating & Quality
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(Color.Black.copy(alpha = 0.7f))
              .padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = null,
              tint = AccentGold,
              modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
              text = movie.rating,
              color = AccentGold,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
          }

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(CrimsonPrimary)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = movie.streamQuality,
              color = Color.White,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        // Center Big Stylized Title Emblem
        Column(
          modifier = Modifier.align(Alignment.Center),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(
            modifier = Modifier
              .size(50.dp)
              .clip(CircleShape)
              .background(Color.Black.copy(alpha = 0.5f))
              .border(1.5.dp, CrimsonLight.copy(alpha = 0.8f), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.PlayArrow,
              contentDescription = "Play",
              tint = CrimsonLight,
              modifier = Modifier.size(28.dp)
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = movie.logoTag,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
          )
        }

        // Bottom Info: Duration & Year
        Row(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = movie.duration,
            color = TextSecondary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
          )
          Text(
            text = movie.year,
            color = TextMuted,
            fontSize = 10.sp
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Title below card
    Text(
      text = movie.title,
      color = TextPrimary,
      fontSize = 13.sp,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )

    Text(
      text = movie.titleKurdish,
      color = CrimsonLight,
      fontSize = 11.sp,
      fontWeight = FontWeight.Medium,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )

    Text(
      text = movie.genre,
      color = TextMuted,
      fontSize = 10.sp,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}
