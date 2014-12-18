package com.example.housingvid2;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class PopUp {

	Button button;
	String title;
	FrameLayout.LayoutParams params;
	FrameLayout layout;
	Animation anim;
	


	public PopUp(String title,int x,int y){
		layout = MainActivity.layout;
		anim =  new ScaleAnimation(0f,1.0f,0f,1.0f,0.5f,0.5f);
		anim.setDuration(1000);
		button = new Button(MainActivity.getContext());
		button.setText(title);
		button.setBackgroundResource(R.drawable.buttonshape);
		button.startAnimation(anim);
		params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.leftMargin = x;
		params.topMargin = y;
		
		layout.addView(button, params);
		
	}
	
	public void setText(String title){
		this.title = title;
		button.setText(title);
	}
	
	
	
	
	public FrameLayout.LayoutParams getParams() {
		return params;
	}
	
	public Button getButton(){
		return button;
	}
	
	public int toPx(int dp){
		

		Resources r = MainActivity.res;
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}
	
	public void dispose(){
		layout.removeView(button);
	}
}
