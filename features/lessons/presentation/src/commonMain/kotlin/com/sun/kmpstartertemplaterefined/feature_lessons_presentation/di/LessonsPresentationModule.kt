package com.sun.kmpstartertemplaterefined.feature_lessons_presentation.di

import com.sun.kmpstartertemplaterefined.feature_lessons_presentation.LessonsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val lessonsPresentationModule = module {
    viewModelOf(::LessonsViewModel)
}