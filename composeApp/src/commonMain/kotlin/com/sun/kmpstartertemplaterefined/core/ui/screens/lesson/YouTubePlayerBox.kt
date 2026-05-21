package com.sun.kmpstartertemplaterefined.core.ui.screens.lesson

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun YouTubePlayerBox(
    youtubeVideoId: String,
    isPlaying: Boolean,
    seekToMs: Long?,
    modifier: Modifier = Modifier,
)