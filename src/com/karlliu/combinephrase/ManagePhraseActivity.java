package com.karlliu.combinephrase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagePhraseActivity extends Activity implements OnClickListener{
	private Button btn_submit, btn_back_to_main, btn_clear;
	private Button btn_a1, btn_a2,btn_c1,btn_e1,btn_e2,btn_e3,btn_e4,btn_i1,btn_i2,btn_o1;
	private Button btn_o2,btn_u1,btn_u2,btn_A1,btn_A2,btn_C1,btn_E1,btn_E2,btn_E3,btn_I1;
	private EditText editArea;
	private TextView tv;
	private String uDBPath, uDBFile;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
//    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.add_phrase);
		
    	tv = (TextView) findViewById(R.id.add_phrase_tv);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);		

		
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
		
		btn_back_to_main = (Button) findViewById(R.id.btn_back_to_main);
		btn_back_to_main.setOnClickListener(this);

		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(this);
		
		editArea = (EditText) findViewById(R.id.add_phrase);
		
		uDBPath = getResources().getString(R.string.userDBPath);
		uDBFile = getResources().getString(R.string.userDBFile);
		
		btn_a1 = (Button) findViewById(R.id.btn_a1);
		btn_a1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.a1));
				setCursorPos(editArea);
			}
		});
		
		btn_a2 = (Button) findViewById(R.id.btn_a2);
		btn_a2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.a2));
				setCursorPos(editArea);
			}
		});		

		btn_c1 = (Button) findViewById(R.id.btn_c1);
		btn_c1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.c1));
				setCursorPos(editArea);
			}
		});
		
		btn_e1 = (Button) findViewById(R.id.btn_e1);
		btn_e1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.e1));
				setCursorPos(editArea);
			}
		});
		
		btn_e2 = (Button) findViewById(R.id.btn_e2);
		btn_e2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.e2));
				setCursorPos(editArea);
			}
		});
		
		btn_e3 = (Button) findViewById(R.id.btn_e3);
		btn_e3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.e3));
				setCursorPos(editArea);
			}
		});

		btn_e4 = (Button) findViewById(R.id.btn_e4);
		btn_e4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.e4));
				setCursorPos(editArea);
			}
		});
		
		btn_i1 = (Button) findViewById(R.id.btn_i1);
		btn_i1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.i1));
				setCursorPos(editArea);
			}
		});
		
		btn_i2 = (Button) findViewById(R.id.btn_i2);
		btn_i2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.i2));
				setCursorPos(editArea);
			}
		});
		
		btn_o1 = (Button) findViewById(R.id.btn_o1);
		btn_o1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.o1));
				setCursorPos(editArea);
			}
		});
		
		btn_o2 = (Button) findViewById(R.id.btn_o2);
		btn_o2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.o2));
				setCursorPos(editArea);
			}
		});
		
		btn_u1 = (Button) findViewById(R.id.btn_u1);
		btn_u1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.u1));
				setCursorPos(editArea);
			}
		});		

		btn_u2 = (Button) findViewById(R.id.btn_u2);
		btn_u2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.u2));
				setCursorPos(editArea);
			}
		});
		
		btn_A1 = (Button) findViewById(R.id.btn_A1);
		btn_A1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.A1));
				setCursorPos(editArea);
			}
		});
		
		btn_A2 = (Button) findViewById(R.id.btn_A2);
		btn_A2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.A2));
				setCursorPos(editArea);
			}
		});
		
		btn_C1 = (Button) findViewById(R.id.btn_C1);
		btn_C1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.C1));
				setCursorPos(editArea);
			}
		});

		btn_E1 = (Button) findViewById(R.id.btn_E1);
		btn_E1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.E1));
				setCursorPos(editArea);
			}
		});
		
		btn_E2 = (Button) findViewById(R.id.btn_E2);
		btn_E2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.E2));
				setCursorPos(editArea);
			}
		});
		
		btn_E3 = (Button) findViewById(R.id.btn_E3);
		btn_E3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.E3));
				setCursorPos(editArea);
			}
		});
		
		btn_I1 = (Button) findViewById(R.id.btn_I1);
		btn_I1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText(editArea.getText() + getResources().getString(R.string.I1));
				setCursorPos(editArea);
			}
		});		
		
		ActivityManageApplication.getInstance().addActivity(this);
    }
    
    @Override
	public void onClick(View v) {
		if (v == btn_submit) {
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
    
//    private void showUserDB() {
//    	File directoryPath = new File(uDBPath);
//		File filePath = new File(uDBPath + "/" + uDBFile);
//		FileReader fr = null;
//		BufferedReader reader = null;
//		String tmpStr = null;
//		
//		try {
//    		if (!directoryPath.exists()) {
//    			directoryPath.mkdirs();
//    			return;
//    		}
//    		if (!filePath.exists()) {
//    			return;
//    		}		
//    		
//    		//clear show phrase contents
//    		show_phrase_tv.setText("");
//    		
//    		fr = new FileReader(filePath);
//    		reader = new BufferedReader(fr);
//			
//			while ((tmpStr = reader.readLine()) != null ) {
//				show_phrase_tv.setText(show_phrase_tv.getText() + "\n" + tmpStr);
//			}
//      		
//		} catch (Exception e) {
//			Log.e("showUserDB", e.toString());
//		} finally {
//			try {
//				if (fr!=null)
//					fr.close();
//				if (reader!=null)
//					reader.close();				
//			} catch (Exception e2) {
//				Log.e("showUserDB", e2.toString());
//			}
//		}
//    }
    
    private void clearUserDB() {
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	
    	if (Environment.getExternalStorageState() != null && !Environment.getExternalStorageState().equals("removed")) {
    		File directoryPath = new File(uDBPath);
    		File filePath = new File(uDBPath + "/" + uDBFile);
    		
    		try {
        		if (!directoryPath.exists()) {
        			return;
        		}
        		if (!filePath.exists()) {
        			return;
        		}			
        		
        		fw = new FileWriter(filePath, false);  //write file from beginning
        		bw = new BufferedWriter(fw);
        		bw.write("");						//write blank
        		bw.flush();

    			Toast.makeText(this, getResources().getString(R.string.userDBClear), Toast.LENGTH_SHORT).show();

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
    
    private void setCursorPos(EditText et) {
		CharSequence text = et.getText();
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable)text;
			Selection.setSelection(spanText, text.length());
		}
    }
}