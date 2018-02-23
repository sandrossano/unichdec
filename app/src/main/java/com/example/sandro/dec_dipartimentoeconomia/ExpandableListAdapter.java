package com.example.sandro.dec_dipartimentoeconomia;

/**
 * Created by Sandro on 20/02/2018.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.corsi;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ExpandedMenuModel> mListDataHeader; // header titles

    // child data in format of header title, child title
    private HashMap<ExpandedMenuModel, List<String>> mListDataChild;
    ExpandableListView expandList;

    public ExpandableListAdapter(Context context, List<ExpandedMenuModel> listDataHeader, HashMap<ExpandedMenuModel, List<String>> listChildData, ExpandableListView mView) {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listChildData;
        this.expandList = mView;
    }

    @Override
    public int getGroupCount() {
        int i = mListDataHeader.size();
        //Log.d("GROUPCOUNT", String.valueOf(i));
        return this.mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;

            childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                    .size();


        //Log.d("childcount", String.valueOf(childCount));
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
       Log.d("groupId:", String.valueOf(groupPosition));
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.d("childId", String.valueOf(childPosition));
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpandedMenuModel headerTitle = (ExpandedMenuModel) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.submenu);
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getIconName());
        headerIcon.setImageResource(headerTitle.getIconImg());

        ImageView identi = (ImageView)convertView.findViewById(R.id.ident);
        if (isExpanded) {
            identi.setImageResource(R.drawable.arrow_left_white);
        } else {
            identi.setImageResource(R.drawable.icon_down_arrow_white);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(childText);

        if(groupPosition>0) {
            ImageView identichild = (ImageView) convertView.findViewById(R.id.identchild);
            identichild.setVisibility(View.VISIBLE);
            identichild.setImageResource(R.drawable.child_arrow_right);
        }else{ImageView identichild = (ImageView) convertView.findViewById(R.id.identchild);
            identichild.setVisibility(View.INVISIBLE);}
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}