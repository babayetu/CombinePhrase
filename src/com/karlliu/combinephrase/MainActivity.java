package com.karlliu.combinephrase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_original, btn_custom,btn_userphrase;
	private TextView tv;
	private String uDBPath, uDBFile;
	private InputStream is = null;
	private DictionaryRead originalDB = null;
	private DictionaryRead userDB = null;
	private FileInputStream fis;
	private Random r;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.activity_main);
    	btn_original = (Button) findViewById(R.id.btn_original);
    	btn_custom = (Button) findViewById(R.id.btn_custom);
    	btn_userphrase = (Button) findViewById(R.id.btn_userphrase);
    	
    	tv = (TextView) findViewById(R.id.app_textview);
    	tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);    	
   	
    	btn_original.setOnClickListener(this);
    	btn_custom.setOnClickListener(this);
    	btn_userphrase.setOnClickListener(this);  
    	
		uDBPath = getResources().getString(R.string.userDBPath);
		uDBFile = getResources().getString(R.string.userDBFile);
		r = new Random();
		
		try {
			originalDB = new DictionaryRead(getResources().getAssets().open("defaultDB.txt"));
			
			File directoryPath = new File(uDBPath);
    		File filePath = new File(uDBPath + "/" + uDBFile);
    		
    		try {
        		if (!directoryPath.exists()) {
        			return;
        		}
        		if (!filePath.exists()) {
        			return;
        		}
        		
        		fis = new FileInputStream(filePath);
        		userDB = new DictionaryRead(fis);
        		
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
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}    	
    }
    
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		int index = 0;
		
		if (v == btn_original) {
			intent.setClass(this, GameActivity.class);
			index = r.nextInt(originalDB.getDBHighLimit());
			intent.putExtra("onephrase", originalDB.getPhrase(index));
			startActivityForResult(intent,0);
		} else if (v == btn_custom) {
			intent.setClass(this, GameActivity.class);
			index = r.nextInt(userDB.getDBHighLimit());
			intent.putExtra("onephrase", userDB.getPhrase(index));
			startActivityForResult(intent,0);
		} else if (v == btn_userphrase) {			
			intent.setClass(this, ManagePhraseActivity.class);
			startActivityForResult(intent,0);			
		}
	}    
}
