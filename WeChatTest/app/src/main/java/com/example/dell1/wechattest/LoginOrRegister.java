package com.example.dell1.wechattest;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.view.adapter.clientIfo;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {

    private TabHost tabHost;

    private Button btnLogin;
    private EditText etLoginUsername;
    private EditText etLoginPassword;
    public Toast toast;
    private Button btnRegister;
    private EditText etRegisterUsername;
    private EditText etRegisterPassword;
    private EditText etInsurePassword;

   /* private ServerManager serverManager = ServerManager.getServerManager();*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginor_register);

        initViews();
    }

    private void initViews() {
        tabHost = (TabHost) findViewById(R.id.tabHost);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etLoginUsername = (EditText) findViewById(R.id.et_login_username);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);

        btnRegister = (Button) findViewById(R.id.btn_register);
        etRegisterUsername = (EditText) findViewById(R.id.et_register_username);
        etRegisterPassword = (EditText) findViewById(R.id.et_register_password);
        etInsurePassword = (EditText) findViewById(R.id.et_insure_password);

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("Login").setIndicator("Login").setContent(R.id.login));
        tabHost.addTab(tabHost.newTabSpec("Register").setIndicator("Register").setContent(R.id.register));

        for (int i = 0; i < 2; i++) {
            TextView tv = ((TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title));
            tv.setAllCaps(false);
            tv.setTextSize(16);
        }

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
       /*serverManager.start();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                String username = etLoginUsername.getText().toString();
                String password = etLoginPassword.getText().toString();
                if (login(username, password)) {
                  /*  serverManager.setUsername(username);*/
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    etLoginUsername.setText("");
                    etLoginPassword.setText("");
                }
                break;
            }
            case R.id.btn_register: {
                String username = etRegisterUsername.getText().toString();
                String password = etRegisterPassword.getText().toString();
                final clientIfo clientIfo=new clientIfo();
                clientIfo.setUsersName(username);
                clientIfo.setCode(password);
                clientIfo.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null)
                        {Toast.makeText(LoginOrRegister.this,"注册成功",Toast.LENGTH_SHORT).show();

                        }

                        else
                            Toast.makeText(LoginOrRegister.this,"注册失败",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default:
                break;
        }
    }

    private boolean login(String username, String password) {
        // check username and password whether legal
        if (username == null || username.length() > 10 || password.length() > 20) {
            return false;
        }
        // send msg to servers
        BmobQuery<clientIfo> bmobQuery=new BmobQuery<clientIfo>();

        /*  serverManager.sendMessage(this, msg);*/
        // get msg from servers return
        /*String ack = serverManager.getMessage();*/
        // deal msg
        // if (ack == null) {
        //    return false;
        //}
        // serverManager.setMessage(null);
        String p = "\\[ACKLOGIN\\]:\\[(.*)\\]";
        Pattern pattern = Pattern.compile(p);
        //Matcher matcher = pattern.matcher(ack);
        //return matcher.find() && matcher.group(1).equals("1");
        //}
//}
        return true;
    }}