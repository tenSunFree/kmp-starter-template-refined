package com.sun.kmpstartertemplaterefined.feature_auth_domain.models

data class RegisterParams(
    val email: String,
    val username: String,
    val password: String,
    val fullName: String,
    val phone: String,
    val gender: String,
)