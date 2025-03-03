plugins {
    alias(libs.plugins.finance.jvm.library)
    alias(libs.plugins.finance.jvm.junit5)
}
dependencies {
    implementation(projects.core.domain)
    implementation(projects.finance.domain)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.junit5.api)
    implementation(libs.coroutines.test)
}
