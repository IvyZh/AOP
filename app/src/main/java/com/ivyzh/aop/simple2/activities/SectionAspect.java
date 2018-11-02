package com.ivyzh.aop.simple2.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 处理切点
 */

@Aspect
public class SectionAspect {


    /**
     * 找到处理的切点
     * * *(..)  可以处理所有的方法
     */
    @Pointcut("execution(@com.ivyzh.aop.simple2.activities.CheckNet * *(..))")
    public void checkNetBehavior() {

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log.d("TAG", "checkNet 处理切面....");// 此处Log日志不可见 MMP
        //Toast.makeText(getContext(joinPoint.getThis()), "此处日志不可见", Toast.LENGTH_LONG).show();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null) {//获取CheckNet注解
            Object obj = joinPoint.getThis();
            Context context = getContext(obj);
            if (context != null) {
                if (!isNetworkConnected(context)) {
                    int resId = checkNet.value();
                    String msg = context.getString(resId);
                    //Toast.makeText(context, msg + " 此处打印日志不可见", Toast.LENGTH_LONG).show();
                    showDialog(context);
                    return null;
                }
            }
        }

        return joinPoint.proceed();
    }

    private void showDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("网络不可用")
                .setMessage("请检查你的网络哦")
                .setPositiveButton("去设置", null)
                .show();
    }


    @SuppressLint("NewApi")
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getContext();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }

    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
