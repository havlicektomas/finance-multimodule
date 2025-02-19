plugins {
    alias(libs.plugins.finance.jvm.library)
    alias(libs.plugins.finance.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)
}