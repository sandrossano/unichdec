package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;


public class MainActivityMultiDipartimento extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Categoria> categorie = SplashActivity.categorie;
    public static JSONArray listaPersone = SplashActivity.listaPersone;
    public static JSONArray listaDocumenti = SplashActivity.listaDocumenti;
    public static ArrayList<Ruoli> ruoli = SplashActivity.ruoli;
    public static ArrayList<SplashActivity.Corso> corsi = SplashActivity.corsi;
    public static ArrayList<SplashActivity.Corso> parent=new ArrayList<>();
    public static Context mContext;

    private DrawerLayout mDrawerLayout;
    private ListView listView;

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multidipartimento);

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


        ArrayList<String> ParentString= new ArrayList<String>();

        listView = (ListView) findViewById(R.id.listmenu);
            for (int j = 0; j < dipartimenti.size(); j++) { // secondo livello: prendere da volley del menù iniziale e mettere gli if per tipo_gruppo

                ParentString.add(dipartimenti.get(j).getSigla());



                listView.setAdapter(new ListAdapter() {
                    @Override
                    public boolean areAllItemsEnabled() {
                        return false;
                    }

                    @Override
                    public boolean isEnabled(int i) {
                        return false;
                    }

                    @Override
                    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

                    }

                    @Override
                    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

                    }

                    @Override
                    public int getCount() {
                        return dipartimenti.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return dipartimenti.get(i);
                    }

                    @Override
                    public long getItemId(int i) {
                        return dipartimenti.get(i).getId();
                    }

                    @Override
                    public boolean hasStableIds() {
                        return false;
                    }

                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View convertView = inflater.inflate(R.layout.list_group, null);
                        ImageView immagine=(ImageView) convertView.findViewById(R.id.ident);
                        TextView text = (TextView) convertView.findViewById(R.id.submenu1);
                        text.setText(dipartimenti.get(i).getSigla());
                        if(i!=0) immagine.setBackgroundResource(R.drawable.didattica_white);
                        else{immagine.setBackgroundResource(R.drawable.dipico_white);}
                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("premuto:",dipartimenti.get(i).getSigla());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            }
                        });
                        return convertView;
                    }

                    @Override
                    public int getItemViewType(int i) {
                        return 2;
                    }

                    @Override
                    public int getViewTypeCount() {
                        return 2;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }
                });

            }
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });*/
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
