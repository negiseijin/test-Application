@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 33
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//val ktlint by configurations.creating

dependencies {
//    ktlint("com.pinterest.ktlint:ktlint-cli:1.0.1") {
//        attributes {
//            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
//        }
//    }
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

//val ktlintCheck by tasks.registering(JavaExec::class) {
//    group = LifecycleBasePlugin.VERIFICATION_GROUP
//    description = "Check Kotlin code style"
//    classpath = ktlint
//    mainClass.set("com.pinterest.ktlint.Main")
//    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
//    args(
//        "**/src/**/*.kt",
//        "**.kts",
//        "!**/build/**",
//    )
//}
//
//tasks.check {
//    dependsOn(ktlintCheck)
//}
//
//tasks.register<JavaExec>("ktlintFormat") {
//    group = LifecycleBasePlugin.VERIFICATION_GROUP
//    description = "Check Kotlin code style and format"
//    classpath = ktlint
//    mainClass.set("com.pinterest.ktlint.Main")
//    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
//    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
//    args(
//        "-F",
//        "**/src/**/*.kt",
//        "**.kts",
//        "!**/build/**",
//    )
//}