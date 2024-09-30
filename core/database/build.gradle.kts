plugins {
    alias(libs.plugins.finance.android.library)
    alias(libs.plugins.finance.android.room)
}

android {
    namespace = "dev.havlicektomas.core.database"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}