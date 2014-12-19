package com.example.housingvid2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Handler;
import android.widget.ImageView;

public class Animation360 extends Activity{
	ImageView im;
	String iniPath;
	String line = "";
	ArrayList<Point> popPoints;
	int noOfFrames;
	MainActivity main;
	boolean killRunnable = false;
	Handler handler;
	Runnable playfor;
	Runnable playrev;
	ArrayList<PopUp> pops;
	Iterator<PopUp> popsIterator;
	boolean playForBool = false;
	boolean playRevBool = false;
	
	

	public Animation360(ImageView img, String inipath, int noofframes) {
		main = new MainActivity();
		this.im = img;
		this.iniPath = inipath;
		this.noOfFrames = noofframes;
		pops = new ArrayList<PopUp>();
		popPoints = new ArrayList<Point>();
		handler = new Handler();
		playfor = new Runnable() {

			public void run() {

				try {
					if (Variables.index <= 9)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "000" + Variables.index + ".jpeg")));

					if (Variables.index > 9 && Variables.index <= 99)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "00" + Variables.index + ".jpeg")));
					if (Variables.index > 99 && Variables.index <= 999)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "0" + Variables.index + ".jpeg")));
					else
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ Variables.index + ".jpeg")));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Variables.index++;
				
				if(Variables.index > noOfFrames){
					Variables.index = 221;
				}

				if (!killRunnable && playForBool && !MainActivity.checkStopage(MainActivity.indicesToStop,Variables.index)) {
					removePops(pops);
					im.postDelayed(this, 50);
					
				}
				else{
					
					try {
						FileInputStream fis = MainActivity.getContext().openFileInput("config.txt");
						BufferedReader br = new BufferedReader(new InputStreamReader(fis));
						while((line = br.readLine()) != null){
							if(Integer.parseInt(line.split(";")[0]) == Variables.index){
								//popPoints.add(new Point(Integer.parseInt(line.split(";")[1]),Integer.parseInt(line.split(";")[2])));
								pops.add(new PopUp(line.split(";")[3],Integer.parseInt(line.split(";")[1])+20,Integer.parseInt(line.split(";")[2])-70));
								
							}
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
						
				}

			
				
//				if(Variables.index == 310){
//					pops.add(new PopUp("TV",100,200));
//				}
//				else{
//					if(!isNull(pops)){
//						removePops(pops);
//					}
//				}
				
				
				
				
				
					

			}

		};
		playrev = new Runnable() {

			public void run() {

				try {
					if (Variables.index <= 9)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "000" + Variables.index + ".jpeg")));

					if (Variables.index > 9 && Variables.index <= 99)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "00" + Variables.index + ".jpeg")));
					if (Variables.index > 99 && Variables.index <= 999)
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ "0" + Variables.index + ".jpeg")));
					else
						im.setImageBitmap(BitmapFactory
								.decodeStream(MainActivity.assets.open(iniPath
										+ Variables.index + ".jpeg")));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Variables.index--;
				
				if(Variables.index < 221){
					Variables.index = noOfFrames;
				}

				if (!killRunnable && playRevBool && !MainActivity.checkStopage(MainActivity.indicesToStop,Variables.index) ) {			//Implement checkStopage() function here.
					im.postDelayed(this, 50);

				}
				
//				if(Variables.index == 310){
//					pops.add(new PopUp("TV",100,200));
//				}
//				else{
//					if(!isNull(pops)){
//						removePops(pops);
//					}
//				}
//				
				
			

			}

		};

	}

	public void proceedForward() {
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
	
	public boolean isNull(ArrayList<PopUp> popsi){
		int i = 0;
		popsIterator = popsi.iterator();
		while(popsIterator.hasNext()){
			if(popsIterator.next() != null)
				i++;
		}
		
		if(i == 0)
			return true;
		else
			return false;
		
	}
	
	public void removePops(ArrayList<PopUp> popsi){
		popsIterator = popsi.iterator();
		while(popsIterator.hasNext()){
			popsIterator.next().dispose();
		}
	}
	


	

}
