package com.karlliu.combinephrase;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Region.Op;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

/**
 * 
 * @author Himi
 * 
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paintDropArea;
	private Paint paintWordBlock;
	private Paint paintText;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private int screenW, screenH;
	// define collision region
	private Rect dropRect;
	private Region dropRegion;
	private final int putBoxHalfHeight = 200; // drop area half Height
	private WordBlock[] mWB;
	private Region[] mRegion;
	private boolean initialized = false;	

	//constant
	private final int randomBlockPlaceY = 600;
	private final int textPaintSize = 40;
	private final int charLengthX = 20;
	private final int charHeightY = 50;
	private final float textShiftX = 5.0f;
	private final float textShiftY = textPaintSize;
	private final float statusBarHeightY = 100.0f;

	/**
	 * SurfaceView constructor
	 */
	public MySurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		
		//drop area paint
		paintDropArea = new Paint();
		paintDropArea.setColor(Color.WHITE);
		paintDropArea.setAntiAlias(true);
		
		//paint word block
		paintWordBlock = new Paint();
		paintWordBlock.setColor(Color.GRAY);
		paintWordBlock.setAntiAlias(true);
		
		//paint the text
		paintText = new Paint();
		paintText.setColor(Color.BLUE);
		paintText.setAntiAlias(true);
		paintText.setTextSize(textPaintSize);
		
		setFocusable(true);
		mWB = new Phrase(1).getContent();
		mRegion = new Region[mWB.length];
	}

	/**
	 * When SurfaceView is created, this function will be called
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		drawDropRect();
		initializeRandomBlocks();
		flag = true;
		// initialize the thread
		th = new Thread(this);
		// start the thread
		th.start();
	}

	/**
	 * Game draw
	 */
	public void myDraw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.BLACK);
				// draw drop area white
				canvas.drawRect(dropRect, paintDropArea);
				for (int i = 0; i < mWB.length; i++) {
					paintWordBlock.setColor(selectColor(mWB[i]));
					canvas.drawRect(mWB[i].getBlockLeft(), mWB[i].getBlockTop(), mWB[i].getBlockRight(), mWB[i].getBlockBottom(), paintWordBlock);
					canvas.drawText(mWB[i].getContent(), mWB[i].getBlockLeft() + textShiftX, mWB[i].getBlockTop() + textShiftY, paintText);					
				}

				// draw initial word block

			}
		} catch (Exception e) {

		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}

	/**
	 * Monitor screen touch event
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// judge which word block is touched
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (int i=0; i<mRegion.length;i++) {
				if (mRegion[i].contains((int) event.getX(), (int) event.getY()))
				{
					mWB[i].setTouched(true);
					break;
				}
			}			
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			for (int i=0; i< mRegion.length;i++) {
				if (mWB[i].getTouched()) {
					mWB[i].setBlockLeft(event.getX());
					mWB[i].setBlockTop(event.getY());
					mWB[i].setBlockRight(event.getX() + mWB[i].getContent().length() * charLengthX);
					mWB[i].setBlockBottom(event.getY() + charHeightY);					
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			for (int i=0; i< mRegion.length;i++) {
				if (mWB[i].getTouched()) {
					mWB[i].setBlockLeft(event.getX());
					mWB[i].setBlockTop(event.getY());
					mWB[i].setBlockRight(event.getX() + mWB[i].getContent().length() * charLengthX);
					mWB[i].setBlockBottom(event.getY() + charHeightY);					
				}
			}
		}
		
		return true;
	}

	/**
	 * Monitor key down event
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Game logic
	 */
	private void logic() {
	}

	@Override
	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			try {
				if (end - start < 50) {
					Thread.sleep(50 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * When SurfaceView status is changed, this function will be called
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * When SurfaceView dies, this function will be called
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	public void drawDropRect() {
			dropRect = new Rect(0, 0, screenW, 300);
			dropRegion = new Region(dropRect);
	}

	private int selectColor(WordBlock wb) {
		// background and foreground
		return Color.GRAY;
	}

	// draw initialized word blocks
	public void initializeRandomBlocks() {
		// Y start from 600, X starts from 0
		if (initialized)
			return;
		for (int i = 0; i < mWB.length; i++) {
			mWB[i].setBlockLeft(mWB[i].getRamdonX() * screenW);
			mWB[i].setBlockTop(mWB[i].getRamdonY() * (screenH - randomBlockPlaceY) + randomBlockPlaceY - statusBarHeightY);
			mWB[i].setBlockRight(mWB[i].getBlockLeft() + mWB[i].getContent().length() * charLengthX);
			mWB[i].setBlockBottom(mWB[i].getBlockTop() + charHeightY);
			mRegion[i] = new Region(new Rect((int)mWB[i].getBlockLeft(), (int)mWB[i].getBlockTop(), (int)mWB[i].getBlockRight(), (int)mWB[i].getBlockBottom()));
		}

		initialized = true;
	}
}
