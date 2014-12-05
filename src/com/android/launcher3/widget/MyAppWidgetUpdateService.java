package com.android.launcher3.widget;

import com.android.launcher3.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class MyAppWidgetUpdateService extends Service {

	public static final String TAG = "MyAppWidgetUpdateService";
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(TAG, "onBind");
		
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.my_appwidget);
		
		Time time = new Time();
		time.setToNow();
		String time_str = String.format("%d:%d", time.hour, time.minute);
		String date_str = String.format("%d月%d日 星期", time.month + 1, time.monthDay);
		
		remoteViews.setTextViewText(R.id.widget_time, time_str);
		remoteViews.setTextViewText(R.id.widget_date, date_str);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
		manager.updateAppWidget(new ComponentName(getApplicationContext(), MyAppWidgetProvider.class), remoteViews);
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind");
		
		return super.onUnbind(intent);
	}

}
