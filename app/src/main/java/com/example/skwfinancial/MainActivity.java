package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.loginbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int1();
            }
        });
        Button btn2=findViewById(R.id.signbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int2();
            }
        });

    }

    public void int1()
    {
        Intent intent=new Intent(this,tableact.class);
        startActivity(intent);
        this.finish();


    }
    public void int2()
    {
        Intent intent=new Intent(this,signact.class);
        startActivity(intent);


    }
}
