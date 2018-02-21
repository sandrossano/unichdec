package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.ruoli;

public class ComitatoRicerca extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        findViewById(R.id.include_ricerca).setVisibility(View.VISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

        int count=0;
        for(int i=0;i<ruoli.size();i++) {
            if (ruoli.get(i).getId() == 34) {
                if (count == 0) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca1);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca1);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 1) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca2);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca2);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 2) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca3);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca3);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 3) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca4);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca4);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 4) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca5);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca5);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 5) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca6);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca6);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 6) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca7);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca7);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 7) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca8);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca8);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 8) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca9);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca9);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 9) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca10);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca10);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 10) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca11);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca11);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 11) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca12);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca12);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 12) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca13);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca13);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 13) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca14);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca14);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 14) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca15);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca15);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 15) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca16);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca16);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 16) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca17);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca17);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 17) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca18);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca18);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 18) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca19);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca19);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 19) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca20);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca20);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 20) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineRicerca21);
                    imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo = ruoli.get(i);
                    TextView testo = (TextView) findViewById(R.id.ricerca21);
                    testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome() + " " + singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(), PersonaSingola.class);
                            i.putExtra("idsing", singolo.getId());
                            i.putExtra("nomesing", singolo.getNome() + " " + singolo.getCognome());
                            i.putExtra("fotosing", singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }
            }
        }

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
        getMenuInflater().inflate(R.menu.main_org, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.consiglio) {
            Intent i=new Intent(getApplicationContext(), ConsiglioDipartimento.class);
            startActivity(i);
            finish();
        }else if (id == R.id.riesame) {
            Intent i=new Intent(getApplicationContext(), GruppoAQ.class);
            startActivity(i);
            finish();
        }else if (id == R.id.didattica) {
            Intent i=new Intent(getApplicationContext(), ComitatoDidattica.class);
            startActivity(i);
            finish();
        }else if (id == R.id.ricerca) {

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
            Intent i=new Intent(getApplicationContext(), Persona.class);
            startActivity(i);
        } else if (id == R.id.organig) {

        }else if (id == R.id.nav_dip) {
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
