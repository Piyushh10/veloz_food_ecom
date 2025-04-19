import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.8.21"
    kotlin("kapt")
}
val localProperties =
    File(rootProject.rootDir, "local.properties").reader().use{
        Properties().apply { load(it) }
    }
val apiKey = localProperties.getProperty("API_KEY", "")
val paymentapiKey = localProperties.getProperty("PAYMENT_API_KEY", "")

android {
    namespace = "com.example.veloz_foodecom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.veloz_foodecom"
        minSdk = 24
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    configurations.all{
        resolutionStrategy{
            force("io.github.jan-tennert.supabase:gotrue-kt-android:1.3.0")

            exclude(group= "io.github.jan-tennert.supabase", module= "gotrue-kt-android-debug")
        }
    }
}

dependencies {


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("android.navigation:navigation-compose:2.6.0-alpha04")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.accompanist:accompanist-permission:0.30.1")
    implementation("io.github.jan-tennert.supabase:supabase-kt:1.3.0")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.3.0"){
        exclude(group= "io.github.jan-tennert.supabase", module= "gotrue-kt-android-debug")
    }
    implementation("io.github.jan-tennert.supabase:supabase-kt:1.3.0"){
        exclude(group="io.github.jan-tennert.supabase", module="gotrue-kt-debug")
    }
    implementation("com.github.skydoves:cloudy:0.1.2")
    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-cio:2.3.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
    implementation("com.razorpay:checkout:1.6.33")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("com.google.firebase:firebase-appcheck-safetynet:16.1.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")
    implementation("androidx.compose.ui:ui-text-google-fonts")
    //implementation(libs.androidx.constraintlayout.compose)
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    implementation("androidx.compose.runtime:runtime")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}