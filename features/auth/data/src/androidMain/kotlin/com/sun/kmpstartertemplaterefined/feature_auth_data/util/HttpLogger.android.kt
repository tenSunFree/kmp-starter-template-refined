package com.sun.kmpstartertemplaterefined.feature_auth_data.util

import android.util.Log

internal actual fun platformLog(tag: String, message: String) {
    Log.d(tag, message)
}