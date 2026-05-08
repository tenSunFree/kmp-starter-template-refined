package com.sun.kmpstartertemplaterefined.feature_auth_data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val status: Boolean,
    val message: String,
    val data: RegisterDataDto? = null,
)

@Serializable
data class RegisterDataDto(
    val user: RegisterUserDto? = null,
)

@Serializable
data class RegisterUserDto(
    val id: String,
    val username: String,
    @SerialName("full_name") val fullName: String,
    val email: String,
    val phone: String,
    val gender: String,
    @SerialName("role_id") val roleId: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String? = null,
)