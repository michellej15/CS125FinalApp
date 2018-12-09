package com.example.michellejun.cs125finalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.os.AsyncTask;

public class DogOrCat extends AppCompatActivity {

    boolean dog = false;
    boolean cat = false;
    int timer = 3;
    boolean endGame = false;
    private static final String TOTAL_COUNT = "total_count";
    private static String TAG = "puppy";
    private static String TAG1 = "kitty";
    private static RequestQueue requestQueue;
    final ImageView imageView = (ImageView) findViewById(R.id.dogCatImage);
    static String dogUrl = "https://api.thedogapi.com/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1";
    static String catUrl = "https://api.thecatapi.com/v1/images/search";


    public void clickDog(View view) {
        if (dog == true) {
            TextView showCountTextView = (TextView) findViewById(R.id.getDog);
            String countString = showCountTextView.getText().toString();
            Integer count = Integer.parseInt(countString);
            count++;
            timer = 3;
            showCountTextView.setText(count.toString());
            getDogOrCatImage();
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


    public void clickCat(View viewCat) {
        if (cat == true) {
            TextView showCountTextView = (TextView) findViewById(R.id.getCat);
            String countString = showCountTextView.getText().toString();
            Integer count = Integer.parseInt(countString);
            count++;
            timer = 3;
            showCountTextView.setText(count.toString());
            getDogOrCatImage();
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

    public String getDogOrCatImage() {
        Random random = new Random();
        String randomString = dogAndCatUrl[random.nextInt(dogAndCatUrl.length)];
        if (randomString.equals(catImage)) {
            cat = true;
        }
        if (randomString.equals(dogImage)) {
            dog = true;
        }
        return randomString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_or_cat);

        new ImageDownload((ImageView) findViewById(R.id.dogCatImage)).execute(getDogOrCatImage());
        final ProgressBar progressBar = findViewById(R.id.progress_view);

        final Button dogButton = findViewById(R.id.getDog);
        final Button catButton = findViewById(R.id.getCat);
        dogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickDog(view);
            }
        });
        catButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickCat(view);
            }
        });
    }

    public void getDogAPI() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://api.thedogapi.com/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        try {
                            Log.d(TAG, response.getJSONObject(0).toString());
                            /*JSONObject dogURL = response.getJSONObject(0);
                            String dogPic = dogURL.getString("url");*/

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG, error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void getCatAPI() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://api.thecatapi.com/v1/images/search",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        try {
                            Log.d(TAG, response.getJSONObject(0).toString());
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG, error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void requestWithSomeHttpHeaders() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String catUrl = "https://api.thecatapi.com/v1/images/search";
        StringRequest getRequest = new StringRequest(Request.Method.GET, catUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("catPic", response);
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

                return params;
            }
        };
        queue.add(getRequest);
    }

    public void requestWithSomeHttpHeadersTwo() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String dogUrl = "https://api.thedogapi.com/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1";
        StringRequest getRequest = new StringRequest(Request.Method.GET, dogUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("catPic", response);
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
                params.put("x-api-key", "04e2b1b5-2841-4d6a-b049-57809ad2d55e");

                return params;
            }
        };
        queue.add(getRequest);
    }

    static JsonParser parser = new JsonParser();
    static JsonObject dogResult = parser.parse(dogUrl).getAsJsonObject();
    static String dogImage = dogResult.get("url").getAsString();

    static JsonObject catResult = parser.parse(catUrl).getAsJsonObject();
    static String catImage = catResult.get("url").getAsString();

    public static final String[] dogAndCatUrl = {dogImage, catImage};


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