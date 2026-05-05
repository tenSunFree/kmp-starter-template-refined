package com.sun.kmpstartertemplaterefined.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sun.kmpstartertemplaterefined.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSideEffects()
            App()
        }
    }

    @Composable
    private fun AndroidSideEffects() {
        val view = LocalView.current
        SideEffect {
            val window = (view.context as ComponentActivity).window
            // White as the default color for the entire screen (applies to dark backgrounds for Splash and Login).
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }
}