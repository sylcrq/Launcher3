package com.android.launcher3.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyAppWidgetProvider extends AppWidgetProvider {

	public static final String TAG = "MyAppWidgetProvider";
	
	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
		
		Log.i(TAG, "onAppWidgetOptionsChanged: appWidgetId="+appWidgetId);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		
		Log.i(TAG, "onDeleted: appWidgetIds="+appWidgetIds.toString());
		//for(int i=0; i<appWidgetIds.length; i++)
		//{
		//	Log.d(TAG, ""+appWidgetIds[i]);
		//}
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		
		Log.i(TAG, "onDisabled");
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		
		Log.i(TAG, "onEnabled");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
		Log.i(TAG, "onReceive");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		Log.i(TAG, "onUpdate: appWidgetIds="+appWidgetIds.toString());
	}

}
