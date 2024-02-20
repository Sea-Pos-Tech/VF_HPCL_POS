plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.hpcl_paytm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hpcl_paytm"
        minSdk = 24
        targetSdk = 34
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
    productFlavors {
        this.create("dev") {
            dimension = "distribution"
            buildConfigField("String", "MAIN_URL", "\"https://posapi.drivetrackplus.com\"")
            buildConfigField("String", "HP_PAY", "\"https://customerapi.hppay.in\"")
            buildConfigField("String", "API_Key", "\"3C25F265-F86D-419D-9A04-EA74A503C197\"")
            buildConfigField(
                "String",
                "Secret_Key",
                "\"PVmMSclp834KBIUa9O-XxpBsDJhsi1dsds74CiGaoo5\""
            )
        }
        this.create("uat") {
            dimension = "distribution"
            buildConfigField("String", "MAIN_URL", "\"https://posapi.drivetrackplus.com\"")
            buildConfigField("String", "HP_PAY", "\"https://customerapi.hppay.in\"")
            buildConfigField("String", "API_Key", "\"3C25F265-F86D-419D-9A04-EA74A503C197\"")
            buildConfigField(
                "String",
                "Secret_Key",
                "\"PVmMSclp834KBIUa9O-XxpBsDJhsi1dsds74CiGaoo5\""
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
        aidl = true
    }


    sourceSets.getByName("main") {
        java.srcDirs("src/main/java", "src/main/aidl")
    }


}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /*************Dagger - Hilt ********************/
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    /***********Glide***************/
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.android.support:support-annotations:28.0.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    /*************navigation graph ********************/
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")


    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    /*************Retrofit ********************/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    /*************Coroutines ********************/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    /****************for piview**********************/
    implementation("io.github.chaosleung:pinview:1.4.4")

    /*******************qrcode generator**************************/
    implementation("com.google.zxing:core:3.4.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    implementation("com.intuit.sdp:sdp-android:1.0.6")
    // For MasterKey
    implementation("androidx.security:security-crypto:1.1.0-alpha01")
    // For EncryptedSharedPreferences
    implementation("androidx.security:security-crypto:1.0.0")

}