package com.example.sandro.dec_dipartimentoeconomia;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class PaginaDocumento extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int iddoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);
        findViewById(R.id.include).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_org).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_cons).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_gruppo).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_didattica).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_ricerca).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.VISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

        iddoc = getIntent().getIntExtra("iddoc", 0);
        String titolo = getIntent().getStringExtra("titolo");
        int idcat = getIntent().getIntExtra("idcat", 0);
        setTitle(titolo);

        TextView wow = (TextView) findViewById(R.id.testodocumento);
        wow.setText("ID: " + iddoc + " , TITOLO:" + titolo + " , ID_CAT:" + idcat);

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
        if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.organig) {
            Intent i = new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        } else if (id == R.id.main_doc) {
            Intent i = new Intent(getApplicationContext(), Documenti.class);
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
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else if (id == R.id.person) {
            Intent i = new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.organig) {
            Intent i = new Intent(getApplicationContext(), Organigramma.class);
            startActivity(i);
        } else if (id == R.id.nav_dip) {
            Intent i = new Intent(getApplicationContext(), Dipartimento.class);
            startActivity(i);
        } else if (id == R.id.nav_dida) {

        } else if (id == R.id.nav_serv) {

        } else if (id == R.id.nav_serch) {

        } else if (id == R.id.nav_share) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("it.cineca.app.unich");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            } else {

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

    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
    public void download(View v) {
        if(haveStoragePermission()==true) {
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse("https://economia.unich.it/dec/download.php?id=" + iddoc);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.allowScanningByMediaScanner();
            request.setMimeType("application/" + getIntent().getStringExtra("estensione"));
            request.setDestinationInExternalPublicDir("/Download", getIntent().getStringExtra("link"));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            Long reference = downloadManager.enqueue(request);
        }
    }

}