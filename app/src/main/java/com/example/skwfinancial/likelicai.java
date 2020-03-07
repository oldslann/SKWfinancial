package com.example.skwfinancial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class likelicai extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String id;
    Bundle bundle;
    ListView listView;
    String pid;
    private  static String TAG="main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelicai);
        bundle=getIntent().getExtras();    //接收Extras
        id=bundle.getString("id");
        String url = "http://192.168.1.102:8080/Mserver/home/likeitem";//http://192.168.1.102:8080/home/FirstS
        new LProductTask().execute(url);


    }

    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id1) {

        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;

        Bundle bundle1 = new Bundle();           //为bundle分配
        bundle1.putString("id",id);
        bundle1.putString("pid",map.get("pid"));
        bundle1.putString("name",map.get("country"));
        bundle1.putString("interest",map.get("ratio"));
        bundle1.putString("style",map.get("style"));
        bundle1.putString("danger",map.get("danger"));
        bundle1.putString("time",map.get("time"));
        bundle1.putString("least",map.get("least"));
        bundle1.putString("dateb",map.get("dateb"));
        bundle1.putString("datee",map.get("datee"));
        bundle1.putString("owner",map.get("owner"));

        Intent intent=new Intent(likelicai.this,productpage.class);
        intent.putExtras(bundle1);
        startActivity(intent);


    }



    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {


        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        pid=map.get("pid");

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("是否将其移除收藏夹")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG,"longclick");

                        String url = "http://192.168.1.102:8080/Mserver/home/Dlike";//http://192.168.1.102:8080/home/FirstS
                        new DLikeTask().execute(url);


                    }
                })
                .setNegativeButton("否",null);
        builder.create().show();
        return true;
    }




    class  LProductTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... params) {
            String par  = params[0];
            URL url = null;
            HttpURLConnection connection = null;
            String result="";
            try {
                url = new URL(par);
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                Log.i(TAG, "ping" );

                // 第二步：设置HttpURLConnection连接相关属性
                connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                // 如果是POST方法，需要在第3步获取输入流之前向连接写入POST参数
                DataOutputStream out = new DataOutputStream(connection.getOutputStream()); // 在此之前也可以用connection.getResponseCode()获取返回码，看是否为200


                String param = "id="+id; // 这里我们先模拟一个参数列表
                out.writeBytes(param);

                Log.i(TAG, "out" );

                // 第三步：打开连接输入流读取返回报文，*注意*在此步骤才真正开始网络请求
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理

                Log.i(TAG, "in" );
                // ...省略返回报文处理代码
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                result=response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i(TAG, result );
            return result;

        }
        //第四步
        @Override
        protected void onPostExecute(String result) {//weizhixing
            //super.onPostExecute(result);
            //保存数据
            String line[]=result.split(";");
            ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
            for (int i=0;i<line.length;i++)
            {
                String a[]=line[i].split(",");
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("pid",a[0]);
                map.put("country",a[1]);
                map.put("ratio",a[2]);
                map.put("style",a[3]);
                map.put("danger",a[4]);
                map.put("time",a[5]);
                map.put("least",a[6]);
                map.put("dateb",a[7]);
                map.put("datee",a[8]);
                map.put("owner",a[9]);



                listItems.add(map);

            }
            RatioAdapter RatioAdapter = new RatioAdapter(likelicai.this,
                    R.layout.list_item,
                    listItems);
            Log.i(TAG, "listView");
            listView=(ListView)findViewById(R.id.like_item);
            listView.setAdapter(RatioAdapter);
            listView.setEmptyView(findViewById(R.id.emptyI));
            listView.setOnItemClickListener(likelicai.this);
            listView.setOnItemLongClickListener(likelicai.this);



        }
    }




    class  DLikeTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... params) {
            String par  = params[0];
            URL url = null;
            HttpURLConnection connection = null;
            String result="";
            try {
                url = new URL(par);
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                Log.i(TAG, "ping" );

                // 第二步：设置HttpURLConnection连接相关属性
                connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                // 如果是POST方法，需要在第3步获取输入流之前向连接写入POST参数
                DataOutputStream out = new DataOutputStream(connection.getOutputStream()); // 在此之前也可以用connection.getResponseCode()获取返回码，看是否为200


                String param = "user="+id+"&pid="+pid; // 这里我们先模拟一个参数列表
                out.writeBytes(param);

                Log.i(TAG, "out" );

                // 第三步：打开连接输入流读取返回报文，*注意*在此步骤才真正开始网络请求
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理

                Log.i(TAG, "in" );
                // ...省略返回报文处理代码
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder response = new StringBuilder();
                if ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                result=response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i(TAG, result+"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" );
            return result;

        }
        //第四步
        @Override
        protected void onPostExecute(String result) {//weizhixing
            //super.onPostExecute(result);
            //保存数据
            if (result.equals("1"))
            {

                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                likelicai.this.finish();
                Intent intent=new Intent(likelicai.this,likelicai.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"删除失败，检查网络设置",Toast.LENGTH_SHORT).show();

            }



        }

    }


}
