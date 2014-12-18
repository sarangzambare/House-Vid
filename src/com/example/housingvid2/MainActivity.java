package com.example.housingvid2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

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

	// The screen size in dp is 640 * 360 (Samsung Galaxy Note 2 );
	
	
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
					animation.proceedForward();
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
		
		
		
		

	}


	
	public static Context getContext(){
		return context;
	}
	
	
	
	public float toPx(int dp){
		

		Resources r = getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
		
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		Runnable mLongPressed = new Runnable() { 
			
		    public void run() { 
		        try {
					writeConfig(Variables.index,x,y,"tag");
					Log.i(SEARCH_SERVICE,"Write done!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }   
		};
		final Handler handler = new Handler();
		if(event.getAction() == MotionEvent.ACTION_DOWN)
	        handler.postDelayed(mLongPressed, 1000);
	    if((event.getAction() == MotionEvent.ACTION_MOVE)||(event.getAction() == MotionEvent.ACTION_UP))
	        handler.removeCallbacks(mLongPressed);
	    return super.onTouchEvent(event);
		
		
	}
	
	public void setStopages(){
		
	}

	// 1. we have to store at what index there should be stopage
	// 2. For a particular stopage, store the coordinates of annotations.
	
	public void writeConfig(int index,int x,int y,String tag) throws IOException {
		FileOutputStream fos = openFileOutput("config.txt",MODE_APPEND);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		
			bw.write(index + ";" + x + ";" + y + ";" + "tag");
			bw.newLine();
		
	 Log.i(STORAGE_SERVICE, "File Write Done!");
		bw.close();
	}
	
	public void showTrial(View v){
		try {
			FileInputStream fos = openFileInput("config.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fos));
			try {
				Toast t = Toast.makeText(getBaseContext(), br.readLine(), Toast.LENGTH_SHORT);
				t.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}