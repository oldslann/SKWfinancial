package com.example.skwfinancial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class waihui extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {

    String url = "http://www.usd-cny.com/bankofchina.htm";
    private static String TAG = "main";
    Handler handler;
    ListView listView;
    ArrayList<HashMap<String, String>> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waihui);


        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 6) {
                    listView=(ListView)findViewById(R.id.waihui_item);


                    listItems = (ArrayList<HashMap<String, String>>) msg.obj;

                    RatioAdapter RatioAdapter = new RatioAdapter(waihui.this,
                            R.layout.list_item,
                            listItems);
                    Log.i(TAG, "listView");
                    listView.setAdapter(RatioAdapter);
                    listView.setEmptyView(findViewById(R.id.emptyI));
                    listView.setOnItemClickListener(waihui.this);


                }


                super.handleMessage(msg);
            }
        };

        //开启子进程
        Thread th = new Thread(waihui.this);
        th.start();


    }

    public void run() {


        Log.i(TAG, "run=  .......... ");


        try {
            Document doc= Jsoup.connect(url).get();
            Elements tables=doc.getElementsByTag("table");
            Element table6=tables.get(0);
            Elements tds=table6.getElementsByTag("td");

            System.out.println(tds.size());
            //List<String> list=new ArrayList<String>();
            ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();


            for(int i=0;i<=tds.size()-6;i+=6)
            {
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String str1=td1.text();
                String val=td2.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("country",str1);
                map.put("ratio",val);
                listItems.add(map);

                //list.add(str1+"----->"+val);

                Log.i(TAG, str1);
                Log.i(TAG, val);

            }

            Message msg = handler.obtainMessage(6);//获取对象 返回主线程
            //msg.what=6;
            //msg.obj=list;
            msg.obj = listItems;
            handler.sendMessage(msg);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG, "wrong");
        }


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {




    }
}