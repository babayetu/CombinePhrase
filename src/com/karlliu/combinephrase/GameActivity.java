package com.karlliu.combinephrase;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	private String phraseDB = null;
	private DictionaryRead originalDB = null;
	private DictionaryRead userDB = null;
	private FileInputStream fis;
	private String[] phrases = null;
	private int dbHighLimit = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
    	
    	super.onCreate(savedInstanceState);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		phraseDB = this.getIntent().getStringExtra("phraseDB");

		try {
			// if we read the default DB 
			if (phraseDB.equals("defaultDB.txt")) {
				originalDB = new DictionaryRead(getResources().getAssets().open("defaultDB.txt"));
				phrases = originalDB.getPhrases();
				dbHighLimit = originalDB.getDBHighLimit();
				
			} else {

	    		File filePath = new File(phraseDB);	    		
	    		try {
	        		if (!filePath.exists()) {
	        			return;
	        		}
	        		
	        		fis = new FileInputStream(filePath);
	        		userDB = new DictionaryRead(fis);
	        		phrases = userDB.getPhrases();
	        		Log.v("phrase", phrases[0]);
	        		dbHighLimit = userDB.getDBHighLimit();
	        		
				} catch (Exception e) {
					Log.e("phrase", e.toString());
				} finally {
					try {
						if (fis!=null)
							fis.close();
					} catch (Exception e2) {
						Log.e("phrase", e2.toString());
					}
				}
			}			
			
		} catch (Exception e3) {
			Log.e("phrase", e3.toString());
		} finally {
			
		}		
		
		setContentView(new MySurfaceView(this));
    }
    
    public String[] getPhrases() {
    	return phrases;
    }
    
    public int getDBHighLimit() {
    	return dbHighLimit;
    }
}