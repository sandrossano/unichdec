package com.example.sandro.dec_dipartimentoeconomia;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.listaPersone;


public class Persona extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);
        findViewById(R.id.include).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.VISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_org).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_cons).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_gruppo).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_didattica).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_ricerca).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

        final TextView mTextView = (TextView) findViewById(R.id.text);
        final LinearLayout layout=(LinearLayout) findViewById(R.id.layout_persone);
        final SearchView cerca= (SearchView) findViewById(R.id.cerca);
        cerca.setIconified(false);
        cerca.setQueryHint("Cerca... ");
        cerca.setFocusable(false);
        cerca.clearFocus();


                            final CaccAdapter adapter;
                            final ListView lista = (ListView) findViewById(R.id.listview);
                            final ArrayList<String> persone=new ArrayList<>();


                            final ArrayList<Singolo> singolo=new ArrayList<>();
                            for(int i=0;i<listaPersone.length();i++){
                                JSONObject expl= null;
                                try {
                                    expl = listaPersone.getJSONObject(i);
                                    persone.add(expl.getString("nome")+" "+expl.getString("cognome"));
                                    singolo.add(new Singolo(expl.getInt("id"),expl.getString("nome")+" "+expl.getString("cognome"),expl.getString("foto")));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapter= new CaccAdapter(getApplicationContext(),persone,singolo);

                            lista.setAdapter(adapter);

                            lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
                            {
                                @Override
                                public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
                                {
                                    CaccAdapter t= (CaccAdapter) lista.getAdapter();
                                    int idscelto= t.singoli.get(position).getId();
                                    String nomescelto= t.singoli.get(position).getNome();
                                    String fotoscelto= t.singoli.get(position).getFoto();
                                    Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                                    i.putExtra("idsing",idscelto);
                                    i.putExtra("nomesing",nomescelto);
                                    i.putExtra("fotosing",fotoscelto);
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
                                            ArrayList<Singolo> tempSingolo=new ArrayList<Singolo>();
                                            int textlength = cerca.getQuery().length();
                                            temp.clear();
                                            for (int i = 0; i < persone.size(); i++)
                                            {
                                                if (textlength <= persone.get(i).length())
                                                {
                                                    if(persone.get(i).toString().toUpperCase().trim().contains(cerca.getQuery().toString().trim().toUpperCase()))
                                                    {
                                                        temp.add(persone.get(i));
                                                        tempSingolo.add(singolo.get(i));
                                                    }
                                                }
                                            }
                                            lista.setAdapter(new CaccAdapter(getApplicationContext(), temp, tempSingolo));


                                    return false;
                                }
                            });
                            lista.setAdapter(adapter);





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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dipart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.organig) {
            Intent i=new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        }else if (id == R.id.main_doc) {
            Intent i=new Intent(getApplicationContext(), Documenti.class);
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

class CaccAdapter extends ArrayAdapter<String>{
    Context context;
    ArrayList<String> persone;
    ArrayList<Singolo> singoli;

    CaccAdapter(Context context,ArrayList<String> titles, ArrayList<Singolo> singoli){
        super(context,R.layout.single_row,R.id.nome_lista,titles);
        persone=titles;
        this.context=context;
        this.singoli=singoli;
    }

    public ArrayList<Singolo> getSingoli(){return singoli;}

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_row,parent,false);
        ImageView myImage=(ImageView)row.findViewById(R.id.imageView);
        TextView a= (TextView) row.findViewById(R.id.nome_lista);
        a.setText(persone.get(position));
        a.setTextColor(Color.BLACK);
        if(singoli.get(position).getFoto()!="null")
        Picasso.with(getContext()).load("https://economia.unich.it/fototessera/"+singoli.get(position).getFoto()).into(myImage);
        else{
            Picasso.with(getContext()).load("https://economia.unich.it/fototessera/persona_generica.jpg").into(myImage);
        }
        return row;
    }

}

class Singolo{
    private int id;
    private String nome;
    private String foto;
    public Singolo(int id, String nomecognome, String foto){
        this.id=id; this.nome=nomecognome;this.foto=foto;
    }
    public int getId(){return id;}
    public String getNome(){return nome;}
    public String getFoto(){return foto;}
}