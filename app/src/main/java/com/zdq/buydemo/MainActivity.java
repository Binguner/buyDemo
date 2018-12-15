package com.zdq.buydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ImageView pro_main_0_0,bq_0_0,main_search_btn;
    private TextView footer_login;
    private TextView footer_register;
    private EditText main_search;
    NestedScrollView main_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusbarTextBlack(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        initID();
        setListener();
        initViews();
    }

    private void initViews() {
        main_search.clearFocus();
    }

    private void setListener() {
        pro_main_0_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,detial_aty.class);
                startActivity(intent);
            }
        });

        bq_0_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,detial_aty.class);
                startActivity(intent);
            }
        });

        footer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginAty.class);
                startActivity(intent);
            }
        });
        footer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterAty.class);
                startActivity(intent);
            }
        });

        main_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ((scrollY-oldScrollY) != 0){
                    InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
        });
        main_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                Toast.makeText(MainActivity.this,"正在搜索，请稍后...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initID() {
        main_search_btn = findViewById(R.id.main_search_btn);
        main_scroll = findViewById(R.id.main_scroll);
        main_search = findViewById(R.id.main_search);
        pro_main_0_0 = findViewById(R.id.pro_main_0_0);
        bq_0_0 = findViewById(R.id.bq_0_0);
        footer_login = findViewById(R.id.footer_login);
        footer_register = findViewById(R.id.footer_register);
    }
}
