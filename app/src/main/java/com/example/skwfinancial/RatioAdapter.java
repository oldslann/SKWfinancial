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

public class RatioAdapter extends ArrayAdapter {

    private static final String TAG="MyAdapter";

    public RatioAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list)
    {
        super(context,resource,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.ratio_item,
                    parent,
                    false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.list_i1);
        TextView detail = (TextView) itemView.findViewById(R.id.list_i2);
        title.setText(map.get("country"));
        detail.setText(map.get("ratio"));
        return itemView;
    }

}
