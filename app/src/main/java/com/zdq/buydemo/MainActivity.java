package com.zdq.buydemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ImageView pro_main_0_0,bq_0_0,main_search_btn;
    private TextView footer_login,footer_go_top,footer_logout;
    private TextView footer_register;
    private EditText main_search;
    NestedScrollView main_scroll;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    Boolean isLogging = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusbarTextBlack(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        initID();
        initDatas();
        setListener();
        initViews();
    }

    private void initDatas() {
        editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences("userdata",MODE_PRIVATE);
        isLogging = sharedPreferences.getBoolean("isLogging",false);
        if(isLogging){
            footer_login.setVisibility(View.GONE);
            footer_register.setVisibility(View.GONE);
            footer_logout.setVisibility(View.VISIBLE);
        }else {
            footer_login.setVisibility(View.VISIBLE);
            footer_register.setVisibility(View.VISIBLE);
            footer_logout.setVisibility(View.GONE);
        }

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
                startActivityForResult(intent,1);
            }
        });
        footer_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterAty.class);
                startActivityForResult(intent,1);
                //startActivity(intent);
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

        footer_go_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_scroll.fling(0);
                main_scroll.smoothScrollTo(0,0);
            }
        });

        footer_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isLogging",false);
                Toast.makeText(MainActivity.this,"退出成功!",Toast.LENGTH_SHORT).show();
                footer_login.setVisibility(View.VISIBLE);
                footer_register.setVisibility(View.VISIBLE);
                footer_logout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            footer_login.setVisibility(View.GONE);
            footer_register.setVisibility(View.GONE);
            footer_logout.setVisibility(View.VISIBLE);
        }
    }

    private void initID() {
        footer_logout = findViewById(R.id.footer_logout);
        footer_go_top = findViewById(R.id.footer_go_top);
        main_search_btn = findViewById(R.id.main_search_btn);
        main_scroll = findViewById(R.id.main_scroll);
        main_search = findViewById(R.id.main_search);
        pro_main_0_0 = findViewById(R.id.pro_main_0_0);
        bq_0_0 = findViewById(R.id.bq_0_0);
        footer_login = findViewById(R.id.footer_login);
        footer_register = findViewById(R.id.footer_register);
    }
}
