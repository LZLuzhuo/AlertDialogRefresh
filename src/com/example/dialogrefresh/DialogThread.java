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
	private OnBack onBack;
	
	public void start(){
		new Thread(){
			public void run() {
				if(onBack != null) onBack.onStart();
				while(x<10){
					if(onBack != null) onBack.onBack(String.valueOf(x));
					SystemClock.sleep(1000);
					x++;
				}
				if(onBack != null) onBack.onEnd();
			};
		}.start();
	}
	
	public void setOnBackListener(OnBack onBack){
		this.onBack= onBack;
	}
}
