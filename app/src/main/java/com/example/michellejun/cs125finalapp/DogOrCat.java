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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class DogOrCat extends AppCompatActivity {

    boolean dog = false;
    boolean cat = false;
    int timer = 3;
    boolean endGame = false;
    private static final String TOTAL_COUNT = "total_count";
    private static String TAG = "puppy";
    private static String TAG1 = "kitty";

    JSONObject

    public void clickDog(View view) {
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


    public void clickCat(View view) {
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
        if (endGame == true) {
            Intent winnerScore = new Intent(this, endGame.class);
            TextView showCountText = (TextView) findViewById(R.id.get_score);
            String countStringEnd = showCountText.getText().toString();
            int endCount = Integer.parseInt(countStringEnd);
            winnerScore.putExtra(TOTAL_COUNT, endCount);
            startActivity(winnerScore);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dog_or_cat);

        final Button dogButton = findViewById(R.id.getDog);
        dogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickDog(view);
            }
        });
        final Button catButton = findViewById(R.id.getCat);
        catButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickCat(view);
            }
        });
    }

    public void getDogAPI() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://api.thecatapi.com/v1/images/search",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG1, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG1, error.toString());
            }
        });
    }

    public void requestWithSomeHttpHeaders() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.thecatapi.com/v1/images/search";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("dogPic", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-api-key", "58a2cd8f-574f-4455-b912-f5ebc769600c");
                params.put("x-api-key", "04e2b1b5-2841-4d6a-b049-57809ad2d55e");

                return params;
            }
        };
        queue.add(getRequest);
    }


}
