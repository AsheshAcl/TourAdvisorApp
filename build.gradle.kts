// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("plugin.serialization") version "1.9.22"
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("de.mannodermaus.android-junit5") version "1.9.3.0" apply false

}

buildscript {
/*    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }*/
}