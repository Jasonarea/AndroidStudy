package com.example.hyeon.part9_25;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Lab25_3Activity extends AppCompatActivity {
    ImageView gifView;
    ImageView networkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab25_3);
        gifView=(ImageView)findViewById(R.id.lab3_gif);
        networkView=(ImageView)findViewById(R.id.lab3_network);

        Glide.with(this)
                .load(R.raw.loading)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .override(200, 200)
                .into(gifView);

        String url="http://192.168.4.171:8000/files/1.jpg";
        Glide.with(this)
                .load(url)
                .override(400, 400)
                .error(R.drawable.error)
                .into(networkView);
    }
}
