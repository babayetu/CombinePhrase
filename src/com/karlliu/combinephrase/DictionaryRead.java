package com.karlliu.combinephrase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class DictionaryRead {
	private String[] mPhraseStore;
	private final int storeSize = 50;
	private int dbNotFullHighLimit = 0;   //if database is not full < storeSize, this value mark the high limit
	
	public DictionaryRead(InputStream is) {
				
		BufferedReader reader = null;
		String tmpStr = null;
		int loop = 0;
		
		//initialization the string array
		mPhraseStore = new String[storeSize];
		for (int i=0; i<storeSize; i++)
		{
			mPhraseStore[i] = new String("");
		}
		
		try {
	
			reader = new BufferedReader(new InputStreamReader(is));
			
			while ((tmpStr = reader.readLine()) != null && loop <=storeSize ) {
				mPhraseStore[loop] = tmpStr;
				loop++;
			}
			
			dbNotFullHighLimit = loop;			
			
		} catch (Exception e) {
			Log.i("DictionaryRead", e.toString());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e2) {
				Log.i("DictionaryRead", e2.toString());
			}

		}
	}	
	
	public String getPhrase(int idx) {
		return mPhraseStore[idx];
	}

	public String[] getPhrases() {
		return mPhraseStore;
	}
	
	public int getDBHighLimit() {
		return (storeSize>dbNotFullHighLimit ? dbNotFullHighLimit : storeSize);
	}
}