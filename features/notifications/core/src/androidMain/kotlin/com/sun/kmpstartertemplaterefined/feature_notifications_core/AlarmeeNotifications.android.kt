package com.sun.kmpstartertemplaterefined.feature_notifications_core

import android.app.NotificationManager
import com.tweener.alarmee.channel.AlarmeeNotificationChannel
import com.tweener.alarmee.configuration.AlarmeeAndroidPlatformConfiguration
import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration
import com.sun.kmpstartertemplaterefined.starter_resources.R

actual fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration {
    return AlarmeeAndroidPlatformConfiguration(
        notificationIconResId = R.drawable.ic_notification,
        notificationChannels = listOf(
            AlarmeeNotificationChannel(
                id = AppNotifications.CHANNEL_MAIN_ID,
                name = AppNotifications.CHANNEL_MAIN_NAME,
                importance = NotificationManager.IMPORTANCE_HIGH
            ),
        )
    )
}