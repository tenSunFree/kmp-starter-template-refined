package com.sun.kmpstartertemplaterefined.feature_auth_data.repositories

import com.sun.kmpstartertemplaterefined.feature_auth_data.mappers.toDomain
import com.sun.kmpstartertemplaterefined.feature_auth_data.mappers.toDto
import com.sun.kmpstartertemplaterefined.feature_auth_data.remote.AuthRemoteDataSource
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterParams
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterResult
import com.sun.kmpstartertemplaterefined.feature_auth_domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun register(params: RegisterParams): Result<RegisterResult> =
        runCatching {
            val response = remoteDataSource.register(params.toDto())
            if (!response.status) error(response.message.ifBlank { "註冊失敗" })
            response.toDomain()
        }
}