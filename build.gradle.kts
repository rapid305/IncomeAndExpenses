// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false // Для компилятора Compose
    alias(libs.plugins.google.ksp) apply false      // Для KSP
    // alias(libs.plugins.jetbrainsCompose) apply false // Оставьте, если используете Compose Multiplatform, иначе можно удалить
}
