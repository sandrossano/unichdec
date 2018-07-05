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
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import static android.view.View.GONE;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.categorie;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaDocumenti;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.finishdocu;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.localhost2;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.tutti_gruppi;


public class Documenti extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean finito=false;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView lista;
    DocuAdapter adapter;
    static ArrayList<String> documenti = new ArrayList<>();
    static ArrayList<Doc> singolo = new ArrayList<>();
    SearchView cerca;
    SearchView searchView;
    private ExpandableListView expandableListView;
    int from_dipartimento=0;
    int from_corso=0;
    static ArrayList<String> temp = new ArrayList<String>();
    static ArrayList<Doc> tempSingolo=new ArrayList<Doc>();
    static int pos_spinner=0;
    static ArrayList<SplashActivity.Corso> Corsi=new ArrayList<SplashActivity.Corso>();
    static ArrayList<String> ListaCorsi=new ArrayList<String>();
    static ArrayList<SplashActivity.Gruppi> Insegnamenti=new ArrayList<SplashActivity.Gruppi>();
    static ArrayList<String> ListaIns=new ArrayList<String>();
    ProgressBar p;
    int id_corso=0;

    private void refreshContent() {
        if(mSwipeRefreshLayout.isEnabled() && finito) {
            adapter = new DocuAdapter(getApplicationContext(), documenti, singolo);
            lista.setAdapter(adapter);
            cerca.setQuery("", false);
            cerca.setQueryHint("Cerca... ");
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if(!finito)mSwipeRefreshLayout.setRefreshing(false);
    }
    ArrayList<Doc> singolo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=componentInfo.getShortClassName();

        p=(ProgressBar) findViewById(R.id.progressBarDocu);
        DrawableCompat.setTint(p.getIndeterminateDrawable(),Color.DKGRAY);
        findViewById(R.id.include).setVisibility(GONE);
        findViewById(R.id.include_pers).setVisibility(GONE);
        findViewById(R.id.include_sing).setVisibility(GONE);
        findViewById(R.id.include_org).setVisibility(GONE);
        findViewById(R.id.include_cons).setVisibility(GONE);
        findViewById(R.id.include_gruppo).setVisibility(GONE);
        findViewById(R.id.include_didattica).setVisibility(GONE);
        findViewById(R.id.include_ricerca).setVisibility(GONE);
        findViewById(R.id.include_doc).setVisibility(View.VISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(GONE);
        findViewById(R.id.include_doc_verbali).setVisibility(GONE);
        findViewById(R.id.include_doc_atti).setVisibility(GONE);
        findViewById(R.id.include_avv).setVisibility(GONE);

        if(getIntent().getIntExtra("from_dipartimento",0)==1) {from_dipartimento=1; setUpAdapter();}
        if(getIntent().getIntExtra("from_corso",0)==1) {from_corso=1; setUpAdapterCorso();}
        id_corso=getIntent().getIntExtra("id_corso",0);
        Corsi.clear();
        ListaCorsi.clear();
        Insegnamenti.clear();
        ListaIns.clear();

        Spinner s2=(Spinner) findViewById(R.id.spinner2);
        s2.setEnabled(false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        lista = (ListView) findViewById(R.id.listview_docu);
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

        CaricaLista();
        caricato();
        snipper();




    }

    public void caricato() {
        if (finishdocu) {
            ListView lista = (ListView) findViewById(R.id.listview_docu);
            lista.setVisibility(View.VISIBLE);
            p.setVisibility(GONE);
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
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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




    public void CaricaLista(){


                        lista = (ListView) findViewById(R.id.listview_docu);

                        cerca = (SearchView) findViewById(R.id.cerca_docu);

                        singolo2=singolo;

            adapter = new DocuAdapter(getApplicationContext(), documenti, singolo);

                        lista.setAdapter(adapter);
                        lista.setAdapter(adapter);

                    }

    public void snipper(){
        final ListView lista = (ListView) findViewById(R.id.listview_docu);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        Corsi.add(null);
        ListaCorsi.add("Seleziona il Corso");
        if (!booleanoscuola) {
            for (int i = 0; i < corsi.size(); i++) {
                if (corsi.get(i).getId_gruppo() == corsi_dipartimento) {
                    ListaCorsi.add(corsi.get(i).getNome());
                    Corsi.add(corsi.get(i));
                }
            }
        }else{
            for (int i = 0; i < corsi.size(); i++) {
                if (corsi.get(i).getTipo_gruppo().equals("CS")) {
                    ListaCorsi.add(corsi.get(i).getNome());
                    Corsi.add(corsi.get(i));
                }
            }
        }
        String[] items = ListaCorsi.toArray(new String[ListaCorsi.size()]);
        //String[] items = new String[]{"CLEA", "CLEC", "CLEII","CLEA/M","CLEC/M"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(Documenti.this,R.array.corsi,android.R.layout.simple_spinner_item)  ;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Documenti.this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter2);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                pos_spinner=position;
                temp.clear();tempSingolo.clear();
                        Log.d("query",searchView.getQuery()+","+position);
                        if (searchView != null) {
                        if (position != 0) {

                            for (int i = 0; i < singolo.size(); i++) {
                                    if ((singolo.get(i).getId_gruppo() == Corsi.get(position).getId() ||
                                            singolo.get(i).getId_gruppo_padre() == Corsi.get(position).getId()) &&
                                            singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {

                                        temp.add(singolo.get(i).getTitolo());
                                        tempSingolo.add(singolo.get(i));


                                    }
                                }
                            ListaIns.clear();
                            Insegnamenti.clear();
                            snipper2();
                            } else{
                            Spinner dropdown = findViewById(R.id.spinner2);
                            dropdown.setEnabled(false);
                                for (int i = 0; i < singolo.size(); i++) {
                                    if(id_corso==0){
                                        if (singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {
                                            temp.add(singolo.get(i).getTitolo());
                                            tempSingolo.add(singolo.get(i));
                                        }
                                    }else {
                                        Spinner spinner1=(Spinner) findViewById(R.id.spinner1);
                                        spinner1.setVisibility(GONE);
                                        TextView t=(TextView) findViewById(R.id.textView11);
                                        t.setVisibility(View.VISIBLE);
                                        for(int h=0;h<corsi.size();h++){
                                            if(corsi.get(h).getId()==id_corso){t.setText(corsi.get(h).getNome());}
                                        }
                                        if ((singolo.get(i).getId_gruppo() == id_corso ||
                                                singolo.get(i).getId_gruppo_padre() == id_corso) &&
                                                singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {

                                            temp.add(singolo.get(i).getTitolo());
                                            tempSingolo.add(singolo.get(i));


                                        }
                                    }
                                }
                            ListaIns.clear();
                            Insegnamenti.clear();
                        }
                            searchView.clearFocus();
                        }

                lista.setAdapter(new DocuAdapter(getApplicationContext(), temp, tempSingolo));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void snipper2(){
        final ListView lista = (ListView) findViewById(R.id.listview_docu);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner2);
        dropdown.setEnabled(true);
        //create a list of items for the spinner.

        Log.d("ins",""+tutti_gruppi.size());
        Log.d("pos",""+pos_spinner);
        if(pos_spinner!=0) {
        Log.d("ins",""+tutti_gruppi.size());
                for (int i = 0; i < tutti_gruppi.size(); i++) {
                    if (tutti_gruppi.get(i).getTipo_gruppo().equals("INS")&& tutti_gruppi.get(i).getId_gruppo()==Corsi.get(pos_spinner).getId()) {
                        ListaIns.add(tutti_gruppi.get(i).getNome());
                        Insegnamenti.add(tutti_gruppi.get(i));
                    }
                }

        }
        Collections.sort(ListaIns);
        Collections.sort(Insegnamenti, new Comparator<SplashActivity.Gruppi>() {
            @Override
            public int compare(SplashActivity.Gruppi fruit2, SplashActivity.Gruppi fruit1)
            {

                return  fruit1.getNome().compareTo(fruit2.getNome());
            }
        });
        ListaIns.add(0,"Scegli l'insegnamento");
        Insegnamenti.add(0,null);
        String[] items = ListaIns.toArray(new String[ListaIns.size()]);
        //String[] items = new String[]{"CLEA", "CLEC", "CLEII","CLEA/M","CLEC/M"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(Documenti.this,R.array.corsi,android.R.layout.simple_spinner_item)  ;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Documenti.this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter2);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //pos_spinner=position;
                //temp.clear();tempSingolo.clear();
                if (searchView != null) {
                    if (position != 0) {

                        /*for (int i = 0; i < singolo.size(); i++) {
                            if ((singolo.get(i).getId_gruppo() == Corsi.get(position).getId() ||
                                    singolo.get(i).getId_gruppo_padre() == Corsi.get(position).getId()) &&
                                    singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {
                                Log.d("corso", Corsi.get(position).getId() + "");
                                //temp.add(singolo.get(i).getTitolo());
                                //tempSingolo.add(singolo.get(i));


                            }
                        }*/
                    } else{

                        /*for (int i = 0; i < singolo.size(); i++) {
                            if (singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())) {
                                //temp.add(singolo.get(i).getTitolo());
                                //tempSingolo.add(singolo.get(i));
                            }
                        }*/
                    }
                    searchView.clearFocus();
                }
                //lista.setAdapter(new DocuAdapter(getApplicationContext(), temp, tempSingolo));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



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
        getMenuInflater().inflate(R.menu.main_doc, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_doc);
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
                adapter = new DocuAdapter(getApplicationContext(), documenti, singolo);
                Spinner s=(Spinner) findViewById(R.id.spinner1);
                s.setSelection(0);
                lista.setAdapter(adapter);
                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                DocuAdapter t = (DocuAdapter) lista.getAdapter();
                int idscelto = t.singoli.get(position).getId();
                String titolo = t.singoli.get(position).getTitolo();
                String descrizione = t.singoli.get(position).getDescrizione();
                String data = t.singoli.get(position).getData();
                int idcat = t.singoli.get(position).getId_categoria();
                int dimensione = t.singoli.get(position).getDimensione();
                String estensione = t.singoli.get(position).getEstensione();
                String link = t.singoli.get(position).getLink();

                String ambito="";
                for(int i=0;i<tutti_gruppi.size();i++){
                    if(t.singoli.get(position).getId_gruppo()==tutti_gruppi.get(i).getId()){ambito=tutti_gruppi.get(i).getNome();break;}
                }
                for(int i=0;i<dipartimenti.size();i++){
                    if(t.singoli.get(position).getId_gruppo()==dipartimenti.get(i).getId()){ambito=dipartimenti.get(i).getNome();break;}
                }

                Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                i.putExtra("iddoc", idscelto);
                i.putExtra("idcat", idcat);
                i.putExtra("titolo", titolo);
                i.putExtra("descrizione", descrizione);
                i.putExtra("data", data);
                i.putExtra("dimensione", dimensione);
                i.putExtra("estensione", estensione);
                i.putExtra("ambito", ambito);
                i.putExtra("link", link);
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
                    if (pos_spinner==0) {
                        int textlength = searchView.getQuery().length();
                        temp.clear();
                        tempSingolo.clear();
                        for (int i = 0; i < documenti.size(); i++) {
                            if (textlength <= documenti.get(i).length()) {
                                if (documenti.get(i).toString().toUpperCase().trim().contains(s.trim().toUpperCase())) {
                                    temp.add(documenti.get(i));
                                    tempSingolo.add(singolo.get(i));
                                }
                            }
                        }
                    }
                    else{
                        int textlength = searchView.getQuery().length();
                        temp.clear();
                        tempSingolo.clear();
                        for (int i = 0; i < singolo.size(); i++) {
                            if (textlength <= singolo.size()) {
                                if (singolo.get(i).getTitolo().toUpperCase().trim().contains(searchView.getQuery().toString().trim().toUpperCase())&&
                                        (singolo.get(i).getId_gruppo() == Corsi.get(pos_spinner).getId() ||
                                        singolo.get(i).getId_gruppo_padre() == Corsi.get(pos_spinner).getId())) {
                                    temp.add(singolo.get(i).getTitolo());
                                    tempSingolo.add(singolo.get(i));
                                }
                            }
                        }
                    }
                lista.setAdapter(new DocuAdapter(getApplicationContext(), temp, tempSingolo));


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

class DocuAdapter extends ArrayAdapter<String>{
    Context context;
    ArrayList<String> documenti;
    ArrayList<Doc> singoli;

    DocuAdapter(Context context,ArrayList<String> titles, ArrayList<Doc> singoli){
        super(context,R.layout.single_row_doc,R.id.nome_lista,titles);
        documenti=titles;
        this.context=context;
        this.singoli=singoli;
    }

    public ArrayList<Doc> getSingoli(){return singoli;}

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_row_doc,parent,false);
        TextView a= (TextView) row.findViewById(R.id.nome_lista);
        TextView b= (TextView) row.findViewById(R.id.testoavviso);
        TextView c= (TextView) row.findViewById(R.id.testodata);
        TextView d= (TextView) row.findViewById(R.id.testodim);
        TextView e= (TextView) row.findViewById(R.id.testocat);
        TextView f= (TextView) row.findViewById(R.id.testoamb);
        String nome="";
        for(int i=0;i<tutti_gruppi.size();i++){
            if(singoli.get(position).getId_gruppo()==tutti_gruppi.get(i).getId()){nome=tutti_gruppi.get(i).getNome();break;}
        }
        for(int i=0;i<dipartimenti.size();i++){
            if(singoli.get(position).getId_gruppo()==dipartimenti.get(i).getId()){nome=dipartimenti.get(i).getNome();break;}
        }
        f.setText(nome);
        e.setText(singoli.get(position).getNome_cat());
        d.setText(""+singoli.get(position).getDimensione());
        c.setText(singoli.get(position).getData());
        b.setText(singoli.get(position).getDescrizione());
        a.setText(documenti.get(position).toUpperCase());
        a.setTextColor(Color.BLACK);
        return row;
    }

}

class Doc{
    private int id;
    private String titolo;
    private String descrizione;
    private String data;
    private String estensione;
    private String link;
    private int id_categoria;
    private int dimensione;
    private String nome_cat;
    private String nome_gruppo;
    private int id_gruppo;
    private int id_gruppo_padre;
    public Doc(int id, String titolo, int id_categoria,String descrizione,String data,int dimensione,String estensione,String link,String nome_cat,String nome_gruppo, int id_gruppo,int id_gruppo_padre) {
        this.id = id;
        this.titolo = titolo;
        this.id_categoria = id_categoria;
        this.link = link;
        this.descrizione = descrizione;
        this.data = data;
        this.dimensione = dimensione;
        this.estensione = estensione;
        this.nome_cat=nome_cat;
        this.nome_gruppo=nome_gruppo;
        this.id_gruppo=id_gruppo;
        this.id_gruppo_padre=id_gruppo_padre;

    }
    public int getId(){return id;}
    public String getTitolo(){return titolo;}
    public String getEstensione(){return estensione;}
    public String getData(){return data;}
    public String getLink(){return link;}
    public int getId_categoria(){return id_categoria;}
    public String getNome_cat(){return nome_cat;}
    public int getId_gruppo(){return id_gruppo;}
    public String getNome_gruppo(){return nome_gruppo;}
    public int getDimensione(){return dimensione;}
    public String getDescrizione(){return descrizione;}
    public int getId_gruppo_padre() {
        return id_gruppo_padre;
    }
}
class Categoria{
    private int id;
    private String nome_gruppo;
    private int id_gruppo;
    private String nome_cat;
    private int id_persona;
    private int corso;

    public Categoria(int id, String nome, int id_gruppo,String nome_cat,int id_persona, int corso){
        this.id=id; this.nome_gruppo=nome;this.id_gruppo=id_gruppo;this.nome_cat=nome_cat;this.id_persona=id_persona;this.corso=corso;
    }
    public int getId(){return id;}

    public int getCorso() {return corso;}
    public int getId_persona() {return id_persona;}
    public String getNome(){return nome_gruppo;}
    public int getId_gruppo(){return id_gruppo;}
    public String getNome_cat(){return nome_cat;}

}
