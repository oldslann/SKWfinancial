package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class usrlike extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usrlike);
        ImageButton btn1=findViewById(R.id.usrlicaib);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likelicai();
            }
        });
    }


    public void likelicai(){//打开用户理财产品订单
        Intent intent=new Intent(this,likelicai.class);
        startActivity(intent);

    }
}
