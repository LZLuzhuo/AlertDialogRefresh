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
	private OnBackAsync onBackAsync;

	@Override
	protected Void doInBackground(Context... params) {
		if(onBackAsync != null) onBackAsync.onStart();
		while(x<10){
			if(onBackAsync != null) onBackAsync.onBack(String.valueOf(x));
			SystemClock.sleep(1000);
			x++;
		}
		if(onBackAsync != null) onBackAsync.onEnd();
		return null;
	}
	
	public void setOnBackAsyncListener(OnBackAsync onBackAsync){
		this.onBackAsync= onBackAsync;
	}
	
	public interface OnBackAsync{
		void onStart();
		void onBack(String backName);
		void onEnd();
	}
}
