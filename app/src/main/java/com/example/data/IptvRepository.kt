package com.example.data

import com.example.data.model.ChannelItem
import com.example.data.model.HeroBannerItem
import com.example.data.model.MovieItem
import com.example.data.model.SportsItem

object IptvRepository {

  val liveTvChannels = listOf(
    ChannelItem(
      id = "ch_rudaw",
      title = "Rudaw News HD",
      titleKurdish = "ڕووداو نیوز",
      channelNumber = "CH 01",
      category = "News",
      categoryKurdish = "هەواڵ",
      logoTag = "RUDAW",
      badgeColor = 0xFFD32F2F,
      streamQuality = "4K 60FPS",
      currentProgram = "Global Live Headlines",
      nextProgram = "Evening Kurdish Insight",
      viewersCount = "38.5K"
    ),
    ChannelItem(
      id = "ch_kurdistan24",
      title = "Kurdistan 24 HD",
      titleKurdish = "کوردستان ٢٤",
      channelNumber = "CH 02",
      category = "News",
      categoryKurdish = "هەواڵ",
      logoTag = "K24",
      badgeColor = 0xFF1976D2,
      streamQuality = "FHD",
      currentProgram = "Kurdistan Today",
      nextProgram = "Middle East Dialogue",
      viewersCount = "24.1K"
    ),
    ChannelItem(
      id = "ch_one_cinema",
      title = "ONE Cinema Max",
      titleKurdish = "وان سینەما",
      channelNumber = "CH 03",
      category = "Entertainment",
      categoryKurdish = "کات بەسەربردن",
      logoTag = "CINEMA",
      badgeColor = 0xFFE50914,
      streamQuality = "4K HDR",
      currentProgram = "Blockbuster Premiere: Dune II",
      nextProgram = "Oppenheimer",
      viewersCount = "45.0K"
    ),
    ChannelItem(
      id = "ch_one_action",
      title = "ONE Action Prime",
      titleKurdish = "وان ئاکشن",
      channelNumber = "CH 04",
      category = "Entertainment",
      categoryKurdish = "کات بەسەربردن",
      logoTag = "ACTION",
      badgeColor = 0xFFFF6F00,
      streamQuality = "FHD",
      currentProgram = "John Wick: Chapter 4",
      nextProgram = "Mission Impossible 7",
      viewersCount = "19.8K"
    ),
    ChannelItem(
      id = "ch_natgeo",
      title = "Nat Geo Wild HD",
      titleKurdish = "ناشناڵ جیۆگرافیک",
      channelNumber = "CH 05",
      category = "Documentary",
      categoryKurdish = "بەڵگەنامەیی",
      logoTag = "NATGEO",
      badgeColor = 0xFFFBC02D,
      streamQuality = "4K",
      currentProgram = "Secrets of the Deep Oceans",
      nextProgram = "African Wildlife Safari",
      viewersCount = "12.3K"
    ),
    ChannelItem(
      id = "ch_kurdmax",
      title = "Kurdmax Drama HD",
      titleKurdish = "کوردماکس دراما",
      channelNumber = "CH 06",
      category = "Entertainment",
      categoryKurdish = "کات بەسەربردن",
      logoTag = "KMAX",
      badgeColor = 0xFF7B1FA2,
      streamQuality = "FHD",
      currentProgram = "Dramaye Shwani Sarbazi",
      nextProgram = "Family Serial Episode 42",
      viewersCount = "29.4K"
    ),
    ChannelItem(
      id = "ch_discovery",
      title = "Discovery Science",
      titleKurdish = "دیسکەڤەری ساینس",
      channelNumber = "CH 07",
      category = "Documentary",
      categoryKurdish = "بەڵگەنامەیی",
      logoTag = "DISC",
      badgeColor = 0xFF0097A7,
      streamQuality = "FHD",
      currentProgram = "Space Engineers Season 3",
      nextProgram = "How It's Made",
      viewersCount = "11.7K"
    ),
    ChannelItem(
      id = "ch_kids_spacetoon",
      title = "Spacetoon HD",
      titleKurdish = "سپەیستوون",
      channelNumber = "CH 08",
      category = "Kids",
      categoryKurdish = "منداڵان",
      logoTag = "KIDS",
      badgeColor = 0xFFE91E63,
      streamQuality = "FHD",
      currentProgram = "Detective Conan",
      nextProgram = "One Piece Adventure",
      viewersCount = "16.4K"
    ),
    ChannelItem(
      id = "ch_bbc_world",
      title = "BBC World News 4K",
      titleKurdish = "بی بی سی جیهانی",
      channelNumber = "CH 09",
      category = "News",
      categoryKurdish = "هەواڵ",
      logoTag = "BBC",
      badgeColor = 0xFFB71C1C,
      streamQuality = "4K",
      currentProgram = "World Business Report",
      nextProgram = "BBC Global Correspondent",
      viewersCount = "21.0K"
    ),
    ChannelItem(
      id = "ch_mtv_live",
      title = "MTV Live Music HD",
      titleKurdish = "ئێم تی ڤی مۆسیقا",
      channelNumber = "CH 10",
      category = "Music",
      categoryKurdish = "مۆسیقا",
      logoTag = "MTV",
      badgeColor = 0xFF00C853,
      streamQuality = "FHD",
      currentProgram = "Top 40 International Hits",
      nextProgram = "Live EDM Festival",
      viewersCount = "14.8K"
    )
  )

