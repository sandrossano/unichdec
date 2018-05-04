package com.example.sandro.dec_dipartimentoeconomia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.drawerCorso;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.position;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawerMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContext;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.nome_dipartimento;
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

    SwipeRefreshLayout mSwipeRefreshLayout;

    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        id_dipartimento=getIntent().getIntExtra("id_dip",0);
        secondolv=getIntent().getStringExtra("secondolv");
        terzolv=getIntent().getStringExtra("terzolv");
        corso=getIntent().getIntExtra("id_corso",-1);

        Intent i=new Intent(this, Visualizza.class);
        i.putExtra("id_dip",id_dipartimento);
        i.putExtra("secondolv",secondolv);
        i.putExtra("terzolv",terzolv);
        i.putExtra("id_corso",corso);
        startActivity(i);
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @SuppressLint({"ClickableViewAccessibility", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        final WebView engine = (WebView) findViewById(R.id.web_engine);
        engine.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(view.canScrollVertically(-1)==true)mSwipeRefreshLayout.setEnabled(false);
                else{mSwipeRefreshLayout.setEnabled(true);}
            }
        });

        String data = "<html>" + "<body style=\"background:#d8e5f0\">" + "<p>Il Dipartimento di Economia (DEC) eÌ€ stato istituito il 28 gennaio 2011 per poi essere formalmente ricostituito con Decreto del Decano n. 953 del 4 luglio 2012, al fine di tenere conto delle novitaÌ€ della riforma Gelmini. Attualmente si compone di 51 afferenti (12 professori ordinari, 19 professori associati, 20 ricercatori) appartenenti a differenti Settori Scientifico Disciplinari (SSD), come indicato nella tabella di seguito riportata.</p>\n" +
                "<table border=\"1\" cellpadding=\"5\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>Macro-settore</td>\n" +
                "<td>Settore Scientifico Disciplinare</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>01/A - MATEMATICA</td>\n" +
                "<td>MAT/03 - GEOMETRIA<br /> MAT/05 - ANALISI MATEMATICA<br /> MAT/06 - PROBABILITA' E STATISTICA MATEMATICA <br /> MAT/09 - RICERCA OPERATIVA</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>01/B - INFORMATICA</td>\n" +
                "<td>INF/01 - INFORMATICA</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>07/A - ECONOMIA AGRARIA ED ESTIMO</td>\n" +
                "<td>AGR/01 - ECONOMIA ED ESTIMO RURALE</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>11/B - GEOGRAFIA</td>\n" +
                "<td>M-GGR/02 - GEOGRAFIA ECONOMICO POLITICA</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>13/A &ndash; ECONOMIA</td>\n" +
                "<td>SECS-P/01 - ECONOMIA POLITICA<br /> SECS-P/02 - POLITICA ECONOMICA<br /> SECS-P/03 - SCIENZA DELLE FINANZE<br /> SECS-P/06 - ECONOMIA APPLICATA</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>13/B - ECONOMIA AZIENDALE</td>\n" +
                "<td>SECS-P/07 - ECONOMIA AZIENDALE<br /> SECS-P/10 - ORGANIZZAZIONE AZIENDALE<br /> SECS-P/11 - ECONOMIA DEGLI INTERMEDIARI FINANZIARI <br /> SECS-P/13 - SCIENZE MERCEOLOGICHE</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>13/C - STORIA ECONOMICA</td>\n" +
                "<td>SECS-P/12 - STORIA ECONOMICA</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>13/D - STATISTICA E METODI MATEMATICI PER LE DECISIONI</td>\n" +
                "<td>SECS-S/01 - STATISTICA<br /> SECS-S/03 - STATISTICA ECONOMICA</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>L'aggregazione dei SSD nel Dipartimento di Economia presenta evidenti tratti di omogeneitaÌ€ ed importanti sinergie scientifiche, in continuitaÌ€ con gli studi economici, aziendali, matematico-statistici e storico-geografici caratterizzanti â€’ nella tradizione dell'universitaÌ€ italiana â€’ la FacoltaÌ€ di Economia e Commercio (poi Economia). Ne emerge immediatamente uno spettro interdisciplinare ampio ed esaustivo, che definisce la coerenza della denominazione scelta in sede di costituzione del Dipartimento e trova riscontro nelle declaratorie dei Settori scientifico-disciplinari ex D.M. 4 ottobre 2000.</p>\n" +
                "<p>La varietaÌ€ e la coerenza scientifica dei SSD del DEC consentono â€’ nell&rsquo;ambito della Scuola delle Scienze Economiche, Aziendali, Giuridiche e Sociologiche che comprende anche il Dipartimento di Scienze Giuridiche e Sociali &ndash; di esprimere una offerta formativa in grado di continuare ad attrarre la domanda espressa da un bacino di utenza giaÌ€ vasto e consistente, ma suscettibile di ulteriore significativo ampliamento.</p>\n" +
                "<p>Nell&rsquo;intento di continuare a favorire lo sviluppo economico e culturale generale ed un piuÌ€ efficace impatto della ricerca dipartimentale sulle dinamiche socio- culturali del territorio in cui esso eÌ€ inserito, il DEC prevede di riproporre nel periodo 2015-2017 i seguenti corsi di laurea giaÌ€ attivi nei precedenti anni accademici ed in quello ancora in corso:<br /><br />Corsi di Laurea Triennale</p>\n" +
                "<ul>\n" +
                "<li><a href=\"visualizza.php?type=gruppo&amp;id=74\">Economia Aziendale</a> (Classe L-18 - Scienze dell'Economia e della Gestione Aziendale), articolato nei percorsi curricolari di Gestione Aziendale, Gestione Ambientale e Professionale;</li>\n" +
                "<li><a href=\"visualizza.php?type=gruppo&amp;id=66\">Economia e Commercio</a> (Classe L-33 - Scienze Economiche),articolato nei percorsi curricolari di Economia e Commercio e Economia e Finanza;</li>\n" +
                "<li><a href=\"visualizza.php?type=gruppo&amp;id=59\">Economia e Informatica per l'Impresa</a> (Classe L-33 - Scienze Economiche).</li>\n" +
                "</ul>\n" +
                "<p>Corsi di Laurea Magistrale</p>\n" +
                "<ul>\n" +
                "<li><a href=\"visualizza.php?type=gruppo&amp;id=75\">Economia Aziendale</a> (Classe LM-77 - Scienze Economico-aziendali), articolato nei percorsi curricolari di Direzione Aziendale, Eco-Management e Professionale;</li>\n" +
                "<li><a href=\"visualizza.php?type=gruppo&amp;id=73\">Economia e Commercio</a> (Classe LM-56 - Scienze dell'Economia).</li>\n" +
                "</ul>\n" +
                "<p>Corsi di Dottorato</p>\n" +
                "<ul>\n" +
                "<li><a \"visualizza.php?type=gruppo&amp;id=76\">Business, Institutions, Markets</a>.</li>\n" +
                "</ul>"+ "</body>" + "</html>";
        data=data.replace("href=","");
        //TextView textView = (TextView) findViewById(R.id.html);
        //textView.setText(Html.fromHtml(Html.fromHtml(data).toString()));




        engine.getSettings().setJavaScriptEnabled(true);
        engine.getSettings().setAppCacheEnabled(false);
        engine.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        engine.setClickable(false);

        engine.loadData(data, "text/html", "UTF-8");

        mContext=this;
        id_dipartimento=getIntent().getIntExtra("id_dip",0);
        secondolv=getIntent().getStringExtra("secondolv");
        terzolv=getIntent().getStringExtra("terzolv");
        corso=getIntent().getIntExtra("id_corso",-1);
        TextView text=(TextView)findViewById(R.id.testovisualizza);

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
        ImageView logo_prec = (ImageView)hView.findViewById(R.id.logo_precedente);

        String str=""; String str2="";
        if(position!=null){str2=nome_dipartimento;str = position.replace("/", "");}
        else{str=nome_dipartimento;}

        Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+str+".png").into(logo);

        if(position!=null)Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+str2+".png").into(logo_prec);
        else{logo_prec.setImageResource(R.drawable.logo_unich); }


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

        fab.setVisibility(View.GONE);

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
                            ParentString.add("Torna a "+dipartimenti.get(c).getSigla());
                        }
                    }
                }
                if (i == 2) {
                    a.add("Torna al MultiDipartimento");
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
