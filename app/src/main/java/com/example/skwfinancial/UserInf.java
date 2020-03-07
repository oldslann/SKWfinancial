package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class UserInf extends AppCompatActivity {
    Bundle bundle;
    private  static String TAG="main";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inf);
        bundle=getIntent().getExtras();    //接收Extras
        id=bundle.getString("id");
        String url = "http://192.168.1.102:8080/Mserver/home/Iof";//http://192.168.1.102:8080/home/FirstS
        new IFOTask().execute(url);





        Button btn1=findViewById(R.id.changeps);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeps();
            }
        });


        Button btn2=findViewById(R.id.changeiof);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeiof();
            }
        });



        Button btn3=findViewById(R.id.changenick);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInf.this.finish();Intent intent=new Intent(UserInf.this,UserInf.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    public void changeps(){//更改用户密码
        Intent intent=new Intent(this,ChangePs.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    public void changeiof(){//更改用户信息
        Intent intent=new Intent(this,ChangeIof.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }



    class  IFOTask extends AsyncTask<String,Integer,String> {
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
           String a[]=result.split(",");
            TextView t1=(TextView)findViewById(R.id.turename);
            TextView t2=(TextView)findViewById(R.id.peopleid);
            TextView t3=(TextView)findViewById(R.id.phonenum);
            TextView t4=(TextView)findViewById(R.id.credict);
            TextView t5=(TextView)findViewById(R.id.checkph);

            t1.setText(a[0]);
            t2.setText(a[1]);
            t3.setText(a[4]);
            t4.setText(a[2]);
            t5.setText(a[3]);



        }
    }


}
