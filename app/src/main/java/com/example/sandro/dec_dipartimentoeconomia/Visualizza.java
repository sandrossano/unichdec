package com.example.sandro.dec_dipartimentoeconomia;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.drawerCorso;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawerMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;

/**
 * Created by sandro on 27/03/18.
 */

public class Visualizza extends AppCompatActivity {
    int id_dipartimento;
    String secondolv;
    String terzolv;
    int corso;
    private ExpandableListView expandableListView;
    static DrawerLayout drawerVisual=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza);

        id_dipartimento=getIntent().getIntExtra("id_dip",0);
        secondolv=getIntent().getStringExtra("secondolv");
        terzolv=getIntent().getStringExtra("terzolv");
        corso=getIntent().getIntExtra("id_corso",-1);
        TextView text=(TextView)findViewById(R.id.testovisualizza);

        if(corso!=-1) {
            if (terzolv == null)
                text.setText("Dipartimento: " + id_dipartimento + "\n" + "Corso: " + corso + "\n" + "Secondo Livello: " + secondolv + "\n");
            else {
                text.setText("Dipartimento: " + id_dipartimento + "\n" + "Corso: " + corso + "\n"  + "Secondo Livello: " + secondolv + "\n" + "Terzo Livello: " + terzolv);
            }
        }
        else {
            if (terzolv == null)
                text.setText("Dipartimento: " + id_dipartimento + "\n" + "Secondo Livello: " + secondolv + "\n");
            else {
                text.setText("Dipartimento: " + id_dipartimento + "\n" + "Secondo Livello: " + secondolv + "\n" + "Terzo Livello: " + terzolv);
            }
        }
        setTitle(secondolv);

        if(corso==-1)setUpAdapter();
        else setUpAdapterCorso();

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

        if(drawerVisual!=null)drawerVisual.closeDrawer(GravityCompat.START);
        drawerMain.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.START);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerVisual = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawerCorso!=null)drawerCorso.closeDrawer(GravityCompat.START);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i != 0 && i != 1) {
                    if (scuola.get(i - 1).getId_gruppo_scuola() == id_dipartimento) {
                        if (scuola.get(i - 1).getId() != id_corso) {
                            a.add(scuola.get(i - 1).getSigla());
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS"));
                            ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else {
                if (i == 0) {
                    for (int m = 0; m < corsi.size(); m++) {
                        if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                            if (corsi.get(m).getId() == id_corso) {
                                //a.add(corsi.get(m).getNome());
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
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }

                if (i != 0 && i != 1) {
                    if (corsi.get(i - 1).getId_gruppo() == corsi_dipartimento) {
                        if (corsi.get(i - 1).getId() != id_corso) {
                            a.add(corsi.get(i - 1).getNome());
                            parent.add(new SplashActivity.Corso(corsi.get(i - 1).getId(), corsi.get(i - 1).getNome(), corsi.get(i - 1).getColor(), corsi_dipartimento, "CS"));
                            ParentString.add(corsi.get(i - 1).getNome());
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
                        if (j == 0 && a.size() == 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                lista.add(livello2dec.get(k).getTitolo());
                            }
                        }
                        if (j == a.size() - 1) {
                            if (livello2dec.get(k).getLivello() == 2 && livello2dec.get(k).getId_pagina() > -2 && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP"));
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i != 0 ) {
                    if (scuola.get(i - 1).getId_gruppo_scuola() == id_dipartimento) {
                        if (scuola.get(i - 1).getId() != id_corso) {
                            a.add(scuola.get(i - 1).getSigla());
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS"));
                            ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else {
                if (i == 0) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP"));
                            ParentString.add(dipartimenti.get(c).getSigla());
                        }
                    }
                }

                if (i != 0) {
                    if (corsi.get(i - 1).getId_gruppo() == corsi_dipartimento) {
                        a.add(corsi.get(i - 1).getNome());
                        parent.add(new SplashActivity.Corso(corsi.get(i - 1).getId(), corsi.get(i - 1).getNome(), corsi.get(i - 1).getColor(), corsi_dipartimento, "CS"));
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
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
