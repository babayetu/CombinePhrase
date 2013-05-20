package com.karlliu.combinephrase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhraseInputActivity extends Activity implements OnClickListener {
	private EditText editArea;
	private TextView tv;
	private String uDBPath, uDBFile;
    private ImageButton imgBtn;
    private Dialog dialog;
    private Button btn_add, btn_back;
	private InputMethodManager imm;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

		setContentView(R.layout.input_phrase);
		
    	tv = (TextView) findViewById(R.id.input_phrase_tv);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);
		
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);		
		
		editArea = (EditText) findViewById(R.id.input_phrase);
		
	    imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		
		imgBtn = (ImageButton) findViewById(R.id.imageBtn);
		imgBtn.setOnTouchListener(new OnTouchListener() {
	    	@Override
	    	public boolean onTouch (View v, MotionEvent event) {
	    		if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    			dialog = new Keypad(PhraseInputActivity.this, imm, editArea);
	    			dialog.show();
	    		}
	    		return false;
	    	}        	
	    });
		
		uDBPath = getResources().getString(R.string.userDBPath);
		uDBFile = getResources().getString(R.string.userDBFile);
		
		ActivityManageApplication.getInstance().addActivity(this);    //register activity, in the end application can finish them all
    }
    
    
    @Override
	public void onClick(View v) {
		if (v == btn_add) {
			saveToUserDB(editArea.getText().toString());
		} else if (v == btn_back) {
			//switch to main interface
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			startActivityForResult(intent,0);			
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

}