# TV-Radio-Stream-Player
Android java youtube TV live stream player and an exoplayer radio player from url

## TV Player
- TV feed is taken from youtube.
- YoutubeAndroidPlayerAPI is used.
- API key has to be placed in config.java
- Video code also placed in the COnfig.java


## Radio Player
Radio url is placed in the config file.
Dependancies used
- com.github.wseemann:FFmpegMediaMetadataRetriever
- Exoplayer

## Configurations

### AndroidManifest.xml

- permissions
<code>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
</code>

