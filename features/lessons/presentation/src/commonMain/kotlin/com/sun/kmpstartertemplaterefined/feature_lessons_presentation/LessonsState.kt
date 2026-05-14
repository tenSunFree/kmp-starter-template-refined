package com.sun.kmpstartertemplaterefined.feature_lessons_presentation

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.Lesson

data class LessonsState(
    val isLoading: Boolean = false,
    val lessons: List<Lesson> = emptyList(),
    val errorMessage: String? = null,
)