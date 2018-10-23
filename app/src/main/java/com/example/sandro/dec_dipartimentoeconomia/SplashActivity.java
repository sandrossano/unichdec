package com.example.sandro.dec_dipartimentoeconomia;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import static com.example.sandro.dec_dipartimentoeconomia.Documenti.documenti;
import static com.example.sandro.dec_dipartimentoeconomia.Documenti.singolo;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.categorie;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaDocumenti;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaPersone;

/**
 * Created by Sandro on 21/02/2018.
 */
public class SplashActivity extends AppCompatActivity{
    public class Pagina {
        private int id;
        private String nome;
        private int id_gruppo;
        private int id_contenuto;


        public Pagina (int i, String nome,int contenuto, int g){
            id=i;id_gruppo=g;this.nome=nome;id_contenuto=contenuto;
        }

        public String getNome() {
            return nome;
        }

        public int getId_contenuto() {
            return id_contenuto;
        }

        public int getId_gruppo() {
            return id_gruppo;
        }

        public int getId() {
            return id;
        }

    }

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

    public class Gruppi{
        private int id;
        private String nome;
        private String sigla;
        private int id_gruppo;
        private String tipo_gruppo;
        private int id_contenuto;
        public Gruppi(int i, String n,String s, int i_g, String t_g,int id_contenuto){
            id=i;nome=n;sigla=s; id_gruppo=i_g; tipo_gruppo=t_g; this.id_contenuto=id_contenuto;
        }

        public int getId_contenuto() {
            return id_contenuto;
        }

        public String getSigla() {
            return sigla;
        }

        public int getId_gruppo() {
            return id_gruppo;
        }

        public String getTipo_gruppo() {
            return tipo_gruppo;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }

    public static class SottoLivelli {
        private int i;
        private String titolo;
        private int id_gruppo;
        private int id_pagina;
        private int livello;
        private String link="";

        public SottoLivelli(int i, String a, int g, int p, int l,String s) {
            this.i = i;
            titolo = a;
            id_gruppo = g;
            id_pagina = p;
            livello = l;
            link=s;
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

        public String setTitolo(String titolo) {
            this.titolo = titolo;
            return this.titolo;
        }

        public String getLink() {
            return link;
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
        private int id_contenuto;


        public Corso(int i, String a,int sem,int gruppo,String tipo,int id_c){
            this.id=i;
            nome=a;
            color=sem;
            id_gruppo=gruppo;
            tipo_gruppo=tipo;
            id_contenuto=id_c;
        }

        public int getId_contenuto() {
            return id_contenuto;
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

    public static class Appuntamento{
        private int id;
        private String titolo;
        private String data_inizio;
        private String data_fine;
        private String descrizione;
        private int id_gruppo;
        private int id_contenuto;

        public Appuntamento(int i, String t,String d_i,String d_f,String desc,int id_g){
            this.id=i;
            titolo=t;
            data_inizio=d_i;
            data_fine=d_f;
            descrizione=desc;
            id_gruppo=id_g;
        }

        public String getTitolo() {
            return titolo;
        }

        public int getId() {
            return id;
        }

        public String getData_inizio() {
            return data_inizio;
        }

        public String getData_fine() {return data_fine;}

        public int getId_gruppo() {
            return id_gruppo;
        }


        public String getDescrizione() {
            return descrizione;
        }
    }

    public static class Immagini{
        private String path;
        private String titolo;
        private String link;
        private String data_fine;
        private int id_gruppo;

        public Immagini(String path,String titolo,String link,String data_fine,int id_g){
            this.id_gruppo=id_g;
            this.path=path;
            this.titolo=titolo;
            this.link=link;
            this.data_fine=data_fine;
        }

        public int getId_gruppo() {
            return id_gruppo;
        }

        public String getPath() {
            return path;
        }

        public String getTitolo() {
            return titolo;
        }

        public String getLink() {
            return link;
        }

        public String getData_fine() {
            return data_fine;
        }
    }




    //public String localhost = "proxybar.altervista.org";
    public static String localhost2 ="https://economia.unich.it/decapp/";
    public static ArrayList<Categoria> categorie = new ArrayList<>();
    public static JSONArray listaPersone = new JSONArray();
    public static JSONArray listaDocumenti = new JSONArray();
    public static ArrayList<Ruoli> ruoli = new ArrayList<>();
    public static ArrayList<Corso> corsi = new ArrayList<>();
    public static ArrayList<Corso> r_corsi = new ArrayList<>();
    public static ArrayList<SottoLivelli> livello2dec = new ArrayList<>();
    public static ArrayList<DipartimentiScuola> dipartimenti = new ArrayList<>();
    public static ArrayList<Scuola> scuola = new ArrayList<>();
    public static ArrayList<Appuntamento> appuntamenti = new ArrayList<>();
    public static ArrayList<Immagini> immagini_dec = new ArrayList<>();
    public static ArrayList<Singolo> singolo_splash=new ArrayList<>();
    public static ArrayList<Gruppi> tutti_gruppi=new ArrayList<>();
    public static ArrayList<Pagina> pagine=new ArrayList<>();
    public boolean sceltaPermesso=false;

    public static int count = 0;

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission");
                sceltaPermesso=true;
                return true;
            } else {

                Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error", "You already have the permission");
            sceltaPermesso=true;
            return true;
        }
    }

    static boolean finish1 = false;
    boolean finish2=false;
    static boolean finish3=false;
    boolean finishcors=false;
    boolean finish7=false;

    boolean finish_imm=false;
    static boolean finishdocu=false;

    boolean finish_pag = false;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Window window = getWindow();
        window.setStatusBarColor(R.color.colorPrimaryDark);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), Color.DKGRAY);


