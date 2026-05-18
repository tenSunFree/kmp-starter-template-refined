package com.sun.kmpstartertemplaterefined.feature_auth_presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.*
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LoginCard
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LoginMode
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LoginTopBar
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LumaLangBlue
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LumaLangLoginBackground
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.LumaLangPink
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.RegisterCard
import com.sun.kmpstartertemplaterefined.feature_auth_presentation.components.SwitchLoginModeButton
import com.sun.kmpstartertemplaterefined.ui_utils.side_effects.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToMain: () -> Unit,
    loginViewModel: LoginViewModel = koinViewModel(),
    registerViewModel: RegisterViewModel = koinViewModel(),
) {
    val loginState by loginViewModel.state.collectAsState()
    val registerState by registerViewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var overlayState by remember { mutableStateOf(LoginOverlayState.None) }
    var loginMode by remember { mutableStateOf(LoginMode.Normal) }
    var companyNo by remember { mutableStateOf("") }
    var rememberPassword by remember { mutableStateOf(false) }

    // Use event-based page navigation instead of the isSuccess flag.
    ObserveAsEvents(flow = loginViewModel.uiEvents) { event ->
        when (event) {
            LoginEvents.NavigateToMain -> onNavigateToMain()
            is LoginEvents.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
        }
    }

    // Use the event to switch back to the login page, instead of using the isSuccess flag.
    ObserveAsEvents(flow = registerViewModel.uiEvents) { event ->
        when (event) {
            RegisterEvents.RegisterSuccess -> {
                overlayState = LoginOverlayState.Login
                loginMode = LoginMode.Normal
                registerViewModel.reset()
            }

            is RegisterEvents.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LumaLangBlue),
    ) {
        LumaLangLoginBackground()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 26.dp),
        ) {
            LoginTopBar(
                modifier = Modifier.align(Alignment.TopCenter),
                onMemberLoginClick = {
                    loginMode = LoginMode.Normal
                    overlayState = LoginOverlayState.Login
                },
            )
            when (overlayState) {
                LoginOverlayState.None -> {
                    Button(
                        onClick = { overlayState = LoginOverlayState.Register },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 32.dp)
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LumaLangPink,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(text = "免費加入", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }

                LoginOverlayState.Register -> {
                    RegisterCard(
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        email = registerState.email,
                        username = registerState.username,
                        password = registerState.password,
                        passwordVisible = registerState.passwordVisible,
                        phone = registerState.phone,
                        fullName = registerState.fullName,
                        selectedGender = registerState.gender,
                        otpCode = registerState.otpCode,
                        step = registerState.step,
                        isLoading = registerState.isLoading,
                        onEmailChange = { registerViewModel.onAction(RegisterAction.EmailChanged(it)) },
                        onUsernameChange = {
                            registerViewModel.onAction(
                                RegisterAction.UsernameChanged(
                                    it
                                )
                            )
                        },
                        onPasswordChange = {
                            registerViewModel.onAction(
                                RegisterAction.PasswordChanged(
                                    it
                                )
                            )
                        },
                        onTogglePasswordVisible = { registerViewModel.onAction(RegisterAction.TogglePasswordVisible) },
                        onPhoneChange = { registerViewModel.onAction(RegisterAction.PhoneChanged(it)) },
                        onFullNameChange = {
                            registerViewModel.onAction(
                                RegisterAction.FullNameChanged(
                                    it
                                )
                            )
                        },
                        onGenderSelect = {
                            registerViewModel.onAction(
                                RegisterAction.GenderChanged(
                                    it
                                )
                            )
                        },
                        onOtpCodeChange = {
                            registerViewModel.onAction(
                                RegisterAction.OtpCodeChanged(
                                    it
                                )
                            )
                        },
                        onVerifyOtpClick = { registerViewModel.onAction(RegisterAction.VerifyOtpClicked) },
                        onResendOtpClick = { registerViewModel.onAction(RegisterAction.ResendOtpClicked) },
                        onCloseClick = { overlayState = LoginOverlayState.None },
                        onSubmitClick = { registerViewModel.onAction(RegisterAction.SubmitClicked) },
                    )
                }

                LoginOverlayState.Login -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LoginCard(
                            loginMode = loginMode,
                            companyNo = companyNo,
                            account = loginState.email,
                            password = loginState.password,
                            passwordVisible = loginState.passwordVisible,
                            isLoading = loginState.isLoading,
                            rememberPassword = rememberPassword,
                            onCompanyNoChange = { companyNo = it },
                            onAccountChange = { loginViewModel.onAction(LoginAction.EmailChanged(it)) },
                            onPasswordChange = {
                                loginViewModel.onAction(
                                    LoginAction.PasswordChanged(
                                        it
                                    )
                                )
                            },
                            onRememberPasswordChange = { rememberPassword = it },
                            onPasswordVisibleChange = { loginViewModel.onAction(LoginAction.TogglePasswordVisible) },
                            onLoginClick = { loginViewModel.onAction(LoginAction.SubmitClicked) },
                            onCloseClick = { overlayState = LoginOverlayState.None },
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SwitchLoginModeButton(
                            loginMode = loginMode,
                            onClick = {
                                loginMode = when (loginMode) {
                                    LoginMode.Normal -> LoginMode.Enterprise
                                    LoginMode.Enterprise -> LoginMode.Normal
                                }
                            },
                        )
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}