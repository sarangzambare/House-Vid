package com.example.housingvid2;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	int offsetY = 0;
	static ImageView im;
	static AssetManager assets;
	static Resources res;
	Animation360 animation;
	Button next;
	Button prev;
	

	Animation anim;
	PopUp pop;
	Button button;
	static Context context;
	static FrameLayout layout;
	FrameLayout.LayoutParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		res = getResources();
		setContentView(R.layout.activity_main);
		layout = (FrameLayout) findViewById(R.id.frameLayout1);
		next = (Button) findViewById(R.id.buttonNext);
		prev = (Button) findViewById(R.id.buttonPrev);
		assets = getAssets();
		im = (ImageView) findViewById(R.id.imageView1);
		im.setScaleType(ScaleType.FIT_XY);
		animation = new Animation360(im,
				"www.icreate3d.com - iCreate 3D House Walkthrough (1)_", 528);
		
		next.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_UP:
					animation.stop();
					break;
				case MotionEvent.ACTION_DOWN:
					animation.playForward();
					break;

				}
				return true;
			}
		});

		prev.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_UP:
					animation.stop();
					break;
				case MotionEvent.ACTION_DOWN:
					animation.playReverse();
					break;

				}
				return true;
			}
		});
		
		PopUp pop = new PopUp("hey",0,50);

	}

	public void playNext(View v) {
		animation.playForward();
	}

	public void playPrev(View v) {
		animation.playReverse();
	}
	
	public static Context getContext(){
		return context;
	}
	
	
	
	public float toPx(int dp){
		

		Resources r = getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}
	

}