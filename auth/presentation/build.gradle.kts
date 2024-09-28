plugins {
    alias(libs.plugins.finance.android.feature.ui)
}

android {
    namespace = "dev.havlicektomas.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}