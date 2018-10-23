package com.example.sandro.dec_dipartimentoeconomia;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawerMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContext;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContextMain;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.nome_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.dipartimenti;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.finish3;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;

public class ThreeLevelListAdapter extends BaseExpandableListAdapter{

    ArrayList<String> parentHeaders;
    List<String[]> secondLevel;
    private Context context;
    List<LinkedHashMap<String, String[]>> data;

    /**
     * Constructor
     * @param context
     * @param parentHeader
     * @param secondLevel
     * @param data
     */
    public ThreeLevelListAdapter(Context context, ArrayList<String> parentHeader, List<String[]> secondLevel, List<LinkedHashMap<String, String[]>> data) {
        this.context = context;

        this.parentHeaders = parentHeader;

        this.secondLevel = secondLevel;

        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return parentHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {


        return child;


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group, null);
        ImageView immagine=(ImageView) convertView.findViewById(R.id.ident);
        TextView text = (TextView) convertView.findViewById(R.id.submenu1);
        text.setText(this.parentHeaders.get(groupPosition));
        if(groupPosition!=0) immagine.setBackgroundResource(R.drawable.didattica_white);
        else{immagine.setBackgroundResource(R.drawable.dipico_white);}

        if(groupPosition!=0){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent corso=new Intent(mContext,Corso.class);
                    corso.putExtra("position", parentHeaders.get(groupPosition));
                    corso.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    fine(corso);

                }
            });

        }
        return convertView;

    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    if(groupPosition==0) {
        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

        String[] headers = secondLevel.get(groupPosition);


        List<String[]> childData = new ArrayList<>();
        HashMap<String, String[]> secondLevelData = data.get(groupPosition);

        for (String key : secondLevelData.keySet()) {


            childData.add(secondLevelData.get(key));

        }


        secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers, childData, groupPosition));

        secondLevelELV.setGroupIndicator(null);
        secondLevelELV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i,long l) {
                    String prova = "";
                    prova = "Hai premuto: id_dip: " + id_dipartimento;
                    Log.d("primolivello", prova);

                    ArrayList<SplashActivity.SottoLivelli> second = new ArrayList<SplashActivity.SottoLivelli>();
                    for (int j = 0; j < livello2dec.size(); j++) {
                        //if (MainActivity.parent.get(groupPosition).getId() == id_dipartimento) {
                        //&& livello2dec.get(j).getId_pagina() == 0
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_gruppo() == id_dipartimento) {
                            second.add(livello2dec.get(j));
                        }
                        //}
                    /*else {
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_pagina() <= 0 && livello2dec.get(j).getId_gruppo() == MainActivity.parent.get(groupPosition).getId()) {
                            second.add(livello2dec.get(j));
                        }
                    }*/
                    }
                    Log.d("secondolivello", "Hai premuto: " + second.get(i).getTitolo());

                    ArrayList<SplashActivity.SottoLivelli> terzo = new ArrayList<SplashActivity.SottoLivelli>();
                    for (int j = 0; j < livello2dec.size(); j++) {
                        SplashActivity.SottoLivelli livello2=new SplashActivity.SottoLivelli(livello2dec.get(j).getI(),livello2dec.get(j).getTitolo(),livello2dec.get(j).getId_gruppo(),livello2dec.get(j).getId_pagina(),livello2dec.get(j).getLivello(),livello2dec.get(j).getLink());
                        if(i==0 && second.size()==1){
                            {terzo.add(livello2);
                            }
                        }
                        if(i==0 && second.size()>1) {
                            {terzo.add(livello2);}
                        }
                        if(i!=0 && i!=second.size()-1){
                            {terzo.add(livello2);}
                        }
                        if(i==second.size()-1){
                            {terzo.add(livello2);}
                        }
                    }

                        if (second.get(i).getId_pagina() != -1 && second.get(i).getId_pagina() != 0) {
                            Intent visualizza = new Intent(mContext, Visualizza.class);
                            visualizza.putExtra("id_dip", id_dipartimento);
                            visualizza.putExtra("secondolv", second.get(i).getTitolo());
                            visualizza.putExtra("terzolv", second.get(i).getTitolo());
                            visualizza.putExtra("terzolvpag", second.get(i).getId_pagina());
                            visualizza.putExtra("link", second.get(i).getLink());
                            visualizza.putExtra("position", parentHeaders.get(groupPosition));
                            visualizza.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            fine(visualizza);

                            }

                        if(second.get(i).getTitolo().toUpperCase().equals("HOME")&&MainActivity.activity.equals(".MainActivity")){
                            drawerMain.closeDrawers();
                            drawer.closeDrawers();
                        }
                        else {

                            if(second.get(i).getTitolo().toUpperCase().equals("HOME")){
                                Intent intent=new Intent(mContext, MainActivity.class);
                                intent.putExtra("id_dipartimento",id_dipartimento);
                                intent.putExtra("nome_dipartimento",nome_dipartimento);
                                drawerMain.closeDrawers();
                                drawer.closeDrawers();
                                fine(intent);
                            }

                        //Toast.makeText(MainActivity.mContext, "Hai Premuto:\n\n" + "primolivello: " + prova + "\n\n secondolivello: " + second.get(i).getTitolo(), Toast.LENGTH_SHORT).show();
                    }
                    return false;

        }});

        secondLevelELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                    String prova = "";
                    prova = "Hai premuto: id_dip: " + id_dipartimento;
                    Log.d("primolivello", prova);


                    ArrayList<SplashActivity.SottoLivelli> second = new ArrayList<SplashActivity.SottoLivelli>();
                    for (int j = 0; j < livello2dec.size(); j++) {
                        //if (MainActivity.parent.get(groupPosition).getId() == id_dipartimento) {
                        //&& livello2dec.get(j).getId_pagina() == 0
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_gruppo() == id_dipartimento) {
                            second.add(livello2dec.get(j));
                        }
                        //}
                    /*else {
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_pagina() <= 0 && livello2dec.get(j).getId_gruppo() == MainActivity.parent.get(groupPosition).getId()) {
                            second.add(livello2dec.get(j));
                        }
                    }*/
                    }
                    Log.d("secondolivello", "Hai premuto: " + second.get(i).getTitolo());

                    ArrayList<SplashActivity.SottoLivelli> terzo = new ArrayList<SplashActivity.SottoLivelli>();
                    for (int j = 0; j < livello2dec.size(); j++) {
                        SplashActivity.SottoLivelli livello2=new SplashActivity.SottoLivelli(livello2dec.get(j).getI(),livello2dec.get(j).getTitolo(),livello2dec.get(j).getId_gruppo(),livello2dec.get(j).getId_pagina(),livello2dec.get(j).getLivello(),livello2dec.get(j).getLink());

                        if (livello2dec.get(j).getLivello() >= 2 && livello2dec.get(j).getId_gruppo() == id_dipartimento && livello2dec.get(j).getI() >= second.get(i).getI()) {
                           {terzo.add(livello2);}
                        }
                    }
                    String[] titolo= terzo.get(i1).getTitolo().split("-> ");
                    Log.d("terzolivello", "Hai premuto: " + titolo[titolo.length-1]);

                    if(!titolo[titolo.length-1].trim().toUpperCase().equals("PERSONE")&& !titolo[titolo.length-1].trim().toUpperCase().equals("DOCUMENTI")&&terzo.get(i1).getId_pagina()!=0&&terzo.get(i1).getId_pagina()!=-1) {
                        Intent visualizza = new Intent(mContext, Visualizza.class);
                        visualizza.putExtra("id_dip", id_dipartimento);
                        visualizza.putExtra("secondolv", second.get(i).getTitolo());
                        visualizza.putExtra("terzolv", terzo.get(i1).getTitolo());
                        visualizza.putExtra("terzolvpag", terzo.get(i1).getId_pagina());
                        visualizza.putExtra("link", terzo.get(i1).getLink());
                        visualizza.putExtra("position", parentHeaders.get(groupPosition));
                        visualizza.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        fine(visualizza);
                    }

                    if (terzo.get(i1).getId_pagina()==-1){
                        String url = terzo.get(i1).getLink();
                        if (url.startsWith("http")){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            fine(intent);
                        }
                    }


                    if (terzo.get(i1).getId_pagina()==0){
                        String url = terzo.get(i1).getLink();
                        if (url.startsWith("visualizza.php?type=pagina&id=")){
                            String split=url.substring(30);
                            int pagina=Integer.parseInt(split);

                            Intent visualizza = new Intent(mContext, Visualizza.class);
                            visualizza.putExtra("id_dip", id_dipartimento);
                            visualizza.putExtra("secondolv", second.get(i).getTitolo());
                            visualizza.putExtra("terzolv", terzo.get(i1).getTitolo());
                            visualizza.putExtra("terzolvpag", pagina);
                            visualizza.putExtra("link", terzo.get(i1).getLink());
                            visualizza.putExtra("position", parentHeaders.get(groupPosition));
                            visualizza.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            fine(visualizza);

                        }
                    }

                    if(titolo[titolo.length-1].toUpperCase().equals("HOME")&&MainActivity.activity.equals(".MainActivity")){
                        drawerMain.closeDrawers();
                    }
                    if(titolo[titolo.length-1].toUpperCase().equals("HOME")&&!MainActivity.activity.equals(".MainActivity")){
                        Intent intent=new Intent(mContext, MainActivity.class);
                        intent.putExtra("id_dipartimento",dipartimenti.get(i).getId());
                        intent.putExtra("nome_dipartimento",dipartimenti.get(i).getSigla());
                        fine(intent);
                    }
                    if(titolo[titolo.length-1].trim().toUpperCase().equals("DOCUMENTI")) {
                        Intent persona = new Intent(mContext, Documenti.class);
                        persona.putExtra("id_dip", id_dipartimento);
                        persona.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        persona.putExtra("from_dipartimento",1);
                       fine(persona);}

                    if(titolo[titolo.length-1].trim().toUpperCase().equals("PERSONE") ) {
                        Intent persona = new Intent(mContext, Persona.class);
                        persona.putExtra("id_dip", id_dipartimento);
                        persona.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        persona.putExtra("from_dipartimento",1);
                        fine(persona);}
                    //Toast.makeText(MainActivity.mContext, "Hai Premuto:\n\n" + "primolivello: " + prova + "\n\n secondolivello: " + second.get(i).getTitolo() + "\n\n terzolivello: " + terzo.get(i1).getTitolo(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (groupPosition != previousGroup)
                        secondLevelELV.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                }
            });


        return secondLevelELV;


    } else{
        int count=0;
        for(int i=0;i<corsi.size();i++) {

            if (corsi.get(i).getId_gruppo() == corsi_dipartimento) {
                count++;
                if(count==groupPosition) {
                    Toast.makeText(MainActivity.mContext, "Hai Premuto:\n\n" + corsi.get(i).getId(), Toast.LENGTH_SHORT).show();

                }
            }
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_empty, null);
        return convertView;
    }

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void fine(final Intent visualizza) {
        if (finish3) {
            mContext.startActivity(visualizza);

        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,"Caricamento in corso...",
                            Toast.LENGTH_SHORT).show();
                    fine(visualizza);
                }
            }, 2050);
        }
    }

}
