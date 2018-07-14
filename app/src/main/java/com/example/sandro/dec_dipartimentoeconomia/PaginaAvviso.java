package com.example.sandro.dec_dipartimentoeconomia;

import android.Manifest;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.appuntamenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.pagine;


public class PaginaAvviso extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int iddoc;
    int id_cont;
    SwipeRefreshLayout mSwipeRefreshLayout;
    WebView wv;
    RequestQueue requestQueue;
    StringRequest jsonObjRequest;
    String contenuto="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);
        findViewById(R.id.include).setVisibility(View.GONE);
        findViewById(R.id.include_pers).setVisibility(View.GONE);
        findViewById(R.id.include_sing).setVisibility(View.GONE);
        findViewById(R.id.include_org).setVisibility(View.GONE);
        findViewById(R.id.include_cons).setVisibility(View.GONE);
        findViewById(R.id.include_gruppo).setVisibility(View.GONE);
        findViewById(R.id.include_didattica).setVisibility(View.GONE);
        findViewById(R.id.include_ricerca).setVisibility(View.GONE);
        findViewById(R.id.include_doc).setVisibility(View.GONE);
        findViewById(R.id.include_apridoc).setVisibility(View.GONE);
        findViewById(R.id.include_apriavv).setVisibility(View.VISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.GONE);
        findViewById(R.id.include_doc_atti).setVisibility(View.GONE);
        findViewById(R.id.include_avv).setVisibility(View.GONE);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=componentInfo.getShortClassName();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setEnabled(false);
        iddoc = getIntent().getIntExtra("id", 0);
        String titolo = getIntent().getStringExtra("titolo");
        String data = getIntent().getStringExtra("data");
        String ambito = getIntent().getStringExtra("ambito");
        id_cont = getIntent().getIntExtra("id_cont", 0);
        setTitle("Avviso");
        String s="id: "+iddoc+", titolo: "+titolo+", data:"+data+", id_cont:"+id_cont;

        int posizione_avviso=0;
        for(int i=0;i<appuntamenti.size();i++){
            if(appuntamenti.get(i).getId()==iddoc){posizione_avviso=i; break;}
        }
        TextView wow11 = (TextView) findViewById(R.id.testoavv3);
        wow11.setText( appuntamenti.get(posizione_avviso).getData_inizio());
        //data

        TextView wow5 = (TextView) findViewById(R.id.testoavv2);
        wow5.setText(ambito); //ambito
        TextView wow = (TextView) findViewById(R.id.testoavv);
        //wow.setText(s);
        wow.setText(titolo);

        makePost();






    }

    public void  caricaDopoRequest(){
        WebView wow2 = (WebView) findViewById(R.id.avviso_webview);
        String data2="";
        data2 = "<html>" + "<body>"+
                contenuto+
                "</body>" + "</html>";
        //data=data.replace("href=","");        rimuovi link
        data2=data2.replace("src=\"documenti/","src=\"https://economia.unich.it/documenti/");
        data2=data2.replace("href=\"documenti/","href=\"https://economia.unich.it/documenti/");

        //data=data.replace("width=\"","width=\"100%\" height=\"100%\" alt=\"");
        //if(terzolv.equals("Esami")){data=data.replace("</body>","vai alla app Uda + intent</body>");}
        wow2.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);

                    return true;
                } else {
                    return false;
                }
            }
        });
        wow2.getSettings().setJavaScriptEnabled(true);
        wow2.loadData(data2, "text/html", "UTF-8");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_dipart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.organig) {
            Intent i = new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        } else if (id == R.id.main_doc) {
            Intent i = new Intent(getApplicationContext(), Documenti.class);
            startActivity(i);
        }else if (id == R.id.atti) {
            Intent i=new Intent(getApplicationContext(), DocumentiAtti.class);
            startActivity(i);
        }else if (id == R.id.verbali) {
            Intent i=new Intent(getApplicationContext(), DocumentiVerbali.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
        */
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.organig) {
            Intent i = new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        } else if (id == R.id.nav_dip) {
            Intent i = new Intent(getApplicationContext(), Dipartimento.class);
            startActivity(i);
        } else if (id == R.id.nav_dida) {

        } else if (id == R.id.nav_serv) {

        } else if (id == R.id.nav_serch) {

        } else if (id == R.id.nav_share) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("it.cineca.app.unich");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            } else {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=it.cineca.app.unich")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=it.cineca.app.unich")));
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void makePost(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonObjRequest = new StringRequest(com.android.volley.Request.Method.POST,
                "https://economia.unich.it/visualizza.php?type=appuntamento&id="+iddoc+"&JSON=on",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject c = null;
                        try {
                            c = new JSONObject(response);
                            JSONObject cacca = c.getJSONObject("records");


                            contenuto=cacca.getString("descrizione_it");
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

        };

        requestQueue.add(jsonObjRequest);
    }
}
