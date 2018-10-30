package com.example.dell1.wechattest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import cn.bmob.v3.Bmob;

public class Welcome extends AppCompatActivity {

    private static final int DELAY = 2000;
    private static final int GO_GUIDE = 0;
    private static final int GO_HOME = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"9a79a911c62873b5b7bf086e6e068eaa");
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initLoad();
    }
    private void initLoad(){
        SharedPreferences sharedPreferences=getSharedPreferences("wechattest",MODE_PRIVATE);
        boolean welcome =sharedPreferences.getBoolean("welcome",true);
        if(!welcome){//只在第一次显示此页面
            handler.sendEmptyMessageDelayed(GO_HOME,DELAY);
        }
        else
        {
            handler.sendEmptyMessageDelayed(GO_GUIDE,DELAY);
           SharedPreferences.Editor editor=sharedPreferences.edit();
           editor.putBoolean("welcome",false);
           editor.apply();

        }
    }
    private void goHome()
    {
        Intent intent=new Intent(this,LoginOrRegister.class);
        startActivity(intent);
        finish();
    }
    private void goGuide()
    {
        Intent intent=new Intent(this,Guide.class);
        startActivity(intent);
        finish();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what)
            {
                case GO_GUIDE:{
                    goGuide();
                    break;
                }
                case GO_HOME:{
                    goHome();
                    break;
                }
            }
        }
    };
}
