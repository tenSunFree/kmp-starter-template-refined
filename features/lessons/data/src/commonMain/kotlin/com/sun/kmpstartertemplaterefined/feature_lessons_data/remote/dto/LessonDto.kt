package com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LessonsResponseDto(
    val data: Map<String, List<LessonDto>> = emptyMap(),
    val message: String = "",
    val status: Boolean = false,
)

@Serializable
data class LessonDto(
    val id: String = "",
    val category: String = "",
    val coverUrl: String = "",
    val createdAt: String = "",
    val description: String = "",
    val durationMs: Long = 0L,
    val isFree: Boolean = false,
    val level: String = "",
    val subtitle: String = "",
    val tags: List<String> = emptyList(),
    val title: String = "",
    val updatedAt: String = "",
    val viewCount: Int = 0,
)