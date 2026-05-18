package com.sun.kmpstartertemplaterefined.feature_auth_presentation

sealed interface LoginEvents {
    data object NavigateToMain : LoginEvents
    data class ShowSnackbar(val message: String) : LoginEvents
}