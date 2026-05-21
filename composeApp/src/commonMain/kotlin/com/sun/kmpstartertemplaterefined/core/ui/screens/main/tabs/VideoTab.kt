package com.sun.kmpstartertemplaterefined.core.ui.screens.main.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sun.kmpstartertemplaterefined.core.ui.screens.main.tabs.shared.TabSearchBar
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.Lesson
import com.sun.kmpstartertemplaterefined.feature_lessons_presentation.LessonsAction
import com.sun.kmpstartertemplaterefined.feature_lessons_presentation.LessonsViewModel
import com.sun.kmpstartertemplaterefined.feature_navigation.StarterNavigator
import com.sun.kmpstartertemplaterefined.feature_navigation.screens.StarterScreens
import com.sun.kmpstartertemplaterefined.ui_components.image.CoilImage
import org.koin.compose.viewmodel.koinViewModel

private val TextDark = Color(0xFF4A4A4A)

@Composable
fun VideoTab(
    viewModel: LessonsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    // Automatic loading upon entering the screen
    LaunchedEffect(Unit) {
        viewModel.onAction(LessonsAction.LoadLessons)
    }
    // Display errors using a Snackbar, then clear them after displaying.
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.onAction(LessonsAction.ErrorShown)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            TabSearchBar()
            Spacer(modifier = Modifier.height(20.dp))
            when {
                // Loading for the first time
                state.isLoading && state.lessons.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
                // Loaded successfully but no data (or empty after error)
                state.lessons.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "目前沒有影片",
                            color = TextDark,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { viewModel.onAction(LessonsAction.RetryClicked) }) {
                            Text("重新載入")
                        }
                    }
                }
                // Information available
                else -> {
                    val navigator = StarterNavigator.getCurrent()

                    state.lessons.forEach { lesson ->
                        VideoCard(
                            lesson = lesson,
                            onClick = { selected ->
                                navigator.navigateTo(StarterScreens.LessonPlayer(lessonId = selected.id))
                            }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun VideoCard(lesson: Lesson, onClick: (Lesson) -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth().clickable { onClick(lesson) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFEFEFEF)),
        ) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                url = lesson.coverUrl,
                contentDescription = lesson.title,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.55f))
                    .padding(vertical = 10.dp, horizontal = 12.dp),
            ) {
                Text(
                    text = lesson.subtitle,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = lesson.tags.firstOrNull() ?: lesson.level,
                color = Color(0xFF888888),
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFFF0F0F0))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "👁 ${lesson.viewCount}次",
                color = Color(0xFF999999),
                fontSize = 13.sp,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = lesson.title,
            color = TextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp,
        )
    }
}