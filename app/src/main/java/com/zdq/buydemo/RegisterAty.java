package com.zdq.buydemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zdq.buydemo.Database.BuyDemoDatabase;

public class RegisterAty extends AppCompatActivity {

    EditText register_username,register_password;
    Button register_ok;
    String username;
    String password;
    BuyDemoDatabase buyDemoDatabase;
    ImageView register_back;
    TextView register_help;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_aty);
        StatusBarUtil.setStatusBarColor(this, R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initID();
        setListener();
    }

    private void setListener() {
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterAty.this.finish();
            }
        });
        register_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterAty.this,"此功能正在开发！",Toast.LENGTH_SHORT).show();
            }
        });

        register_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username = s.toString();
            }
        });

        register_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }

        });

        register_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) RegisterAty.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                if (null == username || "".equals(username)){
                    Toast.makeText(RegisterAty.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (null == password || "".equals(password)){
                    Toast.makeText(RegisterAty.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (buyDemoDatabase.isExistThisUser(username)){
                    Toast.makeText(RegisterAty.this,"此用户名已存在！",Toast.LENGTH_SHORT).show();
                }else {
                    buyDemoDatabase.insertUser(username,password);
                    editor.putBoolean("isLogging",true);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.putExtra("ok","ok");
                    RegisterAty.this.setResult(1,intent);
                    Toast.makeText(RegisterAty.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    RegisterAty.this.finish();
                }
            }
        });
    }

    private void initID() {
        editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences("userdata",MODE_PRIVATE);
        register_help = findViewById(R.id.register_help);
        register_password = findViewById(R.id.register_password);
        register_username = findViewById(R.id.register_username);
        register_ok = findViewById(R.id.register_ok);
        buyDemoDatabase = BuyDemoDatabase.getInstance(this);
        register_back = findViewById(R.id.register_back);
    }
}
