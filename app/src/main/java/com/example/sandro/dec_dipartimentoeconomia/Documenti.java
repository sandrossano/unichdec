package com.example.sandro.dec_dipartimentoeconomia;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.categorie;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaDocumenti;


public class Documenti extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView lista;
    DocuAdapter adapter;
    ArrayList<String> documenti = new ArrayList<>();
    ArrayList<Doc> singolo = new ArrayList<>();
    SearchView cerca;
    SearchView searchView;

    private void refreshContent() {
        if(mSwipeRefreshLayout.isEnabled()) {
            adapter = new DocuAdapter(getApplicationContext(), documenti, singolo);
            lista.setAdapter(adapter);
            cerca.setQuery("", false);
            cerca.setQueryHint("Cerca... ");
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
    ArrayList<Doc> singolo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);

        ProgressBar p=(ProgressBar) findViewById(R.id.progressBar);
        DrawableCompat.setTint(p.getIndeterminateDrawable(),Color.DKGRAY);
        findViewById(R.id.include).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_org).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_cons).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_gruppo).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_didattica).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_ricerca).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.VISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView lista = (ListView) findViewById(R.id.listview_docu);
                ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);
                lista.setVisibility(View.VISIBLE);
            }
        },2000);
        snipper();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    public void CaricaLista(){

        //Documenti

// Instantiate the RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(getApplicationContext());
        String url2 = "http://proxybar.altervista.org/document/read.php";


// Request a string response from the provided URL.
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        lista = (ListView) findViewById(R.id.listview_docu);

                        cerca = (SearchView) findViewById(R.id.cerca_docu);

                        JSONObject expl = null;
                        try {

                            JSONObject c = new JSONObject(response);
                            listaDocumenti = c.getJSONArray("records");
                            for (int i = 0; i < listaDocumenti.length(); i++) {
                                expl = listaDocumenti.getJSONObject(i);
                                String data = expl.getString("data_creazione").substring(0, 10);
                                String link = expl.getString("link");
                                String estensione = link.substring(link.length() - 3, link.length());
                                documenti.add(expl.getString("titolo"));
                                String nome_cat = "";
                                String nome_gruppo = "";
                                int id_gruppo = 0;
                                for (int j = 0; j < categorie.size(); j++) {
                                    if (expl.getInt("id_categoria") == categorie.get(j).getId()) {
                                        nome_cat = categorie.get(j).getNome_cat();
                                        nome_gruppo = categorie.get(j).getNome();
                                        id_gruppo = categorie.get(j).getId_gruppo();
                                    }
                                }
                                singolo.add(new Doc(expl.getInt("id"), expl.getString("titolo"), expl.getInt("id_categoria"), expl.getString("descrizione"), data, expl.getInt("dimensione"), estensione, link, nome_cat, nome_gruppo, id_gruppo));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        singolo2=singolo;
                        adapter = new DocuAdapter(getApplicationContext(), documenti, singolo);

                        lista.setAdapter(adapter);

/*
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

                                Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                                i.putExtra("iddoc", idscelto);
                                i.putExtra("idcat", idcat);
                                i.putExtra("titolo", titolo);
                                i.putExtra("descrizione", descrizione);
                                i.putExtra("data", data);
                                i.putExtra("dimensione", dimensione);
                                i.putExtra("estensione", estensione);
                                i.putExtra("link", link);
                                startActivity(i);

                            }
                        });


                        cerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String s) {
                                return false;
                            }


                            @Override
                            public boolean onQueryTextChange(String s) {
                                ArrayList<String> temp = new ArrayList<String>();
                                ArrayList<Doc> tempSingolo = new ArrayList<Doc>();
                                int textlength = cerca.getQuery().length();
                                temp.clear();
                                for (int i = 0; i < documenti.size(); i++) {
                                    if (textlength <= documenti.get(i).length()) {
                                        if (documenti.get(i).toString().toUpperCase().trim().contains(cerca.getQuery().toString().trim().toUpperCase())) {
                                            temp.add(documenti.get(i));
                                            tempSingolo.add(singolo.get(i));
                                        }
                                    }
                                }
                                lista.setAdapter(new DocuAdapter(getApplicationContext(), temp, tempSingolo));


                                return false;
                            }
                        });*/
                        lista.setAdapter(adapter);

                    }

                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue2.add(stringRequest2);

    }

    public void snipper(){
        final ListView lista = (ListView) findViewById(R.id.listview_docu);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        //String[] items = new String[]{"CLEA", "CLEC", "CLEII","CLEA/M","CLEC/M"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(Documenti.this,R.array.corsi,android.R.layout.simple_spinner_item)  ;
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
        getMenuInflater().inflate(R.menu.main_pers, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }

        searchView.setIconified(false);
        searchView.setFocusable(true);
        searchView.clearFocus();
        searchView.setQueryHint("Cerca... ");

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

                Intent i = new Intent(getApplicationContext(), PaginaDocumento.class);
                i.putExtra("iddoc", idscelto);
                i.putExtra("idcat", idcat);
                i.putExtra("titolo", titolo);
                i.putExtra("descrizione", descrizione);
                i.putExtra("data", data);
                i.putExtra("dimensione", dimensione);
                i.putExtra("estensione", estensione);
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
                ArrayList<String> temp = new ArrayList<String>();
                ArrayList<Doc> tempSingolo = new ArrayList<Doc>();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
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
        TextView b= (TextView) row.findViewById(R.id.testodesc);
        TextView c= (TextView) row.findViewById(R.id.testodata);
        TextView d= (TextView) row.findViewById(R.id.testodim);
        TextView e= (TextView) row.findViewById(R.id.testocat);
        TextView f= (TextView) row.findViewById(R.id.testoamb);
        f.setText(singoli.get(position).getNome_gruppo());
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
    public Doc(int id, String titolo, int id_categoria,String descrizione,String data,int dimensione,String estensione,String link,String nome_cat,String nome_gruppo, int id_gruppo) {
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
