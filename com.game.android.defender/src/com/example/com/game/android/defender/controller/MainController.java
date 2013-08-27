package com.example.com.game.android.defender.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.game.android.defender.GameActivity;
import com.example.com.game.android.defender.R;
import com.example.com.game.android.defender.core.BaseActivity;
import com.example.com.game.android.defender.core.BaseController;
import com.example.com.game.android.defender.util.Mission;

public class MainController extends BaseController {
	private MissionAdapter mMissionAdapter;
	private ListView mListView;
	private ArrayList<Mission> arrayListMission;
	public MainController(BaseActivity mContext) {
		super(mContext);
	}
	public ArrayList<Mission> getArrayListMission() {
		if(arrayListMission==null)
			arrayListMission = new ArrayList<Mission>();
		return arrayListMission;
	}

	@Override
	public void initController() {
		getArrayListMission().add(new Mission(1, "Mission one X", 1,R.drawable.mision1));
		getArrayListMission().add(new Mission(0, "Mission two XI", 0,R.drawable.mision2));
		getArrayListMission().add(new Mission(0, "Mission three XII", 0,R.drawable.mision3));
		getArrayListMission().add(new Mission(0, "Mission four IV" , 0,R.drawable.mision4));
		getArrayListMission().add(new Mission(0, "Mission five V", 0,R.drawable.mision5));
		getArrayListMission().add(new Mission(0, "Mission six VI", 0,R.drawable.mision1));
		getArrayListMission().add(new Mission(0, "Mission seven VII", 0,R.drawable.mision3));
		mMissionAdapter = new MissionAdapter(getActivity(), 0,getArrayListMission());
		mListView = (ListView) getActivity().findViewById(R.id.lv_mission);
		mListView.setAdapter(mMissionAdapter);
	}
	private class MissionAdapter extends ArrayAdapter<Mission>{
		private LayoutInflater inflater;
		public MissionAdapter(Context context, int textViewResourceId, ArrayList<Mission> objects) {
			super(context, textViewResourceId, objects);
			inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			BigDecimal one = new BigDecimal(super.getCount());
			BigDecimal oneTenth = one.divide(new BigDecimal(2),RoundingMode.CEILING);
			return oneTenth.intValue();
		}

		@SuppressLint("NewApi")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=inflater.inflate(R.layout.item_mission, null);
				ViewHolder newHolder = new ViewHolder();
				newHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.item_image1);
				newHolder.mLocker1 = (ImageButton) convertView.findViewById(R.id.item_locker1);
				newHolder.mTextScore1 = (TextView) convertView.findViewById(R.id.item_score1);
				newHolder.mTextTitle1 = (TextView) convertView.findViewById(R.id.item_title1);
				
				newHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.item_image2);
				newHolder.mLocker2 = (ImageButton) convertView.findViewById(R.id.item_locker2);
				newHolder.mTextScore2 = (TextView) convertView.findViewById(R.id.item_score2);
				newHolder.mTextTitle2 = (TextView) convertView.findViewById(R.id.item_title2);
				convertView.setTag(newHolder);
			}
			final ViewHolder holder = (ViewHolder) convertView.getTag();
			position = 2*position;
			holder.position = position;
			holder.mImageView1.setVisibility(View.VISIBLE);
			holder.mTextScore1.setVisibility(View.VISIBLE);
			holder.mTextTitle1.setVisibility(View.VISIBLE);
			holder.mLocker1.setVisibility(View.VISIBLE);
			holder.mImageView1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showChooseLevelDialog();
//					Toast.makeText(getContext(), "Mission "+holder.position+" Clicked!", Toast.LENGTH_SHORT).show();
				}
			});
			holder.mImageView2.setVisibility(View.VISIBLE);
			holder.mTextScore2.setVisibility(View.VISIBLE);
			holder.mTextTitle2.setVisibility(View.VISIBLE);
			holder.mLocker2.setVisibility(View.VISIBLE);
			holder.mImageView2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Toast.makeText(getContext(), "Mission "+(holder.position+1)+" Clicked!", Toast.LENGTH_SHORT).show();
				}
			});
			holder.mImageView1.setImageResource(getItem(position).getmImageId());
			holder.mTextScore1.setText("Score : "+getItem(position).getmScore());
			holder.mTextTitle1.setText(getItem(position).getmTitle());
			if(getItem(position).getmState()==1){
//				holder.mImageView1.setAlpha(1f);
				holder.mLocker1.setVisibility(View.GONE);
				holder.mTextScore1.setVisibility(View.VISIBLE);
			}else{
//				holder.mImageView1.setAlpha(0.5f);
				holder.mLocker1.setVisibility(View.VISIBLE);
				holder.mTextScore1.setVisibility(View.INVISIBLE);
			}
			try{
				holder.mImageView2.setImageResource(getItem(position+1).getmImageId());
				holder.mTextScore2.setText("Score : "+getItem(position+1).getmScore());
				holder.mTextTitle2.setText(getItem(position+1).getmTitle());
				if(getItem(position+1).getmState()==1){
//					holder.mImageView2.setAlpha(1f);
					holder.mLocker2.setVisibility(View.GONE);
					holder.mTextScore2.setVisibility(View.VISIBLE);
				}else{
					holder.mImageView2.setAlpha(.5f);
//					holder.mLocker2.setVisibility(View.VISIBLE);
					holder.mTextScore2.setVisibility(View.INVISIBLE);
				}
			}catch (Exception e) {
				holder.mImageView2.setVisibility(View.INVISIBLE);
				holder.mTextScore2.setVisibility(View.INVISIBLE);
				holder.mTextTitle2.setVisibility(View.INVISIBLE);
				holder.mLocker2.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}
		class ViewHolder{
			ImageButton mLocker1;
			ImageView mImageView1;
			TextView mTextScore1;
			TextView mTextTitle1;
			ImageButton mLocker2;
			ImageView mImageView2;
			TextView mTextScore2;
			TextView mTextTitle2;
			int position;
		}
	}
	public void showChooseLevelDialog(){
		final Dialog a =new Dialog(getActivity(), R.style.ThemeDialogCustom);
		a.setContentView(R.layout.chooselevel_dialog);
		OnClickListener mOnClick = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), GameActivity.class);
				getActivity().startActivity(intent);
				a.dismiss();
			}
		};
		((Button)a.findViewById(R.id.btn_easy)).setOnClickListener(mOnClick);
		((Button)a.findViewById(R.id.btn_normal)).setOnClickListener(mOnClick);
		((Button)a.findViewById(R.id.btn_hard)).setOnClickListener(mOnClick);
		a.show();
	}
}
