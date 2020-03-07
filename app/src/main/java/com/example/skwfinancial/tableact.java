package com.example.skwfinancial;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageButton;

public class tableact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  static String TAG="MAIN";
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle=getIntent().getExtras();    //接收Extras
        String id=bundle.getString("id");
        Log.i(TAG, id+"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" );

        ImageButton btn1=findViewById(R.id.licai);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                licai();

            }
        });

        ImageButton btn2=findViewById(R.id.waihui);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waihui();

            }
        });

        ImageButton btn3=findViewById(R.id.gupiao);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitingF();

            }
        });

        ImageButton btn4=findViewById(R.id.baoxian);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitingF();

            }
        });







        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fabc();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tableact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {//左拉框
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ueriof();

        } else if (id == R.id.nav_gallery) {
            usrpro();

        } else if (id == R.id.nav_slideshow) {
            usrl();

        } else if (id == R.id.nav_tools) {
            sysiofm();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //新闻列表
    public void fabc(){
        Intent intent=new Intent(this,News.class);
        startActivity(intent);
    }


    //理财
    public void licai(){
        Intent intent=new Intent(this,Licai.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //外汇
    public void waihui(){
        Intent intent=new Intent(this,waihui.class);
        startActivity(intent);

    }


    //未做完页面
    public void waitingF(){
        Intent intent=new Intent(this,WaitingF.class);
        startActivity(intent);

    }


    //打开用户信息
    public void ueriof(){
        Intent intent=new Intent(this,UserInf.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //打开用户订单
    public void usrpro(){
        Intent intent=new Intent(this,usrproduct.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //打开用户收藏
    public void usrl(){
        Intent intent=new Intent(this,usrlike.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //打开系统信息
    public void sysiofm(){
        Intent intent=new Intent(this,sysiof.class);
        startActivity(intent);

    }



}
