package com.karlliu.combinephrase;

public class FixedWordBlock {
	protected float blockLeft;
	protected float blockTop;
	protected float blockRight;
	protected float blockBottom;
	
	public FixedWordBlock() {
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

}