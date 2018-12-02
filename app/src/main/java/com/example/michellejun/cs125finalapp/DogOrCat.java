package com.example.michellejun.cs125finalapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DogOrCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_or_cat);
    }

    boolean dog = false;
    boolean cat = false;
    int score = 0;
    int timer = 3;
    boolean endGame = false;
    private static final String TOTAL_COUNT = "total_count";

    public void clickDog (View view) {
        if (dog == true) {
            TextView showCountTextView = (TextView) findViewById(R.id.getDog);
            String countString = showCountTextView.getText().toString();
            Integer count = Integer.parseInt(countString);
            count++;
            timer = 3;
            showCountTextView.setText(count.toString());
        }
        if (timer == 0) {
            endGame = true;
        } else {
            endGame = true;
        }
        if (endGame == true) {
            Intent winnerScore = new Intent(this, endGame.class);
            TextView showCountText = (TextView) findViewById(R.id.get_score);
            String countStringEnd = showCountText.getText().toString();
            int endCount = Integer.parseInt(countStringEnd);
            winnerScore.putExtra(TOTAL_COUNT, endCount);
            startActivity(winnerScore);
        }
    }


    public void clickCat (View view) {
        TextView showCountTextView = (TextView) findViewById(R.id.getCat);
        String countString = showCountTextView.getText().toString();
        Integer count = Integer.parseInt(countString);
        if (cat == true) {
            count++;
            timer = 3;
            showCountTextView.setText(count.toString());
        }
        if (timer == 0) {
            endGame = true;
        } else {
            endGame = true;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dog_or_cat);

        final Button button = findViewById(R.id.getDog);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickDog();
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dog_or_cat);

        final Button button = findViewById(R.id.getCat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickCat();
            }
        });
    }
    public void getDogAPI() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (
                Request.Method.GET,
                "http://dog.ceo/api/breeds/list/all",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
        });
    }

    public void getCatAPI() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (
                Request.Method.GET,
                "https://api.thecatapi.com/v1/images/search",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
        });
    }
}





