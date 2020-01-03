package com.example.skwfinancial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class News extends AppCompatActivity  implements Runnable, AdapterView.OnItemClickListener  {

    String url="";
    private  static String TAG="main";
    Handler handler;
    ListView listView;
    ArrayList<HashMap<String, String>> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);




        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what ==8 ) {
                    listView=(ListView)findViewById(R.id.news_item);


                    listItems = (ArrayList<HashMap<String, String>>) msg.obj;

                    MyAdapter1 myAdapter = new MyAdapter1(News.this,
                            R.layout.list_item,
                            listItems);
                    Log.i(TAG, "listView");
                    listView.setAdapter(myAdapter);
                    listView.setEmptyView(findViewById(R.id.emptyI));
                    listView.setOnItemClickListener(News.this);


                }


                super.handleMessage(msg);
            }
        };

        //开启子进程
        Thread th=new Thread(News.this);
        th.start();


    }

    public void run() {


        Log.i(TAG, "run=  .......... " );



        try {
            url="https://finance.sina.com.cn/";
            Log.i(TAG, "start url " );
            Document doc= Jsoup.connect(url).get();
            Log.i(TAG, "get url " );
            Elements item=doc.getElementsByClass("m-hdline");
            Element body=item.get(0);
            Elements h=body.select("a");
            ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
            Log.i(TAG, "creat hashmap " );

            for(int i=0;i<h.size();i++)
            {
                Element hn=h.get(i);
                String net=hn.attr("href");
                String name=hn.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("name",name);
                map.put("net",net);
                listItems.add(map);

                //list.add(str1+"----->"+val);
                Log.i(TAG, name);
                Log.i(TAG, net);

            }



            /*
            url="http://www.caijing.com.cn/";
            item=doc.getElementsByClass("yaowen_ul yaowen_ulli");
            body=item.get(0);
            h=body.select("a");

            for(int i=0;i<h.size();i++)
            {
                Element hn=h.get(i);
                String net=hn.attr("href");
                String name=hn.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("name",name);
                map.put("net",net);
                listItems.add(map);

                //list.add(str1+"----->"+val);
                Log.i(TAG, name);
                Log.i(TAG, net);

            }
            */


            Message msg=handler.obtainMessage(8);//获取对象 返回主线程
            //msg.what=6;
            //msg.obj=list;
            msg.obj=listItems;
            handler.sendMessage(msg);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG,"wrong");
        }






    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,
    int position, long id) {


        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        String url = map.get("net");
        Log.i(TAG, "onItemClick: titleStr=" + url);


        Intent config = new Intent(this, webc.class);
        config.putExtra("url", url);
        startActivity(config);

    }


}
