package com.example.michellejun.cs125finalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DogOrCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_or_cat);
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
                }
            }, new Response.ErrorListener() {
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
