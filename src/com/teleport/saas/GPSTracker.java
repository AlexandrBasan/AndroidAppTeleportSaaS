// http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
package com.teleport.saas;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


public class GPSTracker extends Service implements LocationListener {

	private final Context mContext;
	
	
	//определ€ем переменную главного активити
		MainActivity ma;
		
		GPSTracker gps;
		Teleport_user_profile_activity UP;
		ReadData RD;
		PostData PD;
		Context rdContext;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
//	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 0;; // 0 minute

	// Declaring a Location Manager
	protected LocationManager locationManager;

	public GPSTracker(Context context) {
		this.mContext = context;
		getLocation();
	}
	
	public static String UserLoginFile;
	public static String UserPassFile;
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ‘ункци€ дл€ определени€ местоположени€
	public Location getLocation() {
		// Ѕеретс€ значение пересылки координат по времени
//		SharedPreferences sharedPrefs =	mContext.getSharedPreferences("settings_udate_data_location_Interval_Values", Context.MODE_PRIVATE);
//    	long GPSnotifTime = sharedPrefs.getLong("settings_udate_data_location_Interval_Values", 300000);
		try {
			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Stop using GPS listener
	 * Calling this function will stop using GPS in your app
	 * */
	public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GPSTracker.this);
		}		
	}
	
	/**
	 * Function to get latitude
	 * */
	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
		}
		
		// return latitude
		return latitude;
	}
	
	/**
	 * Function to get longitude
	 * */
	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
		}
		
		// return longitude
		return longitude;
	}
	
	/**
	 * Function to check GPS/wifi enabled
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}
	
	/**
	 * Function to show settings alert dialog
	 * On pressing Settings button will lauch Settings Options
	 * */
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
   	 
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");
 
        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
 
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            	mContext.startActivity(intent);
            }
        });
 
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
	}
	
	
	
	//событи€ которые происход€т если позици€ помен€лась
	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
				
      //«десь берутс€ из настроек ѕараметры отвечающие за позиционировани€ разрешено или нет
		SharedPreferences sharedPrefs =	mContext.getSharedPreferences("location_share_enordis", Context.MODE_PRIVATE);
		Boolean GPSnotif = sharedPrefs.getBoolean("location_share_enordis", true);
//		String ttt1 = new Boolean(GPSnotif).toString(); //Test GPSnotif
		
   		if (GPSnotif.equals(true)) {
    		//ќтправка местоположени€ если позици€ изменилась 10_06_2013____This block work
   			// ѕочемуто перечылаютс€ значени€ 0.0____________________________________________________________________________________
    		GPSTracker gps = new GPSTracker(this); // работает
    		new PostData(gps, RD, mContext).execute();
//    		double CLDS = location.getLatitude();
//    		double CLDD = location.getLongitude();
//    		Log.d("CLDS", CLDS + "");
//	        Log.d("CLDD", CLDD + "");
    		//Test to LogCat
	        Log.d("LOCATION CHANGED_IN_GPSTracker_Latitude", location.getLatitude() + "");
	        Log.d("LOCATION CHANGED_IN_GPSTracker_Longitude", location.getLongitude() + "");
//    		Toast.makeText(mContext, ttt1, Toast.LENGTH_LONG).show(); //Test if (GPSnotif) - true
        } 
        else {
//        	Toast.makeText(mContext, "false", Toast.LENGTH_LONG).show(); //Test if (GPSnotif) - false
        }

			
	}

	
	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
