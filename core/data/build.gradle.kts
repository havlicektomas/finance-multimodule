plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.jvm.ktor)
}

android {
    namespace = "dev.havlicektomas.core.data"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.timber)
    implementation(libs.bundles.koin)
}