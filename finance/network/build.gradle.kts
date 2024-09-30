plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.jvm.ktor)
}

android {
    namespace = "dev.havlicektomas.finance.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}