package com.sun.kmpstartertemplaterefined.feature_lessons_presentation

sealed interface LessonsAction {
    data object LoadLessons : LessonsAction
    data object RetryClicked : LessonsAction
    data object ErrorShown : LessonsAction
}