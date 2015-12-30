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
 * 描述:线程方式回调
 * 
 * 修订历史:
 * 
 * 
 * =================================================
 **/
public class DialogThread {
	private int x = 0;
	private OnBackThread onBackThread;
	
	public void start(){
		new Thread(){
			public void run() {
				if(onBackThread != null) onBackThread.onStart();
				while(x<10){
					if(onBackThread != null) onBackThread.onBack(String.valueOf(x));
					SystemClock.sleep(1000);
					x++;
				}
				if(onBackThread != null) onBackThread.onEnd();
			};
		}.start();
	}
	
	public void setOnBackThreadListener(OnBackThread onBackThread){
		this.onBackThread = onBackThread;
	}
	
	public interface OnBackThread{
		void onStart();
		void onBack(String backName);
		void onEnd();
	}
}
