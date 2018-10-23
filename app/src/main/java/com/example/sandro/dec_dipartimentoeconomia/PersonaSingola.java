package com.example.sandro.dec_dipartimentoeconomia;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaPersone;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivityMultiDipartimento.pswJson;
import static com.example.sandro.dec_dipartimentoeconomia.Persona.singolo;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.singolo_splash;

public class PersonaSingola extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    int idsing=0;
    String telefono_fisso="";
    String nomesing="";
    String fotosing="";
    String email="";
    String sede="";
    String indirizzo="";

    public void makePost_persona_singola(int id){

        //Persone
        // Instantiate the RequestQueue.
        RequestQueue queue5 = Volley.newRequestQueue(this);
        String url5="https://economia.unich.it/visualizza.php?type=persona&id="+id+"&JSON="+pswJson;

// Request a string response from the provided URL.
        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, url5,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONObject c = new JSONObject(response);
                            JSONObject BHO = c.getJSONObject("records");
                            JSONObject jsonArray = BHO.getJSONObject("dati_base");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject expl = null;
                                try {
                                    expl = jsonArray;
                                    if (!expl.getString("nome").equals(null)) {
                                        nomesing = expl.getString("nome")+" "+expl.getString("cognome");
                                    }else{nomesing = "";}
                                    if (!expl.getString("foto").equals(null)) {
                                        fotosing = expl.getString("foto");
                                    }else{fotosing = "";}
                                    if (!expl.getString("telefono_fisso").equals(null)) {
                                        telefono_fisso = expl.getString("telefono_fisso");
                                    }else{telefono_fisso = "";}
                                    if (!expl.getString("email").equals(null)) {
                                        email = expl.getString("email");
                                    }else{email = "";}
                                    if (!expl.getString("indirizzo").equals(null)) {
                                        indirizzo = expl.getString("indirizzo");
                                    }else{indirizzo = "";}
                                    if (!expl.getString("sede").equals(null)) {
                                        sede = expl.getString("sede");
                                    }else{sede = "";}
                                    if (!expl.getString("piano").equals(null) && !expl.getString("piano").equals("")) {
                                        sede += ", Piano: " + expl.getString("piano");
                                    }else{sede += "";}
                                    if (!expl.getString("stanza").equals(null) && !expl.getString("stanza").equals("")) {
                                        sede += ", Stanza: " + expl.getString("stanza");
                                    }else{sede += "";}

                                    } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            afterPost();

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
        queue5.getCache().clear();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);

        findViewById(R.id.include_avv).setVisibility(View.GONE);
        findViewById(R.id.include).setVisibility(View.GONE);
        findViewById(R.id.include_pers).setVisibility(View.GONE);
        findViewById(R.id.include_sing).setVisibility(View.VISIBLE);
        findViewById(R.id.include_org).setVisibility(View.GONE);
        findViewById(R.id.include_cons).setVisibility(View.GONE);
        findViewById(R.id.include_gruppo).setVisibility(View.GONE);
        findViewById(R.id.include_didattica).setVisibility(View.GONE);
        findViewById(R.id.include_ricerca).setVisibility(View.GONE);
        findViewById(R.id.include_doc).setVisibility(View.GONE);
        findViewById(R.id.include_apridoc).setVisibility(View.GONE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.GONE);
        findViewById(R.id.include_doc_atti).setVisibility(View.GONE);
        findViewById(R.id.include_apriavv).setVisibility(View.GONE);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setEnabled(false);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=componentInfo.getShortClassName();

        idsing = getIntent().getIntExtra("idsing",0);
        nomesing=getIntent().getStringExtra("nomesing");
        fotosing=getIntent().getStringExtra("fotosing");
        telefono_fisso=getIntent().getStringExtra("telefono_fisso");
        email=getIntent().getStringExtra("email");

        makePost_persona_singola(idsing);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void afterPost(){
        ImageView imm=(ImageView) findViewById(R.id.immagineSingolo);

        if(fotosing.endsWith("jpg"))
            Picasso.with(getApplicationContext()).load("https://economia.unich.it/fototessera/"+fotosing).into(imm);
        else{
            Picasso.with(getApplicationContext()).load("https://economia.unich.it/fototessera/persona_generica.jpg").into(imm);
        }
        setTitle(nomesing);

        TextView wow= (TextView) findViewById(R.id.idcacca);
        wow.setText("id: "+idsing+",nome: "+nomesing+",foto: "+fotosing );
        TextView wow1= (TextView) findViewById(R.id.tvNumber1);
        wow1.setText(telefono_fisso);
        TextView wow2= (TextView) findViewById(R.id.tvNumber3);
        wow2.setText(email);
        TextView wow5= (TextView) findViewById(R.id.tvNumber5);
        wow5.setText(sede);
        TextView wow4= (TextView) findViewById(R.id.tvNumber4);
        wow4.setText(indirizzo);

        if(email.equals("")){findViewById(R.id.email).setVisibility(View.GONE);}
        if(telefono_fisso.equals("")){findViewById(R.id.numero).setVisibility(View.GONE);}
        if(sede.equals("")){findViewById(R.id.indirizzo).setVisibility(View.GONE);}
        if(indirizzo.equals("")){findViewById(R.id.sito).setVisibility(View.GONE);}

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
            Intent i=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else if (id == R.id.person) {
            Intent i=new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.nav_dip) {
            Intent i=new Intent(getApplicationContext(), Dipartimento.class);
            startActivity(i);
        } else if (id == R.id.nav_dida) {

        } else if (id == R.id.nav_serv) {

        } else if (id == R.id.nav_serch) {

        } else if (id == R.id.nav_share) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("it.cineca.app.unich");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }
            else{

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

    public void chiamata(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String str=telefono_fisso.replace("/","");
        str=str.replace("-","");
        intent.setData(Uri.parse("tel:"+str));
        startActivity(Intent.createChooser(intent, "Chiama..."));
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
}
