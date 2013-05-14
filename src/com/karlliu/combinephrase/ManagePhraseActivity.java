package com.karlliu.combinephrase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManagePhraseActivity extends Activity implements OnClickListener{
	private Button btn_submit;
	private EditText editArea;
	private TextView tv;
	
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
    }
    
    @Override
	public void onClick(View v) {
		if (v == btn_submit) {
			Log.v("add phrase", editArea.getText().toString());
		}
	}        
}