package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BPLPage extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bplpage);
        bundle=getIntent().getExtras();

        Button btn1=findViewById(R.id.ppo);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int1();
            }
        });

        String did;
        String name;
        String money;
        String bet;
        String endt;


        did=bundle.getString("did");
        name=bundle.getString("name");
        money=bundle.getString("money");
        bet=bundle.getString("did");
        bet=bet.substring(0,8);
        endt=bundle.getString("endt");

        TextView t1=(TextView)findViewById(R.id.bdid);
        TextView t2=(TextView)findViewById(R.id.bdname);
        TextView t3=(TextView)findViewById(R.id.bdm);
        TextView t4=(TextView)findViewById(R.id.bdt);
        TextView t5=(TextView)findViewById(R.id.bde);


        t1.setText(did);
        t2.setText(name);
        t3.setText(money);
        t4.setText(bet);
        t5.setText(endt);


    }


    void int1()
    {
        Intent intent=new Intent(BPLPage.this,productpage.class);
        intent.putExtras(bundle);
        startActivity(intent);



    }

}
