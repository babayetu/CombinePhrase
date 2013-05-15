package com.karlliu.combinephrase;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
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
	private Button btn_submit;
	private EditText editArea;
	private TextView tv;
	private String uDBPath, uDBFile;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.add_phrase);
		
    	tv = (TextView) findViewById(R.id.add_phrase_tv);		
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
		
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
		
		editArea = (EditText) findViewById(R.id.add_phrase);
		
		uDBPath = getResources().getString(R.string.userDBPath);
		uDBFile = getResources().getString(R.string.userDBFile);
    }
    
    @Override
	public void onClick(View v) {
		if (v == btn_submit) {
			//Log.v("add phrase", editArea.getText().toString());
			saveToUserDB(editArea.getText().toString());
		}
	}
    
    private void saveToUserDB(String content) {
    	FileOutputStream fos = null;
    	DataOutputStream dos = null;
    	
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
        		fos = new FileOutputStream(filePath);
        		dos = new DataOutputStream(fos);
        		dos.writeChars(content);
			} catch (Exception e) {
				Log.e("phrase", e.toString());
			} finally {
				try {
					if (fos!=null)
						fos.close();
					if (dos!=null)
						dos.close();
				} catch (Exception e2) {
					Log.e("phrase", e2.toString());
				}
			}

    	} else {
    		Toast.makeText(this, "SD card not ready" , Toast.LENGTH_SHORT).show();
    	}
    }
}