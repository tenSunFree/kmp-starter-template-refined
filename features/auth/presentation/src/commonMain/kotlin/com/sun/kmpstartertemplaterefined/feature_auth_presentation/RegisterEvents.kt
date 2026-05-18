package com.sun.kmpstartertemplaterefined.feature_auth_presentation

sealed interface RegisterEvents {
    // Notify LoginScreen to switch back to login
    data object RegisterSuccess : RegisterEvents
    data class ShowSnackbar(val message: String) : RegisterEvents
}