package com.sun.kmpstartertemplaterefined.core.ui.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color definition (consistent with LoginColors)
private val Pink = Color(0xFFFF3F68)
private val TextDark = Color(0xFF4A4A4A)
private val TextGray = Color(0xFF777777)
private val BorderGray = Color(0xFFE5E5E5)
private val TabUnselected = Color(0xFFC5C5C5)

// MainScreen: The container for the entire main screen
@Composable
fun MainScreen() {
    var selectedBottomIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            MainBottomBar(
                selectedIndex = selectedBottomIndex,
                onTabSelected = { selectedBottomIndex = it },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            when (selectedBottomIndex) {
                0 -> EasyLearningScreen()
                1 -> EmptyPlaceholderScreen("認真學")
                2 -> EmptyPlaceholderScreen("複習")
                3 -> EmptyPlaceholderScreen("精讀收錄")
                4 -> EmptyPlaceholderScreen("更多")
            }
        }
    }
}

// BottomBar
private data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
private fun MainBottomBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    val items = listOf(
        BottomNavItem("輕鬆學", Icons.Filled.Headphones, Icons.Outlined.Headphones),
        BottomNavItem("認真學", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
        BottomNavItem("複習", Icons.Filled.LocalLibrary, Icons.Outlined.LocalLibrary),
        BottomNavItem("精讀收錄", Icons.Filled.Bookmark, Icons.Outlined.Bookmark),
        BottomNavItem("更多", Icons.Filled.Person, Icons.Outlined.Person),
    )
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(26.dp),
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Pink,
                    selectedTextColor = Pink,
                    unselectedIconColor = Color(0xFF888888),
                    unselectedTextColor = Color(0xFF888888),
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}

// EasyLearningScreen: Learn with ease
@Composable
private fun EasyLearningScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("直播室", "FunTV", "影片", "音樂", "童話", "專欄", "大補帖")
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopBar()
        // Swipeable Tab Row
        TabRow(
            tabs = tabs,
            selectedIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
        )
        // Tab content
        when (selectedTabIndex) {
            0 -> LiveStreamingTab()
            1 -> FunTvTab()
            2 -> VideoTab()
            3 -> EmptyPlaceholderScreen("音樂")
            4 -> EmptyPlaceholderScreen("童話")
            5 -> EmptyPlaceholderScreen("專欄")
        }
    }
}

// TopBar: Logo + Icon Column
@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "LumaLang",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark,
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = Icons.Filled.GridView,
            contentDescription = "課表",
            tint = Color(0xFF555555),
            modifier = Modifier.size(26.dp),
        )
        Spacer(modifier = Modifier.width(20.dp))
        // AI robot illustration
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA2B9)),
            contentAlignment = Alignment.Center,
        ) {
            Text("🤖", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(20.dp))
        // Headphone icon (blue)
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E9BEF)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Headphones,
                contentDescription = "耳機",
                tint = Color.White,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

// Customizable horizontally scrollable Tab Row (without Material TabRow,
// because there are many tabs and the width exceeds the screen width)
@Composable
private fun TabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedIndex == index
                Column(
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Pink else TabUnselected,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(52.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (isSelected) Pink else Color.Transparent),
                    )
                }
            }
        }
        HorizontalDivider(color = BorderGray, thickness = 0.8.dp)
    }
}

// Tab 1: Live Room
private data class LiveCourseUi(
    val teacherName: String,
    val title: String,
    val category: String,
    val level: String,
    val isRequired: Boolean,
    val scheduledTime: String,
    val emoji: String,
)

private val fakeLiveCourses = listOf(
    LiveCourseUi(
        teacherName = "KarolChin",
        title = "現在分詞和過去分詞解析",
        category = "語言學習 (Language Learning)",
        level = "A2",
        isRequired = true,
        scheduledTime = "預計2026/05/04 19:00 開始直播",
        emoji = "👩🏻",
    ),
    LiveCourseUi(
        teacherName = "MikeChang",
        title = "雅思寫作學術組-判斷圖表和主題 (Task1-Unit1)",
        category = "檢定班 (MEXAM)",
        level = "B1",
        isRequired = true,
        scheduledTime = "預計2026/05/04 20:00 開始直播",
        emoji = "👨🏻",
    ),
)

