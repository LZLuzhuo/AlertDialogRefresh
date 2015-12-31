package com.example.dialogrefresh;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * =================================================
 * 
 * 作者:卢卓
 * 
 * 版本:1.0
 * 
 * 创建日期:2015-12-20 下午6:19:23
 * 
 * 描述:异步方式回调
 * 
 * 修订历史:
 * 
 * 
 * =================================================
 **/
public class DialogAsync extends AsyncTask<Context, Void, Void> {
	private int x = 0;
	private OnBack onBack;

	@Override
	protected Void doInBackground(Context... params) {
		if(onBack != null) onBack.onStart();
		while(x<10){
			if(onBack != null) onBack.onBack(String.valueOf(x));
			SystemClock.sleep(1000);
			x++;
		}
		if(onBack != null) onBack.onEnd();
		return null;
	}
	
	public void setOnBackListener(OnBack onBack){
		this.onBack= onBack;
	}
}
