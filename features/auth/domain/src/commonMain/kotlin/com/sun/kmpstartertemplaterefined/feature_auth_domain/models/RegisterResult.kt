package com.sun.kmpstartertemplaterefined.feature_auth_domain.models

data class RegisterResult(
    val userId: String,
    val username: String,
    val email: String,
    val fullName: String,
    val phone: String,
    val gender: String,
    val message: String,
)