  val movies = listOf(
    MovieItem(
      id = "mov_gladiator2",
      title = "Gladiator II",
      titleKurdish = "گلادیاتۆر ٢",
      genre = "Action / Epic",
      genreKurdish = "ئاکشن / مێژوویی",
      rating = "8.4",
      year = "2024",
      duration = "2h 28m",
      description = "Years after witnessing the death of Maximus, Lucius enters the Colosseum to fight for the future of Rome.",
      descriptionKurdish = "ساڵانێک دوای شەهیدبوونی ماکسیمۆس، لووسیۆس دەچێتە کۆڵۆسیۆم بۆ جەنگ لە پێناو داهاتووی ڕۆما.",
      logoTag = "GLAD2",
      gradientStart = 0xFF4A100F,
      gradientEnd = 0xFF140405
    ),
    MovieItem(
      id = "mov_dune2",
      title = "Dune: Part Two",
      titleKurdish = "دوون: بەشی دووەم",
      genre = "Sci-Fi / Adventure",
      genreKurdish = "زانستی خەیاڵی / سەرکێشی",
      rating = "8.8",
      year = "2024",
      duration = "2h 46m",
      description = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
      descriptionKurdish = "پۆڵ ئاتریدس لەگەڵ چانی و فریمنەکان یەکدەگرن لە کاتێکدا تۆڵە دەکەنەوە لەوانەی خێزانەکەیان لەناوبرد.",
      logoTag = "DUNE2",
      gradientStart = 0xFF8D5B08,
      gradientEnd = 0xFF1B1202
    ),
    MovieItem(
      id = "mov_deadpool3",
      title = "Deadpool & Wolverine",
      titleKurdish = "دیدپوول و وۆڵڤەرین",
      genre = "Action / Comedy",
      genreKurdish = "ئاکشن / کۆمیدی",
      rating = "8.0",
      year = "2024",
      duration = "2h 08m",
      description = "Wolverine is recovering from his injuries when he crosses paths with the loudmouth, Deadpool, teaming up to defeat a common foe.",
      descriptionKurdish = "وۆڵڤەرین لە برینەکانی چاکدەبێتەوە کاتێک لەگەڵ دیدپوول یەکدەگرن بۆ شکستهێنان بە دوژمنێکی هاوبەش.",
      logoTag = "DP3",
      gradientStart = 0xFF7F1D1D,
      gradientEnd = 0xFF1E293B
    ),
    MovieItem(
      id = "mov_interstellar",
      title = "Interstellar",
      titleKurdish = "ئینتەرستێلار",
      genre = "Sci-Fi / Drama",
      genreKurdish = "زانستی خەیاڵی / دراما",
      rating = "8.7",
      year = "2014",
      duration = "2h 49m",
      description = "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot is tasked with piloting a spacecraft to find a new planet.",
      descriptionKurdish = "گەشتێکی مێژوویی بۆ ناو کونی ڕەش و دۆزینەوەی گەسارەیەکی نوێ بۆ مانەوەی مرۆڤایەتی.",
      logoTag = "SPACE",
      gradientStart = 0xFF0F172A,
      gradientEnd = 0xFF020617
    ),
    MovieItem(
      id = "mov_oppenheimer",
      title = "Oppenheimer",
      titleKurdish = "ئۆپنهایمەر",
      genre = "Biography / Drama",
      genreKurdish = "ژیاننامە / دراما",
      rating = "8.9",
      year = "2023",
      duration = "3h 00m",
      description = "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
      descriptionKurdish = "چیرۆکی دروستکردنی بۆمبی ئەتۆمی بە دەستی فیزیازان جەی ڕۆبەرت ئۆپنهایمەر.",
      logoTag = "OPPEN",
      gradientStart = 0xFF78350F,
      gradientEnd = 0xFF180802
    ),
    MovieItem(
      id = "mov_dark_knight",
      title = "The Dark Knight",
      titleKurdish = "شۆڕەسواری تاریکی",
      genre = "Action / Crime",
      genreKurdish = "ئاکشن / تاوانکاری",
      rating = "9.0",
      year = "2008",
      duration = "2h 32m",
      description = "When the menace known as the Joker wreaks havoc on Gotham, Batman must accept one of the greatest tests to fight injustice.",
      descriptionKurdish = "باتمان لەگەڵ جۆکەر ڕووبەڕوو دەبێتەوە لە گەورەترین شەڕ بۆ پاراستنی شاری گۆسام.",
      logoTag = "BATMAN",
      gradientStart = 0xFF1E293B,
      gradientEnd = 0xFF090D14
    )
  )

