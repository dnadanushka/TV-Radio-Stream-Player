package com.example.youtube_streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

public class Mp3PlayerActivity extends AppCompatActivity {


    ImageButton buttonPlay;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_player);

        ImageButton buttonPlay = (ImageButton) findViewById(R.id.btnPlay);

        //        String filePath = Environment.getExternalStorageDirectory()+
        //                "/New Folder/ztz_3_adding.mp3";
        //        String filePath = "/storage/emulated/0/New Folder/ztz_3_adding.mp3";
        Log.e("Dir", Environment.getExternalStorageDirectory().toString());
        Log.e("Directory", Environment.getExternalStorageDirectory().getPath());

        mediaPlayer = new  MediaPlayer();
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            // mediaPlayer.setDataSource("/storage/emulated/0/New Folder/ztz_3_adding.mp3");
            // mediaPlayer.setDataSource("file://storage/emulated/0/New Folder/ztz_3_adding.mp3");
            mediaPlayer.setDataSource(getApplicationContext(),
                    Uri.parse(Environment.getExternalStorageDirectory().getPath()+
                            "/mp3/La_Dalu_Bopath.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
            //mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

            }
        });


    }
    }
