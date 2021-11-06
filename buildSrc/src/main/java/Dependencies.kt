object Dependencies {
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidx_compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val androidx_compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val androidx_compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val androidx_compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val androidx_compose_ui_test_junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val androidx_activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val androidx_lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx}"
    const val androidx_navigation_compose = "androidx.navigation:navigation-compose:${Versions.navigation_compose}"
    const val androidx_ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    const val androidx_espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit = "junit:junit:${Versions.junit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val simple_xml = "org.simpleframework:simple-xml:${Versions.simple_xml}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val retrofit2_converter_simplexml= "com.squareup.retrofit2:converter-simplexml:${Versions.retrofit2}"
}

private object Versions {
    const val core_ktx = "1.6.0"
    const val appcompat = "1.3.1"
    const val compose = "1.0.1"
    const val activity_compose = "1.3.1"
    const val lifecycle_runtime_ktx = "2.3.1"
    const val navigation_compose = "2.4.0-alpha10"
    const val junit = "4.+"
    const val ext_junit = "1.1.3"
    const val espresso = "3.4.0"
    const val material = "1.4.0"
    const val hilt = "2.38.1"
    const val simple_xml = "2.7.1"
    const val retrofit2 = "2.9.0"
}