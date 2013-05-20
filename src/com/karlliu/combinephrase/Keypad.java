package com.karlliu.combinephrase;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Keypad extends Dialog {
	private final Button letters[] = new Button[20]; // total 20 francais lettre
	private View keypad;
	private EditText editText;
	private InputMethodManager mImm;

	public Keypad(Context context, InputMethodManager  imm, EditText et) {
		super(context);
		editText = et;
		mImm = imm;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.keypad_title);
		setContentView(R.layout.keypad);
		findViews();
		setListeners();
	}

	private void findViews() {
		keypad = findViewById(R.id.keypad);
		letters[0] = (Button)findViewById(R.id.btn_a1);
		letters[1] = (Button)findViewById(R.id.btn_a2);
		letters[2] = (Button)findViewById(R.id.btn_c1);
		letters[3] = (Button)findViewById(R.id.btn_e1);
		letters[4] = (Button)findViewById(R.id.btn_e2);

		letters[5] = (Button)findViewById(R.id.btn_e3);
		letters[6] = (Button)findViewById(R.id.btn_e4);
		letters[7] = (Button)findViewById(R.id.btn_i1);
		letters[8] = (Button)findViewById(R.id.btn_i2);
		letters[9] = (Button)findViewById(R.id.btn_o1);

		letters[10] = (Button)findViewById(R.id.btn_o2);
		letters[11] = (Button)findViewById(R.id.btn_u1);
		letters[12] = (Button)findViewById(R.id.btn_u2);
		letters[13] = (Button)findViewById(R.id.btn_A1);
		letters[14] = (Button)findViewById(R.id.btn_A2);

		letters[15] = (Button)findViewById(R.id.btn_C1);
		letters[16] = (Button)findViewById(R.id.btn_E1);
		letters[17] = (Button)findViewById(R.id.btn_E2);
		letters[18] = (Button)findViewById(R.id.btn_E3);
		letters[19] = (Button)findViewById(R.id.btn_I1);
	}

	private void setListeners() {
		for (int i = 0; i < letters.length; i++) {
			final int t = i;
			letters[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					editText.setText(editText.getText() + letters[t].getText().toString());
					dismiss();
					setCursorPos(editText);	//set cursor at the end of line					
				}
			});
		}
		
		keypad.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			}
		});
	}	
    
    private void setCursorPos(EditText et) {
		CharSequence text = et.getText();
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable)text;
			Selection.setSelection(spanText, text.length());
			mImm.showSoftInput(et, 0);
		}
    }	
}