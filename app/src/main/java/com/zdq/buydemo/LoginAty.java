package com.zdq.buydemo;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zdq.buydemo.Database.BuyDemoDatabase;


public class LoginAty extends AppCompatActivity {

    ImageView toolbar_back;
    TextView login_register;
    EditText login_username,login_password;
    String username,password;
    ConstraintLayout login_cons;
    Button login_btn;
    BuyDemoDatabase buyDemoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aty);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initId();
        setListener();
    }

    private void setListener() {
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAty.this.finish();
            }
        });

        login_username.addTextChangedListener(new TextWatcher() {
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

        login_password.addTextChangedListener(new TextWatcher() {
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

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) LoginAty.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                if(null == username || "".equals(username)){
                    Toast.makeText(LoginAty.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(null == password || "".equals(password)){
                    Toast.makeText(LoginAty.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(buyDemoDatabase.isExistThisUser(username)){
                    if(buyDemoDatabase.isPasswordRight(username,password)){
                        Toast.makeText(LoginAty.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        LoginAty.this.finish();
                    }else {
                        Toast.makeText(LoginAty.this,"密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginAty.this,"此用户尚未注册！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) LoginAty.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAty.this,RegisterAty.class);
                startActivity(intent);
                LoginAty.this.finish();
            }
        });
    }

    private void initId() {
        login_register = findViewById(R.id.login_register);
        login_cons = findViewById(R.id.login_cons);
        toolbar_back = findViewById(R.id.toolbar_back);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        buyDemoDatabase = BuyDemoDatabase.getInstance(this);
    }
}
