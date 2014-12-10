package com.android.launcher3.widget;

import com.android.launcher3.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class NewVideoWidgetProvider extends AppWidgetProvider {
	
	public static final String TAG = "NewVideoWidgetProvider";

	public static final String PREVIOUS_BUTTON = "com.android.launcher3.widget.NewVideoWidgetProvider.PREV_BUTTON";
	public static final String NEXT_BUTTON = "com.android.launcher3.widget.NewVideoWidgetProvider.NEXT_BUTTON";
	
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
		
		String action = intent.getAction();
		int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		Log.i(TAG, "onReceive: action="+action+", appWidgetId="+appWidgetId);
		
		if(action.equals(PREVIOUS_BUTTON))
		{
			// 显示前一页数据
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_video_widget);
			rv.showPrevious(R.id.new_video_flipper);
			
			AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, rv);
		}
		else if(action.equals(NEXT_BUTTON))
		{
			// 显示后一页数据
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_video_widget);
			rv.showNext(R.id.new_video_flipper);
			
			AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, rv);
		}
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
			
			// 设置Service和Adapter
			Intent intent = new Intent(context, NewVideoWidgetService.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setRemoteAdapter(R.id.new_video_flipper, intent);
			
			// 设置前一页button
			Intent prevIntent = new Intent(context, NewVideoWidgetProvider.class);
			prevIntent.setAction(PREVIOUS_BUTTON);
			prevIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			PendingIntent prevPendingIntent = PendingIntent.getBroadcast(context, 0, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.new_video_prev_button, prevPendingIntent);
			
			// 设置后一页button
			Intent nextIntent = new Intent(context, NewVideoWidgetProvider.class);
			nextIntent.setAction(NEXT_BUTTON);
			nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			PendingIntent nextPendingIntent = PendingIntent.getBroadcast(context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.new_video_next_button, nextPendingIntent);
			
			appWidgetManager.updateAppWidget(appWidgetId, rv);
		}
	}

}
