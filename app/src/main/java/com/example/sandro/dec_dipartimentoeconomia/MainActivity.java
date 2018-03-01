package com.example.sandro.dec_dipartimentoeconomia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
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
import android.widget.AdapterView;
import android.widget.ExpandableListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Categoria> categorie = SplashActivity.categorie;
    public static JSONArray listaPersone = SplashActivity.listaPersone;
    public static JSONArray listaDocumenti = SplashActivity.listaDocumenti;
    public static ArrayList<Ruoli> ruoli = SplashActivity.ruoli;
    public static ArrayList<SplashActivity.Corso> corsi = SplashActivity.corsi;
    public static ArrayList<SplashActivity.Corso> parent=new ArrayList<>();
    public static Context mContext;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView expandableListView;

    //



    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("array", livello2dec.toString());

        mContext=getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                parent.add(new SplashActivity.Corso(1,"DEC"));
                ParentString.add("DEC");
            }

            if(i!=0) {
                parent.add(new SplashActivity.Corso(corsi.get(i-1).getId(),corsi.get(i-1).getNome()));
                ParentString.add(corsi.get(i-1).getNome());
            }


            for (int j = 0; j < livello2dec.size(); j++) {
                if(i==0){
                    if(livello2dec.get(j).getLivello()==1 && livello2dec.get(j).getId_pagina()==0 && livello2dec.get(j).getId_gruppo()==1){
                        a.add(livello2dec.get(j).getTitolo());
                        ordinidia.add(new Integer(livello2dec.get(j).getI()));
                    }
                }
                else{
                    if(livello2dec.get(j).getLivello()==1 && livello2dec.get(j).getId_pagina()<=0 && livello2dec.get(j).getId_gruppo()==corsi.get(i-1).getId()){
                        a.add(livello2dec.get(j).getTitolo());
                        ordinidia.add(new Integer(livello2dec.get(j).getI()));
                    }
                }
            }

            ArrayList<String[]> des = new ArrayList<String[]>() {};
            thirdLevelq1 = new LinkedHashMap<>();
            for (int j = 0; j < a.size(); j++) {
                ArrayList<String> lista=new ArrayList<>();
                for(int k=0; k<livello2dec.size();k++){
                    if(i==0){
                        if(j==0){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        if(j+1<a.size()){
                            if(j==1){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==1){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==2){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==2){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==3){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==3){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==4){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==4){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==5){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==5){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==6){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==6){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==7){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==7){if(livello2dec.get(k).getId_pagina()>0 && livello2dec.get(k).getId_gruppo()==1 && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                    }
                    else{
                        if(j==0){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<=ordinidia.get(j+1).intValue()){lista.add(livello2dec.get(k).getTitolo());}}

                        if(j+1<a.size()){
                            if(j==1){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==1){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==2){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==2){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }

                        if(j+1<a.size()){
                            if(j==3){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==3){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }

                        if(j+1<a.size()){
                            if(j==4){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==4){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }

                        if(j+1<a.size()){
                            if(j==5){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==5){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }

                        if(j+1<a.size()){
                            if(j==6){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==6){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }

                        if(j+1<a.size()){
                            if(j==7){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==7){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==8){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==8){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }
                        if(j+1<a.size()){
                            if(j==9){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()<ordinidia.get(j+1).intValue() && livello2dec.get(k).getI()>=ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
                        }else {
                            if(j==9){if(livello2dec.get(k).getLivello()==2 && livello2dec.get(k).getId_pagina()>-2 && livello2dec.get(k).getId_gruppo()==corsi.get(i-1).getId() && livello2dec.get(k).getI()>ordinidia.get(j).intValue()){lista.add(livello2dec.get(k).getTitolo());}}
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

/*
    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("DEC");
        item1.setIconImg(android.R.drawable.ic_delete);
        // Adding data header
        listDataHeader.add(item1);

        List<String> heading1 = new ArrayList<String>();
        for(int i=0;i<livello1dec.size();i++) {
            // Adding child data DEC
            heading1.add(livello1dec.get(i));
        }

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data

        for(int i=0;i<corsi.size();i++){
            ExpandedMenuModel item2 = new ExpandedMenuModel();
            item2.setIconName(corsi.get(i));
            item2.setIconImg(android.R.drawable.ic_delete);
            listDataHeader.add(item2);

            List<String> heading2 = new ArrayList<String>();
            heading2.add("Accedi alla sezione "+corsi.get(i).toUpperCase());

            listDataChild.put(listDataHeader.get(i+1), heading2);
        }

    }
*/
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
