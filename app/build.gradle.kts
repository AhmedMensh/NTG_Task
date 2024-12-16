import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.androidx.navigation.safeargs)
    kotlin("kapt")
    id("kotlin-parcelize")

}
val localProperties = com.android.utils.FileUtils.join(rootDir, "local.properties")
val properties = Properties().apply { load(localProperties.inputStream()) }
android {
    namespace = "com.example.ntg_task"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ntg_task"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("base_url")
            )
            buildConfigField(
                "String",
                "PUBLIC_KEY",
                properties.getProperty("publik_key")
            )
            buildConfigField(
                "String",
                "PRIVATE_KEY",
                properties.getProperty("private_key")
            )
        }
        debug {
            android.buildFeatures.buildConfig = true
            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("base_url")
            )
            buildConfigField(
                "String",
                "PUBLIC_KEY",
                properties.getProperty("publik_key")
            )
            buildConfigField(
                "String",
                "PRIVATE_KEY",
                properties.getProperty("private_key")
            )
        }
    }
    viewBinding {
        enable = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.paging3)
    implementation(libs.glide)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}