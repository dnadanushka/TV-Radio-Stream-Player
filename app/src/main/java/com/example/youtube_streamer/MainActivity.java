package com.example.youtube_streamer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private Button buttonTV;
    private Button buttonRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTV = findViewById(R.id.btn_tv);
        buttonRadio = findViewById(R.id.btn_radio);

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


    }

}