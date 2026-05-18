plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    // from build logic
    alias(libs.plugins.kotlin.serialization)
    id(libs.plugins.build.common.get().pluginId)
    id(libs.plugins.build.koin.compose.get().pluginId)
    id(libs.plugins.build.compose.multiplatform.get().pluginId)
}

compose.resources {
    // Generate public Res class
    publicResClass = true
    // Specify package
    packageOfResClass = "com.sun.kmpstartertemplaterefined.starter_resources"
    // Automatically generated
    generateResClass = auto
    this.always
}

kotlin {
    androidLibrary {
        androidResources.enable = true
        namespace = "com.sun.kmpstartertemplaterefined.starter_resources"
        compileSdk {
            version = release(version = libs.versions.android.compileSdk.get().toInt())
        }
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
    }
    val xcfName = "starter:starterResourcesKit"
    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }
    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.starter.ui.components)
            }
        }
        androidMain {
            dependencies {}
        }
        iosMain {
            dependencies {}
        }
    }
}

// Task: Generate Compose Resource Accessors
val generateAccessors by tasks.registering {
    group = "resources"
    description =
        "Generates type-safe Compose Multiplatform resource accessors for the starter resources module"
    dependsOn(
        ":starter:resources:generateResourceAccessorsForCommonMain",
        ":starter:resources:generateResourceAccessorsForAndroidMain",
        ":starter:resources:generateResourceAccessorsForAppleMain",
        ":starter:resources:generateResourceAccessorsForIosArm64Main",
        ":starter:resources:generateResourceAccessorsForIosSimulatorArm64Main",
        ":starter:resources:generateResourceAccessorsForNativeMain"
    )
    doLast {
        println("✅ Compose resource accessors for starter resources module generated successfully!")
    }
}