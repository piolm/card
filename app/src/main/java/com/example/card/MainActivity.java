package com.example.card;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AudioPlayer audioPlayer = new AudioPlayer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean[] redo = {true};

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageDrawable(getDrawable(R.drawable.conv));
        imageView.setOnClickListener(v -> {
            Log.d(TAG, "CLicked");
            if (redo[0]) { audioPlayer.start();
            redo[0] =false; }
            imageView.setImageDrawable(getDrawable(R.drawable.a));
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioPlayer.mPlayer.stop();
    }

    class AudioPlayer extends MediaPlayer {
        private MediaPlayer mPlayer;
        Context c;

        public AudioPlayer(Context c) {
            this.c = c;
        }

        public void start() {
            mPlayer = MediaPlayer.create(c, R.raw.hny);
            mPlayer.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
            mPlayer.start();
        }


        public void stop() {
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }
        }
    }
}