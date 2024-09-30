plugins {
    alias(libs.plugins.finance.android.feature.ui)
}

android {
    namespace = "dev.havlicektomas.finance.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.finance.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}