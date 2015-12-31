package com.example.dialogrefresh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.dialogrefresh.DialogAsync.OnBackAsync;
import com.example.dialogrefresh.DialogMain.OnBackMain;
import com.example.dialogrefresh.DialogThread.OnBackThread;

/**
 * =================================================
 * 
 * 作者:卢卓
 * 
 * 版本:1.0
 * 
 * 创建日期:2015-12-30 下午9:08:16
 * 
 * 描述:关于AlertDialog刷新问题的研究
 * 
 * 修订历史:
 * 
 * 
 * =================================================
 **/
public class MainActivity extends Activity implements OnClickListener {
	private static final int Start = 0x0001;
	private static final int Back = 0x0002;
	private static final int End = 0x0003;
	private View dialog_view;
	private TextView textview;
	private AlertDialog dialog;
	private Button thread;
	private Button async;
	private String TAG = "MainActivity";
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Start:
				dialog.show();// 显示
				Log.i(TAG, "start");
				break;
			case Back:
				textview.setText((String)msg.obj);
				Log.i(TAG, (String)msg.obj);
				break;
			case End:
				dialog.dismiss();
				Log.i(TAG, "end");
				break;
			}
		}
	};
	private Button main;
	private Button main1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	private void initView() {
		thread = (Button) findViewById(R.id.thread);
		async = (Button) findViewById(R.id.async);
		main = (Button) findViewById(R.id.main);
		main1 = (Button) findViewById(R.id.main1);
		
		thread.setOnClickListener(this);
		async.setOnClickListener(this);
		main.setOnClickListener(this);
		main1.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(thread == v) threadStart(); //1.子线程回调方式
		if(async == v) asyncStart(); //2.异步回调方式
		if(main == v) mainStart(); //3.主线程回调方式
		if(main1 == v) mainStart1(); //4.主线程直接回调方式
	}
	
	private void initData() {
		dialog_view = View.inflate(this, R.layout.dialog, null);
		textview = (TextView) dialog_view.findViewById(R.id.textview);

		// AlertDialog
		AlertDialog.Builder builder = new Builder(this);
		builder.setCancelable(false);
		dialog = builder.create();
		dialog.setView(dialog_view, 0, 0, 0, 0);
	}
	
	/**
	 * 1.子线程方式
	 */
	private void threadStart() {
		DialogThread dialogThread = new DialogThread();
		dialogThread.setOnBackThreadListener(new OnBackThread() {
			@Override
			public void onStart() {
				Message msg = Message.obtain();
				msg.what = Start;
				handler.sendEmptyMessage(Start);
			}
			@Override
			public void onBack(String backName) {
				Message msg = Message.obtain();
				msg.what = Back;
				msg.obj = backName;
				handler.sendMessage(msg);
			}
			@Override
			public void onEnd() {
				Message msg = Message.obtain();
				msg.what = End;
				handler.sendEmptyMessage(End);
			}
		});
		dialogThread.start();
	}
	
	/**
	 * 2.异步方式
	 */
	private void asyncStart() {
		DialogAsync dialogAsync = new DialogAsync();
		dialogAsync.setOnBackAsyncListener(new OnBackAsync() {
			@Override
			public void onStart() {
				Message msg = Message.obtain();
				msg.what = Start;
				handler.sendEmptyMessage(Start);
			}
			@Override
			public void onBack(String backName) {
				Message msg = Message.obtain();
				msg.what = Back;
				msg.obj = backName;
				handler.sendMessage(msg);
			}
			@Override
			public void onEnd() {
				Message msg = Message.obtain();
				msg.what = End;
				handler.sendEmptyMessage(End);
			}
		});
		dialogAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this);
	}
	
	/**
	 * 3.主线程回调方式
	 */
	private void mainStart() {
		DialogMain dialogMain = new DialogMain();
		dialogMain.setOnBackMainListener(new OnBackMain() {
			@Override
			public void onStart() {
				Message msg = Message.obtain();
				msg.what = Start;
				handler.sendEmptyMessage(Start);
			}
			@Override
			public void onBack(String backName) {
				Message msg = Message.obtain();
				msg.what = Back;
				msg.obj = backName;
				handler.sendMessage(msg);
			}
			@Override
			public void onEnd() {
				Message msg = Message.obtain();
				msg.what = End;
				handler.sendEmptyMessage(End);
			}
		});
		dialogMain.start();
	}
	
	/**
	 * 4.主线程回调方式(非Handler)
	 */
	private void mainStart1() {
		DialogMain dialogMain = new DialogMain();
		dialogMain.setOnBackMainListener(new OnBackMain() {
			@Override
			public void onStart() {
				dialog.show();// 显示
				Log.i(TAG, "start");
			}
			@Override
			public void onBack(String backName) {
				textview.setText(backName);
				Log.i(TAG, backName);
			}
			@Override
			public void onEnd() {
				dialog.dismiss();
				Log.i(TAG, "end");
			}
		});
		dialogMain.start();
	}
}
