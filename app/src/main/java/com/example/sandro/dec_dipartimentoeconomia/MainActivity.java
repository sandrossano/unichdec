package com.example.sandro.dec_dipartimentoeconomia;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.Documenti.documenti;
import static com.example.sandro.dec_dipartimentoeconomia.Documenti.singolo;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivityMultiDipartimento.pswJson;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.appuntamenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.finish1;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.finishdocu;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.immagini_dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.localhost2;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.r_corsi;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.tutti_gruppi;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Categoria> categorie = SplashActivity.categorie;
    public static JSONArray listaPersone = SplashActivity.listaPersone;
    public static JSONArray listaDocumenti = SplashActivity.listaDocumenti;
    public static ArrayList<SplashActivity.Ruoli> ruoli = SplashActivity.ruoli;
    public static ArrayList<SplashActivity.Corso> corsi = SplashActivity.corsi;
    public static ArrayList<SplashActivity.Corso> parent=new ArrayList<>();
    public static ArrayList<SplashActivity.Immagini> immagini_multidip=new ArrayList<>();
    public static ArrayList<Doc> documenti_home = new ArrayList<>();
    public static Context mContext;
    public static Context mContextMain;
    static DrawerLayout drawer=null;
    static DrawerLayout drawerMain=null;
    private ViewPager viewPager;
    WebView mWebView;
    private int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    static String activity="";

    private ExpandableListView expandableListView;
    static int id_dipartimento=1;
    static String nome_dipartimento="";
    static int corsi_dipartimento;
    static boolean booleanoscuola=false;

    SwipeRefreshLayout mSwipeRefreshLayout;


    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(0);
                    }
                    else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                }
            });

        }
    }

    private void refreshContent() {
        if(mSwipeRefreshLayout.isEnabled()) {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        id_dipartimento=getIntent().getIntExtra("id_dipartimento",1);
        nome_dipartimento=getIntent().getStringExtra("nome_dipartimento");
        Intent i=new Intent(this, MainActivity.class);
        i.putExtra("id_dipartimento",id_dipartimento);
        i.putExtra("nome_dipartimento",nome_dipartimento);
        startActivity(i);
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    private void init() {
        immagini_multidip.clear();
        int lunghezza_foto=0;
        final Integer[] XMEN= {R.drawable.logo_unich,R.drawable.declogo,R.drawable.dsgs};
        for(int i=0;i<immagini_dec.size();i++) {
            if(immagini_dec.get(i).getId_gruppo()==id_dipartimento) {
                immagini_multidip.add(immagini_dec.get(i));lunghezza_foto++;XMENArray.add(i);
            }
        }
        final int length=lunghezza_foto;
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SlideAdapter(MainActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },500,6000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.activity=".MainActivity";
    }
    public void makePost_avvisi(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url="";
        url="https://economia.unich.it/index.php?sito="+id_dipartimento+"&JSON="+pswJson;

        StringRequest jsonObjRequest = new StringRequest(com.android.volley.Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject c = null;
                        try {
                            c = new JSONObject(response);
                            JSONObject prova;
                            try{
                                 prova = c.getJSONObject("records");
                            }catch (JSONException e){
                                findViewById(R.id.avviso1).setVisibility(View.GONE);
                                findViewById(R.id.avviso2).setVisibility(View.GONE);
                                findViewById(R.id.avviso3).setVisibility(View.GONE);
                                findViewById(R.id.avviso4).setVisibility(View.GONE);
                                findViewById(R.id.avviso5).setVisibility(View.GONE);
                                if(appuntamenti!=null)appuntamenti.clear();
                                return;
                            }


                            JSONArray avvisi = prova.getJSONArray("avvisi");

                            if(appuntamenti!=null)appuntamenti.clear();

                            for (int i = 0; i < avvisi.length(); i++) {
                                JSONObject expl = avvisi.getJSONObject(i);
                                appuntamenti.add(new SplashActivity.Appuntamento(expl.getInt("id"), expl.getString("titolo"), expl.getString("data"), expl.getString("data"), expl.getString("descrizione"), expl.getInt("id_gruppo")));
                            }
                            Log.e("appuntamenti",appuntamenti.size()+"");
                            setAppuntamenti();
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> getParam = new HashMap<String, String>();

                return getParam;
            }

        };

        requestQueue.add(jsonObjRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_dipartimento=getIntent().getIntExtra("id_dipartimento",1);
        nome_dipartimento=getIntent().getStringExtra("nome_dipartimento");

        Log.e("dipartimento",id_dipartimento+"");
        makePost_avvisi();
        makePost_doc_home();
        makePost_doc();

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        activity=".MainActivity";




        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        ScrollView scroll=(ScrollView)findViewById(R.id.scrollmain);
        scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(view.canScrollVertically(-1)==true)mSwipeRefreshLayout.setEnabled(false);
                else{mSwipeRefreshLayout.setEnabled(true);}
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView logo = (ImageView)hView.findViewById(R.id.logo_dipartimento);


        init();

        Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+nome_dipartimento+".png").into(logo);


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

        mContext=MainActivity.this;
        mContextMain=getApplicationContext();

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

        fab.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerMain = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setAppuntamenti();
        setDocumenti();
    }

private void setAppuntamenti(){
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy", Locale.ITALY);
    Date date = null;

    final ArrayList<SplashActivity.Appuntamento> temp=appuntamenti;

    String nome="";
    String nome2="";
    String nome3="";
    String nome4="";
    String nome5="";
    if(temp.size()>=1) {
        TextView data1 = (TextView) findViewById(R.id.data1);
        try {
            date = format1.parse(temp.get(0).getData_inizio());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data1.setText(format2.format(date));
        TextView testo1 = (TextView) findViewById(R.id.testoavviso);
        testo1.setText(temp.get(0).getTitolo());

        TextView testo_ambito = (TextView) findViewById(R.id.testo_ambito_1);
        nome = "";
        for (int i = 0; i < tutti_gruppi.size(); i++) {
            if (temp.get(0).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                nome = tutti_gruppi.get(i).getNome();
                break;
            }
        }
        for (int i = 0; i < dipartimenti.size(); i++) {
            if (temp.get(0).getId_gruppo() == dipartimenti.get(i).getId()) {
                nome = dipartimenti.get(i).getNome();
                break;
            }
        }
        testo_ambito.setText(nome);
        findViewById(R.id.avviso1).setVisibility(View.VISIBLE);
    }
    if(temp.size()>=2) {
        TextView data2 = (TextView) findViewById(R.id.data2);
        try {
            date = format1.parse(temp.get(1).getData_inizio());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data2.setText(format2.format(date));
        TextView testo2 = (TextView) findViewById(R.id.testoavviso2);
        testo2.setText(temp.get(1).getTitolo());

        TextView testo_ambito2 = (TextView) findViewById(R.id.testo_ambito_2);
        nome2 = "";
        for (int i = 0; i < tutti_gruppi.size(); i++) {
            if (temp.get(1).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                nome2 = tutti_gruppi.get(i).getNome();
                break;
            }
        }
        for (int i = 0; i < dipartimenti.size(); i++) {
            if (temp.get(1).getId_gruppo() == dipartimenti.get(i).getId()) {
                nome2 = dipartimenti.get(i).getNome();
                break;
            }
        }
        testo_ambito2.setText(nome2);
        findViewById(R.id.avviso2).setVisibility(View.VISIBLE);
    }

    if(temp.size()>=3) {
        TextView data3 = (TextView) findViewById(R.id.data3);
        try {
            date = format1.parse(temp.get(2).getData_inizio());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data3.setText(format2.format(date));
        TextView testo3 = (TextView) findViewById(R.id.testoavviso3);
        testo3.setText(temp.get(2).getTitolo());

        TextView testo_ambito3 = (TextView) findViewById(R.id.testo_ambito_3);
        nome3 = "";
        for (int i = 0; i < tutti_gruppi.size(); i++) {
            if (temp.get(2).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                nome3 = tutti_gruppi.get(i).getNome();
                break;
            }
        }
        for (int i = 0; i < dipartimenti.size(); i++) {
            if (temp.get(2).getId_gruppo() == dipartimenti.get(i).getId()) {
                nome3 = dipartimenti.get(i).getNome();
                break;
            }
        }
        testo_ambito3.setText(nome3);
        findViewById(R.id.avviso3).setVisibility(View.VISIBLE);
    }

    if(temp.size()>=4) {
        TextView data4 = (TextView) findViewById(R.id.data4);
        try {
            date = format1.parse(temp.get(3).getData_inizio());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data4.setText(format2.format(date));
        TextView testo4 = (TextView) findViewById(R.id.testoavviso4);
        testo4.setText(temp.get(3).getTitolo());

        TextView testo_ambito4 = (TextView) findViewById(R.id.testo_ambito_4);
        nome4 = "";
        for (int i = 0; i < tutti_gruppi.size(); i++) {
            if (temp.get(3).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                nome4 = tutti_gruppi.get(i).getNome();
                break;
            }
        }
        for (int i = 0; i < dipartimenti.size(); i++) {
            if (temp.get(3).getId_gruppo() == dipartimenti.get(i).getId()) {
                nome4 = dipartimenti.get(i).getNome();
                break;
            }
        }
        testo_ambito4.setText(nome4);
        findViewById(R.id.avviso4).setVisibility(View.VISIBLE);
    }
    if(temp.size()>=5) {
        TextView data5 = (TextView) findViewById(R.id.data5);
        try {
            date = format1.parse(temp.get(4).getData_inizio());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data5.setText(format2.format(date));
        TextView testo5 = (TextView) findViewById(R.id.testoavviso5);
        testo5.setText(temp.get(4).getTitolo());

        TextView testo_ambito5 = (TextView) findViewById(R.id.testo_ambito_5);
        nome5 = "";
        for (int i = 0; i < tutti_gruppi.size(); i++) {
            if (temp.get(4).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                nome5 = tutti_gruppi.get(i).getNome();
                break;
            }
        }
        for (int i = 0; i < dipartimenti.size(); i++) {
            if (temp.get(4).getId_gruppo() == dipartimenti.get(i).getId()) {
                nome5 = dipartimenti.get(i).getNome();
                break;
            }
        }
        testo_ambito5.setText(nome5);
        findViewById(R.id.avviso5).setVisibility(View.VISIBLE);
    }

    if(temp.size()>=1) {
        RelativeLayout avviso1 = (RelativeLayout) findViewById(R.id.avviso1);
        final String finalNome = nome;
        avviso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PaginaAvviso.class);
                i.putExtra("id", temp.get(0).getId());
                i.putExtra("titolo", temp.get(0).getTitolo());
                i.putExtra("data", temp.get(0).getData_inizio());
                //i.putExtra("id_cont", temp.get(0).getId_contenuto());
                i.putExtra("ambito", finalNome);
                startActivity(i);
            }
        });
    }
    if(temp.size()>=2) {
        RelativeLayout avviso2 = (RelativeLayout) findViewById(R.id.avviso2);
        final String finalNome1 = nome2;
        avviso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), PaginaAvviso.class);
                i2.putExtra("id", temp.get(1).getId());
                i2.putExtra("titolo", temp.get(1).getTitolo());
                i2.putExtra("data", temp.get(1).getData_inizio());
                //i2.putExtra("id_cont", temp.get(1).getId_contenuto());
                i2.putExtra("ambito", finalNome1);
                startActivity(i2);
            }
        });
    }
    if(temp.size()>=3) {
        RelativeLayout avviso3 = (RelativeLayout) findViewById(R.id.avviso3);
        final String finalNome2 = nome3;
        avviso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), PaginaAvviso.class);
                i3.putExtra("id", temp.get(2).getId());
                i3.putExtra("titolo", temp.get(2).getTitolo());
                i3.putExtra("data", temp.get(2).getData_inizio());
                //i3.putExtra("id_cont", temp.get(2).getId_contenuto());
                i3.putExtra("ambito", finalNome2);
                startActivity(i3);
            }
        });
    }
    if(temp.size()>=4) {
        RelativeLayout avviso4 = (RelativeLayout) findViewById(R.id.avviso4);
        final String finalNome3 = nome4;
        avviso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(getApplicationContext(), PaginaAvviso.class);
                i4.putExtra("id", temp.get(3).getId());
                i4.putExtra("titolo", temp.get(3).getTitolo());
                i4.putExtra("data", temp.get(3).getData_inizio());
                //i4.putExtra("id_cont", temp.get(3).getId_contenuto());
                i4.putExtra("ambito", finalNome3);
                startActivity(i4);
            }
        });
    }
    if(temp.size()>=5) {
        RelativeLayout avviso5 = (RelativeLayout) findViewById(R.id.avviso5);
        final String finalNome4 = nome5;
        avviso5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(getApplicationContext(), PaginaAvviso.class);
                i5.putExtra("id", temp.get(4).getId());
                i5.putExtra("titolo", temp.get(4).getTitolo());
                i5.putExtra("data", temp.get(4).getData_inizio());
                //i5.putExtra("id_cont", temp.get(4).getId_contenuto());
                i5.putExtra("ambito", finalNome4);
                startActivity(i5);
            }
        });
    }
    if(temp.size()==0){
        TextView data1 = (TextView) findViewById(R.id.data1);
        data1.setText("");
        TextView testo1 = (TextView) findViewById(R.id.testoavviso);
        testo1.setText("");

        TextView testo_ambito = (TextView) findViewById(R.id.testo_ambito_1);
        testo_ambito.setText("");

        TextView data2 = (TextView) findViewById(R.id.data2);
        data2.setText("");
        TextView testo2 = (TextView) findViewById(R.id.testoavviso2);
        testo2.setText("");

        TextView testo_ambito2 = (TextView) findViewById(R.id.testo_ambito_2);
        testo_ambito2.setText("");

        TextView data3 = (TextView) findViewById(R.id.data3);
        data3.setText("");
        TextView testo3 = (TextView) findViewById(R.id.testoavviso3);
        testo3.setText("");

        TextView testo_ambito3 = (TextView) findViewById(R.id.testo_ambito_3);
        testo_ambito3.setText("");

        TextView data4 = (TextView) findViewById(R.id.data4);
        data4.setText("");
        TextView testo4 = (TextView) findViewById(R.id.testoavviso4);
        testo4.setText("");

        TextView testo_ambito4 = (TextView) findViewById(R.id.testo_ambito_4);
        testo_ambito4.setText("");

        RelativeLayout avviso1 = (RelativeLayout) findViewById(R.id.avviso1);
        avviso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        RelativeLayout avviso2 = (RelativeLayout) findViewById(R.id.avviso2);
        avviso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        RelativeLayout avviso3 = (RelativeLayout) findViewById(R.id.avviso3);
        avviso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        RelativeLayout avviso4 = (RelativeLayout) findViewById(R.id.avviso4);
        avviso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }
}

    private void setDocumenti(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy", Locale.ITALY);
        Date date = null;

        final ArrayList<Doc> temp=documenti_home;

        String nome="";
        String nome2="";
        String nome3="";
        String nome4="";
        String nome5="";
        if(temp.size()>=1) {
            TextView data1 = (TextView) findViewById(R.id.data_doc1);
            try {
                date = format1.parse(temp.get(0).getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data1.setText(format2.format(date));
            TextView testo1 = (TextView) findViewById(R.id.testodocumento);
            testo1.setText(temp.get(0).getTitolo());

            TextView testo_ambito = (TextView) findViewById(R.id.testo_ambito_doc1);
            nome = "";
            for (int i = 0; i < tutti_gruppi.size(); i++) {
                if (temp.get(0).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                    nome = tutti_gruppi.get(i).getNome();
                    break;
                }
            }
            for (int i = 0; i < dipartimenti.size(); i++) {
                if (temp.get(0).getId_gruppo() == dipartimenti.get(i).getId()) {
                    nome = dipartimenti.get(i).getNome();
                    break;
                }
            }
            testo_ambito.setText(nome);
            findViewById(R.id.documento1).setVisibility(View.VISIBLE);
        }
        if(temp.size()>=2) {
            TextView data2 = (TextView) findViewById(R.id.data_doc2);
            try {
                date = format1.parse(temp.get(1).getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data2.setText(format2.format(date));
            TextView testo2 = (TextView) findViewById(R.id.testodocumento2);
            testo2.setText(temp.get(1).getTitolo());

            TextView testo_ambito2 = (TextView) findViewById(R.id.testo_ambito_doc2);
            nome2 = "";
            for (int i = 0; i < tutti_gruppi.size(); i++) {
                if (temp.get(1).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                    nome2 = tutti_gruppi.get(i).getNome();
                    break;
                }
            }
            for (int i = 0; i < dipartimenti.size(); i++) {
                if (temp.get(1).getId_gruppo() == dipartimenti.get(i).getId()) {
                    nome2 = dipartimenti.get(i).getNome();
                    break;
                }
            }
            testo_ambito2.setText(nome2);
            findViewById(R.id.documento2).setVisibility(View.VISIBLE);
        }

        if(temp.size()>=3) {
            TextView data3 = (TextView) findViewById(R.id.data_doc3);
            try {
                date = format1.parse(temp.get(2).getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data3.setText(format2.format(date));
            TextView testo3 = (TextView) findViewById(R.id.testodocumento3);
            testo3.setText(temp.get(2).getTitolo());

            TextView testo_ambito3 = (TextView) findViewById(R.id.testo_ambito_doc3);
            nome3 = "";
            for (int i = 0; i < tutti_gruppi.size(); i++) {
                if (temp.get(2).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                    nome3 = tutti_gruppi.get(i).getNome();
                    break;
                }
            }
            for (int i = 0; i < dipartimenti.size(); i++) {
                if (temp.get(2).getId_gruppo() == dipartimenti.get(i).getId()) {
                    nome3 = dipartimenti.get(i).getNome();
                    break;
                }
            }
            testo_ambito3.setText(nome3);
            findViewById(R.id.documento3).setVisibility(View.VISIBLE);
        }

        if(temp.size()>=4) {
            TextView data4 = (TextView) findViewById(R.id.data_doc4);
            try {
                date = format1.parse(temp.get(3).getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data4.setText(format2.format(date));
            TextView testo4 = (TextView) findViewById(R.id.testodocumento4);
            testo4.setText(temp.get(3).getTitolo());

            TextView testo_ambito4 = (TextView) findViewById(R.id.testo_ambito_doc4);
            nome4 = "";
            for (int i = 0; i < tutti_gruppi.size(); i++) {
                if (temp.get(3).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                    nome4 = tutti_gruppi.get(i).getNome();
                    break;
                }
            }
            for (int i = 0; i < dipartimenti.size(); i++) {
                if (temp.get(3).getId_gruppo() == dipartimenti.get(i).getId()) {
                    nome4 = dipartimenti.get(i).getNome();
                    break;
                }
            }
            testo_ambito4.setText(nome4);
            findViewById(R.id.documento4).setVisibility(View.VISIBLE);
        }
        if(temp.size()>=5) {
            TextView data5 = (TextView) findViewById(R.id.data5);
            try {
                date = format1.parse(temp.get(4).getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data5.setText(format2.format(date));
            TextView testo5 = (TextView) findViewById(R.id.testodocumento5);
            testo5.setText(temp.get(4).getTitolo());

            TextView testo_ambito5 = (TextView) findViewById(R.id.testo_ambito_doc5);
            nome5 = "";
            for (int i = 0; i < tutti_gruppi.size(); i++) {
                if (temp.get(4).getId_gruppo() == tutti_gruppi.get(i).getId()) {
                    nome5 = tutti_gruppi.get(i).getNome();
                    break;
                }
            }
            for (int i = 0; i < dipartimenti.size(); i++) {
                if (temp.get(4).getId_gruppo() == dipartimenti.get(i).getId()) {
                    nome5 = dipartimenti.get(i).getNome();
                    break;
                }
            }
            testo_ambito5.setText(nome5);
            findViewById(R.id.documento5).setVisibility(View.VISIBLE);
        }

        if(temp.size()>=1) {
            RelativeLayout avviso1 = (RelativeLayout) findViewById(R.id.documento1);
            final String finalNome = nome;
            avviso1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                    i.putExtra("iddoc", temp.get(0).getId());
                    i.putExtra("titolo", temp.get(0).getTitolo());
                    i.putExtra("dimensione", temp.get(0).getDimensione());
                    i.putExtra("data", temp.get(0).getData());
                    try {
                        i.putExtra("link", URLEncoder.encode(temp.get(0).getTitolo(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        i.putExtra("link", " "+temp.get(0).getId()+" ");
                    }
                    i.putExtra("ambito", finalNome);
                    startActivity(i);
                }
            });
        }
        if(temp.size()>=2) {
            RelativeLayout avviso2 = (RelativeLayout) findViewById(R.id.documento2);
            final String finalNome1 = nome2;
            avviso2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                    i.putExtra("iddoc", temp.get(1).getId());
                    i.putExtra("titolo", temp.get(1).getTitolo());
                    i.putExtra("dimensione", temp.get(1).getDimensione());
                    i.putExtra("data", temp.get(1).getData());
                    try {
                        i.putExtra("link", URLEncoder.encode(temp.get(1).getTitolo(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        i.putExtra("link", " "+temp.get(1).getId()+" ");
                    }
                    i.putExtra("ambito", finalNome1);
                    startActivity(i);
                }
            });
        }
        if(temp.size()>=3) {
            RelativeLayout avviso3 = (RelativeLayout) findViewById(R.id.documento3);
            final String finalNome2 = nome3;
            avviso3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                    i.putExtra("iddoc", temp.get(2).getId());
                    i.putExtra("titolo", temp.get(2).getTitolo());
                    i.putExtra("dimensione", temp.get(2).getDimensione());
                    i.putExtra("data", temp.get(2).getData());
                    try {
                        i.putExtra("link", URLEncoder.encode(temp.get(2).getTitolo(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        i.putExtra("link", " "+temp.get(2).getId()+" ");
                    }
                    i.putExtra("ambito", finalNome2);
                    startActivity(i);
                }
            });
        }
        if(temp.size()>=4) {
            RelativeLayout avviso4 = (RelativeLayout) findViewById(R.id.documento4);
            final String finalNome3 = nome4;
            avviso4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                    i.putExtra("iddoc", temp.get(3).getId());
                    i.putExtra("titolo", temp.get(3).getTitolo());
                    i.putExtra("dimensione", temp.get(3).getDimensione());
                    i.putExtra("data", temp.get(3).getData());
                    try {
                        i.putExtra("link", URLEncoder.encode(temp.get(3).getTitolo(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        i.putExtra("link", " "+temp.get(3).getId()+" ");
                    }
                    i.putExtra("ambito", finalNome3);
                    startActivity(i);
                }
            });
        }
        if(temp.size()>=5) {
            RelativeLayout avviso5 = (RelativeLayout) findViewById(R.id.documento5);
            final String finalNome4 = nome5;
            avviso5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                    i.putExtra("iddoc", temp.get(4).getId());
                    i.putExtra("titolo", temp.get(4).getTitolo());
                    i.putExtra("dimensione", temp.get(4).getDimensione());
                    i.putExtra("data", temp.get(4).getData());
                    try {
                        i.putExtra("link", URLEncoder.encode(temp.get(4).getTitolo(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        i.putExtra("link", " "+temp.get(4).getId()+" ");
                    }
                    i.putExtra("ambito", finalNome4);
                    startActivity(i);
                }
            });
        }
        if(temp.size()==0){
            TextView data1 = (TextView) findViewById(R.id.data_doc1);
            data1.setText("");
            TextView testo1 = (TextView) findViewById(R.id.testodocumento);
            testo1.setText("");

            TextView testo_ambito = (TextView) findViewById(R.id.testo_ambito_doc1);
            testo_ambito.setText("");

            TextView data2 = (TextView) findViewById(R.id.data_doc2);
            data2.setText("");
            TextView testo2 = (TextView) findViewById(R.id.testodocumento2);
            testo2.setText("");

            TextView testo_ambito2 = (TextView) findViewById(R.id.testo_ambito_doc2);
            testo_ambito2.setText("");

            TextView data3 = (TextView) findViewById(R.id.data_doc3);
            data3.setText("");
            TextView testo3 = (TextView) findViewById(R.id.testodocumento3);
            testo3.setText("");

            TextView testo_ambito3 = (TextView) findViewById(R.id.testo_ambito_doc3);
            testo_ambito3.setText("");

            TextView data4 = (TextView) findViewById(R.id.data_doc4);
            data4.setText("");
            TextView testo4 = (TextView) findViewById(R.id.testodocumento4);
            testo4.setText("");

            TextView testo_ambito4 = (TextView) findViewById(R.id.testo_ambito_doc4);
            testo_ambito4.setText("");

            RelativeLayout avviso1 = (RelativeLayout) findViewById(R.id.documento1);
            avviso1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    return;
                }
            });
            RelativeLayout avviso2 = (RelativeLayout) findViewById(R.id.documento2);
            avviso2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    return;
                }
            });
            RelativeLayout avviso3 = (RelativeLayout) findViewById(R.id.documento3);
            avviso3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    return;
                }
            });
            RelativeLayout avviso4 = (RelativeLayout) findViewById(R.id.documento4);
            avviso4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    return;
                }
            });
        }
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
                    a.add(SplashActivity.corsi.get(i - 1).getNome());
                    parent.add(new SplashActivity.Corso(SplashActivity.corsi.get(i - 1).getId(), SplashActivity.corsi.get(i - 1).getNome(), 0, id_dipartimento, "CS",0));
                    ParentString.add(SplashActivity.corsi.get(i - 1).getNome());

                    /*
                    a.add(SplashActivity.scuola.get(i - 1).getSigla());
                        parent.add(new SplashActivity.Corso(SplashActivity.scuola.get(i - 1).getId(), SplashActivity.scuola.get(i - 1).getSigla(), 0, id_dipartimento, "CS",0));
                        ParentString.add(SplashActivity.scuola.get(i - 1).getSigla());
*/
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
                            if (livello2dec.get(k).getLivello() >= 2 &&  livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j == 0 && a.size() > 1) {
                            if (livello2dec.get(k).getLivello() >= 2 &&  livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
                                if(livello2dec.get(k).getLivello() > 2){lista.add("-> "+livello2dec.get(k).getTitolo());}
                                else{lista.add(livello2.getTitolo());}
                            }
                        }
                        if (j != 0 && j != a.size() - 1) {
                            if (livello2dec.get(k).getLivello() >= 2 &&  livello2dec.get(k).getId_gruppo() == id_dipartimento && livello2dec.get(k).getI() >= ordinidia.get(j).intValue() && livello2dec.get(k).getI() < ordinidia.get(j + 1).intValue()) {
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
            Intent i=new Intent(getApplicationContext(), ListaAvvisi.class);
            i.putExtra("from_dipartimento",1);
            startActivity(i);
            return true;
        }else if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
            i.putExtra("from_dipartimento",1);
            startActivity(i);
        }else if (id == R.id.item_persone) {
            Intent i=new Intent(getApplicationContext(), Persona.class);
            i.putExtra("from_dipartimento",1);
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

    public void apriAvvisi(View v)
    {
        Intent i=new Intent(getApplicationContext(), ListaAvvisi.class);
        i.putExtra("from_dipartimento",1);
        startActivity(i);
    }
    public void apriDocumenti(View v)
    {
        Intent i=new Intent(getApplicationContext(), Documenti.class);
        i.putExtra("from_dipartimento",1);
        startActivity(i);
    }

    public void makePost_doc_home(){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url="";
            url="https://economia.unich.it/index.php?sito="+id_dipartimento+"&JSON="+pswJson;

            StringRequest jsonObjRequest = new StringRequest(com.android.volley.Request.Method.GET,
                    url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject c = null;
                            try {
                                c = new JSONObject(response);
                                JSONObject prova;
                                JSONArray documenti;
                                try{
                                    prova = c.getJSONObject("records");
                                    documenti = prova.getJSONArray("documenti");
                                }catch (JSONException e){
                                    findViewById(R.id.card_view_doc).setVisibility(View.GONE);
                                    findViewById(R.id.documento1).setVisibility(View.GONE);
                                    findViewById(R.id.documento2).setVisibility(View.GONE);
                                    findViewById(R.id.documento3).setVisibility(View.GONE);
                                    findViewById(R.id.documento4).setVisibility(View.GONE);
                                    findViewById(R.id.documento5).setVisibility(View.GONE);
                                    if(documenti_home!=null)documenti_home.clear();
                                    Log.e("documenti",e.toString());
                                    return;
                                }

                                if(documenti_home!=null)documenti_home.clear();



                                for (int i = 0; i < documenti.length(); i++) {
                                    JSONObject expl = documenti.getJSONObject(i);
                                    String data = expl.getString("data").substring(0, 10);
                                    String link = expl.getString("descrizione");
                                    String estensione = link.substring(link.length() - 3, link.length());
                                    documenti_home.add(new Doc(expl.getInt("id"), expl.getString("titolo"), expl.getString("gruppo"), expl.getString("descrizione"), data, 0, estensione, link, expl.getString("gruppo"), expl.getString("gruppo"), expl.getInt("id_gruppo")));
                                }

                                Log.e("documenti",documenti_home.size()+"");
                                setDocumenti();
                                mSwipeRefreshLayout.setRefreshing(false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new com.android.volley.Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> getParam = new HashMap<String, String>();

                    return getParam;
                }

            };

            requestQueue.add(jsonObjRequest);

    }

    public void makePost_doc(){
        RequestQueue requestQueue;
        StringRequest jsonObjRequest;

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url="";
        url="https://economia.unich.it/pag_documenti.php?JSON="+pswJson+"&gruppo="+id_dipartimento;
        //else{url="https://economia.unich.it/pag_documenti.php?JSON="+pswJson+"&gruppo="+id_corso;} per corso
        jsonObjRequest = new StringRequest(com.android.volley.Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject expl = null;
                        try {
                            if(documenti!=null)documenti.clear();
                            if(singolo!=null)singolo.clear();

                            JSONObject c = new JSONObject(response);
                            listaDocumenti = c.getJSONArray("records");
                            for (int i = 0; i < listaDocumenti.length(); i++) {
                                expl = listaDocumenti.getJSONObject(i);
                                String data = expl.getString("data").substring(0, 10);
                                String link = expl.getString("nome_file");
                                String estensione = link.substring(link.length() - 3, link.length());
                                documenti.add(expl.getString("titolo"));
                                singolo.add(new Doc(expl.getInt("id"), expl.getString("titolo"), expl.getString("categoria"), expl.getString("descrizione"), data, expl.getInt("dimensione"), estensione, link, expl.getString("categoria"), "", expl.getInt("id_sezione")));
                            }
                            finishdocu=true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> getParam = new HashMap<String, String>();

                return getParam;
            }

        };

        requestQueue.add(jsonObjRequest);

    }

}
