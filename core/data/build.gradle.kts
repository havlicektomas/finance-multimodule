plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.jvm.ktor)
    alias(libs.plugins.finance.android.room)
}

android {
    namespace = "dev.havlicektomas.core.data"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.timber)
    implementation(libs.bundles.koin)
    // Crypto
    implementation(libs.androidx.security.crypto.ktx)
}