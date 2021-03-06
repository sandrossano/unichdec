package com.example.sandro.dec_dipartimentoeconomia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.StringUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.drawerCorso;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.position;
import static com.example.sandro.dec_dipartimentoeconomia.Documenti.singolo;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawerMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaPersone;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContext;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.nome_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.appuntamenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.pagine;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.r_corsi;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.ruoli;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.tutti_gruppi;

/**
 * Created by sandro on 27/03/18.
 */

public class Visualizza extends AppCompatActivity {
    int id_dipartimento;
    String secondolv;
    String terzolv;
    int terzolvpag;
    String link;
    int corso;
    private ExpandableListView expandableListView;
    static DrawerLayout drawerVisual=null;
    String contenuto="";
    SwipeRefreshLayout mSwipeRefreshLayout;
    static int pos_pagina=4;
    RequestQueue requestQueue;
    StringRequest jsonObjRequest;
    WebView engine ;
    TextView text;

    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        id_dipartimento=getIntent().getIntExtra("id_dip",0);
        secondolv=getIntent().getStringExtra("secondolv");
        terzolv=getIntent().getStringExtra("terzolv");
        corso=getIntent().getIntExtra("id_corso",-1);

        Intent i=new Intent(this, Visualizza.class);
        i.putExtra("id_dip",id_dipartimento);
        i.putExtra("secondolv",secondolv);
        i.putExtra("terzolv",terzolv);
        i.putExtra("terzolvpag",terzolvpag);
        i.putExtra("id_corso",corso);
        i.putExtra("link",link);
        startActivity(i);
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @SuppressLint({"ClickableViewAccessibility", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.GONE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (drawerVisual != null) drawerVisual.closeDrawer(GravityCompat.START);
        drawerMain.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.START);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerVisual = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerCorso != null) drawerCorso.closeDrawer(GravityCompat.START);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        engine = (WebView) findViewById(R.id.web_engine);
        text=(TextView)findViewById(R.id.testovisualizza);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=".Visualizza";

        mContext=this;
        id_dipartimento=getIntent().getIntExtra("id_dip",0);
        secondolv=getIntent().getStringExtra("secondolv");
        terzolv=getIntent().getStringExtra("terzolv");
        terzolvpag=getIntent().getIntExtra("terzolvpag",0);
        link=getIntent().getStringExtra("link");
        corso=getIntent().getIntExtra("id_corso",-1);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


