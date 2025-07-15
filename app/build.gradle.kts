plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler) // Применяем плагин компилятора Compose
    alias(libs.plugins.google.ksp)       // Применяем плагин KSP
}

android {
    namespace = "com.example.incomeexpensesapplication"
    compileSdk = 35 // Убедитесь, что это последняя стабильная версия или та, что вам нужна

    defaultConfig {
        applicationId = "com.example.incomeexpensesapplication"
        minSdk = 21 // или выше, если требуется для библиотек
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner" // Хорошо бы добавить для тестов
    }

    buildFeatures {
        compose = true
    }

    // Compose Options могут быть здесь, если нужны специфичные настройки
    // composeOptions {
    //     kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() // Если вы НЕ используете compose.compiler плагин, а старый способ
    // }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    // packagingOptions { // Могут понадобиться для исключения дубликатов файлов
    //     resources {
    //         excludes += "/META-INF/{AL2.0,LGPL2.1}"
    //     }
    // }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation
    val nav_version = "2.9.1"
    implementation("androidx.navigation:navigation-compose:$nav_version")

}


