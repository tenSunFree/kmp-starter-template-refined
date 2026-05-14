package com.sun.kmpstartertemplaterefined.feature_lessons_data.di

import com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.LessonsRemoteDataSource
import com.sun.kmpstartertemplaterefined.feature_lessons_data.remote.LessonsRemoteDataSourceImpl
import com.sun.kmpstartertemplaterefined.feature_lessons_data.repositories.LessonsRepositoryImpl
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.repositories.LessonsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun lessonsDataModule(baseUrl: String) = module {

    single<LessonsRemoteDataSource> {
        LessonsRemoteDataSourceImpl(
            // Reuse an existing HttpClient created with auth
            httpClient = get(named("authHttpClient")),
            baseUrl = baseUrl,
        )
    }

    single<LessonsRepository> {
        LessonsRepositoryImpl(remoteDataSource = get())
    }
}