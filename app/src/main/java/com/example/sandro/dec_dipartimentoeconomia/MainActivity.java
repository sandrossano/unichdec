package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.r_corsi;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Categoria> categorie = SplashActivity.categorie;
    public static JSONArray listaPersone = SplashActivity.listaPersone;
    public static JSONArray listaDocumenti = SplashActivity.listaDocumenti;
    public static ArrayList<Ruoli> ruoli = SplashActivity.ruoli;
    public static ArrayList<SplashActivity.Corso> corsi = SplashActivity.corsi;
    public static ArrayList<SplashActivity.Corso> parent=new ArrayList<>();
    public static Context mContext;
    static DrawerLayout drawer=null;
    static DrawerLayout drawerMain=null;

    private ExpandableListView expandableListView;
    static int id_dipartimento=1;
    static int corsi_dipartimento;
    static boolean booleanoscuola=false;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        id_dipartimento=getIntent().getIntExtra("id_dipartimento",1);
        Intent i=new Intent(this, MainActivity.class);
        i.putExtra("id_dipartimento",id_dipartimento);
        startActivity(i);
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView logo = (ImageView)hView.findViewById(R.id.logo_dipartimento);

        id_dipartimento=getIntent().getIntExtra("id_dipartimento",1);
        if(id_dipartimento==1)logo.setImageResource(R.drawable.declogo);
        if(id_dipartimento==1275)logo.setImageResource(R.drawable.dsgs);
        if(id_dipartimento==1270)logo.setImageResource(R.drawable.seags);

        for (int i=0;i<r_corsi.size();i++){
            if(r_corsi.get(i).getId_gruppo()==id_dipartimento)corsi_dipartimento=r_corsi.get(i).getId();
        }

        for(int c=0;c<dipartimenti.size();c++) {
            if (dipartimenti.get(c).getId() == id_dipartimento) {
                setTitle(dipartimenti.get(c).getSigla());
                if(dipartimenti.get(c).getTipo_gruppo().equals("SCU"))booleanoscuola=true;
                else {booleanoscuola=false;}
            }
        }

        mContext=getApplicationContext();

        setUpAdapter();

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

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerMain = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.avvisi) {
            return true;
        }else if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
            startActivity(i);
        }else if (id == R.id.item_persone) {
            Intent i=new Intent(getApplicationContext(), Persona.class);
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

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void apriAvviso(View v)
    {
        Intent i=new Intent(getApplicationContext(), Avviso.class);
        i.putExtra("id",1);
        startActivity(i);
    }
}
