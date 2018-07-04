package com.example.sandro.dec_dipartimentoeconomia;

import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.categorie;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaDocumenti;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.appuntamenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.r_corsi;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.tutti_gruppi;


public class ListaAvvisi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean finito=false;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView lista;
    AvvAdapter adapter;
    ArrayList<String> documenti = new ArrayList<>();
    ArrayList<SplashActivity.Appuntamento> singolo = new ArrayList<>();
    SearchView cerca;
    SearchView searchView;
    private ExpandableListView expandableListView;
    int from_dipartimento=0;
    int from_corso=0;
    boolean caricati=false;

    private void refreshContent() {
        if(mSwipeRefreshLayout.isEnabled() && finito) {
            adapter = new AvvAdapter(getApplicationContext(), documenti, singolo);
            lista.setAdapter(adapter);

            mSwipeRefreshLayout.setRefreshing(false);
        }
        if(!finito)mSwipeRefreshLayout.setRefreshing(false);
    }
    ArrayList<SplashActivity.Appuntamento> singolo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);

        ProgressBar p=(ProgressBar) findViewById(R.id.progressBar);
        DrawableCompat.setTint(p.getIndeterminateDrawable(),Color.DKGRAY);
        findViewById(R.id.include).setVisibility(View.GONE);
        findViewById(R.id.include_avv).setVisibility(View.VISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.GONE);
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
        findViewById(R.id.include_apriavv).setVisibility(View.GONE);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=componentInfo.getShortClassName();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        lista = (ListView) findViewById(R.id.listview_avv);
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

        final LinearLayout layout=(LinearLayout) findViewById(R.id.layout_persone);
        SearchView cerca= (SearchView) findViewById(R.id.cerca_docu);

        cerca.setIconified(false);
        cerca.setQueryHint("Cerca... ");
        cerca.setFocusable(false);
        cerca.clearFocus();


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

        if(getIntent().getIntExtra("from_dipartimento",0)==1) {from_dipartimento=1; setUpAdapter();}
        if(getIntent().getIntExtra("from_corso",0)==1) {from_corso=1; setUpAdapterCorso();}


        CaricaLista();
        caricato();
        //snipper();


    }

    public void caricato() {
        if(caricati) {
            ListView lista = (ListView) findViewById(R.id.listview_avv);
            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            finito=true;
            lista.setVisibility(View.VISIBLE);
        }
        else{
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    caricato();
                }
            },100);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


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
                        parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento,"DIP"));
                        ParentString.add(dipartimenti.get(c).getSigla());}
                }
            }

            if(i!=0) {
                if(booleanoscuola==false) {
                    if (corsi.get(i - 1).getId_gruppo() == corsi_dipartimento) {
                        a.add(corsi.get(i - 1).getNome());
                        parent.add(new SplashActivity.Corso(corsi.get(i - 1).getId(), corsi.get(i - 1).getNome(), corsi.get(i - 1).getColor(), corsi_dipartimento, "CS"));
                        ParentString.add(corsi.get(i - 1).getNome());
                    }
                }
                else{
                    a.add(SplashActivity.scuola.get(i - 1).getSigla());
                    parent.add(new SplashActivity.Corso(SplashActivity.scuola.get(i - 1).getId(), SplashActivity.scuola.get(i - 1).getSigla(), 0, id_dipartimento, "CS"));
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
                        SplashActivity.SottoLivelli livello2=new SplashActivity.SottoLivelli(livello2dec.get(k).getI(),livello2dec.get(k).getTitolo(),livello2dec.get(k).getId_gruppo(),livello2dec.get(k).getId_pagina(),livello2dec.get(k).getLivello(),livello2dec.get(k).getLink());
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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
                                parent.add(new SplashActivity.Corso(scuola.get(m).getId(), scuola.get(m).getSigla(), 0, id_dipartimento, "CS"));
                                ParentString.add(scuola.get(m).getSigla());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP"));
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
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS"));
                            //ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else{
                if (i == 0) {
                    for (int m = 0; m < corsi.size(); m++) {
                        if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                            if (corsi.get(m).getId() == id_corso) {
                                parent.add(new SplashActivity.Corso(corsi.get(m).getId(), corsi.get(m).getNome(), corsi.get(m).getColor(), corsi_dipartimento, "CS"));
                                ParentString.add(corsi.get(m).getNome());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP"));
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
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS"));
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
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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




    public void CaricaLista() {

        if (from_dipartimento==1) {
            //AVVISI
            for (int i=0;i<appuntamenti.size();i++){
                if(appuntamenti.get(i).getId_gruppo()==id_dipartimento){
                    singolo.add(new SplashActivity.Appuntamento(appuntamenti.get(i).getId(), appuntamenti.get(i).getTitolo(), appuntamenti.get(i).getData_inizio(), appuntamenti.get(i).getData_fine(), appuntamenti.get(i).getData_inizio_pubb(), appuntamenti.get(i).getData_fine_pubb(), appuntamenti.get(i).getDescrizione(), appuntamenti.get(i).getId_gruppo(), appuntamenti.get(i).getId_contenuto()));
                    documenti.add(appuntamenti.get(i).getTitolo());
                    continue;}
                for (int j=0;j<r_corsi.size();j++){
                    if (r_corsi.get(j).getId_gruppo()==id_dipartimento && r_corsi.get(j).getId()==appuntamenti.get(i).getId_gruppo()){
                        singolo.add(new SplashActivity.Appuntamento(appuntamenti.get(i).getId(), appuntamenti.get(i).getTitolo(), appuntamenti.get(i).getData_inizio(), appuntamenti.get(i).getData_fine(), appuntamenti.get(i).getData_inizio_pubb(), appuntamenti.get(i).getData_fine_pubb(), appuntamenti.get(i).getDescrizione(), appuntamenti.get(i).getId_gruppo(), appuntamenti.get(i).getId_contenuto()));
                        documenti.add(appuntamenti.get(i).getTitolo());}
                }
            }

            caricati = true;

            singolo2 = singolo;
            adapter = new AvvAdapter(getApplicationContext(), documenti, singolo);

            lista.setAdapter(adapter);
        }
        else {
            for (int i=0;i<appuntamenti.size();i++){
                if(appuntamenti.get(i).getId_gruppo()==id_corso){
                    singolo.add(new SplashActivity.Appuntamento(appuntamenti.get(i).getId(), appuntamenti.get(i).getTitolo(), appuntamenti.get(i).getData_inizio(), appuntamenti.get(i).getData_fine(), appuntamenti.get(i).getData_inizio_pubb(), appuntamenti.get(i).getData_fine_pubb(), appuntamenti.get(i).getDescrizione(), appuntamenti.get(i).getId_gruppo(), appuntamenti.get(i).getId_contenuto()));
                    documenti.add(appuntamenti.get(i).getTitolo());
                    continue;}
                for (int j=0;j<tutti_gruppi.size();j++){
                    if (tutti_gruppi.get(j).getId()==appuntamenti.get(i).getId_gruppo() && tutti_gruppi.get(j).getId_gruppo()==id_corso){
                        singolo.add(new SplashActivity.Appuntamento(appuntamenti.get(i).getId(), appuntamenti.get(i).getTitolo(), appuntamenti.get(i).getData_inizio(), appuntamenti.get(i).getData_fine(), appuntamenti.get(i).getData_inizio_pubb(), appuntamenti.get(i).getData_fine_pubb(), appuntamenti.get(i).getDescrizione(), appuntamenti.get(i).getId_gruppo(), appuntamenti.get(i).getId_contenuto()));
                        documenti.add(appuntamenti.get(i).getTitolo());}
                }
                if(appuntamenti.get(i).getId_gruppo()==1270){
                    singolo.add(new SplashActivity.Appuntamento(appuntamenti.get(i).getId(), appuntamenti.get(i).getTitolo(), appuntamenti.get(i).getData_inizio(), appuntamenti.get(i).getData_fine(), appuntamenti.get(i).getData_inizio_pubb(), appuntamenti.get(i).getData_fine_pubb(), appuntamenti.get(i).getDescrizione(), appuntamenti.get(i).getId_gruppo(), appuntamenti.get(i).getId_contenuto()));
                    documenti.add(appuntamenti.get(i).getTitolo());
                    continue;}
            }

            caricati = true;

            singolo2 = singolo;
            adapter = new AvvAdapter(getApplicationContext(), documenti, singolo);

            lista.setAdapter(adapter);
        }
    }
    class AvvAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<String> documenti;
        ArrayList<SplashActivity.Appuntamento> singoli;

        AvvAdapter(Context context,ArrayList<String> titles, ArrayList<SplashActivity.Appuntamento> singoli){
            super(context,R.layout.single_row_doc,R.id.nome_lista,titles);
            documenti=titles;
            this.context=context;
            this.singoli=singoli;
        }

        public ArrayList<SplashActivity.Appuntamento> getSingoli(){return singoli;}

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_row_avviso,parent,false);
            TextView b= (TextView) row.findViewById(R.id.data1);
            TextView a= (TextView) row.findViewById(R.id.testoavviso);
            TextView t_a=(TextView)row.findViewById(R.id.testo_ambito_row);
            t_a.setTextColor(Color.GRAY);

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy", Locale.ITALY);
            Date date = null;


            TextView data1=(TextView)findViewById(R.id.data1);
            try {
                date = format1.parse(singoli.get(position).getData_inizio());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            b.setText(format2.format(date));
            a.setText(singoli.get(position).getTitolo());


            String nome4="";
            for(int i=0;i<tutti_gruppi.size();i++){
                if(singoli.get(position).getId_gruppo()==tutti_gruppi.get(i).getId()){nome4=tutti_gruppi.get(i).getNome();t_a.setText(nome4);;break;}
            }
            for(int i=0;i<dipartimenti.size();i++){
                if(singoli.get(position).getId_gruppo()==dipartimenti.get(i).getId()){nome4=dipartimenti.get(i).getNome();t_a.setText(nome4);;break;}
            }



            return row;
        }

    }
    /*
    public void snipper(){
        final ListView lista = (ListView) findViewById(R.id.listview_avv);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        //String[] items = new String[]{"CLEA", "CLEC", "CLEII","CLEA/M","CLEC/M"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(ListaAvvisi.this,R.array.corsi,android.R.layout.simple_spinner_item)  ;
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Documenti.this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter2);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<Doc> tempSingolo=new ArrayList<Doc>();
                temp.clear();

                switch (position) {
                    case 0:
                        return;

                    case 1:
                        for (int n = 0; n < singolo2.size(); n++)
                        {
                            for(int i=0;i<categorie.size();i++){
                                if (categorie.get(i).getCorso()==74){
                                    if (categorie.get(i).getId()==singolo2.get(n).getId_categoria()){
                                        temp.add(singolo2.get(n).getTitolo());
                                        tempSingolo.add(singolo2.get(n));

                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int n = 0; n < singolo2.size(); n++)
                        {
                            for(int i=0;i<categorie.size();i++){
                                if (categorie.get(i).getCorso()==66){
                                    if (categorie.get(i).getId()==singolo2.get(n).getId_categoria()){
                                        temp.add(singolo2.get(n).getTitolo());
                                        tempSingolo.add(singolo2.get(n));

                                    }
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int n = 0; n < singolo2.size(); n++)
                        {
                            for(int i=0;i<categorie.size();i++){
                                if (categorie.get(i).getCorso()==59){
                                    if (categorie.get(i).getId()==singolo2.get(n).getId_categoria()){
                                        temp.add(singolo2.get(n).getTitolo());
                                        tempSingolo.add(singolo2.get(n));

                                    }
                                }
                            }
                        }
                        break;
                    case 4:
                        for (int n = 0; n < singolo2.size(); n++)
                        {
                            for(int i=0;i<categorie.size();i++){
                                if (categorie.get(i).getCorso()==75){
                                    if (categorie.get(i).getId()==singolo2.get(n).getId_categoria()){
                                        temp.add(singolo2.get(n).getTitolo());
                                        tempSingolo.add(singolo2.get(n));

                                    }
                                }
                            }
                        }
                        break;
                    case 5:
                        for (int n = 0; n < singolo2.size(); n++)
                        {
                            for(int i=0;i<categorie.size();i++){
                                if (categorie.get(i).getCorso()==73){
                                    if (categorie.get(i).getId()==singolo2.get(n).getId_categoria()){
                                        temp.add(singolo2.get(n).getTitolo());
                                        tempSingolo.add(singolo2.get(n));

                                    }
                                }
                            }
                        }
                        break;

                }


                lista.setAdapter(new DocuAdapter(getApplicationContext(), temp, tempSingolo));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_avv, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_avv);
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
        //searchView.setFocusable(true);
        searchView.setQueryHint("Cerca... ");
        searchView.clearFocus();

        int searchImgId = this.getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView searchImage = (ImageView) searchView.findViewById(searchImgId);

        ((ViewGroup) searchImage.getParent()).removeView(searchImage);
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
                adapter = new AvvAdapter(getApplicationContext(), documenti, singolo);
                lista.setAdapter(adapter);
                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                AvvAdapter t = (AvvAdapter) lista.getAdapter();
                String titolo = t.singoli.get(position).getTitolo();
                String data = t.singoli.get(position).getData_inizio();
                int id_cont=t.singoli.get(position).getId_contenuto();
                String nome4="";
                for(int i=0;i<tutti_gruppi.size();i++){
                    if(t.singoli.get(position).getId_gruppo()==tutti_gruppi.get(i).getId()){nome4=tutti_gruppi.get(i).getNome();break;}
                }
                for(int i=0;i<dipartimenti.size();i++){
                    if(t.singoli.get(position).getId_gruppo()==dipartimenti.get(i).getId()){nome4=dipartimenti.get(i).getNome();break;}
                }

                Intent i = new Intent(getApplicationContext(), PaginaAvviso.class);
                i.putExtra("id",t.singoli.get(position).getId());
                i.putExtra("titolo", titolo);
                i.putExtra("data", data);
                i.putExtra("id_cont", id_cont);
                i.putExtra("ambito", nome4);
                startActivity(i);

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<SplashActivity.Appuntamento> tempSingolo = new ArrayList<SplashActivity.Appuntamento>();
                int textlength = searchView.getQuery().length();
                temp.clear();
                for (int i = 0; i < documenti.size(); i++) {
                    if (textlength <= documenti.get(i).length()) {
                        if (documenti.get(i).toString().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {
                            temp.add(documenti.get(i));
                            tempSingolo.add(singolo.get(i));
                        }
                    }
                }
                lista.setAdapter(new AvvAdapter(getApplicationContext(), temp, tempSingolo));


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

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            if(from_dipartimento==1)i.putExtra("from_dipartimento",1);
            if(from_corso==1)i.putExtra("from_corso",1);
            startActivity(i);
        }else if (id == R.id.organig) {
            Intent i=new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        }else if (id == R.id.atti) {
            Intent i=new Intent(getApplicationContext(), DocumentiAtti.class);
            startActivity(i);
        }else if (id == R.id.verbali) {
            Intent i=new Intent(getApplicationContext(), DocumentiVerbali.class);
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

