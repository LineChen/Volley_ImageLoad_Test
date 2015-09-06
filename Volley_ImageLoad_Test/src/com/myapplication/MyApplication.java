package com.myapplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static RequestQueue queues;//请求队列
	
	private static Context context;//全局上下文
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		queues = Volley.newRequestQueue(context);
		
	}
	
	/**
	 * 获取全局的网络请求队列 
	 * @return
	 */
	public static RequestQueue getHttpQueues(){
		return queues;
	}
	
	//获得全局上下文
	public static Context getContext(){
		return context;
	}
}















