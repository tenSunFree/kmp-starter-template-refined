package com.sun.kmpstartertemplaterefined.feature_auth_presentation

// What cards should be displayed on the control screen
internal enum class LoginOverlayState {
    None,   // Initial screen: only background + free join button
    Register,   // Display RegisterCard
    Login,  // Display LoginCard + SwitchLoginModeButton
}