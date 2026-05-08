package com.sun.kmpstartertemplaterefined.feature_auth_data.remote

import com.sun.kmpstartertemplaterefined.feature_auth_data.remote.dto.RegisterRequestDto
import com.sun.kmpstartertemplaterefined.feature_auth_data.remote.dto.RegisterResponseDto

interface AuthRemoteDataSource {
    suspend fun register(request: RegisterRequestDto): RegisterResponseDto
}