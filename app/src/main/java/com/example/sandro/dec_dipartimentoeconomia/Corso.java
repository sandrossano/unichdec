package com.example.sandro.dec_dipartimentoeconomia;

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

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;

/**
 * Created by sandro on 27/03/18.
 */

public class Corso extends AppCompatActivity {
    String position;
    static int id_corso;
    private ExpandableListView expandableListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corso);

        position=getIntent().getStringExtra("position");
        for(int i=0;i<corsi.size();i++) {
            if(position.equals(corsi.get(i).getNome())){
            id_corso = corsi.get(i).getId();
            setTitle(corsi.get(i).getNome());}
        }
        ArrayList<SplashActivity.Corso> listacorsiDipartimento=new ArrayList<>();
        for(int i=0;i<corsi.size();i++) {
            if (corsi.get(i).getId_gruppo() == corsi_dipartimento) {
                listacorsiDipartimento.add(corsi.get(i));
            }
        }
        TextView text=(TextView)findViewById(R.id.testocorso);

        text.setText("Dipartimento: "+id_dipartimento+"\n"+"Id_Corso: "+id_corso+"\n"+"Nome_Corso: "+position);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        for(int i=0;i<corsi.size();i++) {
            if(i==0) {
                for (int m = 0; m < corsi.size(); m++) {
                    if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                        if (corsi.get(m).getId() == id_corso) {
                            parent.add(new SplashActivity.Corso(corsi.get(m).getId(), corsi.get(m).getNome(), corsi.get(m).getColor(), corsi_dipartimento));
                            ParentString.add(corsi.get(m).getNome());
                        }
                    }
                }
            }
            if (i == 1) {
                for (int c = 0; c < dipartimenti.size(); c++) {
                    if (dipartimenti.get(c).getId() == id_dipartimento) {
                        a.add(dipartimenti.get(c).getNome());
                        parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento));
                        ParentString.add(dipartimenti.get(c).getSigla());
                    }
                }
            }

            if(i!=0 && i!=1) {
                if(corsi.get(i-2).getId_gruppo()==corsi_dipartimento) {
                    if(corsi.get(i-2).getId()!=id_corso) {
                        a.add(corsi.get(i - 2).getNome());
                        parent.add(new SplashActivity.Corso(corsi.get(i - 2).getId(), corsi.get(i - 2).getNome(), corsi.get(i - 2).getColor(), corsi_dipartimento));
                        ParentString.add(corsi.get(i - 2).getNome());
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

        expandableListView = (ExpandableListView) findViewById(R.id.menucorso);
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
