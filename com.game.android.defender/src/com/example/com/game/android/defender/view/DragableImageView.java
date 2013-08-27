package com.example.com.game.android.defender.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.com.game.android.defender.R;

@SuppressLint("NewApi")
public class DragableImageView extends ImageView{
	private int size = 50;
	int width_,height_;
	private DragShadowBuilder myDragShadowBuilder;
	
	public DragableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}
	public DragableImageView(Context context) {
		super(context);
		initView();
	}
	private void initView() {
		myDragShadowBuilder = new MyDragShadowBuilder(this);
	}
	public DragShadowBuilder getDragShadowBuilder() {
		return myDragShadowBuilder;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	private class MyDragShadowBuilder extends View.DragShadowBuilder {
        private Drawable mShadow;

        public MyDragShadowBuilder(View v) {
            super(v);

            final TypedArray a = v.getContext().obtainStyledAttributes(R.styleable.AppTheme);
            mShadow = a.getDrawable(R.styleable.AppTheme_itemDragShadowBackground);
            mShadow.setCallback(v);
            mShadow.setBounds(0, 0, v.getWidth(), v.getHeight());
            a.recycle();
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
            mShadow.draw(canvas);
            getView().draw(canvas);
        }
    }

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(width_==0 && w!=0){
			width_ = w;
		}
		if(height_==0 && h!=0){
			height_ = h;
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getWidth_() {
		return width_;
	}
	public int getHeight_() {
		return height_;
	}
	
	
}
