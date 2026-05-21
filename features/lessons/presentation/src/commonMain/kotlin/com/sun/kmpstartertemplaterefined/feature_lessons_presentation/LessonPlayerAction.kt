package com.sun.kmpstartertemplaterefined.feature_lessons_presentation

sealed interface LessonPlayerAction {
    data class Load(val lessonId: String) : LessonPlayerAction
    data object PlayPauseClicked : LessonPlayerAction
    data class SeekTo(val positionMs: Long) : LessonPlayerAction
    data object ErrorShown : LessonPlayerAction
}