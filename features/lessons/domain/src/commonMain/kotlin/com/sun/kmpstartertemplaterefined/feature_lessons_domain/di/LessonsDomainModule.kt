package com.sun.kmpstartertemplaterefined.feature_lessons_domain.di

import com.sun.kmpstartertemplaterefined.feature_lessons_domain.logics.GetLessonDetailLogic
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.logics.GetLessonsLogic
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val lessonsDomainModule = module {
    singleOf(::GetLessonsLogic)
    singleOf(::GetLessonDetailLogic)
}