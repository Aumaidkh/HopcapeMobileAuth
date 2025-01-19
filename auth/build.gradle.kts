import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.mokkery)
    `maven-publish`
    alias(libs.plugins.vaniktechMavenPublish)
    alias(libs.plugins.dokka)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        publishLibraryVariants("release","debug")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "auth"
            isStatic = true
        }
    }
    tasks.dokkaHtml {
        outputDirectory.set(layout.buildDirectory.dir("documentation/html"))
    }
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.compose.svg)

            api(libs.datastore)
            api(libs.datastore.preferences)

            api(libs.multiplatform.settings.no.arg)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
    }
}

android {
    namespace = "com.hopcape.auth"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
mavenPublishing {
    coordinates(
        groupId = "io.github.aumaidkh",
        artifactId = "mobile-auth",
        version = "1.0.0-ALPHA_03"
    )

    pom{
        name.set("Mobile Auth Library")
        description.set("A Library for authentication in Android and iOS Apps")
        inceptionYear.set("2025")
        url.set("https://github.com/Aumaidkh/HopcapeMobileAuth")

        licenses {
            license{
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer{
                id.set("Aumaidkh")
                name.set("Murtaza Khursheed")
                email.set("aumaidm.m.c@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/Aumaidkh/HopcapeMobileAuth")
        }

    }

    // Configure publishing to maven central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable gpg signing for all publications
    signAllPublications()
}
