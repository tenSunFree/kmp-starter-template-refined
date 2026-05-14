package com.sun.kmpstartertemplaterefined.feature_lessons_domain.repositories

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.Lesson

interface LessonsRepository {
    suspend fun getLessons(): Result<List<Lesson>>
}