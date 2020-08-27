# TV-Radio-Stream-Player
Android java youtube TV live stream player and an exoplayer radio player from url

## TV Player
- TV feed is taken from youtube.
- YoutubeAndroidPlayerAPI is used.
- Google play console account is required.
- enable Youtube Data API and add the project.
- Get the API key.
- API key has to be placed in config.java
- Video code also placed in the COnfig.java
- donwload the latest version of the YouTube Android Player API from  https://developers.google.com/youtube/android/player/downloads/
- Extract and copy the jar file to libs folder

## Radio Player
Radio url is placed in the config file.
Dependancies used
- com.github.wseemann:FFmpegMediaMetadataRetriever
- Exoplayer

## Configurations

### AndroidManifest.xml

- permissions

```html
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
- http.legacy
```html
    <uses-library
        android:name="org.apache.http.legacy"
        android:required="false" />
```

### build.gradle(app)

- Add Compile Options
<pre>
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

</pre>

- Dependancies
<pre>
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    compile files('libs/YouTubeAndroidPlayerApi.jar')
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.google.android.exoplayer:exoplayer:2.10.4'
    implementation 'com.github.wseemann:FFmpegMediaMetadataRetriever:1.0.14'
}
</pre>

### Config.java

- Replace YOUTUBE_API_KEY with the generated api key.

## Demo

![Main Screen](/images/Main.jpg)


![TV Screen](/images/tv.jpg)


![Radio Screen](/images/radio.jpg)
