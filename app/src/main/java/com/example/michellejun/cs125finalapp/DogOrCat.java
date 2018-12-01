package com.example.michellejun.cs125finalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    public boolean clickDog() {
        if (timer == 0) {
            endGame = true;
        }
        if (dog == true) {
            score += 1;
            timer = 3;
        } else {
            endGame = true;
        }
        return true;
    }

    public boolean clickCat() {
        if (timer == 0) {
            endGame = true;
        }
        if (cat == true) {
            score += 1;
            timer = 3;
        } else {
            endGame = true;
        }
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_layout_id);

        final Button button = findViewById(R.id.getDog);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickDog();
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_layout_id);

        final Button button = findViewById(R.id.getCat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickCat();
            }
        });
    }
}


JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
        Request.Method.GET,
        "",
        null,
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                Log.d(TAG, response.toString());
                "http://dog.ceo/api/breeds/list/all";

            }
        }, new Response.ErrorListener() {}
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG, error.toString());
            }
        }) {

        @Override
        public Map<String, String> getHeaders() {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("Ocp-Apim-Subscription-Key", "YOUR_API_KEY");
            Log.d(TAG, params.toString());
            return params;
        }
    };



