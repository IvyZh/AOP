package com.ivyzh.aop.simple1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ivyzh.aop.R;
import com.ivyzh.aop.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 账户 需要网络监测
    public void request(View view) {
        if (Utils.isNetworkConnected(this)) {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        } else {
            //提示网络不可用，可能是Toast或者Dialog
            Toast.makeText(this, "网络不可用", Toast.LENGTH_LONG).show();
        }
    }

    // 转账 需要网络监测
    public void request1(View view) {
        if (Utils.isNetworkConnected(this)) {
            Intent intent = new Intent(this, TransActivity.class);
            startActivity(intent);
        } else {
            //提示网络不可用，可能是Toast或者Dialog
            Toast.makeText(this, "网络不可用", Toast.LENGTH_LONG).show();
        }
    }

    // 理财 需要网络监测
    public void request2(View view) {
        if (Utils.isNetworkConnected(this)) {
            Intent intent = new Intent(this, FinaceActivity.class);
            startActivity(intent);
        } else {
            //提示网络不可用，可能是Toast或者Dialog
            Toast.makeText(this, "网络不可用", Toast.LENGTH_LONG).show();
        }
    }

    // 设置 不需要网络监测
    public void request3(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
