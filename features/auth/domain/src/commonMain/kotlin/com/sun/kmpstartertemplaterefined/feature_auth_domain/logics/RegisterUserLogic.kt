package com.sun.kmpstartertemplaterefined.feature_auth_domain.logics

import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterParams
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterResult
import com.sun.kmpstartertemplaterefined.feature_auth_domain.repositories.AuthRepository

class RegisterUserLogic(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(params: RegisterParams): Result<RegisterResult> {
        if (params.email.isBlank()) return Result.failure(IllegalArgumentException("請輸入 Email"))
        if (params.username.isBlank()) return Result.failure(IllegalArgumentException("請輸入帳號"))
        if (params.password.isBlank()) return Result.failure(IllegalArgumentException("請輸入密碼"))
        if (params.fullName.isBlank()) return Result.failure(IllegalArgumentException("請輸入姓名"))
        if (params.phone.isBlank()) return Result.failure(IllegalArgumentException("請輸入手機號碼"))
        return authRepository.register(params)
    }
}