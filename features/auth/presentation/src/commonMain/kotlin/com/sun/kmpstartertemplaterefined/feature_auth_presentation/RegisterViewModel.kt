package com.sun.kmpstartertemplaterefined.feature_auth_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.RegisterUserLogic
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.RegisterParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserLogic: RegisterUserLogic,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun onAction(action: RegisterAction) {
        _state.value = when (action) {
            is RegisterAction.EmailChanged -> _state.value.copy(email = action.value)
            is RegisterAction.UsernameChanged -> _state.value.copy(username = action.value)
            is RegisterAction.PasswordChanged -> _state.value.copy(password = action.value)
            is RegisterAction.PhoneChanged -> _state.value.copy(phone = action.value)
            is RegisterAction.FullNameChanged -> _state.value.copy(fullName = action.value)
            is RegisterAction.GenderChanged -> _state.value.copy(gender = action.value)
            is RegisterAction.TogglePasswordVisible -> _state.value.copy(passwordVisible = !_state.value.passwordVisible)
            is RegisterAction.ErrorShown -> _state.value.copy(errorMessage = null)
            is RegisterAction.SubmitClicked -> {
                register()
                return
            }
        }
    }

    private fun register() {
        val s = _state.value
        _state.value = s.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val params = RegisterParams(
                email = s.email.trim(),
                username = s.username.trim(),
                password = s.password,
                fullName = s.fullName.trim(),
                phone = s.phone.trim(),
                gender = if (s.gender == "男") "male" else "female",
            )

            registerUserLogic(params)
                .onSuccess {
                    _state.value = RegisterState(isSuccess = true)
                }
                .onFailure { error ->
                    _state.value = s.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "註冊失敗，請稍後再試",
                    )
                }
        }
    }
}