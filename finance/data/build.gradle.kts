plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.jvm.ktor)
}

android {
    namespace = "dev.havlicektomas.finance.data"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.finance.domain)

    implementation(libs.bundles.koin)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}