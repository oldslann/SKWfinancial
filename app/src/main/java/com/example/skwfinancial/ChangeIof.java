package com.example.skwfinancial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeIof extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_iof);
        Button btn1=findViewById(R.id.changeiofb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int1();

            }
        });


    }

    public void int1(){

        String sname=((EditText)findViewById(R.id.turenametxt)).getText().toString();
        String spid=((EditText)findViewById(R.id.pidtxt)).getText().toString();
        String sph=((EditText)findViewById(R.id.phtxt)).getText().toString();

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


                            Toast.makeText(ChangeIof.this,"信息已提交，请等待审核",Toast.LENGTH_SHORT).show();
                            ChangeIof.this.finish();
                        }
                    })
                    .setNegativeButton("否",null);
            builder.create().show();


        }




    }



}
