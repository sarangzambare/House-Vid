package com.example.housingvid2;

import java.io.IOException;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageRequest;

public class AnimationNormal {
	ImageView im;
	String iniPath;
	int noOfFrames;
	int index = 221;
	boolean killRunnable = false;
	Handler handler;
	Runnable playfor;
	Runnable playrev;
	boolean playForBool = false;
	boolean playRevBool = false;
	
	public AnimationNormal(){
		
	}

	public AnimationNormal(ImageView img, String inipath, int noofframes) {
		this.im = img;
		this.iniPath = inipath;
		this.noOfFrames = noofframes;
		handler = new Handler();
		playfor = new Runnable() {

			public void run() {

				try {
					if (index <= 9)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "000" + index + ".jpeg")));

					if (index > 9 && index <= 99)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "00" + index + ".jpeg")));
					if (index > 99 && index <= 999)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "0" + index + ".jpeg")));
					else
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ index + ".jpeg")));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (index++ < noOfFrames + 1 && !killRunnable && playForBool) {
					im.postDelayed(this, 50);

				}

			}

		};
		playrev = new Runnable() {

			public void run() {

				try {
					if (index <= 9)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "000" + index + ".jpeg")));

					if (index > 9 && index <= 99)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "00" + index + ".jpeg")));
					if (index > 99 && index <= 999)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "0" + index + ".jpeg")));
					else
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ index + ".jpeg")));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (index-- > 0 && !killRunnable && playRevBool) {
					im.postDelayed(this, 50);

				}

			}

		};

	}

	public void playForward() {
		playRevBool = false;
		playForBool = true;
		killRunnable = false;
		handler.postDelayed(playfor, 70);

	}

	public void stop() {
		killRunnable = true;
	}

	public void playReverse() {
		playForBool = false;
		playRevBool = true;
		killRunnable = false;
		handler.postDelayed(playrev, 10);

	}

	public int getIndex() {
		return index;
	}

}
