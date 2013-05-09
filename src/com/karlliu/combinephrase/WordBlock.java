package com.karlliu.combinephrase;

import java.util.Random;

public class WordBlock extends FixedWordBlock {
	private String content;
	private int index;
	private float randomX;
	private float randomY;
	private boolean touched;
	
	public WordBlock() {
		content = null;
		index = -1;
		Randomlize();
	}
	
	public WordBlock(String aString, int aIndex) {
		content = aString;
		index = aIndex;
		Randomlize();
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
	
	public float getRamdonX() {
		return randomX;
	}

	public float getRamdonY() {
		return randomY;
	}
	
	public void Randomlize() {
		Random r = new Random();
		randomX = r.nextFloat();	//0.0 ~ 1.0
		randomY = r.nextFloat();
	}
	
	public void setTouched(boolean aTouched) {
		touched = aTouched;
	}
	
	public boolean getTouched() {
		return touched;
	}
}