plugins {
    alias(libs.plugins.finance.android.library)
}

android {
    namespace = "dev.havlicektomas.finance.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}