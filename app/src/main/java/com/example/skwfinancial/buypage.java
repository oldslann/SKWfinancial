package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class buypage extends AppCompatActivity {

    private  static String TAG="main";
    String pid;
    String time;
    String id;
    String least;
    Bundle bundle;
    int moneyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buypage);



        bundle=getIntent().getExtras();    //接收Extras

        Log.i(TAG, "in" );
        String name;
        String interest;
        String danger;


        id=bundle.getString("id");
        pid=bundle.getString("pid");
        name=bundle.getString("name");
        interest=bundle.getString("interest");
        danger=bundle.getString("danger");
        least=bundle.getString("least");
        time=bundle.getString("time");
        Log.i(TAG, id );
        Log.i(TAG, pid );
        Log.i(TAG, name );
        Log.i(TAG, interest );
        TextView t1=(TextView)findViewById(R.id.pronameb);
        TextView t2=(TextView)findViewById(R.id.dangerb);
        TextView t3=(TextView)findViewById(R.id.interestb);
        TextView t4=(TextView)findViewById(R.id.leastb);


        t1.setText(name);
        t2.setText(danger);
        t3.setText(interest);
        t4.setText(least);

        Button btn1=findViewById(R.id.sendL);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int1();
            }
        });

    }


    void int1(){
        EditText t1=(EditText)findViewById(R.id.buym);

        String money=t1.getText().toString().trim();
        moneyi=Integer.valueOf(money);
        int leasti=Integer.valueOf(least);
        if(moneyi<leasti)
        {
            Toast.makeText(getApplicationContext(),"输入金额小于最小限度",Toast.LENGTH_SHORT).show();

        }
        else
        {
            String url = "http://192.168.1.102:8080/Mserver/home/buyP";//http://192.168.1.102:8080/home/FirstS
            new BuyTask().execute(url);
        }


    }



    class  BuyTask extends AsyncTask<String,Integer,String> {
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


                String param = "id="+id+"&pid="+pid+"&money="+moneyi+"&time="+time; // 这里我们先模拟一个参数列表
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
            if(result.equals("1")){
                Toast.makeText(getApplicationContext(),"购买成功，详情请在订单中查看",Toast.LENGTH_SHORT).show();
                buypage.this.finish();

                return;
            }
            else{

                Toast.makeText(getApplicationContext(),"购买失败,账号或未通过审核",Toast.LENGTH_SHORT).show();

                return;

            }

        }
    }


}
