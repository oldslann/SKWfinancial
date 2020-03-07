package com.example.skwfinancial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.skwfinancial.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends ArrayAdapter {

    private static final String TAG="MyAdapter";

    public ProductAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list)
    {
        super(context,resource,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.product_item,
                    parent,
                    false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        Log.i(TAG, "1");
        TextView title = (TextView) itemView.findViewById(R.id.pname2);
        Log.i(TAG, "2");
        TextView detail = (TextView) itemView.findViewById(R.id.pmoney1);
        Log.i(TAG, "3");
        TextView detai2 = (TextView) itemView.findViewById(R.id.pendt);
        Log.i(TAG, "4");
        title.setText(map.get("name"));
        Log.i(TAG, "5");
        detail.setText(map.get("money"));
        Log.i(TAG, "6");
        detai2.setText(map.get("endt"));
        return itemView;
    }
}
