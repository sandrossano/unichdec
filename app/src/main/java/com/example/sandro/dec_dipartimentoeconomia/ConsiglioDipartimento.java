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

public class ConsiglioDipartimento extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipartimento);
        findViewById(R.id.include).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_pers).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_sing).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_org).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_gruppo).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_cons).setVisibility(View.VISIBLE);
        findViewById(R.id.include_didattica).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_apridoc).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_verbali).setVisibility(View.INVISIBLE);
        findViewById(R.id.include_doc_atti).setVisibility(View.INVISIBLE);

        int count=0;
        for(int i=0;i<ruoli.size();i++) {
            if(ruoli.get(i).getId()==19) {
                if (count == 0) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio1);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio1);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 1) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio2);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio2);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 2) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio3);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio3);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 3) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio4);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio4);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 4) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio5);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio5);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 5) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio6);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio6);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 6) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio7);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio7);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 7) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio8);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio8);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 8) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio9);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio9);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 9) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio10);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio10);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 10) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio11);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio11);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 11) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio12);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio12);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 12) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio13);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio13);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 13) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio14);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio14);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 14) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio15);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio15);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 15) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio16);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio16);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 16) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio17);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio17);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 17) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio18);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio18);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 18) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio19);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio19);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 19) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio20);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio20);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 20) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio21);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio21);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 21) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio22);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio22);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 22) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio23);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio23);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 23) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio24);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio24);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 24) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio25);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio25);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 25) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio26);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio26);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 26) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio27);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio27);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 27) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio28);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio28);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 28) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio29);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio29);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 29) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio30);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio30);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 30) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio31);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio31);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 31) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio32);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio32);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 32) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio33);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio33);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 33) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio34);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio34);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 34) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio35);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio35);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 35) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio36);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio36);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 36) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio37);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio37);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 37) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio38);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio38);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 38) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio39);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio39);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 39) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio40);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio40);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 40) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio41);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio41);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 41) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio42);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio42);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 42) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio43);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio43);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 43) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio44);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio44);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 44) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio45);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio45);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 45) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio46);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio46);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 46) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio47);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio47);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 47) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio48);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio48);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 48) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio49);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio49);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 49) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio50);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio50);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 50) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio51);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio51);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 51) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio52);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio52);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 52) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio53);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio53);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 53) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio54);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio54);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 54) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio55);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio55);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 55) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio56);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio56);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 56) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio57);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio57);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 57) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio58);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio58);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 58) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio59);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio59);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 59) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio60);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio60);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 60) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio61);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio61);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 61) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio62);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio62);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 62) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio63);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio63);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 63) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio64);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio64);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 64) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio65);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio65);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 65) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio66);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio66);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 66) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio67);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio67);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 67) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio68);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio68);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 68) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio69);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio69);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 69) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio70);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio70);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 70) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio71);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio71);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 71) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio72);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio72);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 72) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio73);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio73);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 73) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio74);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio74);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    continue;
                }if (count == 74) {
                    ImageView imm3 = (ImageView) findViewById(R.id.immagineConsiglio75);imm3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext()).load("https://economia.unich.it/dec/fototessera/" + ruoli.get(i).getFoto()).into(imm3);
                    final Ruoli singolo=ruoli.get(i);
                    TextView testo=(TextView) findViewById(R.id.consiglio75);testo.setVisibility(View.VISIBLE);
                    count++;
                    testo.setText(singolo.getNome()+" "+singolo.getCognome().toUpperCase());
                    testo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
                            startActivity(i);
                        }
                    });
                    imm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent (getApplicationContext(),PersonaSingola.class);
                            i.putExtra("idsing",singolo.getId());
                            i.putExtra("nomesing",singolo.getNome()+" "+singolo.getCognome());
                            i.putExtra("fotosing",singolo.getFoto());
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

        }else if (id == R.id.riesame) {
            Intent i=new Intent(getApplicationContext(), GruppoAQ.class);
            startActivity(i);
            finish();
        }else if (id == R.id.didattica) {
            Intent i=new Intent(getApplicationContext(), ComitatoDidattica.class);
            startActivity(i);
            finish();
        }else if (id == R.id.ricerca) {
            Intent i=new Intent(getApplicationContext(), ComitatoRicerca.class);
            startActivity(i);
            finish();
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
