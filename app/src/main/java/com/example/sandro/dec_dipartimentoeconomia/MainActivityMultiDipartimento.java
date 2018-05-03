package com.example.sandro.dec_dipartimentoeconomia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
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


public class MainActivityMultiDipartimento extends AppCompatActivity {

    public static ArrayList<SplashActivity.Corso> corsi = SplashActivity.corsi;
    public static ArrayList<SplashActivity.Corso> parent=new ArrayList<>();
    public static Context mContext;

    private ListView listView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        finish();
        startActivity(new Intent(this, MainActivityMultiDipartimento.class));
        overridePendingTransition(0, 0);
        Log.d("aggiorna","ok");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multidipartimento);
        setTitle("Multidipartimento");
        mContext=getApplicationContext();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


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
                                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("id_dipartimento",dipartimenti.get(i).getId());
                                intent.putExtra("nome_dipartimento",dipartimenti.get(i).getSigla());
                                startActivity(intent);
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
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Conferma")
                    .setMessage("Sei sicuro di voler uscire?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            int pid = Process.myPid();
                            Process.killProcess(pid);
                            MainActivityMultiDipartimento.super.onBackPressed();

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }


}
