package com.teleport.saas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.telephony.TelephonyManager;

public class ReadData {
	private final Context rdContext;
//	private final Context pContext;
	
	public ReadData(Context context) {
		this.rdContext = context;
//		this.pContext = context;
		readSavedDataLogin ( );
		readSavedDataPass ( );
		readSavedDataCourierID ( );
	}
	
    public String readSavedDataLogin ( ) {
        String datax = "" ;
        String FILENAME = "TeleportSAASUser.txt";
        if(datax != null){
        try {
        	FileInputStream fIn = rdContext.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax = datax + readString ;
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        }
        return datax ;
    }
    public String readSavedDataPass ( ) {
        String datax = "" ;
        String FILENAME = "TeleportSAASPass.txt";
        if(datax != null){
        try {
        	FileInputStream fIn = rdContext.openFileInput ( FILENAME ) ;
           InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax = datax + readString ;
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        }
        return datax ;
    }
    
    public String readSavedDataCourierID ( ) {
        String datax = "" ;
        String FILENAME = "TeleportSAASCourierID.txt";
        if(datax != null){
        try {
        	FileInputStream fIn = rdContext.openFileInput ( FILENAME ) ;
           InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax = datax + readString ;
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        }
        return datax ;
    }
    
  
    
}
