plugins {
    alias(libs.plugins.finance.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}