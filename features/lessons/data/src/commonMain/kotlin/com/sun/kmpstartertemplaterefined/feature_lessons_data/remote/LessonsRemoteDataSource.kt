package com.sun.kmpstartertemplaterefined.feature_lessons_data.remote

import com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.dto.LessonsResponseDto

interface LessonsRemoteDataSource {
    suspend fun getLessons(): LessonsResponseDto
}