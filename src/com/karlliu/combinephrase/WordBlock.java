package com.karlliu.combinephrase;

import java.util.Random;

public class WordBlock extends FixedWordBlock {
	private String content;
	private int index;
	private boolean touched;
	private int color;
	
	public WordBlock() {
		content = null;
		index = -1;
	}
	
	public WordBlock(String aString, int aIndex) {
		content = aString;
		index = aIndex;
	}	
	
	public String getContent() {
		return content;
	}
	public void setContent(String aContent) {
		content = aContent;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int aIndex) {
		index = aIndex;
	}
	
	public void setTouched(boolean aTouched) {
		touched = aTouched;
	}
	
	public boolean getTouched() {
		return touched;
	}
	
	public void setColor(int aColor) {
		color = aColor;
	}
	
	public int getColor() {
		return color;
	}	
}