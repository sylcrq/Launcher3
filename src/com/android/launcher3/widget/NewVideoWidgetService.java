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
		
		//private String[] mText = new String[] {
		//	"Pic 1", "Pic 2", "Pic 3",
		//	"Pic 4", "Pic 5", "Pic 6",
		//	"Pic 7", "Pic 8", "Pic 9"
		//};
		
		//private int[] mImage = new int[] {
		//	R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3,
		//	R.drawable.pic_4, R.drawable.pic_5, R.drawable.pic_6,
		//	R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9
		//};
		
		//private ArrayList<HashMap<String, Object>> mData;
		
		public NewVideoRemoteViewsFactory(Context context, Intent intent) {
			Log.i(TAG, "NewVideoRemoteViewsFactory");
			
			mContext = context;
			mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		@Override
		public int getCount() {
			Log.i(TAG, "getCount = 1");
			return 1;
			
			//Log.i(TAG, "getCount = " + mData.size());			
			//return mData.size();
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
			
			//HashMap<String, Object> map = mData.get(position);
			//rv.setImageViewResource(R.id.image_item, (Integer)map.get(IMAGE_ITEM));
			//rv.setTextViewText(R.id.text_item, (String)map.get(TEXT_ITEM));
			rv.setImageViewResource(R.id.new_video_item_image_1, R.drawable.pic_1);
			rv.setTextViewText(R.id.new_video_item_text_1, "Pic 1");
			rv.setImageViewResource(R.id.new_video_item_image_2, R.drawable.pic_2);
			rv.setTextViewText(R.id.new_video_item_text_2, "Pic 2");
			rv.setImageViewResource(R.id.new_video_item_image_3, R.drawable.pic_3);
			rv.setTextViewText(R.id.new_video_item_text_3, "Pic 3");
			
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
