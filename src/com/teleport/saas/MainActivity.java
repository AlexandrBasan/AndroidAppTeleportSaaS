package com.teleport.saas;
//rails - отключено для Teleport SaaS Rails 3.0

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//переменная главного Вэб Вью
	private WebView mWebView;
	// переменные которая отвечает за работу приложения только через wifi
//	public static final String WIFI = "Wi-Fi";
//	public static final String ANY = "Any";
    // Whether there is a Wi-Fi connection.
//    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
//    private static boolean mobileConnected = false;
    // Whether the display should be refreshed.
//    public static boolean refreshDisplay = true;

    // The user's current network preference setting.
//    public static String sPref = null;
    // The BroadcastReceiver that tracks network connectivity changes.
//    private NetworkReceiver receiver = new NetworkReceiver();
    // определение переменной позиционирования
//    Location location; // location
//    double latitude; // latitude
//    double longitude; // longitude  
	// GPSTracker class
//rails	GPSTracker gps;
//rails	Teleport_user_profile_activity UP;
//rails	ReadData RD;
	Widget WI;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        
        // Register BroadcastReceiver to track connection changes.
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        receiver = new NetworkReceiver();
//        this.registerReceiver(receiver, filter);
        
        mWebView = (WebView) findViewById(R.id.webview);  	  
        mWebView.setWebViewClient(new WebViewClient()); 
          
        
      
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Сохраняем пароль
        mWebView.getSettings().setSavePassword(true);
        // Сохраняем данные форм для ввода пароля
        mWebView.getSettings().setSaveFormData(true);
        // устанавливаем Zoom control
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
     // разрешаем cookie
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
    	// указываем страницу загрузки
       // mWebView.loadUrl("http://saas.teleport-ds.com/");  //work server
        mWebView.loadUrl("http://ec2-54-245-114-39.us-west-2.compute.amazonaws.com:3000/signin"); //test server
       
      
        
     // обработка прогресс бара загрузки страницы
        final Activity activity = this;
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
              activity.setTitle(R.string.loading);
              activity.setProgress(progress * 100);

              if(progress == 100)
            	  activity.setTitle(R.string.app_name);
            }
          });
        
        mWebView.setWebViewClient(new WebViewClient() {
        	// обработка ошибок
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
             Toast.makeText(getApplicationContext(), "Error: " + description+ " " + failingUrl, Toast.LENGTH_LONG).show();
            }
            
         // набераем номер телефона при клике на него
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            	  if (url.startsWith("tel:")) { 
                      Intent intent = new Intent(Intent.ACTION_DIAL,
                              Uri.parse(url)); 
                      startActivity(intent); 
              }else if(url.startsWith("http:") || url.startsWith("https:")) {
                  view.loadUrl(url);
              }
              else if(url.startsWith("geo:0,0?q=")) {
              	Intent intent = new Intent(Intent.ACTION_VIEW,
                          Uri.parse(url)); 
                  startActivity(intent); 
             }
              return true;
          }
            
            
        });        
                
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // обработка событий нажатия меню
 	@Override
 	public boolean onMenuItemSelected(int featureId, MenuItem item) {
 		switch (item.getItemId()) {
 		case R.id.menu_about: {
 			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
 			startActivity(browserIntent);
 			break;
 		}
 		case R.id.menu_update_page: {
 			mWebView.reload();
 			break;
 		}
//rails 		case R.id.menu_send_location_manual: {
 			
//rails 				 GPSdetermination();
 				 //new PostData().execute();
 				 // PostData task = new PostData(MainActivity.this);
 				 // task.execute();
 		//		 String UserInfo = gps.LoadUserInfoFromFilePostData();
 		//		 gps = new GPSTracker(MainActivity.this); //Test old
 		//		 RD = new ReadData (MainActivity.this); //Test
 		//		 String UserInfo = RD.readSavedDataLogin(); //Test
 		//		 String UserPass = RD.readSavedDataPass(); //Test
 		//		 Toast.makeText(getApplicationContext(), UserInfo, Toast.LENGTH_LONG).show(); //Test
 		//		 Toast.makeText(getApplicationContext(), UserPass, Toast.LENGTH_LONG).show(); //Test
//rails 		         new PostData(gps, RD, getBaseContext()).execute();
 				 
 				// postData(); - вызывает ошибку на реальном устройстве
//rails 		         Toast.makeText(getApplicationContext(), R.string.location_send_manuall_toast, Toast.LENGTH_LONG).show();
//rails 			break;
//rails 		}
//rails 		case R.id.menu_settings: {
//rails 			 Intent settingsActivity = new Intent(getBaseContext(),
//rails 	                    Preferences.class);
//rails 	            startActivity(settingsActivity); 
//rails 			break;
//rails 		}
//rails 		case R.id.menu_wallets: {
//rails 			mWebView.loadUrl("http://saas.teleport-ds.com/user/funds/deposit");
//rails			break;
//rails		}
//rails 		case R.id.menu_user_profile: {
//rails 			Intent launchNewIntent = new Intent(MainActivity.this,Teleport_user_profile_activity.class);
//rails 			startActivityForResult(launchNewIntent, 0);
//rails 			break;
//rails 		}
//rails 		case R.id.enable_disable_gps: {
//rails 			startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//rails 		break;
//rails 	}
 		case R.id.menu_exit: {
 			onDestroy();  
 			break;
 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}   
   
 	
 	
 	
    
	public void onPageStarted (WebView view, String url, Bitmap favicon) {
        // Отключаем загрузку картинок в начале открытия страницы
        mWebView.getSettings().setLoadsImagesAutomatically(false);
      }
      
      public void onPageFinished (WebView view, String url) {
    	// Включаем загрузку картинок в конце открытия страницы
    	  mWebView.getSettings().setLoadsImagesAutomatically(true);
      }   
      
    
    
    //обработка события нажатия кнопки назад 31.05 Если первый экран выбивало ошибку
  	@Override
  	public boolean onKeyDown(int keyCode, KeyEvent event) {
  		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
  			mWebView.goBack();
  			return true;
  		}
  		return super.onKeyDown(keyCode, event);
  	}
  	
  	
      
      
      // Refreshes the display if the network connection and the
      // pref settings allow it.
//      @Override
//      public void onStart() {
//          super.onStart();

          // Gets the user's network preference settings
//          SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

          // Retrieves a string value for the preferences. The second parameter
          // is the default value to use if a preference value is not found.
//          sPref = sharedPrefs.getString("listPref", "Wi-Fi");

//          updateConnectedFlags();

          // Only loads the page if refreshDisplay is true. Otherwise, keeps previous
          // display. For example, if the user has set "Wi-Fi only" in prefs and the
          // device loses its Wi-Fi connection midway through the user using the app,
          // you don't want to refresh the display--this would force the display of
          // an error page instead of stackoverflow.com content.
//          if (refreshDisplay) {
//        	  if (((sPref.equals(ANY)) && (wifiConnected || mobileConnected))
//                      || ((sPref.equals(WIFI)) && (wifiConnected))) {
                  // AsyncTask subclass
                  
//              } else {
//                  showErrorPage();
//              }
//          }
//      }
      
      @Override
      public void onDestroy() {
         super.onDestroy();
//          if (receiver != null) {
//              this.unregisterReceiver(receiver);
//              gps.stopUsingGPS();
       //Останавливаем наш сервис определения координат при выходе из приложения кнопкой Exit в меню 10_06_2013
//rails        Intent intentstop = new Intent(this,GPSTracker.class);
//rails         stopService(intentstop);
//rails         Log.d("Service Stoped", stopService(intentstop) + "");

//     	Toast.makeText(getApplicationContext(), "service STOP", Toast.LENGTH_LONG).show();
  	        System.runFinalizersOnExit(true);
//  	        System.exit(0); // При перевороте экрана также вызываеться onDestroy и приложение закрываеться 
//          }
      }
      
      
      ///////////////////////////////////////////БЛОК ОТВЕЧАЮЩИЙ ЗА ПРИМЕНЕНИЕ НАСТРОЕК WIFI/////////////////////////////////////////////////////////////////////////////	
      // Checks the network connection and sets the wifiConnected and mobileConnected
      // variables accordingly.
//      private void updateConnectedFlags() {
//          ConnectivityManager connMgr =
//                  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

//          NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
//          if (activeInfo != null && activeInfo.isConnected()) {
//              wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
//             mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
//          } else {
//              wifiConnected = false;
//              mobileConnected = false;
//          }
//      }

      
      // Displays an error if the app is unable to load content.
      private void showErrorPage() {
          setContentView(R.layout.activity_main);

          // The specified network connection is not available. Displays error message.
          WebView myWebView = (WebView) findViewById(R.id.webview);
          myWebView.loadData(getResources().getString(R.string.connection_error),
                  "text/html", null);
      }
    
    
      /**
      *
      * This BroadcastReceiver intercepts the android.net.ConnectivityManager.CONNECTIVITY_ACTION,
      * which indicates a connection change. It checks whether the type is TYPE_WIFI.
      * If it is, it checks whether Wi-Fi is connected and sets the wifiConnected flag in the
      * main activity accordingly.
      *
      */
//    public class NetworkReceiver extends BroadcastReceiver {

//        @Override
//         public void onReceive(Context context, Intent intent) {
//             ConnectivityManager connMgr =
//                     (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//             NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

             // Checks the user prefs and the network connection. Based on the result, decides
             // whether
             // to refresh the display or keep the current display.
             // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
//             if (WIFI.equals(sPref) && networkInfo != null
//                     && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                 // If device has its Wi-Fi connection, sets refreshDisplay
//                 // to true. This causes the display to be refreshed when the user
//                 // returns to the app.
//                 refreshDisplay = true;
//                 Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();
//
//                 // If the setting is ANY network and there is a network connection
//                 // (which by process of elimination would be mobile), sets refreshDisplay to true.
//             } else if (ANY.equals(sPref) && networkInfo != null) {
//                 refreshDisplay = true;

                 // Otherwise, the app can't download content--either because there is no network
                 // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
                 // is no Wi-Fi connection.
                 // Sets refreshDisplay to false.
//             } else {
//                 refreshDisplay = false;
//                 Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();
//             }
//         }
//     }
   
/////////////////////////////////////////////////////////////////////////////////блок определения местоположения/////////////////////
     //просто выводит информацию о местоположении в виде Toast
//rails     public void GPSdetermination() {		
    	 // create class object
//rails    	 gps = new GPSTracker(MainActivity.this);

    	 // check if GPS enabled		
//rails    	 if(gps.canGetLocation()){

//rails    		 double latitude = gps.getLatitude();
//rails    		 double longitude = gps.getLongitude();

    		 // \n is for new line
//rails    		 Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
//rails    	 }else{
    		 // can't get location
    		 // GPS or Network is not enabled
    		 // Ask user to enable GPS/network in settings
//rails    		 gps.showSettingsAlert();
//rails    	 }

//rails     }


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
//////////////////////		 // определяем номер телефона абонента (можно использовать для передачи телефона курьера в саас)
	 	TelephonyManager t = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
	 	String simnumber = t.getLine1Number();
	 	Log.d("SIM PHONE", simnumber + "");
	 	
		//10_06_2013
		//Останавливаем наш сервис определения координат
//rails		Intent intentstop = new Intent(this,GPSTracker.class);
//rails    	stopService(intentstop);
//rails    	Log.d("Service Stoped", stopService(intentstop) + "");
//    	Toast.makeText(getApplicationContext(), "service STOP", Toast.LENGTH_LONG).show(); //Test
		//Стартуем наш сервис определения координат
//rails    	Intent intentstart = new Intent(this,GPSTracker.class);
//rails		startService(intentstart);
//rails		Log.d("Service Started", startService(intentstart) + "");
//		Toast.makeText(getApplicationContext(), "service START", Toast.LENGTH_LONG).show(); //Test
		// Gets the user's network preference settings
//rails        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Здесь берутся из настроек Параметры отвечающие за позиционирования разрешено или нет
//rails    	Boolean GPSnotif = sharedPrefs.getBoolean("location_share_enordis", true);
		//проверяем наличие файла если его нет то координаты не отправляются 12_06_2013 //______This block work
//rails		String FILELOGIN = "TeleportSAASUser.txt";
//rails		File filelogin = getBaseContext().getFileStreamPath(FILELOGIN);
//rails		if (filelogin.exists()) {
    	//______This block work
		
//rails    	if (GPSnotif.equals(true)) {
//rails    		GPSdetermination();
//rails    		new PostData(gps, RD, getBaseContext()).execute();
//rails    		Toast.makeText(getApplicationContext(), R.string.location_send_toast, Toast.LENGTH_LONG).show();
//rails        }
//rails        else {
//rails        	Toast.makeText(getApplicationContext(), R.string.share_location_NO, Toast.LENGTH_LONG).show();
//rails        }
//rails		}
//rails		else {
//rails        	Toast.makeText(getApplicationContext(), R.string.check_user_profile_toast, Toast.LENGTH_LONG).show();
//rails        }
		
	} 


////////////////////////////////////////////////////////////////ON RESUME/////////////////////////////////////////////////////////////////

     
//rails	private boolean isMyServiceGPSTrackerRunning() {
//rails	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//rails	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//rails	        if (GPSTracker.class.getName().equals(service.service.getClassName())) {
//rails	            return true;
//rails	        }
//rails	    }
//rails	    return false;
//rails	}
     
     
     

    
}



