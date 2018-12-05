package com.example.michellejun.cs125finalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class endGame extends DogOrCat {
    private static final String TOTAL_COUNT = "total_count";

    public void getCount() {
        TextView gameOver = (TextView) findViewById(R.id.get_endGame);
        TextView countView = (TextView) findViewById(R.id.get_points);
        int count = getIntent().getIntExtra(TOTAL_COUNT, 0);
        countView.setText(Integer.toString(count));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        new ImageDownload((ImageView) findViewById(R.id.get_doggy)).execute("https://i.imgur.com/JgK9z4Q.jpg");

        getCount();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private class ImageDownload extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public ImageDownload(ImageView imageView) {
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return logo;
        }
        protected void onPostExecute (Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
