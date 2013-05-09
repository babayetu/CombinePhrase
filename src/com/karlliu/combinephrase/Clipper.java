package com.karlliu.combinephrase;

import android.graphics.Paint;
import android.graphics.Path;

public class Clipper extends Path {
	private float mLeft = 0.0f;
	private float mTop = 0.0f;
	private Path mPath;
	private float holeX;
	private float holeY;
	private float radius = 2.0f;
	
	private final float smallAngleWidth = 3.0f;	//small angle
	private final float halfClipperWidth = 6.0f;  //middle V shape point
	private final float thirdClipperHeight = 6.0f;  
	
	public Clipper() {
		clipperFactory();		
	}
	
	public Clipper(float aLeft, float aTop) {
		mLeft = aLeft;
		mTop = aTop;
		clipperFactory();
	}
	
	public Path getClipper() {
		return mPath;
	}
	
	public float getHoleX() {
		return holeX;
	}
	
	public float getHoleY() {
		return holeY;
	}
	
	public float getRadius() {
		return radius;
	}
	
	private void clipperFactory() {
		mPath = new Path();
		mPath.moveTo(mLeft, mTop); // start point of clipper
		mPath.lineTo(mLeft + smallAngleWidth, mTop);
		mPath.lineTo(mLeft + halfClipperWidth, mTop + thirdClipperHeight);		
		mPath.lineTo(mLeft + smallAngleWidth + halfClipperWidth, mTop);
		mPath.lineTo(mLeft + halfClipperWidth *2,mTop);
		mPath.lineTo(mLeft + halfClipperWidth *2, mTop + thirdClipperHeight * 3);
		mPath.lineTo(mLeft , mTop + thirdClipperHeight * 3);
		mPath.close();
		
		holeX = mLeft + halfClipperWidth;
		holeY = mTop + thirdClipperHeight * 2;
		//mPaint.setStyle(Paint.Style.FILL);  fill style
		// mPaint.setStyle(Paint.Style.STROKE);   hollow style
	}
}