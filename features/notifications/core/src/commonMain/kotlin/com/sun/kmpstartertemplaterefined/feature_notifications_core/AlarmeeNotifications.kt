package com.sun.kmpstartertemplaterefined.feature_notifications_core

import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration

expect fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration

object AppNotifications {
    const val CHANNEL_MAIN_ID = "kmp_starter_main_channel"
    const val CHANNEL_MAIN_NAME = "KMP Starter"
}