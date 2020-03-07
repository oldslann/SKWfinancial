package com.example.skwfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class productpage extends AppCompatActivity {

    String id;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);
        bundle=getIntent().getExtras();    //接收Extras
        Button btn1=findViewById(R.id.buyb);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int1();
            }
        });

        String pid;
        String name;
        String interest;
        String style;
        String danger;
        String time;
        String least;
        String dateb;
        String datee;
        String owner;
        id=bundle.getString("id");
        pid=bundle.getString("pid");
        name=bundle.getString("name");
        interest=bundle.getString("interest");
        style=bundle.getString("style");
        danger=bundle.getString("danger");
        time=bundle.getString("time");
        least=bundle.getString("least");
        dateb=bundle.getString("dateb");
        datee=bundle.getString("datee");
        owner=bundle.getString("owner");
        TextView t1=(TextView)findViewById(R.id.proname);
        TextView t2=(TextView)findViewById(R.id.prostyle);
        TextView t3=(TextView)findViewById(R.id.danger);
        TextView t4=(TextView)findViewById(R.id.howlong);
        TextView t5=(TextView)findViewById(R.id.prointererst);
        TextView t6=(TextView)findViewById(R.id.proleast);
        TextView t7=(TextView)findViewById(R.id.proB);
        TextView t8=(TextView)findViewById(R.id.proE);
        TextView t9=(TextView)findViewById(R.id.prosource);

        t1.setText(name);
        t2.setText(style);
        t3.setText(danger);
        t4.setText(time);
        t5.setText(interest);
        t6.setText(least);
        t7.setText(dateb);
        t8.setText(datee);
        t9.setText(owner);



    }
    void int1()
    {
        Intent intent=new Intent(productpage.this,buypage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
