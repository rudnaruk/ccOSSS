package com.example.com.game.android.defender.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

@SuppressLint("NewApi")
public class Map extends View{
	private float top,left;
	private int w,h,screenWidth, screenHeight;
	private Rect bTop,bLef,bRight,bBottom, mSelect,mArea;
	private Paint mPaintBorder,mPaintSelect,mPaintArea,mPaint;
	public Map(Context context) {
		super(context);
		initView(context);
	}

	public Map(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	public Map(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	private void initView(Context context) {
		mSelect = new Rect(0, 0, 0, 0);
		bTop = new Rect(0, 0, screenWidth, (int) top);
		bLef = new Rect(0, 0, (int) left, screenHeight);
		bRight = new Rect((int) (screenWidth - left), 0, screenWidth, screenHeight);
		bBottom = new Rect(0, (int) (screenHeight-top), screenWidth, screenHeight);
		mPaintBorder  = new Paint();
		mPaintSelect = new Paint();
		mPaintArea = new Paint();
		mPaint = new Paint();
	}
	public void updateMap(float top,float left,int width,int height,int screenWidth, int screenHeight){
		this.top = top;
		this.left = left;
		this.w = width;
		this.h = height;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		bTop = new Rect(0, 0, screenWidth, (int) top);
		bLef = new Rect(0, 0, (int) left, screenHeight);
		bRight = new Rect((int) (screenWidth - left), 0, screenWidth, screenHeight);
		bBottom = new Rect(0, (int) (screenHeight-top), screenWidth, screenHeight);
		mPaintBorder.setColor(Color.BLACK);
		mPaintSelect.setColor(Color.argb(255, 200, 255, 200));
		mPaint.setColor(Color.argb(100, 90, 90, 90));
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(w==0 || h ==0){
			return;
		}
		canvas.drawRect(bTop, mPaintBorder);
		canvas.drawRect(bLef, mPaintBorder);
		canvas.drawRect(bRight, mPaintBorder);
		canvas.drawRect(bBottom, mPaintBorder);
		canvas.drawRect(mSelect, mPaintSelect);
		for(int i = 0 ; i<screenWidth/w; i++){
			float startX = left+(w*i);
			float startY = top;
			float stopX = startX;
			float stopY = screenHeight;
			canvas.drawLine(startX, startY, stopX, stopY, mPaint);
		}
		for(int i = 0 ; i<screenHeight/h; i++){
			float startX = left;
			float startY = top+(h*i);
			float stopX = screenWidth;
			float stopY = startY;
			canvas.drawLine(startX, startY, stopX, stopY, mPaint);
		}
	}
	public void clearMarkArea(){
		mSelect.right = 0;
		mSelect.bottom = 0;
		mSelect.top = 0;
		mSelect.left = 0;
	}
	public Rect getmSelect() {
		return mSelect;
	}

	public void findLocation(DragEvent event, View v) {
		int nX = (int)(event.getX());
		int nY = (int)(event.getY()+h/4);
		mSelect.left = (int) (w*(nX/w));
		mSelect.top = (int) (h*(nY/h));
		mSelect.right = (int) (mSelect.left+w);
		mSelect.bottom = (int) (mSelect.top+h);
		invalidate();
	}

	
	
}
