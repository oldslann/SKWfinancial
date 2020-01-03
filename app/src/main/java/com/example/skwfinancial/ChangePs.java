package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ps);

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

                Toast.makeText(ChangePs.this,"更改成功",Toast.LENGTH_SHORT).show();
                this.finish();

            }


            else
            {
                Toast.makeText(ChangePs.this,"两次输入的密码不相同",Toast.LENGTH_SHORT).show();



            }

        }



    }


}
