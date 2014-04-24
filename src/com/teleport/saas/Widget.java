package com.teleport.saas;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {
	GPSTracker gps;
	Teleport_user_profile_activity UP;
	ReadData RD;
	MainActivity ma;
	private static final String ACTION_CLICK = "ACTION_CLICK";
	
	@Override
	  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	      int[] appWidgetIds) {

	    // Get all ids
	    ComponentName thisWidget = new ComponentName(context,
	    		Widget.class);
	    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	    for (int widgetId : allWidgetIds) {
	    	
	    	AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    	RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);
	   //почемуто не меняется на красную_________________________________________________________________ 	
	        // Create some random data
		   	if (GPSTracker.class != null) { 
		    	/* get the handle on your widget*/
		   		Log.d("widget update", "GPSTracker Enable");
		   		Log.d("GREEN", "Green");
		    	/* replace the image */
		    	remoteViews.setImageViewResource(R.id.update_service_work, R.drawable.indicatorgreen24);
     		
		   	}else {
		    	/* replace the image */
		    	remoteViews.setImageViewResource(R.id.update_service_work, R.drawable.indicatorred24);
		   		Log.d("widget update", "GPSTracker Disable");
		   		Log.d("RED", "Red");
		   		
		    	}
		   	// Вызов MainActivity при клике на кнопку Т
		   	Intent intent = new Intent(context, MainActivity.class);
	        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
	        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
	        views.setOnClickPendingIntent(R.id.imageButton1, pendingIntent);
	        appWidgetManager.updateAppWidget(thisWidget, views);
	       
	    	
		   	manager.updateAppWidget(thisWidget, remoteViews);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	  

	 
	    
	   	
	   	
	   	
	  }
	
	
	
	
	
}
