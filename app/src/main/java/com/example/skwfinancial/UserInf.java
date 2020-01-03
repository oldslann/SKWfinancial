package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class UserInf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inf);

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

            }
        });

    }

    public void changeps(){//更改用户密码
        Intent intent=new Intent(this,ChangePs.class);
        startActivity(intent);

    }
    public void changeiof(){//更改用户信息
        Intent intent=new Intent(this,ChangeIof.class);
        startActivity(intent);

    }



}
