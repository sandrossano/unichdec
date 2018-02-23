package com.example.sandro.dec_dipartimentoeconomia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sandro on 21/02/2018.
 */

public class SplashActivity extends AppCompatActivity{
    public String localhost = "proxybar.altervista.org";
    public static ArrayList<Categoria> categorie = new ArrayList<>();
    public static JSONArray listaPersone = new JSONArray();
    public static JSONArray listaDocumenti = new JSONArray();
    public static ArrayList<Ruoli> ruoli = new ArrayList<>();
    public static ArrayList<String> corsi = new ArrayList<>();
    public static ArrayList<String> livello1dec = new ArrayList<>();

    public static int count = 0;

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission");
                return true;
            } else {

                Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error", "You already have the permission");
            return true;
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), Color.DKGRAY);
        haveStoragePermission();

        if (count == 0) {
//Ruoli
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://" + localhost + "/organigramma/read.php";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    ruoli.add(new Ruoli(expl.getInt("id"), expl.getString("nome"), expl.getInt("id_persona"), expl.getString("nome_pers"), expl.getString("cognome"), expl.getString("foto")));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

// Add the request to the RequestQueue.
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);


//Corsi
            // Instantiate the RequestQueue.
            RequestQueue queuecors = Volley.newRequestQueue(this);
            String urlcors = "http://" + localhost + "/corsi/read.php";

// Request a string response from the provided URL.
            StringRequest stringRequestcors = new StringRequest(Request.Method.GET, urlcors,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    corsi.add(expl.getString("sigla"));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

// Add the request to the RequestQueue.
            stringRequestcors.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queuecors.add(stringRequestcors);


//Livello1Dec
            // Instantiate the RequestQueue.
            RequestQueue queueliv1 = Volley.newRequestQueue(this);
            String urlliv1 = "http://" + localhost + "/menu/read.php";

// Request a string response from the provided URL.
            StringRequest stringRequestliv1 = new StringRequest(Request.Method.GET, urlliv1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    livello1dec.add(expl.getString("titolo"));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

// Add the request to the RequestQueue.
            stringRequestliv1.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queueliv1.add(stringRequestliv1);


// Categorie... IMPORTANTE!!!!!

            // Instantiate the RequestQueue.
            RequestQueue queue4 = Volley.newRequestQueue(this);
            String url4 = "http://" + localhost + "/category/read.php";

// Request a string response from the provided URL.
            StringRequest stringRequest4 = new StringRequest(Request.Method.GET, url4,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca2 = c.getJSONArray("records");


                                for (int i = 0; i < cacca2.length(); i++) {
                                    JSONObject expl = cacca2.getJSONObject(i);
                                    categorie.add(new Categoria(expl.getInt("id"), expl.getString("nome"), expl.getInt("id_gruppo"), expl.getString("nome_cat"), expl.getInt("id_persona"), expl.getInt("corso")));

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

// Add the request to the RequestQueue.
            stringRequest4.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue4.add(stringRequest4);


//Persone
            // Instantiate the RequestQueue.
            RequestQueue queue5 = Volley.newRequestQueue(this);
            String url5 = "http://" + localhost + "/person/read.php";

// Request a string response from the provided URL.
            StringRequest stringRequest5 = new StringRequest(Request.Method.GET, url5,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                listaPersone = c.getJSONArray("records");


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

// Add the request to the RequestQueue.
            stringRequest5.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue5.add(stringRequest5);

            count++;
        }


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent (getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            },8000);


    }
}