        engine.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(view.canScrollVertically(-1)==true)mSwipeRefreshLayout.setEnabled(false);
                else{mSwipeRefreshLayout.setEnabled(true);}
            }
        });
        Log.d("pagina",""+terzolvpag);

        if (link.contains("pag_appuntamenti.php")) {

            if (corso != -1) { //from corso
                Intent i = new Intent(getApplicationContext(), ListaAvvisi.class);
                i.putExtra("from_corso", 1);
                startActivity(i);
                finish();
                return;
            } else {
                Intent i = new Intent(getApplicationContext(), ListaAvvisi.class);
                i.putExtra("from_dipartimento", 1);
                startActivity(i);
                finish();
                return;
            }
        }
        if (link.contains("pag_persone.php")) {

            if (corso != -1) { //from corso
                Intent i = new Intent(getApplicationContext(), Persona.class);
                i.putExtra("from_corso", 1);
                startActivity(i);
                finish();
                return;
            } else {
                Intent i = new Intent(getApplicationContext(), Persona.class);
                i.putExtra("from_dipartimento", 1);
                startActivity(i);
                finish();
                return;
            }
        }
        if (link.contains("pag_documenti.php")) {

            if (corso != -1) { //from corso
                Intent i = new Intent(getApplicationContext(), Documenti.class);
                i.putExtra("from_corso", 1);
                startActivity(i);
                finish();
                return;
            } else {
                Intent i = new Intent(getApplicationContext(), Documenti.class);
                i.putExtra("from_dipartimento", 1);
                startActivity(i);
                finish();
                return;
            }
        }

        for(int i=0;i<pagine.size();i++){
            if(pagine.get(i).getId()==terzolvpag){pos_pagina=i;break;}
        }

        makePost();

    }

    private void caricaDopoRequest(){
        String data = "";
        String completa = "";
        String a = "";

        if (link.contains("visualizza.php")) {


            String[] titolo = terzolv.split("->");
            if (!contenuto.contains("[[[")) {
                data = "<html>" + "<body style=\"background:#d8e5f0\">" + "<h2 align=\"center\">" + titolo[titolo.length - 1] + "</h2>" +
                        contenuto +
                        "</body>" + "</html>";
                //data=data.replace("href=","");        rimuovi link
                data = data.replace("src=\"documenti/", "src=\"https://economia.unich.it/documenti/");
                data = data.replace("href=\"documenti/", "href=\"https://economia.unich.it/documenti/");

                if (data.contains("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;")) {
                    data = data.replace("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;", "<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: none; ");
                }

                //data=data.replace("width=\"","width=\"100%\" height=\"100%\" alt=\"");
                if (terzolv.equals("Esami")) {
                    data = data.replace("</body>", "App Uda</body>");
                }
            } else {
                int count = 0;
                Pattern p = Pattern.compile("]]]");
                Matcher m = p.matcher(contenuto);
                while (m.find()) {
                    count++;
                }
                a = "";
                completa = contenuto;
                int start = 0;

                //Log.d("contenuto",contenuto);
                for (int i = 0; i < count; i++) {
                    int primo = contenuto.indexOf("[[[", start) + 3;
                    int ultimo = contenuto.indexOf("]]]", start) - 1;
                    start = contenuto.indexOf("]]]", start) + 1;
                    String pulita = contenuto.substring(primo, ultimo + 1);
                    String[] array = pulita.split(";");
                    int gruppo = -250;
                    int ruolo = -250;
                    int cat=-250;
                    String tipo_gr = "ciao";
                    String modulo = "";
                    for (int j = 0; j < array.length; j++) {
                        if (array[j].startsWith("modulo=")) {
                            String[] subarray = array[j].split("=");
                            modulo = subarray[1];
                        }
                        if (array[j].startsWith("gruppo=")) {
                            String[] subarray = array[j].split("=");
                            gruppo = Integer.parseInt(subarray[1]);
                        }
                        if (array[j].startsWith("ruolo=")) {
                            String[] subarray = array[j].split("=");
                            ruolo = Integer.parseInt(subarray[1]);
                        }
                        if (array[j].startsWith("tipo=")) {
                            String[] subarray = array[j].split("=");
                            tipo_gr = subarray[1];
                        }
                        if (array[j].startsWith("cat=")) {
                            String[] subarray = array[j].split("=");
                            cat = Integer.parseInt(subarray[1]);
                        }

                    }
                    //a="gruppo: "+gruppo+" - ruolo: "+ruolo+";";
                    if (ruolo != -250) {               //paginatore persona
                        for (int k = 0; k < ruoli.size(); k++) {
                            if (ruoli.get(k).getId() == ruolo) {
                                if (ruoli.get(k).getId_gruppo() == gruppo) {
                                    if (ruoli.get(k).getFoto() != "null") {
                                        a += "<div>\n";
                                        a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/persone/id_persona=" + ruoli.get(k).getId_persona() + "\">" +
                                                "<div class=\"card container\">\n" +
                                                "  <img src=\"https://economia.unich.it/fototessera/" + ruoli.get(k).getFoto() + "\" alt=\"Avatar\"   style=\"width:70px; height:70px; float:right;\">\n" +
                                                "  <p>" + ruoli.get(k).getNome() + "  " + ruoli.get(k).getCognome() + "</p> \n" +
                                                "</div>" +
                                                "</a></div><br>";
                                    } else {
                                        a += "<div>\n";
                                        a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/persone/id_persona=" + ruoli.get(k).getId_persona() + "\">";
                                        a += "<div class=\"card container\">\n" +
                                                "  <img src=\"https://economia.unich.it/fototessera/persona_generica.jpg\" alt=\"Avatar\"   style=\"width:70px; height:70px; float:right;\">\n" +
                                                "  <p>" + ruoli.get(k).getNome() + "  " + ruoli.get(k).getCognome() + "</p> \n" +
                                                "</div>";
                                        a += "</a></div><br>";
                                    }
                                }
                            }
                        }
                        completa = completa.replace("[[[" + pulita + "]]]", a);
                        a = "";

                    }
                    int numero = gruppo;
                    if (modulo.equals("pag_sezioni")) {
                        for (int n = 0; n < tutti_gruppi.size(); n++) {
                            if (tipo_gr.equals("GR")) {
                                if (tutti_gruppi.get(n).getTipo_gruppo().equals("R_GR") && tutti_gruppi.get(n).getId_gruppo() == gruppo) {
                                    numero = tutti_gruppi.get(n).getId();
                                    break;
                                }
                            }
                            if (tipo_gr.equals("CS")) {
                                if (tutti_gruppi.get(n).getTipo_gruppo().equals("R_CS") && tutti_gruppi.get(n).getId_gruppo() == gruppo) {
                                    numero = tutti_gruppi.get(n).getId();
                                    break;
                                }
                            }
                            if (tipo_gr.equals("CD")) {
                                if (tutti_gruppi.get(n).getTipo_gruppo().equals("R_CD") && tutti_gruppi.get(n).getId_gruppo() == gruppo) {
                                    numero = tutti_gruppi.get(n).getId();
                                    break;
                                }
                            }
                        }
                        for (int l = 0; l < tutti_gruppi.size(); l++) {
                            if (booleanoscuola){
                                if (tutti_gruppi.get(l).getTipo_gruppo().equals(tipo_gr)) {
                                    if(tipo_gr.equals("CS")){
                                        a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/sezioni/id_corso=" + tutti_gruppi.get(l).getSigla() + "\">" +

                                                "<div align=\"center\">\n" +
                                                "  <div style=\"width:100%;\">\n" +
                                                "    <img src=\"https://economia.unich.it/html/images/categorie/"+tutti_gruppi.get(l).getSigla().replace("/","").toLowerCase()+".jpg\" alt=\"Norway\" style=\"width:50%\">\n" +
                                                "    <div style=\"margin:-15px 0px 20px 0px;\">\n" +
                                                "      "+tutti_gruppi.get(l).getNome()+"\n" +
                                                "    </div>\n" +
                                                "  </div>\n" +
                                                "</div></a>";}
                                    else {
                                        a += "<!--<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/sezioni/id_gruppo=" + tutti_gruppi.get(l).getId() + "\"> -->" +

                                                "<div align=\"center\">\n" +
                                                "  <div style=\"width:100%;\">\n" +
                                                "    <div style=\"margin:-15px 0px 20px 0px;color:red;font-size:15px\">\n" +
                                                "      "+tutti_gruppi.get(l).getNome()+"\n" +
                                                "    </div>\n" +
                                                "  </div>\n" +
                                                "</div>" +
                                                "<!-- </a> -->";
                                   /*a += "<div>\n";
                                a += "<a style=\"text-decoration: none;color:black;\" href=\"visualizza.php?type=gruppo&id=" + tutti_gruppi.get(l).getId() + "\">";
                                a += "  <p style=\"color:red;\">" + tutti_gruppi.get(l).getNome() + "</p> \n";
                                a += "</a></div><br>";*/
                                    }
                                }
                            }
                        else {
                                if (tutti_gruppi.get(l).getTipo_gruppo().equals(tipo_gr) && tutti_gruppi.get(l).getId_gruppo() == numero) {
                                    if (tipo_gr.equals("CS")) {
                                        a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/sezioni/id_corso=" + tutti_gruppi.get(l).getSigla() + "\">" +

                                                "<div align=\"center\">\n" +
                                                "  <div style=\"width:100%;\">\n" +
                                                "    <img src=\"https://economia.unich.it/html/images/categorie/" + tutti_gruppi.get(l).getSigla().replace("/", "").toLowerCase() + ".jpg\" alt=\"Norway\" style=\"width:50%\">\n" +
                                                "    <div style=\"margin:-15px 0px 20px 0px;\">\n" +
                                                "      " + tutti_gruppi.get(l).getNome() + "\n" +
                                                "    </div>\n" +
                                                "  </div>\n" +
                                                "</div></a>";
                                    } else {
                                        a += "<!--<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/sezioni/id_gruppo=" + tutti_gruppi.get(l).getId() + "\"> -->" +

                                                "<div align=\"center\">\n" +
                                                "  <div style=\"width:100%;\">\n" +
                                                "    <div style=\"margin:-15px 0px 20px 0px;color:red;font-size:15px\">\n" +
                                                "      " + tutti_gruppi.get(l).getNome() + "\n" +
                                                "    </div>\n" +
                                                "  </div>\n" +
                                                "</div>" +
                                                "<!-- </a> -->";
                                   /*a += "<div>\n";
                                a += "<a style=\"text-decoration: none;color:black;\" href=\"visualizza.php?type=gruppo&id=" + tutti_gruppi.get(l).getId() + "\">";
                                a += "  <p style=\"color:red;\">" + tutti_gruppi.get(l).getNome() + "</p> \n";
                                a += "</a></div><br>";*/
                                    }
                                }
                            }
                        }

                        completa = completa.replace("[[[" + pulita + "]]]", a);
                        a = "";
                    }
                    if (modulo.equals("pag_documenti")) {
                        for (int d = 0; d < singolo.size(); d++) {
                            if (singolo.get(d).getId_gruppo() == gruppo ) {


                                Log.e("cat",cat+" ; "+singolo.get(d).getNome_cat());
                                if(singolo.get(d).getId_categoria().toLowerCase().contains("modulistica") && terzolv.toUpperCase().contains("MODULISTICA")){
                                    a += "<div>";
                                    a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/documenti/id_doc=" + singolo.get(d).getId() + "\">" +
                                        "<div class=\"card container\">\n";
                                    a+= "  <p>" + singolo.get(d).getTitolo() + "</p> \n";
                                    a+=        "</div>" + "</a></div><br>";
                                    }
                                if(!terzolv.toUpperCase().contains("MODULISTICA")){
                                    a += "<div>";
                                    a += "<a style=\"text-decoration: none;color:black;\" href=\"https://economia.unich.it/decapp/documenti/id_doc=" + singolo.get(d).getId() + "\">" +
                                                "<div class=\"card container\">\n";
                                    a+= "  <p>" + singolo.get(d).getTitolo() + "</p> \n";
                                    a+=        "</div>" + "</a></div><br>";
                                }

                            }
                        }

                        completa = completa.replace("[[[" + pulita + "]]]", a);
                        a = "";
                    }
                    if (modulo.equals("pag_appuntamenti")) {
                        if (corso != -1) { //from corso
                            Intent qq = new Intent(getApplicationContext(), ListaAvvisi.class);
                            qq.putExtra("from_corso", 1);
                            startActivity(qq);
                            finish();
                            return;
                        } else {
                            Intent qq = new Intent(getApplicationContext(), ListaAvvisi.class);
                            qq.putExtra("from_dipartimento", 1);
                            startActivity(qq);
                            finish();
                            return;
                        }

                    }
                }


                data = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                        "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
                        "<style>\n" +
                        ".card {\n" +
                        "    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\n" +
                        "    transition: 0.3s;\n" +
                        "    border-radius: 25px;\n" +
                        "    background-color:#FFF;" +
                        "    width:90%;" +
                        "   \n" +
                        "}\n" +
                        ".card_avv {   \n" +
                        "box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);  \n" +
                        "transition: 0.3s;   \n" +
                        " background-color:#FFF; width:90%;\n" +
                        " }\n" +
                        "                       \n" +
                        ".card_avv:hover {box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);}" +
                        "\n" +
                        ".card:hover {\n" +
                        "    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);\n" +
                        "}\n" +
                        "\n" +
                        "img {\n" +
                        "\n" +
                        "  margin:auto;  border-radius: 120px;\n" +
                        "}\n" +
                        "\n" +
                        ".container {\n" +
                        " font-family: Arial, Helvetica, sans-serif;\n" +
                        "    padding: 15px;\n" +
                        "}\n" +
                        "p{\n" +
                        "margin:17px 0px 17px 10px;\n" +
                        "}\n" +
                        "img{\n" +
                        "margin:-9px 0px 25px 10px;\n" +
                        "}\n" +
                        "a,a:hover, a:active, a:focus {\n" +
                        "    outline: none;\n" +
                        "    color: inherit; border: 0;\n" +
                        "}" +
                        "</style></head><body style=\"background:#d8e5f0\">" + completa + "</body>" + "</html>";
                //data = "<html>" + "<body style=\"background:#d8e5f0\">" + "<h2 align=\"center\">"+terzolv+"</h2>"+
                //  completa+"</body>" + "</html>";
                //<img style="display: block; margin-left: auto; margin-right: auto;" src="documenti/FOTO/IMG_20150729_151450.jpg" alt="" width="600" height="333">
                if (data.contains("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;")) {
                    data = data.replace("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;", "<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: none; ");
                }
                //data=data.replace("href=\"documenti/","href=\"https://economia.unich.it/documenti/");
                data = data.replace("src=\"documenti/", "src=\"https://economia.unich.it/documenti/");
                //data=data.replace("src=\"documenti/","src=\"https://economia.unich.it/documenti/");

                data = data.replace("href=\"", "href=\"https://economia.unich.it/");


                //TextView textView = (TextView) findViewById(R.id.html);
                //textView.setText(Html.fromHtml(Html.fromHtml(data).toString()));
            }

            engine.getSettings().setBuiltInZoomControls(true);
            engine.getSettings().setSupportZoom(true);
            engine.getSettings().setDisplayZoomControls(false);

            engine.getSettings().setJavaScriptEnabled(true);
            engine.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            engine.getSettings().setAppCacheEnabled(false);
            engine.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);

            engine.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    String[] a = url.split("id_persona=");
                    String[] b = url.split("id_doc=");
                    String[] c = url.split("id_corso=");
                    String[] d = url.split("id_gruppo=");

                    if (a.length != 1) {
                        Log.d("id_persona", a[1]);
                        Intent intent = new Intent(Visualizza.this, PersonaSingola.class);
                        intent.putExtra("idsing", Integer.parseInt(a[1]));
                        mContext.startActivity(intent);
                        return true;
                    }
                    if (b.length != 1) {
                        String[] valori = b[1].split("&");
                        Intent intent = new Intent(Visualizza.this, PaginaDocumento.class);
                        intent.putExtra("iddoc", Integer.parseInt(valori[0]));
                        try {
                            intent.putExtra("titolo", URLDecoder.decode(valori[1], "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            intent.putExtra("titolo", valori[1]);
                        }
                        intent.putExtra("idcat", Integer.parseInt(valori[2]));
                        mContext.startActivity(intent);
                        return true;
                    }
                    if (c.length != 1) {

                        Intent intent = new Intent(Visualizza.this, Corso.class);
                        intent.putExtra("position", c[1]);
                        mContext.startActivity(intent);
                        return true;
                    }
                    if (d.length != 1) {

                        Intent intent = new Intent(Visualizza.this, Corso.class);
                        intent.putExtra("position", "");
                        id_corso=Integer.parseInt(d[1]);
                        mContext.startActivity(intent);
                        return true;
                    }
                    if (!url.startsWith("http")) {
                        url = "https://economia.unich.it/" + url;
                    }
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    return true;
                }
            });
            engine.setClickable(false);

            engine.loadData(data, "text/html", "UTF-8");

            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshContent();
                }
            });
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View hView = navigationView.getHeaderView(0);
            ImageView logo = (ImageView) hView.findViewById(R.id.logo_dipartimento);
            ImageView logo_prec = (ImageView) hView.findViewById(R.id.logo_precedente);

            String str = "";
            String str2 = "";
            if (position != null) {
                str2 = nome_dipartimento;
                str = position.replace("/", "");
            } else {
                str = nome_dipartimento;
            }

            Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/" + str + ".png").into(logo);

            if (position != null)
                Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/" + str2 + ".png").into(logo_prec);
            else {
                logo_prec.setImageResource(R.drawable.logo_unich);
            }


            if (corso != -1) {
                if (terzolv == null)
                    text.setText("Dipartimento: " + id_dipartimento + "\n" + "Corso: " + corso + "\n" + "Secondo Livello: " + secondolv + "\n");
                else {
                    text.setText("Dipartimento: " + id_dipartimento + "\n" + "Corso: " + corso + "\n" + "Secondo Livello: " + secondolv + "\n" + "Terzo Livello: " + terzolv);
                }
            } else {
                if (terzolv == null)
                    text.setText("Dipartimento: " + id_dipartimento + "\n" + "Secondo Livello: " + secondolv + "\n");
                else {
                    text.setText("Dipartimento: " + id_dipartimento + "\n" + "Secondo Livello: " + secondolv + "\n" + "Terzo Livello: " + terzolv);
                }
            }
            setTitle(secondolv);


            if (corso == -1) setUpAdapter();
            else {
                setUpAdapterCorso();
                for (int i = 0; i < corsi.size(); i++) {
                    if (corsi.get(i).getId() == id_corso) {
                        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
                        if (corsi.get(i).getColor() == 0) {
                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#0e185a"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 1) {
                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#b30000"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 2) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#e6b800"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 3) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#00cc7a"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 4) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#00663d"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 5) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#b30077"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 6) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#804000"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 7) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#808080"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 8) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#600080"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 9) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#b38f00"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 10) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#ff704d"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                        if (corsi.get(i).getColor() == 11) {

                            ColorDrawable colorDrawable = new ColorDrawable(
                                    Color.parseColor("#33bbff"));
                            getSupportActionBar().setBackgroundDrawable(colorDrawable);
                            navigationView1.setBackground(colorDrawable);
                        }
                    }
                }
            }
        }
    }

    private void setUpAdapterCorso() {

        LinkedHashMap<String, String[]> thirdLevelq1;
        /**
         * Second level array list
         */
        List<String[]> secondLevel = new ArrayList<>();
        /**
         * Inner level data
         */
        List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


        ArrayList<String> ParentString= new ArrayList<String>();
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<Integer> ordinidia = new ArrayList<Integer>() {};

        for(int i=0;i<=corsi.size();i++) {
            if(booleanoscuola==true) {
                if (i == 0) {
                    for (int m = 0; m < scuola.size(); m++) {
                        if (scuola.get(m).getId_gruppo_scuola() == id_dipartimento) {
                            if (scuola.get(m).getId() == id_corso) {
                                parent.add(new SplashActivity.Corso(scuola.get(m).getId(), scuola.get(m).getSigla(), 0, id_dipartimento, "CS",0));
                                ParentString.add(scuola.get(m).getSigla());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",0));
                            ParentString.add("Torna a "+dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i == 2) {
                    a.add("Torna al MultiDipartimento");
                    ParentString.add("Torna al MultiDipartimento");
                }
                if (i != 0 && i != 1 && i!=2) {
                    if (scuola.get(i - 1).getId_gruppo_scuola() == id_dipartimento) {
                        if (scuola.get(i - 1).getId() != id_corso) {
                            a.add(scuola.get(i - 1).getSigla());
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS",0));
                            //ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else {
                if (i == 0) {
                    for (int m = 0; m < corsi.size(); m++) {
                        if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                            if (corsi.get(m).getId() == id_corso) {
                                //a.add(corsi.get(m).getNome());
                                parent.add(new SplashActivity.Corso(corsi.get(m).getId(), corsi.get(m).getNome(), corsi.get(m).getColor(), corsi_dipartimento, "CS",0));
                                ParentString.add(corsi.get(m).getNome());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",0));
                            ParentString.add("Torna a "+dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i == 2) {
                    ParentString.add("Torna al MultiDipartimento");
                }
                if (i != 0 && i != 1 && i!=2) {
                    if (scuola.get(i - 1).getId_gruppo_scuola() == id_dipartimento) {
                        if (scuola.get(i - 1).getId() != id_corso) {
                            a.add(scuola.get(i - 1).getSigla());
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS",0));
                            //ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }

            }
            for (int j = 0; j < livello2dec.size(); j++) {
                if(i==0){
                    //&& livello2dec.get(j).getId_pagina()<=0
                    if(livello2dec.get(j).getLivello()==1  && livello2dec.get(j).getId_gruppo()==id_corso){
                        a.add(livello2dec.get(j).getTitolo());
                        ordinidia.add(new Integer(livello2dec.get(j).getI()));
                    }
                }
                /*else{                     //Se ci sono altri espandibili oltre il dipartimento

                    for(int l=0;l<corsi.size();l++) {

                        if (corsi.get(l).getId_gruppo() == corsi_dipartimento) {
                            a.add(corsi.get(l).getNome());
                        }
                    }
                }*/
            }

            if(i==0) {
                ArrayList<String[]> des = new ArrayList<String[]>() {
                };
                thirdLevelq1 = new LinkedHashMap<>();
                for (int j = 0; j < a.size(); j++) {
                    ArrayList<String> lista = new ArrayList<>();
                    for (int k = 0; k < livello2dec.size(); k++) {
                        SplashActivity.SottoLivelli livello2=new SplashActivity.SottoLivelli(livello2dec.get(k).getI(),livello2dec.get(k).getTitolo(),livello2dec.get(k).getId_gruppo(),livello2dec.get(k).getId_pagina(),livello2dec.get(k).getLivello(),livello2dec.get(k).getLink());
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }

                    }

                    des.add(lista.toArray(new String[0]));
                    thirdLevelq1.put(a.get(j), des.get(j));
                    lista.removeAll(lista);

                }
                secondLevel.add(a.toArray(new String[0]));
                data.add(thirdLevelq1);
                a.removeAll(a);
                ordinidia.removeAll(ordinidia);
                thirdLevelq1 = null;
            }
        }

        expandableListView = (ExpandableListView) findViewById(R.id.menuvisual);
        //passing three level of information to constructor
        ThreeLevelListAdapterCorsi threeLevelListAdapterAdapter = new ThreeLevelListAdapterCorsi(this, ParentString, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.expandGroup(0);
    }


    private void setUpAdapter() {

        LinkedHashMap<String, String[]> thirdLevelq1;
        /**
         * Second level array list
         */
        List<String[]> secondLevel = new ArrayList<>();
        /**
         * Inner level data
         */
        List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


        ArrayList<String> ParentString= new ArrayList<String>();
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<Integer> ordinidia = new ArrayList<Integer>() {};

        for(int i=0;i<=corsi.size();i++) {
            if(booleanoscuola==true) {
                if (i == 0) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            //a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",0));
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i != 0 ) {
                    if (scuola.get(i - 1).getId_gruppo_scuola() == id_dipartimento) {
                        if (scuola.get(i - 1).getId() != id_corso) {
                            a.add(scuola.get(i - 1).getSigla());
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS",0));
                            ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else {
                if (i == 0) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",0));
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }

                if (i != 0) {
                    if (corsi.get(i - 1).getId_gruppo() == corsi_dipartimento) {
                        a.add(corsi.get(i - 1).getNome());
                        parent.add(new SplashActivity.Corso(corsi.get(i - 1).getId(), corsi.get(i - 1).getNome(), corsi.get(i - 1).getColor(), corsi_dipartimento, "CS",0));
                        ParentString.add(corsi.get(i - 1).getNome());
                    }
                }

            }
                for (int j = 0; j < livello2dec.size(); j++) {
                    if (i == 0) {
                        //&& livello2dec.get(j).getId_pagina()<=0
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_gruppo() == id_dipartimento) {
                            a.add(livello2dec.get(j).getTitolo());
                            ordinidia.add(new Integer(livello2dec.get(j).getI()));
                        }
                    }
                /*else{                     //Se ci sono altri espandibili oltre il dipartimento

                    for(int l=0;l<corsi.size();l++) {

                        if (corsi.get(l).getId_gruppo() == corsi_dipartimento) {
                            a.add(corsi.get(l).getNome());
                        }
                    }
                }*/
                }

            if(i==0) {
                ArrayList<String[]> des = new ArrayList<String[]>() {
                };
                thirdLevelq1 = new LinkedHashMap<>();
                for (int j = 0; j < a.size(); j++) {
                    ArrayList<String> lista = new ArrayList<>();
                    for (int k = 0; k < livello2dec.size(); k++) {
                        SplashActivity.SottoLivelli livello2=new SplashActivity.SottoLivelli(livello2dec.get(k).getI(),livello2dec.get(k).getTitolo(),livello2dec.get(k).getId_gruppo(),livello2dec.get(k).getId_pagina(),livello2dec.get(k).getLivello(),livello2dec.get(k).getLink());
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }

                    }

                    des.add(lista.toArray(new String[0]));
                    Log.d("des", des.toString());
                    thirdLevelq1.put(a.get(j), des.get(j));
                    lista.removeAll(lista);

                }
                secondLevel.add(a.toArray(new String[0]));
                data.add(thirdLevelq1);
                a.removeAll(a);
                ordinidia.removeAll(ordinidia);
                thirdLevelq1 = null;
            }
        }

        expandableListView = (ExpandableListView) findViewById(R.id.menuvisual);
        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, ParentString, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.expandGroup(0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.activity=".Visualizza";
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void makePost(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonObjRequest = new StringRequest(com.android.volley.Request.Method.POST,
                "https://economia.unich.it/decapp/contenuto/",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject c = null;
                        try {
                            c = new JSONObject(response);
                            JSONArray cacca = c.getJSONArray("records");
                            JSONObject expl = cacca.getJSONObject(0);

                            contenuto=expl.getString("testocontenuto");
                            caricaDopoRequest();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("idcontenuto", ""+pagine.get(pos_pagina).getId_contenuto());

                return postParam;
            }

        };

        requestQueue.add(jsonObjRequest);
    }

}
