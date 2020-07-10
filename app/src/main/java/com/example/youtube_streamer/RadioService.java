package com.example.youtube_streamer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.example.youtube_streamer.Config.STREAMING_URL;


public class RadioService extends Service implements AudioManager.OnAudioFocusChangeListener {

    private WifiManager.WifiLock mWiFiLock;
    private PowerManager.WakeLock mWakeLock;
    private AudioManager audioManager;
    private MediaSource mediaSource;
    private SimpleExoPlayer player;

    @Override
    public void onCreate() {
        audioManager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("playStop");
        if (input != null && input.equals("play")) {
            if (requestFocus()) {
                initPlayer();
                play();
            }
        } else {
            stop();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        stop();
        destroyPlayer();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            stop();
            System.exit(0);
        }
    }

    private boolean requestFocus() {
        return (audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
    }

    private void play() {
        player.setForegroundMode(true);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    private void stop() {
        player.setPlayWhenReady(false);
        player.stop();
    }

    private void initPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(getApplicationContext());
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(getApplicationContext(), "exoPlayerSample"));
        mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(STREAMING_URL));
        lockWiFi();
        lockCPU();
    }

    private void destroyPlayer() {
        unlockCPU();
        unlockWiFi();
    }

    private void lockCPU() {
        PowerManager mgr = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (mgr == null) {
            return;
        }
        mWakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getSimpleName());
        mWakeLock.acquire();
    }

    private void unlockCPU() {
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    private void lockWiFi() {
        ConnectivityManager connManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return;
        }
        NetworkInfo lWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (lWifi != null && lWifi.isConnected()) {
            WifiManager manager = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE));
            if (manager != null) {
                mWiFiLock = manager.createWifiLock(WifiManager.WIFI_MODE_FULL, RadioService.class.getSimpleName());
                mWiFiLock.acquire();
            }
        }
    }

    private void unlockWiFi() {
        if (mWiFiLock != null && mWiFiLock.isHeld()) {
            mWiFiLock.release();
            mWiFiLock = null;
        }
    }
}