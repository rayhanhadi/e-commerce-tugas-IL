plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.e_commerce"
    compileSdk = 35
    // Ubah ke 34 karena 35 belum dirilis secara stabil

    defaultConfig {
        applicationId = "com.example.e_commerce"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // Ubah ke 17 untuk kompatibilitas terbaik
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"  // Sesuaikan dengan compileOptions
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"  // Tambahkan versi compiler compose
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.navigation:navigation-compose:2.7.3")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)

    // Debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    debugImplementation(libs.androidx.ui.tooling)
    implementation("androidx.compose.material:material-icons-core:1.7.5")
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    debugImplementation(libs.androidx.ui.test.manifest)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}