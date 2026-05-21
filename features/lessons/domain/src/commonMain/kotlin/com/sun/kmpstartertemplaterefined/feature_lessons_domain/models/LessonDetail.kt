package com.sun.kmpstartertemplaterefined.feature_lessons_domain.models

data class LessonDetail(
    val lesson: Lesson,
    val playback: Playback,
    val captionsVersion: Int,
    val captions: List<Caption>,
    val vocabularyItems: List<VocabularyItem>,
)

data class Playback(
    val videoProvider: String,
    val youtubeVideoId: String,
    val videoUrl: String,
    val hlsUrl: String,
    val durationMs: Long,
    val startAtMs: Long,
    val allowSeek: Boolean,
    val allowPlaybackSpeed: Boolean,
)

data class Caption(
    val id: String,
    val sortOrder: Int,
    val startMs: Long,
    val endMs: Long,
    val textEn: String,
    val textZhTw: String,
)

data class VocabularyItem(
    val id: String,
    val captionId: String,
    val startMs: Long,
    val endMs: Long,
    val phrase: String,
    val definitionEn: String,
    val definitionZhTw: String,
    val noteZhTw: String,
    val level: String,
    val examples: List<VocabularyExample>,
)

data class VocabularyExample(
    val en: String,
    val zhTw: String,
)