        final TextView textView= findViewById(R.id.text_splash);

        if (count == 0) {



//NON SPOSTARE
            //decLv2e3
            // Instantiate the RequestQueue.
            RequestQueue queue23 = Volley.newRequestQueue(this);
            String url23 = localhost2 + "menus/menu_dipartimenti.php";

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
                                    if (expl.getInt("id_pagina")>-2)
                                    livello2dec.add(new SottoLivelli(expl.getInt("ordine"), expl.getString("titolo"), expl.getInt("id_gruppo"), expl.getInt("id_pagina"), expl.getInt("livello"),expl.getString("link")));
                                    else{
                                        if(expl.getInt("id_gruppo")==1270){
                                            livello2dec.add(new SottoLivelli(expl.getInt("ordine"), expl.getString("titolo"), expl.getInt("id_gruppo"), expl.getInt("id_pagina"), expl.getInt("livello"),expl.getString("link")));
                                        }
                                    }
                                }
                                Log.d("res1", "ok");
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
            queue23.getCache().clear();



            //pagine
            RequestQueue queue_pag = Volley.newRequestQueue(this);
            String url_pag = localhost2 + "pagine/";

// Request a string response from the provided URL.
            StringRequest stringRequest_pag = new StringRequest(Request.Method.GET, url_pag,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    //livello2dec.add(new SottoLivelli(expl.getInt("ordine"), expl.getString("titolo"), expl.getInt("id_gruppo"), expl.getInt("id_pagina"),expl.getInt("livello")));
                                    //immagini_dec.add(new Immagini(expl.getString("path"),expl.getString("titolo"),expl.getString("link"),expl.getString("data_fine")));
                                    pagine.add(new Pagina(expl.getInt("id"), expl.getString("nome"),expl.getInt("id_contenuto"),expl.getInt("id_gruppo")));
                                }

