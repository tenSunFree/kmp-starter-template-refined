package com.sun.kmpstartertemplaterefined.feature_lessons_domain.logics

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.models.LessonDetail
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.repositories.LessonsRepository

class GetLessonDetailLogic(
    private val repository: LessonsRepository,
) {
    suspend operator fun invoke(lessonId: String): Result<LessonDetail> =
        repository.getLessonDetail(lessonId)
}