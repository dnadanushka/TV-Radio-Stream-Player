package com.example.youtube_streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

public class Mp3PlayerActivity extends AppCompatActivity {


    String filename;
    String title;

    ImageView buttonPlay;

    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView txtProgress;
    Handler seekHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mp3_player);

        filename = getIntent().getStringExtra("FILENAME");
        title = getIntent().getStringExtra("TITLE");
        ImageView buttonPlay = (ImageView) findViewById(R.id.btnPlay);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txtProgress = (TextView) findViewById(R.id.txt_progress);
        txtProgress.setTextColor(getResources().getColor(R.color.grey));
        seekBar.setBackgroundColor(getResources().getColor(R.color.grey));
        seekHandler = new Handler();

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
                    Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/mp3/"+filename+
                            ".mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                seekBar.setMax(mp.getDuration());
                updateSeekBar();
            }
        });

        try {
            mediaPlayer.prepare();
            //mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    buttonPlay.setImageResource(R.drawable.ic_white_play);
                    mediaPlayer.pause();
                }else{
                    buttonPlay.setImageResource(R.drawable.ic_white_pause);
                    mediaPlayer.start();
                }


            }
        });


    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            View view = getWindow().getDecorView();
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
            layoutParams.gravity = Gravity.BOTTOM;
            getWindowManager().updateViewLayout(view, layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
    private String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    private void updateSeekBar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        txtProgress.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()) + "/"+milliSecondsToTimer(mediaPlayer.getDuration()));
        seekHandler.postDelayed(runnable, 50);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };
}
