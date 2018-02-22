package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;

public class Avviso extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.avviso);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            int id = getIntent().getIntExtra("id",0);

            TextView avv1=(TextView) findViewById(R.id.avviso1);
            TextView avv=(TextView) findViewById(R.id.avviso);
            avv.setVisibility(View.VISIBLE);
            if(id!=0){CharSequence text=avv.getText()+""+id; avv.setText(text);}
            avv1.setVisibility(View.INVISIBLE);

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

            TextView avviso= (TextView) findViewById(R.id.avviso);
            avviso.setText(corsi.toString());
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
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("id",1);
                startActivity(i);
            } else if (id == R.id.nav_dip) {
                Intent i=new Intent(getApplicationContext(), Dipartimento.class);
                i.putExtra("id",1);
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
