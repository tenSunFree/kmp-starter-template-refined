package com.sun.kmpstartertemplaterefined.feature_lessons_data.util

import io.ktor.client.plugins.logging.Logger

internal expect fun platformLog(tag: String, message: String)

internal object HttpLogger : Logger {
    private const val TAG = "LESSONS-HTTP"

    override fun log(message: String) {
        platformLog(TAG, message)
    }
}