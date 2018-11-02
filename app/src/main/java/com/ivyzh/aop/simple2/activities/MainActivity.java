package com.ivyzh.aop.simple2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ivyzh.aop.R;
import com.ivyzh.aop.simple1.AccountActivity;
import com.ivyzh.aop.simple1.FinaceActivity;
import com.ivyzh.aop.simple1.SettingsActivity;
import com.ivyzh.aop.simple1.TransActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 账户 需要网络监测
    @CheckNet(R.string.str_net_error)
    public void request(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    // 转账 需要网络监测
    @CheckNet(R.string.str_net_error1)
    public void request1(View view) {
        Intent intent = new Intent(this, TransActivity.class);
        startActivity(intent);
    }

    // 理财 需要网络监测
    @CheckNet(R.string.str_net_error2)
    public void request2(View view) {
        Intent intent = new Intent(this, FinaceActivity.class);
        startActivity(intent);
    }

    // 设置 不需要网络监测
    public void request3(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
