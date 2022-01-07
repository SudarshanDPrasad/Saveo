# Saveo

This project is used by the following companies:

* GitHub
* Recycler View
* View Pager
* Retrofit Library
* API
* MVVM Model 

# Tech Stack ✨

* [Android Studio](https://developer.android.com/studio)
* [Kotlin](https://kotlinlang.org/)

# Dependencies

    //Anko
    implementation "org.jetbrains.anko:anko-commons:$anko_version"

    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0-alpha03'

    //Retrofit
    def retrofit2_version = "2.9.0"
    def okhttp3_version = "4.9.0"
    
     //retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"

    //Okhttp3
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")


    annotationProcessor "com.google.dagger:dagger-android-processor:2.11"
