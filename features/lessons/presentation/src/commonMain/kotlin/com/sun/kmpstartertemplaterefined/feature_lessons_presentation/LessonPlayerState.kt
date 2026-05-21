package com.sun.kmpstartertemplaterefined.feature_lessons_presentation

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.Caption
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.LessonDetail

data class LessonPlayerState(
    val isLoading: Boolean = false,
    val lessonDetail: LessonDetail? = null,
    val errorMessage: String? = null,
    val isPlaying: Boolean = false,
    val currentMs: Long = 0L,
    val seekToMs: Long? = null,
) {
    val durationMs: Long
        get() = lessonDetail?.playback?.durationMs ?: 0L

    val currentCaption: Caption?
        get() = lessonDetail?.captions?.firstOrNull { currentMs in it.startMs until it.endMs }
}