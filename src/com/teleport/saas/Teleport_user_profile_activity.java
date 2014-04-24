package com.teleport.saas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;





import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Teleport_user_profile_activity extends Activity {
	
	private EditText saveTextlogin;
	private EditText saveTextPass;
	private EditText saveTextCourierID;
	private static String LOG_APP_TAG = "tag";
	EditText username,userpass,userid;
	DatabaseHandler db;
//	UserInfo ui;
	// Database Name
	public static final String DATABASE_NAME = "UserInfoManager";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teleport_user_profile_activity);
        
    	saveTextlogin = (EditText) findViewById(R.id.editTextlogin);
    	saveTextPass = (EditText) findViewById(R.id.editTextpassword);
    	saveTextCourierID = (EditText) findViewById(R.id.editTextCourierID);
    	
    }
    
//сохраняем логин юзера и его пароль для передачи координат
    public void buttonsavelogin_Click(View v){
    	//сохранение в файл
   	String FILENAME = "TeleportSAASUser.txt";
    String FILENAMEPASS = "TeleportSAASPass.txt";
    String FILENAMECourierID = "TeleportSAASCourierID.txt";
    	
    	FileOutputStream outputStream = null;
    	                try {
    	                    outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
    	                    outputStream.write(saveTextlogin.getText().toString().getBytes());
    	                    Toast.makeText(getApplicationContext(), R.string.user_saved, Toast.LENGTH_LONG).show();
    	                } catch(IOException e) {
    	                    Log.e(LOG_APP_TAG, e.getMessage());
    	                } finally {
    	                    if (outputStream != null) {
    	                        try {
    	                            outputStream.flush();
    	                            outputStream.close();
    	                        } catch (IOException e) {
    	                            Log.e(LOG_APP_TAG, e.getMessage());
    	                        }
    	                    }
    	                }
    	  FileOutputStream outputStreamPass = null;
    	                try {
    	                    outputStreamPass = openFileOutput(FILENAMEPASS, Context.MODE_PRIVATE);
    	                    outputStreamPass.write(saveTextPass.getText().toString().getBytes());
    	                   
    	                } catch(IOException e) {
    	                    Log.e(LOG_APP_TAG, e.getMessage());
    	                } finally {
    	                    if (outputStream != null) {
    	                        try {
    	                            outputStream.flush();
    	                            outputStream.close();
    	                        } catch (IOException e) {
    	                            Log.e(LOG_APP_TAG, e.getMessage());
    	                        }
    	                    }
    	                }
    	                
    	  FileOutputStream outputStreamCourierID = null;
    	                try {
    	                	outputStreamCourierID = openFileOutput(FILENAMECourierID, Context.MODE_PRIVATE);
    	                	outputStreamCourierID.write(saveTextCourierID.getText().toString().getBytes());
    	                   
    	                } catch(IOException e) {
    	                    Log.e(LOG_APP_TAG, e.getMessage());
    	                } finally {
    	                    if (outputStream != null) {
    	                        try {
    	                            outputStream.flush();
    	                            outputStream.close();
    	                        } catch (IOException e) {
    	                            Log.e(LOG_APP_TAG, e.getMessage());
    	                        }
    	                    }
    	                }
    	                
    	              //сохранение в бд
    	                DatabaseHandler dbOpenHelper = new DatabaseHandler(Teleport_user_profile_activity.this);
    	                SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
    	                ContentValues cv = new ContentValues();
    	                cv.put(dbOpenHelper.KEY_NAME,saveTextlogin.getText().toString());
    	                cv.put(dbOpenHelper.KEY_PASS,saveTextPass.getText().toString());
//					    cv.put(dbOpenHelper.KEY_ID,saveTextCourierID.getText().toString());
    	                db.insert(dbOpenHelper.TABLE_NAME,null,cv);
    	                db.close();
    	                saveTextlogin.setText("");
    	                saveTextPass.setText("");
    	                saveTextCourierID.setText("");
    }


    public void buttoncancellogin_Click(View v){
    	finish();
    }
    
    public void onClickRegister(View v){
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/user/register/"));
			startActivity(browserIntent);
    }
    
    public void buttonloadinfouser_Click(View v){
    	
//    	DatabaseHandler dbOpenHelper = new DatabaseHandler(Teleport_user_profile_activity.this);
//        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
     // делаем запрос всех данных из таблицы mytable, получаем Cursor 
//        Cursor c = db.query(DATABASE_NAME, null, null, null, null, null, null);
    
    
        LoadUserInfoFromFile();
        LoadUserInfopassFromFile();
        LoadUserInfocourierIDFromFile();
    } 
    
    public void LoadUserInfoFromFile() {	
 		String FILENAME = "TeleportSAASUser.txt";
 		FileInputStream inputStream = null;
 		EditText LoadTextlogin = (EditText)findViewById(R.id.editTextlogin);
 	    try {
 	        inputStream = openFileInput(FILENAME);
 	        byte[] reader = new byte[inputStream.available()];
 	        while (inputStream.read(reader) != -1) {}
 	       LoadTextlogin.setText(new String(reader));
 	      // UserLoginFile = new String(reader);
 	   //  Toast.makeText(getApplicationContext(), LoadTextLogin, Toast.LENGTH_LONG).show();
 	    } catch(IOException e) {
 	        Log.e(LOG_APP_TAG, e.getMessage());
 	    } finally {
 	        if (inputStream != null) {
 	            try {
 	                inputStream.close();
 	            } catch (IOException e) {
 	                Log.e(LOG_APP_TAG, e.getMessage());
 	            }
 	        }
 	    }
 	 }  
    
    public void LoadUserInfopassFromFile() {	
 		String FILENAME = "TeleportSAASPass.txt";
 		FileInputStream inputStream = null;
 		EditText LoadTextPass = (EditText)findViewById(R.id.editTextpassword);
 	    try {
 	        inputStream = openFileInput(FILENAME);
 	        byte[] reader = new byte[inputStream.available()];
 	        while (inputStream.read(reader) != -1) {}
 	       LoadTextPass.setText(new String(reader));
 	    // UserPassFile = new String(reader);
 	   //  Toast.makeText(getApplicationContext(), LoadTextLogin, Toast.LENGTH_LONG).show();
 	    } catch(IOException e) {
 	        Log.e(LOG_APP_TAG, e.getMessage());
 	    } finally {
 	        if (inputStream != null) {
 	            try {
 	                inputStream.close();
 	            } catch (IOException e) {
 	                Log.e(LOG_APP_TAG, e.getMessage());
 	            }
 	        }
 	    }
 	 }  
    
    public void LoadUserInfocourierIDFromFile() {	
 		String FILENAME = "TeleportSAASCourierID.txt";
 		FileInputStream inputStream = null;
 		EditText LoadTextCourierID = (EditText)findViewById(R.id.editTextCourierID);
 	    try {
 	        inputStream = openFileInput(FILENAME);
 	        byte[] reader = new byte[inputStream.available()];
 	        while (inputStream.read(reader) != -1) {}
 	       LoadTextCourierID.setText(new String(reader));
 	    // UserPassFile = new String(reader);
 	   //  Toast.makeText(getApplicationContext(), LoadTextLogin, Toast.LENGTH_LONG).show();
 	    } catch(IOException e) {
 	        Log.e(LOG_APP_TAG, e.getMessage());
 	    } finally {
 	        if (inputStream != null) {
 	            try {
 	                inputStream.close();
 	            } catch (IOException e) {
 	                Log.e(LOG_APP_TAG, e.getMessage());
 	            }
 	        }
 	    }
 	 }  
 
}
