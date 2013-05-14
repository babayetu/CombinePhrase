package com.karlliu.combinephrase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_original, btn_custom,btn_userphrase;
	private TextView tv;
	
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
    }
    
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		
		if (v == btn_original) {
			intent.setClass(this, GameActivity.class);
			startActivityForResult(intent,0);
		} else if (v == btn_custom) {
			tv.setText("use customer db");
		} else if (v == btn_userphrase) {			
			intent.setClass(this, ManagePhraseActivity.class);
			startActivityForResult(intent,0);			
		}
	}    
}
