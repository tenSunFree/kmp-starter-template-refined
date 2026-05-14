package com.sun.kmpstartertemplaterefined.feature_lessons_domain.logics

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.Lesson
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.repositories.LessonsRepository

class GetLessonsLogic(
    private val repository: LessonsRepository,
) {
    suspend operator fun invoke(): Result<List<Lesson>> = repository.getLessons()
}