                                Log.d("res_pag", "ok");
                                finish_pag=true;
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
            stringRequest_pag.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue_pag.add(stringRequest_pag);
            queue_pag.getCache().clear();





//IMMAGINI
            RequestQueue queue_imm = Volley.newRequestQueue(this);
            String url_imm = localhost2 + "immagini/";

// Request a string response from the provided URL.
            StringRequest stringRequest_imm = new StringRequest(Request.Method.GET, url_imm,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {
                                textView.setText("Carico le Immagini...");
                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    //livello2dec.add(new SottoLivelli(expl.getInt("ordine"), expl.getString("titolo"), expl.getInt("id_gruppo"), expl.getInt("id_pagina"),expl.getInt("livello")));
                                    immagini_dec.add(new Immagini(expl.getString("path"), expl.getString("titolo"), expl.getString("link"), expl.getString("data_fine"), expl.getInt("id_gruppo")));
                                }
                                Log.d("res_imm", "ok");
                                finish_imm = true;
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
            stringRequest_imm.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue_imm.add(stringRequest_imm);
            queue_imm.getCache().clear();


            //Ruoli
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = localhost2 + "ruoli/";

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
                                    ruoli.add(new Ruoli(expl.getInt("idRuolo"), expl.getString("nomeRuolo"), expl.getInt("idPersonaAppartenenza"), expl.getString("nomePersona"), expl.getString("cognomePersona"), expl.getString("fotoPersona"),expl.getInt("id_gruppo")));

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
            queue.getCache().clear();

//SoloCorsi
            // Instantiate the RequestQueue.
            RequestQueue queuesolocors = Volley.newRequestQueue(this);
            String urlsolocors = localhost2 + "corsidilaurea/index_solo_corsi.php";

// Request a string response from the provided URL.
            StringRequest stringRequestsolocors = new StringRequest(Request.Method.GET, urlsolocors,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                textView.setText("Carico i Corsi di Laurea...");
                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");
                                corsi.clear();
                                r_corsi.clear();
                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    if (expl.getString("tipo_gruppo").equals("R_CS"))
                                    r_corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), -100, expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"),expl.getInt("id_contenuto")));

                                    if (expl.getString("tipo_gruppo").equals("CS"))
                                    corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), expl.getInt("semestre"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"),expl.getInt("id_contenuto")));


                                }
                                Log.d("resCors", "ok");
                                finishcors = true;

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
            stringRequestsolocors.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queuesolocors.add(stringRequestsolocors);
            queuesolocors.getCache().clear();


//TUTTI I GRUPPI
            // Instantiate the RequestQueue.
            RequestQueue queuecors = Volley.newRequestQueue(this);
            String urlcors = localhost2 + "corsidilaurea/";

// Request a string response from the provided URL.
            StringRequest stringRequestcors = new StringRequest(Request.Method.GET, urlcors,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                textView.setText("Carico i Corsi di Laurea...");
                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");
                                corsi.clear();
                                r_corsi.clear();
                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    tutti_gruppi.add(new Gruppi(expl.getInt("id"), expl.getString("nome"), expl.getString("sigla"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"), expl.getInt("id_contenuto")));

                                    if (!expl.isNull("id_gruppo_scuola") && !expl.isNull("semestre")) {
                                        scuola.add(new Scuola(expl.getInt("id"), expl.getString("sigla"), expl.getInt("id_gruppo"), expl.getInt("id_gruppo_scuola"), expl.getString("tipo_gruppo")));
                                        corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), expl.getInt("semestre"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"),expl.getInt("id_contenuto")));

                                    } else {
                                        if (expl.getString("tipo_gruppo").equals("R_CS"))
                                            r_corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), -100, expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"),expl.getInt("id_contenuto")));
                                        if (expl.getString("tipo_gruppo").equals("CS") && !expl.isNull("semestre"))
                                            corsi.add(new Corso(expl.getInt("id"), expl.getString("sigla"), expl.getInt("semestre"), expl.getInt("id_gruppo"), expl.getString("tipo_gruppo"),expl.getInt("id_contenuto")));

                                    }

                                }
                                Log.d("res3", "ok");
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
            queuecors.getCache().clear();




//Menù
            // Instantiate the RequestQueue.
            RequestQueue queueini = Volley.newRequestQueue(this);
            String urlini = localhost2 + "menus/menu_ini.php";

// Request a string response from the provided URL.
            StringRequest stringRequestini = new StringRequest(Request.Method.GET, urlini,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {
                                textView.setText("Carico il menù...");
                                JSONObject c = new JSONObject(response);
                                JSONArray cacca = c.getJSONArray("records");

                                for (int i = 0; i < cacca.length(); i++) {
                                    JSONObject expl = cacca.getJSONObject(i);
                                    dipartimenti.add(new DipartimentiScuola(expl.getInt("id"), expl.getString("nome"), expl.getString("sigla"), expl.getString("tipo_gruppo"), expl.getInt("livello")));
                                }
                                Log.d("res7", "ok");
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
            queueini.getCache().clear();


            count++;
        }
        fine();
    }




    public void fine() {
        if(finish7&& finish_imm&&finish_pag&finish1&&finishcors) { // && finish_cont&&finishdocu&& finish_pers&&finish2&&finishapp
            haveStoragePermission();
            if(sceltaPermesso) {
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
    class Ruoli{
        private int id;
        private String nome;
        private String nome_pers;
        private String cognome;
        private String foto;
        private int id_persona;
        private int id_gruppo;
        public Ruoli(int id, String nome, int id_persona, String nome_pers, String cognome,String foto,int id_gruppo){
            this.nome=nome;this.id=id;this.nome_pers=nome_pers;this.id_persona=id_persona;this.cognome=cognome;this.foto=foto;this.id_gruppo=id_gruppo;
        }

        public int getId_gruppo() {
            return id_gruppo;
        }

        public String getFoto() {return foto;}
        public int getId(){return id;}
        public String getCognome(){return cognome;}
        public String  getNome(){return nome_pers;}
        public String  getNome_titolo(){return nome;}
        public int getId_persona(){return id_persona;}
    }
}
