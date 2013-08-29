package com.example.com.game.android.defender.controller;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.example.pathanimation.AnimatorPath;
import com.android.example.pathanimation.PathEvaluator;
import com.android.example.pathanimation.PathPoint;
import com.example.com.game.android.defender.R;
import com.example.com.game.android.defender.core.BaseActivity;
import com.example.com.game.android.defender.core.BaseController;
import com.example.com.game.android.defender.util.Map;
import com.example.com.game.android.defender.view.DragableImageView;
import com.example.com.game.android.defender.view.MyAbsoluteLayout;

@SuppressLint("NewApi")
public class GameController extends BaseController implements OnDragListener ,OnLongClickListener {

	private DragableImageView dragView;
	private MyAbsoluteLayout mContentView;
	private int UNIT,TW,TH,SCREEN_W,SCREEN_H;
	private final int LIMIT_SCREEN_W = 800;
	private final int LIMIT_SCREEN_H = 480;
	private float scaleFactor = 1.0f;
	private Map map;
	private Handler mHandler;
	private float top;
	private float left;
	private Button mButton;
	public GameController(BaseActivity mContext) {
		super(mContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initController() {
		mContentView = ((MyAbsoluteLayout)getActivity().findViewById(R.id.myabsolutelayout));
		mContentView.setOnDragListener(this);
		map = (Map)getActivity().findViewById(R.id.map1);
		((DragableImageView)getActivity().findViewById(R.id.DragableImageView01)).setOnLongClickListener(this);
		((DragableImageView)getActivity().findViewById(R.id.DragableImageView02)).setOnLongClickListener(this);
		((DragableImageView)getActivity().findViewById(R.id.dragableImageView1)).setOnLongClickListener(this);
		UNIT = (int) getActivity().getResources().getDimension(R.dimen.map_unit);
		TW = (int) getActivity().getResources().getDimension(R.dimen.tower_unit_width);
		TH = (int) getActivity().getResources().getDimension(R.dimen.tower_unit_height);
		mHandler =new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				updateScreenFactor();
			}
		},1000);
		mButton = (Button) getActivity().findViewById(R.id.delete_me);
		// Set up the path we're animating along
		AnimatorPath path = new AnimatorPath();
		path.moveTo(0, 0);
		path.lineTo(300, 0);
		path.lineTo(300, 70);
		path.lineTo(0, 70);
		path.lineTo(0, 140);
		path.lineTo(300, 140);
		path.lineTo(300, 210);
		path.lineTo(0, 210);
		path.curveTo(100, 0, 300, 900, 400, 500);

		// Set up the animation
		final ObjectAnimator anim = ObjectAnimator.ofObject(this, "buttonLoc",
		        new PathEvaluator(), path.getPoints().toArray());
		anim.setDuration(100000);

		mButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        Log.d("united", "button clicked");
		        anim.start();
		    }
		});
	}
	private void updateScreenFactor(){
		SCREEN_W = mContentView.getWidth();
		SCREEN_H = mContentView.getHeight();
		scaleFactor = updateScalFactor(SCREEN_W,SCREEN_H,LIMIT_SCREEN_W,LIMIT_SCREEN_H);
		UNIT = (int) (scaleFactor * UNIT);
		TW = (int) (scaleFactor * TW);
		TH =(int) (scaleFactor * TH);
		top = (SCREEN_W - (SCREEN_W * scaleFactor))/2;
		left = (SCREEN_H - (SCREEN_H * scaleFactor))/2;
		Log.d("UNIT","UNIT : "+UNIT+",TW : "+TW+",TH : "+TH+ ",scaleFactor : "+scaleFactor+",SCREEN_W : "+SCREEN_W+",SCREEN_H : "+SCREEN_H);
		Log.d("UNIT","TOP : "+top+",LEFT : "+left);
		map.updateMap(top, left, UNIT, UNIT, SCREEN_W, SCREEN_H);
		map.invalidate();
		
	}
	private float updateScalFactor(int newWidth, int newHeight, int sourceWidth, int sourceHeight){
		float xScale = (float) newWidth / sourceWidth;
	    float yScale = (float) newHeight / sourceHeight;
	    return Math.min(xScale, yScale);
	}
	@Override
	public boolean onDrag(View v, DragEvent event) {
		Log.d("ACTION",""+ event.getAction());
		switch (event.getAction()) {
	        case DragEvent.ACTION_DRAG_ENTERED:
	            break;
	        case  DragEvent.ACTION_DRAG_LOCATION:
	        	map.updateMap(top, left, UNIT, UNIT, SCREEN_W, SCREEN_H);
	        	map.findLocation(event,v);
	        	break;
	        case DragEvent.ACTION_DRAG_EXITED:
	            v.setBackgroundColor(Color.TRANSPARENT);
	            break;
	
	        case DragEvent.ACTION_DRAG_STARTED:
	        	return true;
	
	        case DragEvent.ACTION_DROP:
	            v.setBackgroundColor(Color.TRANSPARENT);
	            return processDrop(event, v);
	    }
	    return false;
	}

	private boolean processDrop(DragEvent event, View v) {
		 if(!map.isAvailableSpace(event)){
			 return false;
		 }
		 int nX = (int)(event.getX()-TW/2);
		 int nY = (int)(event.getY()-TH/2);
		 int limitY=0;
		 if(nX<0){
			 nX=0;
		 }else if(nX > (mContentView.getWidth() - TW)){
			 nX = mContentView.getWidth() - TW;
		 }
		 if(nY<limitY){
			 nY = limitY;
		 }else if(nY > (mContentView.getHeight() - TH)){
			 nY = mContentView.getHeight() - TH;
		 }
		MyAbsoluteLayout.LayoutParams lp = new MyAbsoluteLayout.LayoutParams (TW,TH, map.getmSelect().left, map.getmSelect().top -  (TH/2));
        ImageButton img = new ImageButton(getActivity());
        img.setImageDrawable(dragView.getDrawable());
        img.setBackgroundColor(Color.TRANSPARENT);
       	((MyAbsoluteLayout)mContentView).addView(img, lp);
       	map.clearMarkArea();
       	return true;
       
	}

	@Override
	public boolean onLongClick(View v) {
		DragableImageView a = (DragableImageView)v;
		final String title = "Image";
		final String textData = String.format("%d||%d", 1, 1);
		ClipData data = ClipData.newPlainText(title, textData);
		v.startDrag(data, a.getDragShadowBuilder(), null, 0);
		dragView = a;
		return true;
	}
	 public void setButtonLoc(PathPoint newLoc) {
	        mButton.setTranslationX(newLoc.mX);
	        mButton.setTranslationY(newLoc.mY);
	    }
}
