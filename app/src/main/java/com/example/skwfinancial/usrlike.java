package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class usrlike extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usrlike);
        ImageButton btn1=findViewById(R.id.likelicai);
        bundle=getIntent().getExtras();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likelicai();
            }
        });
    }


    public void likelicai(){//打开用户理财产品订单
        Intent intent=new Intent(this,likelicai.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
