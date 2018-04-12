package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.Corso.id_corso;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.drawer;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.id_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.mContext;
import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.nome_dipartimento;
import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;

public class ThreeLevelListAdapterCorsi extends BaseExpandableListAdapter{

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
    public ThreeLevelListAdapterCorsi(Context context, ArrayList<String> parentHeader, List<String[]> secondLevel, List<LinkedHashMap<String, String[]>> data) {
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
        if(groupPosition!=1) immagine.setBackgroundResource(R.drawable.didattica_white);
        else{immagine.setBackgroundResource(R.drawable.dipico_white);}
        if(groupPosition!=0 && groupPosition!=1){
            /*
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent corso=new Intent(mContext,Corso.class);
                    corso.putExtra("position", parentHeaders.get(groupPosition));
                    corso.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    mContext.startActivity(corso);

                }
            });
            */
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent multi=new Intent(mContext,MainActivityMultiDipartimento.class);
                    multi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    mContext.startActivity(multi);

                }
            });

        }
        if(groupPosition==1){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent dipartimento=new Intent(mContext,MainActivity.class);
                    dipartimento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    dipartimento.putExtra("id_dipartimento",id_dipartimento);
                    dipartimento.putExtra("nome_dipartimento",nome_dipartimento);
                    dipartimento.putExtra("position", parentHeaders.get(groupPosition));
                    mContext.startActivity(dipartimento);

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
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_gruppo() == id_corso) {
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
                        if(i==0 && second.size()==1){
                            if (livello2dec.get(j).getLivello() == 2 && livello2dec.get(j).getId_pagina() > -2 && livello2dec.get(j).getId_gruppo() == id_corso && livello2dec.get(j).getI() >= second.get(i).getI()) {
                                terzo.add(livello2dec.get(j));
                            }
                        }
                        if(i==0 && second.size()>1) {
                            if (livello2dec.get(j).getLivello() == 2 && livello2dec.get(j).getId_pagina() > -2 && livello2dec.get(j).getId_gruppo() == id_corso && livello2dec.get(j).getI() >= second.get(i).getI() && livello2dec.get(j).getI() < second.get(i+1).getI()) {
                                terzo.add(livello2dec.get(j));
                            }
                        }
                        if(i!=0 && i!=second.size()-1){
                            if (livello2dec.get(j).getLivello() == 2 && livello2dec.get(j).getId_pagina() > -2 && livello2dec.get(j).getId_gruppo() == id_corso && livello2dec.get(j).getI() >= second.get(i).getI() && livello2dec.get(j).getI() < second.get(i+1).getI()) {
                                terzo.add(livello2dec.get(j));
                            }
                        }
                        if(i==second.size()-1){
                            if (livello2dec.get(j).getLivello() == 2 && livello2dec.get(j).getId_pagina() > -2 && livello2dec.get(j).getId_gruppo() == id_corso && livello2dec.get(j).getI() >= second.get(i).getI()) {
                                terzo.add(livello2dec.get(j));
                            }
                        }
                    }

                    if(terzo.size()==0) {
                        Intent visualizza=new Intent(mContext,Visualizza.class);
                        visualizza.putExtra("id_dip", id_dipartimento);
                        visualizza.putExtra("id_corso", id_corso);
                        visualizza.putExtra("secondolv",second.get(i).getTitolo());
                        visualizza.putExtra("position", parentHeaders.get(groupPosition));
                        visualizza.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(visualizza);

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
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_gruppo() == id_corso) {
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
                        if (livello2dec.get(j).getLivello() == 2 && livello2dec.get(j).getId_pagina() > -2 && livello2dec.get(j).getId_gruppo() == id_corso && livello2dec.get(j).getI() >= second.get(i).getI()) {
                            terzo.add(livello2dec.get(j));
                        }
                    }
                    Log.d("terzolivello", "Hai premuto: " + terzo.get(i1).getTitolo());

                    Intent visualizza=new Intent(mContext,Visualizza.class);
                    visualizza.putExtra("id_dip", id_dipartimento);
                    visualizza.putExtra("id_corso",id_corso);
                    visualizza.putExtra("secondolv",second.get(i).getTitolo());
                    visualizza.putExtra("terzolv",terzo.get(i1).getTitolo());
                    visualizza.putExtra("position", parentHeaders.get(groupPosition));
                    visualizza.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(visualizza);

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
                    //Toast.makeText(MainActivity.mContext, "Hai Premuto:\n\n" + corsi.get(i).getId(), Toast.LENGTH_SHORT).show();

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
}
