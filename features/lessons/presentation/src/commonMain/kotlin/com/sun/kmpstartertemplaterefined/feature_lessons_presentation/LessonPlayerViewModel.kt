package com.sun.kmpstartertemplaterefined.feature_lessons_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.kmpstartertemplaterefined.feature_lessons_domain.logics.GetLessonDetailLogic
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LessonPlayerViewModel(
    private val getLessonDetailLogic: GetLessonDetailLogic,
) : ViewModel() {

    private val _state = MutableStateFlow(LessonPlayerState())
    val state = _state.asStateFlow()
    private var progressJob: Job? = null

    fun onAction(action: LessonPlayerAction) {
        when (action) {
            is LessonPlayerAction.Load -> load(action.lessonId)
            LessonPlayerAction.PlayPauseClicked -> togglePlayPause()
            is LessonPlayerAction.SeekTo -> seekTo(action.positionMs)
            LessonPlayerAction.ErrorShown -> _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun load(lessonId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true, errorMessage = null, currentMs = 0L, isPlaying = false
                )
            }
            getLessonDetailLogic(lessonId).onSuccess { detail ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        lessonDetail = detail,
                        currentMs = detail.playback.startAtMs,
                    )
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(isLoading = false, errorMessage = e.message ?: "取得影片詳情失敗")
                }
            }
        }
    }

    private fun togglePlayPause() {
        val nextPlaying = !_state.value.isPlaying
        _state.update { it.copy(isPlaying = nextPlaying) }
        if (nextPlaying) startProgressTimer() else stopProgressTimer()
    }

    private fun startProgressTimer() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            while (true) {
                delay(500)
                val current = _state.value
                if (!current.isPlaying) break
                val nextMs = (current.currentMs + 500).coerceAtMost(current.durationMs)
                _state.update { it.copy(currentMs = nextMs) }
                if (nextMs >= current.durationMs) {
                    _state.update { it.copy(isPlaying = false) }
                    break
                }
            }
        }
    }

    private fun stopProgressTimer() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun seekTo(positionMs: Long) {
        _state.update {
            val safePos = positionMs.coerceIn(0L, it.durationMs)
            it.copy(
                currentMs = safePos,
                seekToMs = safePos,
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopProgressTimer()
    }
}