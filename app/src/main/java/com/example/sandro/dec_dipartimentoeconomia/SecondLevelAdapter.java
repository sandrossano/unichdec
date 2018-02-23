package com.example.sandro.dec_dipartimentoeconomia;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ExpandableListAdapter;

import java.util.List;
import java.util.Map;

public class SecondLevelAdapter extends BaseExpandableListAdapter
{
    private final Context mContext;
    private final List<String> mListDataHeader;
    private final Map<String, List<String>> mListDataChild;
    public SecondLevelAdapter(Context mContext, List<String> mListDataHeader, Map<String, List<String>> mListDataChild) {
        this.mContext = mContext;
        this.mListDataHeader = mListDataHeader;
        this.mListDataChild = mListDataChild;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(childText);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition)
    {
        try {
            return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public Object getGroup(int groupPosition)
    {
        return this.mListDataHeader.get(groupPosition);
    }
    @Override
    public int getGroupCount()
    {
        return this.mListDataHeader.size();
    }
    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){

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
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
