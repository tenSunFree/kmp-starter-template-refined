plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.serialization)
    id(libs.plugins.build.koin.compose.get().pluginId)
    id(libs.plugins.build.compose.multiplatform.get().pluginId)
    id(libs.plugins.build.common.get().pluginId)
}

val packageName = "com.sun.kmpstartertemplaterefined.feature_auth_presentation"

compose.resources {
    generateResClass = never
}

kotlin {
    androidLibrary {
        namespace = packageName
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.auth.domain)
            implementation(projects.starter.ui.utils)
            implementation(projects.starter.resources)
        }
        androidMain.dependencies {}
        iosMain.dependencies {}
    }
}