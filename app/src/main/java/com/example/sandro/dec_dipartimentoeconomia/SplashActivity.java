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
    public class Scuola {
        private int id;
        private String sigla;
        private int id_gruppo;
        private int id_gruppo_scuola;
        private String tipo_gruppo;

        public Scuola (int i, String nome,int g, int s, String tp){
            id=i;id_gruppo=g;id_gruppo_scuola=s;tipo_gruppo=tp;sigla=nome;
        }

        public String getSigla() {
            return sigla;
        }

        public int getId_gruppo() {
            return id_gruppo;
        }

        public int getId() {
            return id;
        }

        public int getId_gruppo_scuola() {
            return id_gruppo_scuola;
        }

        public String getTipo_gruppo() {
            return tipo_gruppo;
        }
    }
    public class SottoLivelli {
        private int i;
        private String titolo;
        private int id_gruppo;
        private int id_pagina;
        private int livello;

        public SottoLivelli(int i, String a, int g, int p, int l) {
            this.i = i;
            titolo = a;
            id_gruppo = g;
            id_pagina = p;
            livello = l;
        }

        public int getI() {
            return i;
        }

        public String getTitolo() {
            return titolo;
        }
        public int getId_gruppo() {
            return id_gruppo;
        }

        public int getId_pagina() {
            return id_pagina;
        }

        public int getLivello() {
            return livello;
        }
    }


    public static class DipartimentiScuola {
        private int id;
        private String nome;
        private String sigla;
        private String tipo_gruppo;
        private int livello;

        public DipartimentiScuola(int i, String a, String g, String p, int l) {
            this.id = i;
            nome = a;
            sigla = g;
            tipo_gruppo = p;
            livello = l;
        }
        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
        public String getSigla() {
            return sigla;
        }

        public String getTipo_gruppo() {
            return tipo_gruppo;
        }

        public int getLivello() {
            return livello;
        }
    }

    public static class Corso{
        private int id;
        private String nome;
        private int color;
        private int id_gruppo;
        private String tipo_gruppo;

        public Corso(int i, String a,int sem,int gruppo,String tipo){
            this.id=i;
            nome=a;
            color=sem;
            id_gruppo=gruppo;
            tipo_gruppo=tipo;
        }

        public int getColor() {
            return color;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public int getId_gruppo() {return id_gruppo;}

        public String getTipo_gruppo() {
            return tipo_gruppo;
        }
    }

    public String localhost = "proxybar.altervista.org";
    public String localhost2 ="https://economia.unich.it/decapp/";
    public static ArrayList<Categoria> categorie = new ArrayList<>();
    public static JSONArray listaPersone = new JSONArray();
    public static JSONArray listaDocumenti = new JSONArray();
    public static ArrayList<Ruoli> ruoli = new ArrayList<>();
    public static ArrayList<Corso> corsi = new ArrayList<>();
    public static ArrayList<Corso> r_corsi = new ArrayList<>();
    public static ArrayList<SottoLivelli> livello2dec = new ArrayList<>();
    public static ArrayList<DipartimentiScuola> dipartimenti = new ArrayList<>();
    public static ArrayList<Scuola> scuola = new ArrayList<>();

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

    boolean finish1 = false;
    boolean finish2=false;
    boolean finish3=false;
    boolean finish4=false;
    boolean finish5=false;
    boolean finish6=false;
    boolean finish7=false;
    boolean finish8=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), Color.DKGRAY);
        haveStoragePermission();


        if (count == 0) {

            //decLv2e3
            // Instantiate the RequestQueue.
            RequestQueue queue23 = Volley.newRequestQueue(this);
            String url23 = localhost2+"menus/menu_dipartimenti.php";

// Request a string response from the provided URL.
            StringRequest stringRequest23 = new StringRequest(Request.Method.GET, url23,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    livello2dec.add(new SottoLivelli(expl.getInt("ordine"), expl.getString("titolo"), expl.getInt("id_gruppo"), expl.getInt("id_pagina"),expl.getInt("livello")));

                                }
                                Log.d("res1","ok");
                                finish1 = true;
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
            stringRequest23.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue23.add(stringRequest23);
/*
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
                                finish2 = true;
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
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);

*/
//Corsi
            // Instantiate the RequestQueue.
            RequestQueue queuecors = Volley.newRequestQueue(this);
            String urlcors = localhost2+"corsidilaurea/";

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
                                    if(!expl.isNull("id_gruppo_scuola") && !expl.isNull("semestre")) {
                                        scuola.add(new Scuola(expl.getInt("id"), expl.getString("sigla"), expl.getInt("id_gruppo"), expl.getInt("id_gruppo_scuola"),expl.getString("tipo_gruppo")));
                                        corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), expl.getInt("semestre"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo")));

                                    }
                                    else{
                                            if (expl.getString("tipo_gruppo").equals("R_CS"))
                                                r_corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"),-100, expl.getInt("id_gruppo"), expl.getString("tipo_gruppo")));
                                            if (expl.getString("tipo_gruppo").equals("CS") && !expl.isNull("semestre"))
                                                corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), expl.getInt("semestre"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo")));

                                    }

                                }

                                Log.d("res3","ok");
                                finish3 = true;
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
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queuecors.add(stringRequestcors);

//Corsi
            // Instantiate the RequestQueue.
            RequestQueue queueini = Volley.newRequestQueue(this);
            String urlini = localhost2+"menus/menu_ini.php";

// Request a string response from the provided URL.
            StringRequest stringRequestini = new StringRequest(Request.Method.GET, urlini,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                           try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    dipartimenti.add(new DipartimentiScuola(expl.getInt("id"), expl.getString("nome"),expl.getString("sigla"),expl.getString("tipo_gruppo"), expl.getInt("livello")));
                                }
                               Log.d("res7","ok");
                                finish7 = true;
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
            stringRequestini.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queueini.add(stringRequestini);

/*
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
                                finish5 = true;
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
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue4.add(stringRequest4);

*/

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
                                Log.d("res6","ok");
                                finish6 = true;
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
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue5.add(stringRequest5);


            /*
//R_Corsi e id_gruppo_scuola
            // Instantiate the RequestQueue.
            RequestQueue queue33 = Volley.newRequestQueue(this);
            String url33 = "https://" + localhost2 + "decapp/gruppi/";

// Request a string response from the provided URL.
            StringRequest stringRequest33 = new StringRequest(Request.Method.GET, url33,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                if(c.get("id_gruppo_scuola")!=null) {
                                    scuola.add(new Scuola(c.getInt("id"), c.getInt("id_gruppo"), c.getInt("id_gruppo_scuola"),c.getString("tipo_gruppo")));
                                }
                                else{
                                    r_corsi.add(new Corsi(c.getInt("id"), c.getInt("id_gruppo"),c.getString("tipo_gruppo")));
                                }

                                finish8 = true;
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
            stringRequest33.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue33.add(stringRequest33);
*/

            count++;
        }
        fine();
    }




    public void fine() {
        if(finish1 && finish3 && finish7 ) {
            Intent i = new Intent(getApplicationContext(), MainActivityMultiDipartimento.class);
            startActivity(i);
        }
        else{
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   fine();
                }
            },100);
            }

    }

}
