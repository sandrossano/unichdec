package com.example.sandro.dec_dipartimentoeconomia;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.categorie;


public class DocumentiVerbali extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);
        ProgressBar p=(ProgressBar) findViewById(R.id.progressBar3);
        DrawableCompat.setTint(p.getIndeterminateDrawable(),Color.DKGRAY);
        findViewById(R.id.include).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_org).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_cons).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_gruppo).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_didattica).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_ricerca).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.VISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

        final TextView mTextView = (TextView) findViewById(R.id.text);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout_persone);
        final SearchView cerca = (SearchView) findViewById(R.id.cerca_docu_verbali);
        cerca.setIconified(false);
        cerca.setQueryHint("Cerca... ");
        cerca.setFocusable(false);
        cerca.clearFocus();


// Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        String url = "http://proxybar.altervista.org/document/read_verbali.php";

// Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.
                                        try {
                                            final DocAdapter adapter;
                                            final ListView lista = (ListView) findViewById(R.id.listview_docu_verbali);
                                            final ArrayList<String> documenti = new ArrayList<>();
                                            final ArrayList<Doc> singolo = new ArrayList<>();

                                            JSONObject c = new JSONObject(response);
                                            JSONArray cacca = c.getJSONArray("records");


                                            for (int i = 0; i < cacca.length(); i++) {
                                                JSONObject expl = cacca.getJSONObject(i);
                                                String data = expl.getString("data_creazione").substring(0, 10);
                                                String link = expl.getString("link");
                                                String estensione = link.substring(link.length() - 3, link.length());
                                                documenti.add(expl.getString("titolo"));
                                                String nome_cat = "" + categorie.size();
                                                String nome_gruppo = "";
                                                int id_gruppo = 0;
                                                for (int j = 0; j < categorie.size(); j++) {
                                                    if (expl.getInt("id_categoria") == categorie.get(j).getId()) {
                                                        nome_cat = categorie.get(j).getNome_cat();
                                                        nome_gruppo = categorie.get(j).getNome();
                                                        id_gruppo = categorie.get(j).getId_gruppo();
                                                    }
                                                }
                                                singolo.add(new Doc(expl.getInt("id"), expl.getString("titolo"), expl.getInt("id_categoria"), expl.getString("descrizione"), data, expl.getInt("dimensione"), estensione, link, nome_cat, nome_gruppo, id_gruppo));

                                            }

                                            adapter = new DocAdapter(getApplicationContext(), documenti, singolo);

                                            lista.setAdapter(adapter);

                                            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                                                    DocAdapter t = (DocAdapter) lista.getAdapter();
                                                    int idscelto = t.singoli.get(position).getId();
                                                    String titolo = t.singoli.get(position).getTitolo();
                                                    String descrizione = t.singoli.get(position).getDescrizione();
                                                    String data = t.singoli.get(position).getData();
                                                    int idcat = t.singoli.get(position).getId_categoria();
                                                    int dimensione = t.singoli.get(position).getDimensione();
                                                    String estensione = t.singoli.get(position).getEstensione();
                                                    String link = t.singoli.get(position).getLink();

                                                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                                                    i.putExtra("iddoc", idscelto);
                                                    i.putExtra("idcat", idcat);
                                                    i.putExtra("titolo", titolo);
                                                    i.putExtra("descrizione", descrizione);
                                                    i.putExtra("data", data);
                                                    i.putExtra("dimensione", dimensione);
                                                    i.putExtra("estensione", estensione);
                                                    i.putExtra("link", link);
                                                    startActivity(i);

                                                }
                                            });

                                            cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                @Override
                                                public boolean onQueryTextSubmit(String s) {
                                                    return false;
                                                }

                                                @Override
                                                public boolean onQueryTextChange(String s) {

                                                    ArrayList<String> temp = new ArrayList<String>();
                                                    ArrayList<Doc> tempSingolo = new ArrayList<Doc>();
                                                    int textlength = cerca.getQuery().length();
                                                    temp.clear();
                                                    for (int i = 0; i < documenti.size(); i++) {
                                                        if (textlength <= documenti.get(i).length()) {
                                                            if (documenti.get(i).toString().toUpperCase().trim().contains(cerca.getQuery().toString().trim().toUpperCase())) {
                                                                temp.add(documenti.get(i));
                                                                tempSingolo.add(singolo.get(i));
                                                            }
                                                        }
                                                    }
                                                    lista.setAdapter(new DocAdapter(getApplicationContext(), temp, tempSingolo));


                                                    return false;
                                                }
                                            });
                                            lista.setAdapter(adapter);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            mTextView.append(", error, ");
                                        }


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.append("Problema Server");
                            }
                        });

// Add the request to the RequestQueue.
                        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                                30000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
                ProgressBar p=(ProgressBar) findViewById(R.id.progressBar3);
                p.setVisibility(View.GONE);
                ListView lista = (ListView) findViewById(R.id.listview_docu_verbali);
                lista.setVisibility(View.VISIBLE);
            }
        });


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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        getMenuInflater().inflate(R.menu.main_dipart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        }else if (id == R.id.organig) {
            Intent i=new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        }else if (id == R.id.atti) {
            Intent i=new Intent(getApplicationContext(), DocumentiAtti.class);
            startActivity(i);
        }else if (id == R.id.verbali) {

        }else if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
            startActivity(i);
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

        }else if (id == R.id.organig) {
            Intent i=new Intent(getApplicationContext(), Organigramma.class);
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
}
