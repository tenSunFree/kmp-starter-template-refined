package com.sun.kmpstartertemplaterefined.feature_lessons_data.remote

import com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.dto.LessonDetailResponseDto
import com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.dto.LessonsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LessonsRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : LessonsRemoteDataSource {

    override suspend fun getLessons(): LessonsResponseDto =
        httpClient.get("$baseUrl/lessons").body()

    override suspend fun getLessonDetail(lessonId: String): LessonDetailResponseDto =
        httpClient.get("$baseUrl/lessons/$lessonId").body()
}