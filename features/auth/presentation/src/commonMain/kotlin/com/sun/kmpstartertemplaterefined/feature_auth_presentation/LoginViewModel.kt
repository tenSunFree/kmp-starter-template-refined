package com.sun.kmpstartertemplaterefined.feature_auth_presentation

import androidx.lifecycle.viewModelScope
import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.LoginLogic
import com.sun.kmpstartertemplaterefined.feature_auth_domain.models.LoginParams
import com.sun.kmpstartertemplaterefined.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginLogic: LoginLogic,
) : MviViewModel<LoginState, LoginAction, LoginEvents>() {

    override val initialState get() = LoginState()

    override fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged ->
                _state.value = _state.value.copy(email = action.value)

            is LoginAction.PasswordChanged ->
                _state.value = _state.value.copy(password = action.value)

            LoginAction.TogglePasswordVisible ->
                _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)

            LoginAction.SubmitClicked -> login()
            LoginAction.ErrorShown ->
                _state.value = _state.value.copy(errorMessage = null)
        }
    }

    private fun login() {
        val s = _state.value
        _state.value = s.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            loginLogic(LoginParams(email = s.email.trim(), password = s.password))
                .onSuccess {
                    _state.value = _state.value.copy(isLoading = false)
                    emitEvent(LoginEvents.NavigateToMain)
                }
                .onFailure { error ->
                    val message = error.message ?: "登入失敗，請確認帳號密碼"
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = message,
                    )
                    emitEvent(LoginEvents.ShowSnackbar(message))
                }
        }
    }
}