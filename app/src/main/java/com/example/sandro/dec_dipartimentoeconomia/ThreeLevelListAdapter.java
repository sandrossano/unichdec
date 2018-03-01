package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.livello2dec;

public class ThreeLevelListAdapter extends BaseExpandableListAdapter {

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


        // no idea why this code is working

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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group, null);
        TextView text = (TextView) convertView.findViewById(R.id.submenu1);
        text.setText(this.parentHeaders.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

        String[] headers = secondLevel.get(groupPosition);


        List<String[]> childData = new ArrayList<>();
        HashMap<String, String[]> secondLevelData = data.get(groupPosition);

        for (String key : secondLevelData.keySet()) {


            childData.add(secondLevelData.get(key));

        }


        secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers, childData));

        secondLevelELV.setGroupIndicator(null);

        secondLevelELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String prova="";
                prova="Hai premuto: "+MainActivity.parent.get(groupPosition).getNome() +", id: "+MainActivity.parent.get(groupPosition).getId();
                Log.d("primolivello",prova);

                ArrayList<SplashActivity.SottoLivelli>second=new ArrayList<SplashActivity.SottoLivelli>();
                for (int j=0;j<livello2dec.size();j++) {
                    if(MainActivity.parent.get(groupPosition).getId()==1) {
                        if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_pagina() == 0 && livello2dec.get(j).getId_gruppo() == MainActivity.parent.get(groupPosition).getId()) {
                            second.add(livello2dec.get(j));
                        }
                    }
                        else{
                            if (livello2dec.get(j).getLivello() == 1 && livello2dec.get(j).getId_pagina() <= 0 && livello2dec.get(j).getId_gruppo() == MainActivity.parent.get(groupPosition).getId()) {
                                second.add(livello2dec.get(j));
                            }
                        }
                }
                Log.d("secondolivello","Hai premuto: "+second.get(i).getTitolo());

                ArrayList<SplashActivity.SottoLivelli>terzo=new ArrayList<SplashActivity.SottoLivelli>();
                for (int j=0;j<livello2dec.size();j++) {
                        if(livello2dec.get(j).getLivello()==2 && livello2dec.get(j).getId_pagina()>-2 && livello2dec.get(j).getId_gruppo()==MainActivity.parent.get(groupPosition).getId() && livello2dec.get(j).getI()>=second.get(i).getI()){
                            terzo.add(livello2dec.get(j));
                        }
                }
                Log.d("terzolivello","Hai premuto: "+terzo.get(i1).getTitolo());;
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
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
