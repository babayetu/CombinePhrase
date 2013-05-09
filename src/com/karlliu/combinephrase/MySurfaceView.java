package com.karlliu.combinephrase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * 
 * @author Himi
 * 
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;

	// different paint pen
	private Paint paintDropArea;
	private Paint paintWordBlock;
	private Paint paintText;

	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private int screenW, screenH;
	private WordBlock[] mWB;
	private FixedWordBlock[] fixedWB;
	private boolean initialized = false;
	private int dropBoxColumns = 4; // columns one line

	// constant
	private final int randomBlockPlaceY = 200;	//random word block initial Y position - below the Y lines
	private final int textPaintSize = 40;
	private final int charLengthX = 20;
	private final int charHeightY = 50;
	private final int xBlockPadding = 20;		//word block x need to be longer to hold all chars
	private final float textShiftX = 5.0f;
	private final float textShiftY = textPaintSize;
	private final float statusBarHeightY = charHeightY;

	// constant drop area
	private final float lineSpace = 20.0f;
	private final float columnSpace = 10.0f;
	private final float dropAreaLeft = 10.0f;
	private final float dropAreaTop = 10.0f;
	private final float dropBoxWidth = charLengthX * 10.0f;
	private final float dropBoxHeight = charHeightY;

	/**
	 * SurfaceView constructor
	 */
	public MySurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);

		// drop area paint
		paintDropArea = new Paint();
		paintDropArea.setColor(Color.WHITE);
		paintDropArea.setAntiAlias(true);

		// paint word block
		paintWordBlock = new Paint();
		paintWordBlock.setColor(Color.GRAY);
		paintWordBlock.setAntiAlias(true);

		// paint the text
		paintText = new Paint();
		paintText.setColor(Color.BLUE);
		paintText.setAntiAlias(true);
		paintText.setTextSize(textPaintSize);

		setFocusable(true);
		mWB = new Phrase(1).getContent();

		fixedWB = new FixedWordBlock[mWB.length];
		for (int i = 0; i < fixedWB.length; i++) {
			fixedWB[i] = new FixedWordBlock();
		}
	}

	/**
	 * When SurfaceView is created, this function will be called
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();   //landscape width=854 height=480
		screenH = this.getHeight();
		//Log.i("phrase", "width=" + screenW + " height=" + screenH);
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
				drawDropArea();

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
			for (int i = 0; i < mWB.length; i++) {
				if (judgeTouched(mWB[i], event.getX(), event.getY())) {
					mWB[i].setTouched(true);
					Log.i("phrase", "down " + i + " selected");
					break;
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			for (int i = 0; i < mWB.length; i++) {
				if (mWB[i].getTouched()) {
					updateWordBlock(mWB[i], event.getX(), event.getY());
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			for (int i = 0; i < mWB.length; i++) {
				if (mWB[i].getTouched()) {
					boolean movedInDropBox = false;
					// release the finger and judge if the destination is within the fixedWB
					for (int j = 0; j < fixedWB.length; j++) {
						if (judgeTouched(fixedWB[j], event.getX(), event.getY())) {
							updateWordBlock(mWB[i], fixedWB[j].getBlockLeft(), fixedWB[j].getBlockTop());
							movedInDropBox = true;
							Log.i("phrase", "dropped in " + j + " drop box");
							break;
						}
					}

					// not moved in drop box
					if (!movedInDropBox)
						updateWordBlock(mWB[i], event.getX(), event.getY());

					mWB[i].setTouched(false);
					Log.i("phrase", "up " + i + " changed");

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

	/**
	 * Draw drop area
	 */
	public void drawDropArea() {
		for (int i = 0; i < fixedWB.length; i++) {
			canvas.drawRect(fixedWB[i].getBlockLeft(), fixedWB[i].getBlockTop(), fixedWB[i].getBlockRight(), fixedWB[i].getBlockBottom(),
					paintDropArea);
		}
	}

	/**
	 * return word block color randomly
	 */
	private int selectColor(WordBlock wb) {
		// background and foreground
		return Color.GRAY;
	}

	// change word block position
	private void updateWordBlock(WordBlock aWb, float x, float y) {
		aWb.setBlockLeft(x);
		aWb.setBlockTop(y);
		aWb.setBlockRight(x + aWb.getContent().length() * charLengthX + xBlockPadding);
		aWb.setBlockBottom(y + charHeightY);
	}

	// judge word block is touched
	private boolean judgeTouched(FixedWordBlock aWB, float x, float y) {
		if (aWB.getBlockLeft() < x && x < aWB.getBlockRight() && aWB.getBlockTop() < y && y < aWB.getBlockBottom())
			return true;
		return false;
	}

	// initialized word blocks
	public void initializeRandomBlocks() {
		// Y start from 600, X starts from 0
		if (initialized)
			return;
		for (int i = 0; i < mWB.length; i++) {
			updateWordBlock(mWB[i], mWB[i].getRamdonX() * screenW, mWB[i].getRamdonY() * (screenH - randomBlockPlaceY) + randomBlockPlaceY
					- statusBarHeightY);
		}

		// initialize drop area box
		for (int i = 0; i < fixedWB.length; i++) {
			int row = i / dropBoxColumns; // word block in which line and column
			int col = i % dropBoxColumns;

			fixedWB[i].setBlockLeft(dropAreaLeft + col * dropBoxWidth + col * columnSpace);
			fixedWB[i].setBlockTop(dropAreaTop + row * dropBoxHeight + row * lineSpace);
			fixedWB[i].setBlockRight(fixedWB[i].getBlockLeft() + dropBoxWidth);
			fixedWB[i].setBlockBottom(fixedWB[i].getBlockTop() + dropBoxHeight);
		}

		initialized = true;
	}
}
