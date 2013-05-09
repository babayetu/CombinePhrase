package com.karlliu.combinephrase;

import java.math.*;
import java.util.*;

public class WordBlock {
	private String content;
	private int index;
	private float randomX;
	private float randomY;
	private float blockLeft;
	private float blockTop;
	private float blockRight;
	private float blockBottom;
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
	
	public void setBlockLeft(float aBlockLeft) {
		blockLeft = aBlockLeft;
	}
	public void setBlockTop(float aBlockTop) {
		blockTop = aBlockTop;
	}	
	public void setBlockRight(float aBlockRight) {
		blockRight = aBlockRight;
	}
	public void setBlockBottom(float aBlockBottom) {
		blockBottom = aBlockBottom;
	}
	
	public float getBlockLeft() {
		return blockLeft;
	}
	public float getBlockTop() {
		return blockTop;
	}	
	public float getBlockRight() {
		return blockRight;
	}
	public float getBlockBottom() {
		return blockBottom;
	}	
	
	public void setTouched(boolean aTouched) {
		touched = aTouched;
	}
	
	public boolean getTouched() {
		return touched;
	}	
}