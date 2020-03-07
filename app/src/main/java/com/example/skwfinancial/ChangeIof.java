package com.example.skwfinancial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class ChangeIof extends AppCompatActivity {

    private  static String TAG="main";
    String id;
    String sname;
    String spid;
    String sph;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_iof);
        bundle=getIntent().getExtras();    //接收Extras
        id=bundle.getString("id");
        Button btn1=findViewById(R.id.changeiofb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int1();

            }
        });


    }

    public void int1(){

        sname=((EditText)findViewById(R.id.turenametxt)).getText().toString();
        spid=((EditText)findViewById(R.id.pidtxt)).getText().toString();
        sph=((EditText)findViewById(R.id.phtxt)).getText().toString();

        if (sname.isEmpty())
        {
            Toast.makeText(ChangeIof.this,"名字不能为空",Toast.LENGTH_SHORT).show();
        }
        else if (spid.length()!=18)
        {
            Toast.makeText(ChangeIof.this,"请输入正确的身份证号",Toast.LENGTH_SHORT).show();

        }
       else if (sph.length()!=11)
        {
            Toast.makeText(ChangeIof.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();

        }
        else
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示")
                    .setMessage("请确认您的信息：  " +"姓名： "+sname+"  身份证号： "+spid+"   手机号： "+sph+"更改之后将重置您的审核状况")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {//确定更改信息的函数

                            String url = "http://192.168.1.102:8080/Mserver/home/CIof";//http://192.168.1.102:8080/home/FirstS
                            new CIFOTask().execute(url);


                        }
                    })
                    .setNegativeButton("否",null);
            builder.create().show();


        }




    }


    class  CIFOTask extends AsyncTask<String,Integer,String> {
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


                String param = "id="+id+"&truename="+ URLEncoder.encode(sname,"UTF-8")+"&pid="+spid+"&phn="+sph; // 这里我们先模拟一个参数列表
                out.writeBytes(param);

                Log.i(TAG, "out" );
                Log.i(TAG, sname );

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
                Toast.makeText(ChangeIof.this,"信息已提交，请等待审核",Toast.LENGTH_SHORT).show();
                ChangeIof.this.finish();

            }
            else
            {
                Toast.makeText(ChangeIof.this,"出错，请稍后再试",Toast.LENGTH_SHORT).show();
            }




        }
    }



}