  val sportsMatches = listOf(
    SportsItem(
      id = "sp_ucl_1",
      title = "Real Madrid vs Manchester City",
      titleKurdish = "ڕیاڵ مەدرید - مانچستەر سیتی",
      league = "UEFA Champions League",
      leagueKurdish = "خولی پاڵەوانەکانی ئەوروپا",
      teamHome = "Real Madrid",
      teamAway = "Man City",
      homeScore = "2",
      awayScore = "1",
      matchStatus = "73' LIVE",
      isLive = true,
      channelName = "ONE Sport 1 4K",
      logoTag = "UCL",
      streamQuality = "4K 60FPS"
    ),
    SportsItem(
      id = "sp_elclasico",
      title = "FC Barcelona vs Real Madrid",
      titleKurdish = "بارسێلۆنا - ڕیاڵ مەدرید",
      league = "La Liga Santander",
      leagueKurdish = "خولی ئیسپانیا - لالیگا",
      teamHome = "Barcelona",
      teamAway = "Real Madrid",
      homeScore = "3",
      awayScore = "2",
      matchStatus = "88' LIVE",
      isLive = true,
      channelName = "ONE Sport 2 HD",
      logoTag = "LALIGA",
      streamQuality = "FHD 60FPS"
    ),
    SportsItem(
      id = "sp_pl_arsenal_liverpool",
      title = "Liverpool vs Arsenal",
      titleKurdish = "لیڤەرپوول - ئارسناڵ",
      league = "Premier League",
      leagueKurdish = "خولی نایابی ئینگلیزی",
      teamHome = "Liverpool",
      teamAway = "Arsenal",
      homeScore = "1",
      awayScore = "1",
      matchStatus = "54' LIVE",
      isLive = true,
      channelName = "ONE Sport 3 HD",
      logoTag = "PL",
      streamQuality = "FHD 60FPS"
    ),
    SportsItem(
      id = "sp_derby_milan",
      title = "Inter Milan vs AC Milan",
      titleKurdish = "ئینتەر میلان - ئەی سی میلان",
      league = "Serie A Italy",
      leagueKurdish = "خولی ئیتاڵیا - سێریا ئەی",
      teamHome = "Inter",
      teamAway = "AC Milan",
      homeScore = "0",
      awayScore = "0",
      matchStatus = "Today 21:45",
      isLive = false,
      channelName = "ONE Sport 4 HD",
      logoTag = "SERIE_A",
      streamQuality = "FHD"
    ),
    SportsItem(
      id = "sp_bayern_bvb",
      title = "Bayern Munich vs Dortmund",
      titleKurdish = "بایرن میونشن - دۆرتمۆند",
      league = "Bundesliga",
      leagueKurdish = "خولی ئەڵمانیا - بۆندسلیگا",
      teamHome = "Bayern",
      teamAway = "Dortmund",
      homeScore = "4",
      awayScore = "2",
      matchStatus = "FT",
      isLive = false,
      channelName = "ONE Sport Replay",
      logoTag = "BUNDES",
      streamQuality = "1080p"
    )
  )

  val heroBanners = listOf(
    HeroBannerItem(
      id = "banner_1",
      tag = "EXCLUSIVE LIVE STREAM",
      tagKurdish = "پەخشی ڕاستەوخۆ تایبەت",
      title = "UEFA Champions League Semifinals",
      titleKurdish = "پێش کۆتایی خولی پاڵەوانەکانی ئەوروپا",
      subtitle = "Real Madrid vs Manchester City • Ultra HD 4K 60FPS Stream",
      quality = "4K 60FPS",
      gradientStart = 0xFF8A0009,
      gradientEnd = 0xFF190204,
      media = sportsMatches[0]
    ),
    HeroBannerItem(
      id = "banner_2",
      tag = "PREMIERE MOVIE",
      tagKurdish = "فیلمی نوێی هەفتە",
      title = "Gladiator II: Rome Reigns",
      titleKurdish = "گلادیاتۆر ٢: گەڕانەوە بۆ ڕۆما",
      subtitle = "Dolby Atmos • 4K HDR • Kurdish Subtitles Available",
      quality = "4K DOLBY",
      gradientStart = 0xFF5B1109,
      gradientEnd = 0xFF140405,
      media = movies[0]
    ),
    HeroBannerItem(
      id = "banner_3",
      tag = "BREAKING NEWS",
      tagKurdish = "هەواڵی بەپەلە",
      title = "Rudaw 24/7 Live Stream",
      titleKurdish = "ڕووداو پەخشی ڕاستەوخۆی جیهانی",
      subtitle = "Full HD Broadcast with Instant Low-Latency Server",
      quality = "FHD LOW-LATENCY",
      gradientStart = 0xFF8F1414,
      gradientEnd = 0xFF1C0608,
      media = liveTvChannels[0]
    )
  )
}
