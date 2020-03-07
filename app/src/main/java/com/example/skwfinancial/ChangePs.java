package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.net.URLEncoder;

public class ChangePs extends AppCompatActivity {

    private  static String TAG="main";
    Bundle bundle;
    String id;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ps);
        bundle=getIntent().getExtras();    //接收Extras
        id=bundle.getString("id");

        Button btn1=findViewById(R.id.changepsb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int1();

            }
        });

    }

    public void int1(){

        String sa=((EditText)findViewById(R.id.cps1)).getText().toString();
        String sb=((EditText)findViewById(R.id.cps2)).getText().toString();

        if (sa.length()<=8)//密码过短
        {
            Toast.makeText(ChangePs.this,"你输入的密码太短",Toast.LENGTH_SHORT).show();

        }

        else
        {
            if (sa.equals(sb))//两次密码相同
            {

                password=sa;
                String url = "http://192.168.1.102:8080/Mserver/home/CPWS";//http://192.168.1.102:8080/home/FirstS
                new CPSTask().execute(url);


            }


            else
            {
                Toast.makeText(ChangePs.this,"两次输入的密码不相同",Toast.LENGTH_SHORT).show();



            }

        }



    }



    class  CPSTask extends AsyncTask<String,Integer,String> {
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


                String param = "id="+id+"&passward="+ password; // 这里我们先模拟一个参数列表
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
            if (result.equals("1"))
            {
                Toast.makeText(ChangePs.this,"更改成功",Toast.LENGTH_SHORT).show();
                ChangePs.this.finish();

            }
            else
            {
                Toast.makeText(ChangePs.this,"更改失败，请重试",Toast.LENGTH_SHORT).show();

            }




        }
    }




}
