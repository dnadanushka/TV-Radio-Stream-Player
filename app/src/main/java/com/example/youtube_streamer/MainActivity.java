package com.example.youtube_streamer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends Activity {

    private Button buttonTV;
    private Button buttonRadio;
    private Button buttonDeshana;
    private Button buttonKarmasthana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTV = findViewById(R.id.btn_tv);
        buttonRadio = findViewById(R.id.btn_radio);
        buttonDeshana = findViewById(R.id.btn_deshana);
        buttonKarmasthana = findViewById(R.id.btn_karmasthana);

        buttonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, TVPlayer.class);
                startActivity(intent);
            }
        });

        buttonRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, RadioPlayer.class);
                startActivity(intent);
            }
        });

        buttonDeshana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, DeshanaActivity.class);
                startActivity(intent);
            }
        });

        buttonKarmasthana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KarmasthanaActivity.class);
                startActivity(intent);
            }
        });

    }

}