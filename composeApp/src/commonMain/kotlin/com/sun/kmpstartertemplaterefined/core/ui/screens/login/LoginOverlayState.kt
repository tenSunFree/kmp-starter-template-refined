package com.sun.kmpstartertemplaterefined.core.ui.screens.login

// What cards should be displayed on the control screen
internal enum class LoginOverlayState {
    None,   // Initial screen: only background + free join button
    Register,   // Display RegisterCard
    Login,  // Display LoginCard + SwitchLoginModeButton
}