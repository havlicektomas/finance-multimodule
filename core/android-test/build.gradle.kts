plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.android.junit5)
    alias(libs.plugins.finance.jvm.ktor)
}

android {
    namespace = "dev.havlicektomas.android_test"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.data)
    api(projects.core.test)

    implementation(libs.coroutines.test)
}