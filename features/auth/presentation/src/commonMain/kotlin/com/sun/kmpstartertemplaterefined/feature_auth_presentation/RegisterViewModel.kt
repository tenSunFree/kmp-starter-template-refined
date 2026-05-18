package com.sun.kmpstartertemplaterefined.feature_auth_presentation

import androidx.lifecycle.viewModelScope
import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.RegisterUserLogic
import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.SendOtpLogic
import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.VerifyOtpLogic
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterParams
import com.sun.kmpstartertemplaterefined.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserLogic: RegisterUserLogic,
    private val sendOtpLogic: SendOtpLogic,
    private val verifyOtpLogic: VerifyOtpLogic,
) : MviViewModel<RegisterState, RegisterAction, RegisterEvents>() {

    override val initialState get() = RegisterState()

    override fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.EmailChanged ->
                _state.value = _state.value.copy(email = action.value)

            is RegisterAction.UsernameChanged ->
                _state.value = _state.value.copy(username = action.value)

            is RegisterAction.PasswordChanged ->
                _state.value = _state.value.copy(password = action.value)

            is RegisterAction.PhoneChanged ->
                _state.value = _state.value.copy(phone = action.value)

            is RegisterAction.FullNameChanged ->
                _state.value = _state.value.copy(fullName = action.value)

            is RegisterAction.GenderChanged ->
                _state.value = _state.value.copy(gender = action.value)

            is RegisterAction.OtpCodeChanged -> {
                val filtered = action.value.filter { it.isDigit() }.take(6)
                _state.value = _state.value.copy(otpCode = filtered)
            }

            RegisterAction.TogglePasswordVisible ->
                _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)

            RegisterAction.SubmitClicked -> registerAndSendOtp()
            RegisterAction.VerifyOtpClicked -> verifyOtp()
            RegisterAction.ResendOtpClicked -> resendOtp()
            RegisterAction.ErrorShown ->
                _state.value = _state.value.copy(errorMessage = null)
        }
    }

    private fun registerAndSendOtp() {
        val s = _state.value
        _state.value = s.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            val email = s.email.trim()
            registerUserLogic(
                RegisterParams(
                    email = email,
                    username = s.username.trim(),
                    password = s.password,
                    fullName = s.fullName.trim(),
                    phone = s.phone.trim(),
                    gender = if (s.gender == "男") "male" else "female",
                )
            ).onSuccess {
                sendOtpLogic(email)
                    .onSuccess {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            step = RegisterStep.OTP,
                            otpCode = "",
                        )
                    }
                    .onFailure { error ->
                        val msg = error.message ?: "驗證碼發送失敗，請稍後再試"
                        _state.value = _state.value.copy(isLoading = false, errorMessage = msg)
                        emitEvent(RegisterEvents.ShowSnackbar(msg))
                    }
            }.onFailure { error ->
                val msg = error.message ?: "註冊失敗，請稍後再試"
                _state.value = s.copy(isLoading = false, errorMessage = msg)
                emitEvent(RegisterEvents.ShowSnackbar(msg))
            }
        }
    }

    private fun resendOtp() {
        val email = _state.value.email.trim()
        _state.value = _state.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            sendOtpLogic(email)
                .onSuccess {
                    _state.value = _state.value.copy(isLoading = false, otpCode = "")
                }
                .onFailure { error ->
                    val msg = error.message ?: "重新發送驗證碼失敗"
                    _state.value = _state.value.copy(isLoading = false, errorMessage = msg)
                    emitEvent(RegisterEvents.ShowSnackbar(msg))
                }
        }
    }

    private fun verifyOtp() {
        val s = _state.value
        _state.value = s.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            verifyOtpLogic(email = s.email.trim(), code = s.otpCode)
                .onSuccess {
                    _state.value = _state.value.copy(isLoading = false)
                    emitEvent(RegisterEvents.RegisterSuccess)
                }
                .onFailure { error ->
                    val msg = error.message ?: "驗證碼錯誤，請重新確認"
                    _state.value = s.copy(isLoading = false, errorMessage = msg)
                    emitEvent(RegisterEvents.ShowSnackbar(msg))
                }
        }
    }

    fun reset() {
        _state.value = RegisterState()
    }
}