@Composable
private fun LiveStreamingTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp),
    ) {
        // Header column
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Live Streaming",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "ⓘ", fontSize = 18.sp, color = Color(0xFFAAAAAA))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Tune,
                contentDescription = "篩選",
                tint = Color(0xFF555555),
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        // No live stream notification at present
        Text(
            text = "目前沒有正在進行的直播，\n敬請期待即將開播的課程！",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = TextGray,
            fontSize = 17.sp,
            lineHeight = 28.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Coming soon Title
        Text(
            text = "Coming soon...",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark,
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = BorderGray)
        Spacer(modifier = Modifier.height(16.dp))
        // Course List
        fakeLiveCourses.forEach { course ->
            LiveCourseCard(course = course)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun LiveCourseCard(course: LiveCourseUi) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Teacher's headshot (fake image, will be replaced with AsyncImage later)
            Box(
                modifier = Modifier
                    .size(width = 120.dp, height = 150.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = course.emoji, fontSize = 52.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                // Teacher's Name + Required Course Badge + Grade
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = course.teacherName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        modifier = Modifier.weight(1f),
                    )
                    if (course.isRequired) {
                        Text(
                            text = "必修",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Pink)
                                .padding(horizontal = 7.dp, vertical = 3.dp),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                    Text(
                        text = course.level,
                        color = Pink,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Course Title
                Text(
                    text = course.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark,
                    lineHeight = 22.sp,
                )
                Spacer(modifier = Modifier.height(6.dp))
                // Classification
                Text(
                    text = course.category,
                    fontSize = 14.sp,
                    color = TextGray,
                )
                Spacer(modifier = Modifier.height(12.dp))
                // Textbook button
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, BorderGray),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Download,
                        contentDescription = null,
                        tint = TextDark,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Textbook",
                        color = TextDark,
                        fontSize = 15.sp,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        // Time + Reminder Button
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = course.scheduledTime,
                color = TextGray,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f),
            )
            Button(
                onClick = {},
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF444444),
                    contentColor = Color.White,
                ),
                modifier = Modifier.height(40.dp),
            ) {
                Text("提醒", fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider(color = BorderGray)
    }
}

// Tab 2：FunTV
private val funTvOptions = listOf(
    "News", "Music", "Video", "Trend",
    "Living", "Office", "Column", "Story",
)

@Composable
private fun FunTvTab() {
    // Preset to select the first 3
    val selected = remember { mutableStateListOf("News", "Music", "Video") }
    var rememberPrefs by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 24.dp),
    ) {
        funTvOptions.forEach { option ->
            FunTvOptionRow(
                title = option,
                isSelected = selected.contains(option),
                onClick = {
                    if (selected.contains(option)) selected.remove(option)
                    else selected.add(option)
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Remember preferences
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Checkbox(
                checked = rememberPrefs,
                onCheckedChange = { rememberPrefs = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Pink,
                    checkmarkColor = Color.White,
                ),
            )
            Text(
                text = "Remember preferences",
                color = TextGray,
                fontSize = 17.sp,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Play button
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Pink,
                contentColor = Color.White,
            ),
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Play",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun FunTvOptionRow(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(1.dp, BorderGray, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = TextGray,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f),
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Pink),
                contentAlignment = Alignment.Center,
            ) {
                Text("✓", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFFCCCCCC), CircleShape),
            )
        }
    }
}

// Tab 3: Video
private data class VideoUi(
    val title: String,
    val subtitle: String,
    val views: Int,
    val emoji: String,
    val tag: String = "中英字幕",
)

private val fakeVideos = listOf(
    VideoUi(
        title = "LumaLang Cinephile 電影迷 | Paterson 派特森",
        subtitle = "LumaLang | CINEPHILE  看電影學英文",
        views = 975,
        emoji = "🎬",
    ),
    VideoUi(
        title = "「不要為流量犧牲全部價值」想當YouTuber前，你必須知道的現實",
        subtitle = "LumaLang Chat",
        views = 576,
        emoji = "🎙️",
    ),
)

@Composable
private fun VideoTab() {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        // Search box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, BorderGray, RoundedCornerShape(10.dp))
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (searchText.isEmpty()) "Search..." else searchText,
                color = if (searchText.isEmpty()) Color(0xFFAAAAAA) else TextDark,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "搜尋",
                tint = Color(0xFF888888),
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        fakeVideos.forEach { video ->
            VideoCard(video = video)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun VideoCard(video: VideoUi) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Thumbnail area (later replaced with AsyncImage / Coil)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFF333333)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = video.emoji, fontSize = 64.sp)
            // Bottom subtitle overlay
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.55f))
                    .padding(vertical = 10.dp, horizontal = 12.dp),
            ) {
                Text(
                    text = video.subtitle,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Tags + Views
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = video.tag,
                color = Color(0xFF888888),
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFFF0F0F0))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "👁 ${video.views}次",
                color = Color(0xFF999999),
                fontSize = 13.sp,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Movie Title
        Text(
            text = video.title,
            color = TextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp,
        )
    }
}

// Empty screen (other unfinished Tabs/BottomBars)
@Composable
private fun EmptyPlaceholderScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$title\n（畫面開發中）",
            color = Color(0xFFAAAAAA),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
        )
    }
}