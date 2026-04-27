@file:Suppress("DEPRECATION")

package com.sun.kmpstartertemplaterefined.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sun.kmpstartertemplaterefined.App
import com.sun.kmpstartertemplaterefined.core.datastore.theme.ThemeDataStore
import com.sun.kmpstartertemplaterefined.core.events.enums.ThemeMode
import com.sun.kmpstartertemplaterefined.ui_utils.side_effects.ObserveAsEvents
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSideEffects()
            App()
        }
    }

    @Composable
    private fun AndroidSideEffects(
        themeDataStore: ThemeDataStore = koinInject(),
        systemUiController: SystemUiController = rememberSystemUiController(),
    ) {
        val isSystemInDarkTheme = isSystemInDarkTheme()
        ObserveAsEvents(
            flow = themeDataStore.themeMode
        ) { themeMode ->
            val darkIcons = when (themeMode) {
                ThemeMode.LIGHT -> true
                ThemeMode.DARK -> false
                ThemeMode.SYSTEM -> !isSystemInDarkTheme
            }
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = darkIcons
            )
        }
    }
}