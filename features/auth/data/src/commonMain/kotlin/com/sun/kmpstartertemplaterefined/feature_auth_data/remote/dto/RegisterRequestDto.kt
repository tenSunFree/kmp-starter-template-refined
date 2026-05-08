package com.sun.kmpstartertemplaterefined.feature_auth_data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val email: String,
    @SerialName("full_name") val fullName: String,
    val gender: String,
    val password: String,
    val phone: String,
    val username: String,
)