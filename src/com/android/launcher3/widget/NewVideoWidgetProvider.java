package com.android.launcher3.widget;

import com.android.launcher3.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class NewVideoWidgetProvider extends AppWidgetProvider {
	
	public static final String TAG = "NewVideoWidgetProvider";

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
		
		Log.i(TAG, "onDeleted");
		for(int appWidgetId : appWidgetIds)
		{
			Log.d(TAG, "onDeleted: appWidgetId="+appWidgetId);
		}
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
		
		Log.i(TAG, "onUpdate");
		
		for(int appWidgetId : appWidgetIds)
		{
			Log.d(TAG, "onUpdate: appWidgetId="+appWidgetId);
			
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_video_widget);
			
			Intent intent = new Intent(context, NewVideoWidgetService.class);
			rv.setRemoteAdapter(R.id.new_video_flipper, intent);
						
			appWidgetManager.updateAppWidget(appWidgetId, rv);
		}
	}

}
