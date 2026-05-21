package com.sun.kmpstartertemplaterefined.core.ui.screens.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun YouTubePlayerBox(
    youtubeVideoId: String,
    isPlaying: Boolean,
    seekToMs: Long?,
    modifier: Modifier,
) {
    // This is a temporary placeholder for iOS; it can be integrated with WKWebView or youtube-ios-player-helper later.
    Box(modifier = modifier.background(Color.Black))
}