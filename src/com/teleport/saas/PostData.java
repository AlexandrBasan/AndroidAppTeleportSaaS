package com.teleport.saas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;

import android.os.AsyncTask;
import android.text.format.Time;
import android.util.Log;

class PostData extends AsyncTask<Void, Void, Void> {
	// GPSTracker class
		GPSTracker gps;
		Context rdContext;
	//определяем переменную главного активити
		MainActivity ma;
		Teleport_user_profile_activity UP;
		ReadData RD;
		
		
		public PostData (GPSTracker gps, ReadData RD, Context c) {
		    this.gps = gps;
		    this.RD = RD;
		    rdContext = c;
		}

	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			// do stuff before posting data
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			postData();
			return null;
		}
	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			// do stuff after posting data
			super.onPostExecute(result);
		}

		public void postData() {
			// TODO Auto-generated method stub
			// Create a new HttpClient and Post Header
			//переводим значение double в стринг
			Log.d("Start AsyncTask PostData", "Started");
			double latitudep = gps.getLatitude();
			Log.d("LOCATION_IN_PostData_Latitude", gps.getLatitude() + "");
			double longitudep = gps.getLongitude();
			Log.d("LOCATION_IN_PostData_Longitude", gps.getLongitude() + "");
			double totalLatitude = latitudep;
			double totalLongitude = longitudep;
			String stotalLatitude = String.valueOf(totalLatitude);
			String stotalLongitude = String.valueOf(totalLongitude);
			// временная переменная для определения времени устройства
			Time nowTime = new Time();
			nowTime.setToNow();
			String snowTime = String.valueOf(nowTime);
			//берем информацию о юзере
			RD = new ReadData(rdContext);
			String UserInfo = RD.readSavedDataLogin();
			String UserPass = RD.readSavedDataPass();
			String UserCourierID = RD.readSavedDataCourierID();
			//посылка данных на сервер
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://saas.teleport-ds.com/android");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

			nameValuePairs.add(new BasicNameValuePair("latitude", stotalLatitude));
			nameValuePairs.add(new BasicNameValuePair("longitude", stotalLongitude));
			nameValuePairs.add(new BasicNameValuePair("Android_device_time", snowTime));
			nameValuePairs.add(new BasicNameValuePair("user_info", UserInfo));
			nameValuePairs.add(new BasicNameValuePair("user_pass", UserPass));
			nameValuePairs.add(new BasicNameValuePair("user_courierID", UserCourierID));
			nameValuePairs.add(new BasicNameValuePair("separator", "______________________________________"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			httpclient.execute(httppost);

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (NullPointerException e) {
            e.printStackTrace();
        } 

}



}   