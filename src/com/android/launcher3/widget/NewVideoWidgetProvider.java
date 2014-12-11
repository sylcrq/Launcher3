package com.android.launcher3.widget;

import com.android.launcher3.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NewVideoWidgetProvider extends AppWidgetProvider {
	
	public static final String TAG = "NewVideoWidgetProvider";

	public static final String BUTTON_ACTION = "com.android.launcher3.widget.BUTTON_ACTION";
	public static final String COLLECTION_VIEW_ACTION = "com.android.launcher3.widget.COLLECTION_VIEW_ACTION";
	public static final String COLLECTION_VIEW_EXTRA = "com.android.launcher3.widget.COLLECTION_VIEW_EXTRA";
	
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
		
		if(action.equals(BUTTON_ACTION))
		{
			// 显示下一组图片
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_video_widget);
			rv.showNext(R.id.new_video_flipper);
			
			AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, rv);
		}
		else if(action.equals(COLLECTION_VIEW_ACTION))
		{
			// 提示当前被点击的view的下标
			int viewIndex = intent.getIntExtra(COLLECTION_VIEW_EXTRA, 0) + 1;
			Toast.makeText(context, "View "+viewIndex+" is Clicked", Toast.LENGTH_SHORT).show();
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
			// 唯一标识当前intent(存在多个同一widget的情况下)
			intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
			rv.setRemoteAdapter(R.id.new_video_flipper, intent);
			
			// 设置button
			Intent btIntent = new Intent(context, NewVideoWidgetProvider.class);
			btIntent.setAction(BUTTON_ACTION);
			btIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			// 唯一标识当前intent(存在多个同一widget的情况下)
			btIntent.setData(Uri.parse(btIntent.toUri(Intent.URI_INTENT_SCHEME)));
			PendingIntent btPendingIntent = PendingIntent.getBroadcast(context, 0, btIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.new_video_button, btPendingIntent);
			
			// 设置intent模板
			Intent flipperIntent = new Intent(context, NewVideoWidgetProvider.class);
			flipperIntent.setAction(COLLECTION_VIEW_ACTION);
			flipperIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			// 唯一标识当前intent(存在多个同一widget的情况下)
			flipperIntent.setData(Uri.parse(flipperIntent.toUri(Intent.URI_INTENT_SCHEME)));
			PendingIntent flipperPendingIntent = PendingIntent.getBroadcast(context, 0, flipperIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setPendingIntentTemplate(R.id.new_video_flipper, flipperPendingIntent);
			
			appWidgetManager.updateAppWidget(appWidgetId, rv);
		}
	}

}
