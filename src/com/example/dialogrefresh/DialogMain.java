package com.example.dialogrefresh;

import android.os.SystemClock;

/**
 * =================================================
 * 
 * 作者:卢卓
 * 
 * 版本:1.0
 * 
 * 创建日期:2015-12-30 下午9:23:17
 * 
 * 描述:ui线程方式回调
 * 
 * 修订历史:
 * 
 * 
 * =================================================
 **/
public class DialogMain {
	private int x = 0;
	private OnBackMain onBackMain;
	
	public void start(){
		if(onBackMain != null) onBackMain.onStart();
		while(x<10){
			if(onBackMain != null) onBackMain.onBack(String.valueOf(x));
			SystemClock.sleep(1000);
			x++;
		}
		if(onBackMain != null) onBackMain.onEnd();
	}
	
	public void setOnBackMainListener(OnBackMain onBackMain){
		this.onBackMain = onBackMain;
	}
	
	public interface OnBackMain{
		void onStart();
		void onBack(String backName);
		void onEnd();
	}
}
