package com.sun.kmpstartertemplaterefined.feature_auth_presentation

enum class RegisterStep {
    FORM,   // Display registration form
    OTP,    // Display OTP verification screen
}

data class RegisterState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val phone: String = "",
    val fullName: String = "",
    val gender: String = "男",
    val passwordVisible: Boolean = false,
    val otpCode: String = "",
    val step: RegisterStep = RegisterStep.FORM,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)