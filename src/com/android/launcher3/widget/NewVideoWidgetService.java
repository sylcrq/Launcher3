package com.android.launcher3.widget;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.launcher3.R;
import com.android.launcher3.R.integer;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class NewVideoWidgetService extends RemoteViewsService {

	public static final String TAG = "NewVideoWidgetService";
	
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		Log.i(TAG, "onGetViewFactory");
		
		return new NewVideoRemoteViewsFactory(this, intent);
	}

	// RemoteViewsFactory
	private class NewVideoRemoteViewsFactory implements RemoteViewsFactory {

		private Context mContext;
		private int mAppWidgetId;
		
		//private static final String IMAGE_ITEM = "image_item";
		//private static final String TEXT_ITEM = "text_item";
		
		private String[] mText = new String[] {
			"Pic 1", "Pic 2", "Pic 3",
			"Pic 4", "Pic 5", "Pic 6",
			"Pic 7", "Pic 8", "Pic 9"
		};
		
		private int[] mImage = new int[] {
			R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3,
			R.drawable.pic_4, R.drawable.pic_5, R.drawable.pic_6,
			R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9
		};
		
		//private ArrayList<HashMap<String, Object>> mData;
		
		public NewVideoRemoteViewsFactory(Context context, Intent intent) {
			Log.i(TAG, "NewVideoRemoteViewsFactory: appWidgetId="+mAppWidgetId);
			
			mContext = context;
			mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		@Override
		public int getCount() {			
			Log.i(TAG, "getCount: TextSize="+mText.length+", ImageSize="+mImage.length);			
			return (mText.length / 3);
		}

		@Override
		public long getItemId(int position) {
			Log.i(TAG, "getItemId = "+position);
			
			return position;
		}

		@Override
		public RemoteViews getLoadingView() {
			Log.i(TAG, "getLoadingView");
			
			return null;
		}

		@Override
		public RemoteViews getViewAt(int position) {
			Log.i(TAG, "getViewAt = "+position);
			
			RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.new_video_widget_items);
			
			int start = position * 3;
			
			rv.setImageViewResource(R.id.new_video_item_image_1, mImage[start]);
			rv.setTextViewText(R.id.new_video_item_text_1, mText[start]);
			
			rv.setImageViewResource(R.id.new_video_item_image_2, mImage[start+1]);
			rv.setTextViewText(R.id.new_video_item_text_2, mText[start+1]);
			
			rv.setImageViewResource(R.id.new_video_item_image_3, mImage[start+2]);
			rv.setTextViewText(R.id.new_video_item_text_3, mText[start+2]);
			
			return rv;
		}

		@Override
		public int getViewTypeCount() {
			Log.i(TAG, "getViewTypeCount = 1");
			
			return 1;
		}

		@Override
		public boolean hasStableIds() {
			Log.i(TAG, "hasStableIds = true");
			
			return true;
		}

		@Override
		public void onCreate() {
			Log.i(TAG, "onCreate");
		
			// 填充数据
			//mData = new ArrayList<HashMap<String,Object>>();
			//
			//for(int i=0; i<mImage.length; i++)
			//{
			//	HashMap<String, Object> map = new HashMap<String, Object>();
			//	map.put(TEXT_ITEM, mText[i]);
			//	map.put(IMAGE_ITEM, mImage[i]);
			//	mData.add(map);
			//}
		}

		@Override
		public void onDataSetChanged() {
			Log.i(TAG, "onDataSetChanged");
			
		}

		@Override
		public void onDestroy() {
			Log.i(TAG, "onDestroy");
			
			//mData.clear();
		}
		
	}
}
