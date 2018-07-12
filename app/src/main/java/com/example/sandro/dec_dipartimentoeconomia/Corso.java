package com.example.sandro.dec_dipartimentoeconomia;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.booleanoscuola;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawerMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.nome_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.parent;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.appuntamenti;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.pagine;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.r_corsi;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.scuola;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.tutti_gruppi;
import static com.example.sandro.dec_dipartimentoeconomia.Visualizza.drawerVisual;

/**
 * Created by sandro on 27/03/18.
 */

public class Corso extends AppCompatActivity {
    static String position;
    static int id_corso;
    private ExpandableListView expandableListView;
    static DrawerLayout drawerCorso=null;
    RequestQueue requestQueue;
    StringRequest jsonObjRequest;
    WebView engine ;
    TextView text;
    int id_cont=0;
    String contenuto="";

    SwipeRefreshLayout mSwipeRefreshLayout;

    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        Intent i=new Intent(this, Corso.class);
        position=getIntent().getStringExtra("position");
        i.putExtra("position",position);
        startActivity(i);
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.activity=".Corso";
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corso);

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


        engine = (WebView) findViewById(R.id.web_engine);
        text=(TextView)findViewById(R.id.testovisualizza);

        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        MainActivity.activity=".Corso";

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

        position=getIntent().getStringExtra("position");


        String str = position.replace("/", "");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView logo = (ImageView)hView.findViewById(R.id.logo_dipartimento);
        ImageView logo_prec = (ImageView)hView.findViewById(R.id.logo_precedente);

        /*
        ImageView corso = (ImageView)findViewById(R.id.image_corso);

        Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+str+".png").into(corso);
*/

        Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+str+".png").into(logo);

        Picasso.with(getApplicationContext()).load("https://economia.unich.it/html/images/categorie/"+nome_dipartimento+".png").into(logo_prec);




        for(int i=0;i<corsi.size();i++) {
            if(position.equals(corsi.get(i).getNome())){
            id_corso = corsi.get(i).getId();
            id_cont=corsi.get(i).getId_contenuto();
            setTitle(corsi.get(i).getNome());}

        }

        ArrayList<SplashActivity.Corso> listacorsiDipartimento=new ArrayList<>();
        for(int i=0;i<corsi.size();i++) {
            if (corsi.get(i).getId_gruppo() == corsi_dipartimento) {
                listacorsiDipartimento.add(corsi.get(i));
            }
        }

        for (int i =0;i<corsi.size();i++) {
            if (corsi.get(i).getId()==id_corso) {
                Log.e("color",""+corsi.get(i).getColor());
                NavigationView navigationView1=(NavigationView) findViewById(R.id.nav_view);
                if(corsi.get(i).getColor()==0) {
                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#0e185a"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==1) {
                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#b30000"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==2) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#e6b800"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==3) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#00cc7a"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==4) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#00663d"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==5) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#b30077"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==6) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#804000"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==7) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#808080"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==8) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#600080"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==9) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#b38f00"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==10) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#ff704d"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
                if(corsi.get(i).getColor()==11) {

                    ColorDrawable colorDrawable = new ColorDrawable(
                            Color.parseColor("#33bbff"));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    navigationView1.setBackground(colorDrawable);
                }
            }
        }

        makePost_contenuto();
        makePost_avvisi();
    }

    public void caricaDopoRequest(){
        String data="";
        data = "<html>" + "<body>"+
                contenuto+
                "</body>" + "</html>";
        //data=data.replace("href=","");        rimuovi link
        data=data.replace("src=\"documenti/","src=\"https://economia.unich.it/documenti/");
        if(data.contains("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;")){data=data.replace("<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: block;","<iframe style=\"border: 1px solid #cccccc; margin-bottom: 5px; max-width: 100%; display: none; ");}
        //data=data.replace("width=\"","width=\"100%\" height=\"100%\" alt=\"");
        //if(terzolv.equals("Esami")){data=data.replace("</body>","vai alla app Uda + intent</body>");}
        WebView image_top= (WebView) findViewById(R.id.image_top);
        image_top.getSettings().setJavaScriptEnabled(true);
        image_top.loadData(data, "text/html", "UTF-8");

        RelativeLayout corso=findViewById(R.id.relative_corso);
        corso.setVisibility(View.VISIBLE);

        //TextView text=(TextView)findViewById(R.id.testocorso);

        //text.setText("Dipartimento: "+id_dipartimento+"\n"+"Id_Corso: "+id_corso+"\n"+"Nome_Corso: "+position);

        if(drawerCorso!=null)drawerCorso.closeDrawer(GravityCompat.START);
        drawerMain.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.START);
        if(drawerVisual!=null)drawerVisual.closeDrawer(GravityCompat.START);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerCorso = (DrawerLayout) findViewById(R.id.drawer_layout);
        setUpAdapter();

    }

    public void setAppuntamenti(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy", Locale.ITALY);
        Date date = null;


        final ArrayList<SplashActivity.Appuntamento> temp=new ArrayList<>();


        for (int i=0;i<appuntamenti.size();i++){
            if(appuntamenti.get(i).getId_gruppo()==id_corso){temp.add(appuntamenti.get(i));continue;}
            for (int j=0;j<tutti_gruppi.size();j++){
                if (tutti_gruppi.get(j).getId()==appuntamenti.get(i).getId_gruppo() && tutti_gruppi.get(j).getId_gruppo()==id_corso){temp.add(appuntamenti.get(i));}
            }
            if(appuntamenti.get(i).getId_gruppo()==1270){temp.add(appuntamenti.get(i));continue;}
        }

        String nome="";
        String nome2="";
        String nome3="";
        String nome4="";
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
    }

    public void makePost_avvisi(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url="";
        url="https://economia.unich.it/pag_appuntamenti.php?JSON=on&gruppo="+id_corso;

        StringRequest jsonObjRequest = new StringRequest(com.android.volley.Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject c = null;
                        try {
                            c = new JSONObject(response);

                            JSONArray prova = c.getJSONArray("records");

                            if(appuntamenti!=null)appuntamenti.clear();

                            for (int i = 0; i < prova.length(); i++) {
                                JSONObject expl = prova.getJSONObject(i);
                                appuntamenti.add(new SplashActivity.Appuntamento(expl.getInt("id"), expl.getString("titolo"), expl.getString("data_inizio"), expl.getString("data_fine"), expl.getString("descrizione"), expl.getInt("id_sezione")));
                            }
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
                    for (int m = 0; m < scuola.size(); m++) {
                        if (scuola.get(m).getId_gruppo_scuola() == id_dipartimento) {
                            if (scuola.get(m).getId() == id_corso) {
                                parent.add(new SplashActivity.Corso(scuola.get(m).getId(), scuola.get(m).getSigla(), 0, id_dipartimento, "CS",id_cont));
                                ParentString.add(scuola.get(m).getSigla());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",id_cont));
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
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS",id_cont));
                            //ParentString.add(scuola.get(i - 1).getSigla());
                        }
                    }
                }
            } else{
                if (i == 0) {
                    for (int m = 0; m < corsi.size(); m++) {
                        if (corsi.get(m).getId_gruppo() == corsi_dipartimento) {
                            if (corsi.get(m).getId() == id_corso) {
                                parent.add(new SplashActivity.Corso(corsi.get(m).getId(), corsi.get(m).getNome(), corsi.get(m).getColor(), corsi_dipartimento, "CS",id_cont));
                                ParentString.add(corsi.get(m).getNome());
                            }
                        }
                    }
                }
                if (i == 1) {
                    for (int c = 0; c < dipartimenti.size(); c++) {
                        if (dipartimenti.get(c).getId() == id_dipartimento) {
                            a.add(dipartimenti.get(c).getNome());
                            parent.add(new SplashActivity.Corso(dipartimenti.get(c).getId(), dipartimenti.get(c).getSigla(), -1, corsi_dipartimento, "DIP",id_cont));
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
                            parent.add(new SplashActivity.Corso(scuola.get(i - 1).getId(), scuola.get(i - 1).getSigla(), -1, id_dipartimento, "CS",id_cont));
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
                            if (livello2dec.get(k).getLivello() >= 2  && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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
                            if (livello2dec.get(k).getLivello() >= 2  && livello2dec.get(k).getId_gruppo() == id_corso && livello2dec.get(k).getI() >= ordinidia.get(j).intValue()) {
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
            i.putExtra("from_corso",1);
            startActivity(i);
            return true;
        }else if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
            i.putExtra("id_corso", id_corso);
            i.putExtra("id_dip", id_dipartimento);
            i.putExtra("from_corso",1);
            startActivity(i);
        }else if (id == R.id.item_persone) {
            Intent i=new Intent(getApplicationContext(), Persona.class);
            i.putExtra("from_corso",1);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
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
    public void apriAvvisi(View v)
    {
        Intent i=new Intent(getApplicationContext(), ListaAvvisi.class);
        i.putExtra("from_corso",1);
        startActivity(i);
    }

    private void makePost_contenuto(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonObjRequest = new StringRequest(com.android.volley.Request.Method.POST,
                "https://economia.unich.it/decapp/contenuto/index_senzaPagina.php",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject c = null;
                        try {
                            c = new JSONObject(response);
                            JSONArray cacca = c.getJSONArray("records");
                            JSONObject expl = cacca.getJSONObject(0);

                            contenuto=expl.getString("testocontenuto");
                            caricaDopoRequest();
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
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("idcontenuto", ""+id_cont);

                return postParam;
            }

        };

        requestQueue.add(jsonObjRequest);
    }


}
