// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}

// Root build.gradle.kts
subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
            configure<com.android.build.gradle.BaseExtension> {
                lintOptions {
                    isCheckReleaseBuilds = true
                    isAbortOnError = true
                    isIgnoreWarnings = false
                    enable.add("all")
                    disable.add("TypographyFractions")
                    disable.add("TypographyQuotes")
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        all {
                            it.jvmArgs("-XX:MaxHeapSize=512m")
                        }
                    }
                }
            }

            dependencies {
                // Add common test dependencies for all modules
                "testImplementation"(libs.junit)
                "testImplementation"(libs.mockito.core)
}
        }
    }
}