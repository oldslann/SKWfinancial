package com.example.skwfinancial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter1 extends ArrayAdapter {
    private static final String TAG="MyAdapter";

    public MyAdapter1(Context context, int resource, ArrayList<HashMap<String,String>> list)
    {
        super(context,resource,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,
                    false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.list_i1);
        title.setText( map.get("name"));
        return itemView;
    }
}
