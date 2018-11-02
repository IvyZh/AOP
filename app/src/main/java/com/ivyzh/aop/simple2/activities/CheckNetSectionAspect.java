package com.ivyzh.aop.simple2.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Ivy on 2018/11/2.
 */

@Aspect
public class CheckNetSectionAspect {


    @Pointcut("execution(@com.ivyzh.aop.simple2.activities.HxgAopCheckNet * *(..))")
    public void checkNetBehavior() {

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) {
        Log.d("TAG", "checkNet 处理切面....");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        HxgAopCheckNet checkNet = methodSignature.getMethod().getAnnotation(HxgAopCheckNet.class);
        if (checkNet != null) {
            Object object = joinPoint.getThis();
            Context context = getContext(object);
            if (context != null) {
                if (!isNetworkConnected(context)) {
                    int resId = checkNet.value();
                    Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
                    return null;
                }
            }
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
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

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    private static boolean isNetworkConnected(Context context) {
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