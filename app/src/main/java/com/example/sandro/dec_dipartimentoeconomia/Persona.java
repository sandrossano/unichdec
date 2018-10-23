package com.example.sandro.dec_dipartimentoeconomia;

import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.icu.text.RelativeDateTimeFormatter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaDocumenti;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaPersone;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContext;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivityMultiDipartimento.pswJson;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.finishdocu;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.singolo_splash;


public class Persona extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView lista;
    PersonAdapter adapter;
    ArrayList<String> persone=new ArrayList<>();
    static ArrayList<Singolo> singolo=new ArrayList<>();
    SearchView cerca;
    SearchView searchView;
    private ExpandableListView expandableListView;
    int from_dipartimento=0;
    int from_corso=0;

    private void refreshContent() {
        if(mSwipeRefreshLayout.isEnabled()) {
            adapter = new PersonAdapter(getApplicationContext(), persone, singolo);
            lista.setAdapter(adapter);
            searchView.setQuery("", false);
            searchView.setQueryHint("Cerca... ");
            searchView.clearFocus();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void makePost_persona(){

        //Persone
        // Instantiate the RequestQueue.
        RequestQueue queue5 = Volley.newRequestQueue(this);
        String url5="";
        if(from_dipartimento==1){url5="https://economia.unich.it/pag_persone.php?JSON="+pswJson+"&gruppo="+id_dipartimento;}
        else{url5="https://economia.unich.it/pag_persone.php?JSON="+pswJson+"&gruppo="+id_corso;}

// Request a string response from the provided URL.
        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, url5,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONObject c = new JSONObject(response);
                            listaPersone = c.getJSONArray("records");

                            singolo_splash.clear();
                            singolo.clear();

                            for (int i = 0; i < listaPersone.length(); i++) {
                                JSONObject expl = null;
                                try {
                                    expl = listaPersone.getJSONObject(i);
                                    String telefono = "";
                                    String email = "";
                                    String indirizzo = "";
                                    String sede = "";

                                    /*
                                    if (!expl.getString("indirizzo").equals(null)) {
                                        indirizzo = expl.getString("indirizzo");
                                    }
                                    if (!expl.getString("sede").equals(null)) {
                                        sede = expl.getString("sede");
                                    }
                                    if (!expl.getString("piano").equals(null) && !expl.getString("piano").equals("")) {
                                        sede += ", Piano: " + expl.getString("piano");
                                    }
                                    if (!expl.getString("scala").equals(null) && !expl.getString("scala").equals("")) {
                                        sede += ", Scala: " + expl.getString("scala");
                                    }*/

                                    if (!expl.getString("email").equals(null)) {
                                        email = expl.getString("email");
                                    }
                                    if (!expl.getString("telefono").equals(null)) {
                                        telefono = expl.getString("telefono");
                                    }


                                    singolo_splash.add(new Singolo(expl.getInt("id"), expl.getString("nome") + " " + expl.getString("cognome"), expl.getString("foto"), email, telefono, sede, indirizzo));
                                    singolo.add(new Singolo(expl.getInt("id"), expl.getString("nome") + " " + expl.getString("cognome"), expl.getString("foto"), email, telefono, sede, indirizzo));
                                    persone.add(expl.getString("nome") + " " + expl.getString("cognome"));
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
        findViewById(R.id.include).setVisibility(View.GONE);
        findViewById(R.id.include_pers).setVisibility(View.VISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.GONE);
        findViewById(R.id.include_org).setVisibility(View.GONE);
        findViewById(R.id.include_cons).setVisibility(View.GONE);
        findViewById(R.id.include_gruppo).setVisibility(View.GONE);
        findViewById(R.id.include_didattica).setVisibility(View.GONE);
        findViewById(R.id.include_ricerca).setVisibility(View.GONE);
        findViewById(R.id.include_doc).setVisibility(View.GONE);
        findViewById(R.id.include_apridoc).setVisibility(View.GONE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.GONE);
        findViewById(R.id.include_doc_atti).setVisibility(View.GONE);
        findViewById(R.id.include_avv).setVisibility(View.GONE);
        findViewById(R.id.include_apriavv).setVisibility(View.GONE);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=componentInfo.getShortClassName();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makePost_persona();
                refreshContent();
            }
        });



        if(getIntent().getIntExtra("from_dipartimento",0)==1) {from_dipartimento=1; setUpAdapter();}
        if(getIntent().getIntExtra("from_corso",0)==1) {from_corso=1; setUpAdapterCorso();}

        makePost_persona();

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

    public void afterPost(){
        lista = (ListView) findViewById(R.id.listview);
        lista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(absListView.canScrollList(-1)==true)mSwipeRefreshLayout.setEnabled(false);
                else{mSwipeRefreshLayout.setEnabled(true);}
            }
        });


        adapter= new PersonAdapter(getApplicationContext(),persone,singolo);

        lista.setAdapter(adapter);
        mSwipeRefreshLayout.setEnabled(true);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                PersonAdapter t= (PersonAdapter) lista.getAdapter();
                int idscelto= t.singoli.get(position).getId();
                String nomescelto= t.singoli.get(position).getNome();
                String fotoscelto= t.singoli.get(position).getFoto();
                Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                i.putExtra("idsing",idscelto);
                i.putExtra("nomesing",nomescelto);
                i.putExtra("fotosing",fotoscelto);
                i.putExtra("email",t.singoli.get(position).getEmail());
                i.putExtra("telefono_fisso",t.singoli.get(position).getTelefono_fisso());

                startActivity(i);

            }
        });
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
        getMenuInflater().inflate(R.menu.main_pers, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_pers);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }

        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        //searchView.setFocusable(false);
        searchView.setQueryHint("Cerca... ");
        searchView.clearFocus();


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do whatever you need
                return true; // KEEP IT TO TRUE OR IT DOESN'T OPEN !!
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchView.clearFocus();
                searchView.setQuery("",false);
                adapter = new PersonAdapter(getApplicationContext(), persone, singolo);
                lista.setAdapter(adapter);

                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });

        int searchImgId = this.getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView searchImage = (ImageView) searchView.findViewById(searchImgId);

        ((ViewGroup) searchImage.getParent()).removeView(searchImage);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<Singolo> tempSingolo=new ArrayList<Singolo>();
                int textlength = searchView.getQuery().length();
                temp.clear();
                tempSingolo.clear();
                for (int i = 0; i < persone.size(); i++)
                {
                    if (textlength <= persone.get(i).length())
                    {
                        if(persone.get(i).toString().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase()))
                        {
                            temp.add(persone.get(i));
                            tempSingolo.add(singolo.get(i));
                        }
                    }
                }
                lista.setAdapter(new PersonAdapter(getApplicationContext(), temp, tempSingolo));


                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
            if(from_dipartimento==1)i.putExtra("from_dipartimento",1);
            if(from_corso==1)i.putExtra("from_corso",1);
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
            if (i == 0) {
                for(int c=0;c<dipartimenti.size();c++) {
                    if(dipartimenti.get(c).getId()==id_dipartimento){
                        parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento,"DIP",0));
                        ParentString.add(dipartimenti.get(c).getSigla());}
                }
            }

            if(i!=0) {
                if(booleanoscuola==false) {
                    if (corsi.get(i - 1).getId_gruppo() == corsi_dipartimento) {
                        a.add(corsi.get(i - 1).getNome());
                        parent.add(new SplashActivity.Corso(corsi.get(i - 1).getId(), corsi.get(i - 1).getNome(), corsi.get(i - 1).getColor(), corsi_dipartimento, "CS",0));
                        ParentString.add(corsi.get(i - 1).getNome());
                    }
                }
                else{
                    a.add(SplashActivity.scuola.get(i - 1).getSigla());
                    parent.add(new SplashActivity.Corso(SplashActivity.scuola.get(i - 1).getId(), SplashActivity.scuola.get(i - 1).getSigla(), 0, id_dipartimento, "CS",0));
                    ParentString.add(SplashActivity.scuola.get(i - 1).getSigla());

                }
            }


            for (int j = 0; j < livello2dec.size(); j++) {
                if(i==0){
                    //&& livello2dec.get(j).getId_pagina()<=0
                    if(livello2dec.get(j).getLivello()==1  && livello2dec.get(j).getId_gruppo()==id_dipartimento){
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
                        SplashActivity.SottoLivelli livello2=livello2dec.get(k);
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
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

        expandableListView = (ExpandableListView) findViewById(R.id.navigationmenu);
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
            } else{
                if (i == 0) {
                    for (int m = 0; m < corsi.size(); m++) {
                        if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                            if (corsi.get(m).getId() == id_corso) {
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
                    ParentString.add("Torna al Multidipartimento");
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
                        SplashActivity.SottoLivelli livello2=livello2dec.get(k);
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){livello2.setTitolo("-> "+livello2dec.get(k).getTitolo());lista.add(livello2.getTitolo());}
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

        expandableListView = (ExpandableListView) findViewById(R.id.navigationmenu);
        //passing three level of information to constructor
        ThreeLevelListAdapterCorsi threeLevelListAdapterAdapter = new ThreeLevelListAdapterCorsi(this, ParentString, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);
        expandableListView.expandGroup(0);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }

}

class PersonAdapter extends ArrayAdapter<String>{
    Context context;
    ArrayList<String> persone;
    ArrayList<Singolo> singoli;

    PersonAdapter(Context context, ArrayList<String> titles, ArrayList<Singolo> singoli){
        super(context,R.layout.single_row,R.id.nome_lista,titles);
        persone=titles;
        this.context=context;
        this.singoli=singoli;
    }

    public ArrayList<Singolo> getSingoli(){return singoli;}

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_row,parent,false);
        ImageView myImage=(ImageView)row.findViewById(R.id.logo_dipartimento);
        TextView a= (TextView) row.findViewById(R.id.nome_lista);
        a.setText(persone.get(position));
        a.setTextColor(Color.BLACK);
        if(singoli.get(position).getFoto()!="null")
        Picasso.with(getContext()).load("https://economia.unich.it/fototessera/"+singoli.get(position).getFoto()).into(myImage);
        else{
            Picasso.with(getContext()).load("https://economia.unich.it/fototessera/persona_generica.jpg").into(myImage);
        }
        return row;
    }



}

class Singolo{
    private int id;
    private String nome;
    private String foto;
    private String email;
    private String telefono_fisso;
    private String sede;
    private String indirizzo;
    public Singolo(int id, String nomecognome, String foto,String email,String telefono,String sede, String indirizzo){
        this.id=id; this.nome=nomecognome;this.foto=foto;this.email=email;this.telefono_fisso=telefono; this.sede=sede;this.indirizzo=indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getSede() {
        return sede;
    }

    public int getId(){return id;}
    public String getNome(){return nome;}
    public String getFoto(){return foto;}
    public String getEmail(){return email;}
    public String getTelefono_fisso(){return telefono_fisso;}
}