package com.sun.kmpstartertemplaterefined.feature_auth_presentation

data class RegisterState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val phone: String = "",
    val fullName: String = "",
    val gender: String = "男",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)