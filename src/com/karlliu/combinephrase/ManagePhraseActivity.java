package com.karlliu.combinephrase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagePhraseActivity extends Activity implements OnClickListener{
	private Button btn_submit, btn_back_to_main, btn_clear;
	private EditText editArea;
	private TextView tv, show_phrase_tv;
	private String uDBPath, uDBFile;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
//    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.add_phrase);
		
    	tv = (TextView) findViewById(R.id.add_phrase_tv);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
		
		show_phrase_tv = (TextView) findViewById(R.id.show_phrase_tv);
		show_phrase_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
		
		btn_back_to_main = (Button) findViewById(R.id.btn_back_to_main);
		btn_back_to_main.setOnClickListener(this);

		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(this);
		
		editArea = (EditText) findViewById(R.id.add_phrase);
		
		uDBPath = getResources().getString(R.string.userDBPath);
		uDBFile = getResources().getString(R.string.userDBFile);
		
		showUserDB();		
    }
    
    @Override
	public void onClick(View v) {
		if (v == btn_submit) {
			//Log.v("add phrase", editArea.getText().toString());
			saveToUserDB(editArea.getText().toString());
		} else if (v == btn_back_to_main) {
			//switch to main interface
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			startActivityForResult(intent,0);			
		} else if (v == btn_clear) {
			clearUserDB();
		}
	}
    
    private void saveToUserDB(String content) {
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	
    	if (Environment.getExternalStorageState() != null && !Environment.getExternalStorageState().equals("removed")) {
    		File directoryPath = new File(uDBPath);
    		File filePath = new File(uDBPath + "/" + uDBFile);
    		
    		try {
        		if (!directoryPath.exists()) {
        			directoryPath.mkdirs();
        		}
        		if (!filePath.exists()) {
        			filePath.createNewFile();
        		}			
        		fw = new FileWriter(filePath, true);  //append file
        		bw = new BufferedWriter(fw);
        		bw.write(content,0,content.length());   //write the whole string into userDb file
        		bw.newLine();
        		bw.flush();

    			editArea.setText("");   //clear content
    			Toast.makeText(this, "Phrase added successfully" , Toast.LENGTH_SHORT).show();
    			showUserDB();
			} catch (Exception e) {
				Log.e("saveToUserDB1", e.toString());
			} finally {
				try {
					if (fw!=null)
						fw.close();
					if (bw!=null)
						bw.close();
				} catch (Exception e2) {
					Log.e("saveToUserDB2", e2.toString());
				}
			}

    	} else {
    		Toast.makeText(this, "SD card not ready" , Toast.LENGTH_SHORT).show();
    	}
    }
    
    private void showUserDB() {
    	File directoryPath = new File(uDBPath);
		File filePath = new File(uDBPath + "/" + uDBFile);
		FileReader fr = null;
		BufferedReader reader = null;
		String tmpStr = null;
		
		try {
    		if (!directoryPath.exists()) {
    			directoryPath.mkdirs();
    			return;
    		}
    		if (!filePath.exists()) {
    			return;
    		}		
    		
    		//clear show phrase contents
    		show_phrase_tv.setText("");
    		
    		fr = new FileReader(filePath);
    		reader = new BufferedReader(fr);
			
			while ((tmpStr = reader.readLine()) != null ) {
				show_phrase_tv.setText(show_phrase_tv.getText() + "\n" + tmpStr);
			}
      		
		} catch (Exception e) {
			Log.e("showUserDB", e.toString());
		} finally {
			try {
				if (fr!=null)
					fr.close();
				if (reader!=null)
					reader.close();				
			} catch (Exception e2) {
				Log.e("showUserDB", e2.toString());
			}
		}
    }
    
    private void clearUserDB() {
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	
    	if (Environment.getExternalStorageState() != null && !Environment.getExternalStorageState().equals("removed")) {
    		File directoryPath = new File(uDBPath);
    		File filePath = new File(uDBPath + "/" + uDBFile);
    		
    		try {
        		if (!directoryPath.exists()) {
        			show_phrase_tv.setText("");
        			return;
        		}
        		if (!filePath.exists()) {
        			show_phrase_tv.setText("");
        			return;
        		}			
        		
        		fw = new FileWriter(filePath, false);  //write file from beginning
        		bw = new BufferedWriter(fw);
        		bw.write("");						//write blank
        		bw.flush();

    			Toast.makeText(this, getResources().getString(R.string.userDBClear), Toast.LENGTH_SHORT).show();
    			showUserDB();
			} catch (Exception e) {
				Log.e("clearUserDB", e.toString());
			} finally {
				try {
					if (fw!=null)
						fw.close();
					if (bw!=null)
						bw.close();
				} catch (Exception e2) {
					Log.e("clearUserDB", e2.toString());
				}
			}

    	} else {
    		Toast.makeText(this, getResources().getString(R.string.sdNotReady) , Toast.LENGTH_SHORT).show();
    	}    	
    }
}