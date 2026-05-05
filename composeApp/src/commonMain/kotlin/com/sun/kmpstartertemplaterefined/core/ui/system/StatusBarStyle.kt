package com.sun.kmpstartertemplaterefined.core.ui.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun SetStatusBarStyle(
    color: Color = Color.Transparent,
    darkIcons: Boolean,
)