package com.example.snake;

import java.util.ArrayList;
import java.util.Random;

import android.R.drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewDebug.IntToString;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class SnakeCage extends SurfaceView implements Runnable{
	
	SurfaceHolder aHolder;
	Thread aThread = null;
	boolean isRunning = true;
	boolean snakeMoved;
	boolean gameOver;
	boolean changedDir;
	boolean dinnerExists;
	boolean arc;
	int numberOfDinners;
	int direction, jump;  //jump - size of one pole or snake
	int left,right,top,bottom, counter;
	boolean initVar; 
	Bitmap head, ant, tail_epmty, tail_curve;
	int dinnerX, dinnerY;
	Canvas canvas;
	Paint snakePaint, dinnerPaint;
	ArrayList<SnakeFields> snakeTail; 
	ArrayList<SnakeFields> dinnerTable;
	SnakeFields[][] playground;
	SnakeFields snakeHead, snakeButt;
	Context mContext;
	int score, dinnerAmount, FPS, prevDir;
	
	
	public SnakeCage(Context context) {
		super(context);
		init();
	}

	public SnakeCage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public SnakeCage(Context context, AttributeSet attrs, int defStyle) {
		 super(context, attrs, defStyle);
		 init();
	}
	
	public void pause() {
		isRunning = false;
		while(true){
			try {
				aThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		Log.d("pause()","game has ended");
		aThread = null;
	}

	public void resume() {
		isRunning = true;		
		aThread = new Thread(this);
		aThread.start();
	}
	
	public void init(){
		arc = PlayingActivity.arc;
		if(arc){
			dinnerAmount = 5;
		}else 
			dinnerAmount = 1;
		
		aHolder = getHolder();
		snakePaint = new Paint();
		snakePaint.setColor(Color.RED);
		tail_curve = BitmapFactory.decodeResource(getResources(), R.drawable.tail_curve);
		tail_epmty = BitmapFactory.decodeResource(getResources(), R.drawable.tail_empty);
		ant = BitmapFactory.decodeResource(getResources(), R.drawable.ant_small);
		head = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		dinnerPaint = new Paint();
		dinnerPaint.setColor(Color.YELLOW);
		direction = score = numberOfDinners = 0;
		snakeMoved = changedDir = dinnerExists = gameOver = false;
		initVar = true;
		FPS = 500;
		prevDir = 1;
		//head = BitmapFactory.decodeResource(getResources(), R.drawable.snake_icon);
		snakeTail = new ArrayList<SnakeFields>();
		dinnerTable = new ArrayList<SnakeFields>();
		playground = new SnakeFields[24][20];
		for(int i=0 ; i<24 ; i++){
			for(int j=0 ; j<20 ; j++){
				SnakeFields a = new SnakeFields();
				playground[i][j] = a;
				a.posX=i;
				a.posY=j;
				a.empty = true;
				a.dinner = false;
				a.snake = false;
				//a.paint = snakePaint;
			}
		}
	}
	
	
	public void move(int prevDir){
		
		if(changedDir) return;
		
		switch(prevDir){
		case 1: //up
			if(direction == 2) break;
			direction = 1;
			changedDir = true;
			break;
		case 2: // down
			if(direction == 1) break;
			direction = 2;
			changedDir = true;
			break;
		case 3: // left
			if(direction == 4) break;
			direction = 3;
			changedDir = true;
			break;
		case 4: // right
			if(direction == 3) break;
			direction = 4;
			changedDir = true;
			break;
		}
	}
	
	public void update(){
		int pDir = 0;
		int pHeadX = snakeHead.posX;
		int pHeadY = snakeHead.posY;
		int pButtX = snakeButt.posX;
		int pButtY = snakeButt.posY;
		
		switch(direction){
		case 0:
			return;
		case 1: //up
			pDir = 1;
			pHeadX--;
			snakeMoved = true;
			break;
		case 2: // down
			pDir = 2;
			pHeadX++;
			snakeMoved = true;
			break;
		case 3: // left
			pDir = 3;
			pHeadY--;
			snakeMoved = true;
			break;
		case 4: // right
			pDir = 4;
			pHeadY++;
			snakeMoved = true;
			break;
		}
		
		gameOver(pHeadY, pHeadX);
		
		if(gameOver) 
			return;
		
		// new head
		playground[pHeadX][pHeadY].dir = pDir;
		snakeHead.dir = pDir;
		playground[pHeadX][pHeadY].snake = true;
		playground[pHeadX][pHeadY].empty = false;
		//Log.d("update()", "new head position: "+pHeadX+":"+pHeadY);
		
		// add new head
		snakeHead = playground[pHeadX][pHeadY];
		snakeTail.add(snakeHead);
		
		if(snakeHead.dinner){
			// snake eats
			score ++;
			if(arc && FPS > 250) FPS -=5;
			//Log.d("update()", "trafilem na dinner w "+pHeadX+" "+pHeadY);
			dinnerExists = false;
			numberOfDinners --;
			playground[pHeadX][pHeadY].dinner = false;
			dinnerTable.remove(playground[pHeadX][pHeadY]);
			return;
		}
		
		// tail is now empty
		//Log.d("update()", "removing snake from: "+pButtX+":"+pButtY);
		playground[pButtX][pButtY].snake = false;
		playground[pButtX][pButtY].empty = true;
		
		// remove tail from array
		snakeTail.remove(0);
		snakeButt = snakeTail.get(0);
	}
	
	private void gameOver(int pHeadY, int pHeadX) {
		//Log.d("gameOver()", "sprawdzam");
		if(pHeadY<0 || pHeadX<0 || pHeadX>23 || pHeadY>19 || playground[pHeadX][pHeadY].snake){
			Log.d("gameOver()", "koniec gry");
			gameOver = true;
		}
	}
	
	public void debug(){
		int snake,dinner;
		
		for(int i=0 ; i<24 ; i++){
			for(int j=0 ; j<20 ; j++){
				snake = dinner = 0;
				if(playground[i][j].snake) snake = 1;
				if(playground[i][j].empty) continue;
				if(playground[i][j].dinner) dinner = 1;
				
				Log.d("debug()",""+i+":"+j+" "+snake+" "+ dinner);
			}
		}
	}
	
	public void run() {
		
		while (isRunning){
			if(!aHolder.getSurface().isValid())
				continue;
			
			canvas = aHolder.lockCanvas();
			canvas.drawRGB(0, 255, 255);
			
			makePlayground(initVar);
			
			//gameOver();
			update();
			if(gameOver) {
				isRunning = false;
				//Log.d("go","i am here");
				Context context = getContext();
				
				Intent intent = new Intent(context, PopupHiScoreActivity.class);
				intent.putExtra("key",score); 
				if(!PopupHiScoreActivity.newScoreSaved) {
					context.startActivity(intent);
				}
				if (PopupHiScoreActivity.newScoreSaved){
					Intent intent2 = new Intent(context, HiScoreActivity.class);
					context.startActivity(intent2);
					((PlayingActivity)context).finish();
				}
				
				}
			rollTheDinner();
			//debug();
			drawPlayground();
			//Log.d("YourTag", "snakeHead[i][j] = "+ snakeHead.posX +" ,"+snakeHead.posY);

			//Log.d("YourTag", "snakeButt[i][j] = "+ snakeButt.posX +" ,"+snakeButt.posY);
			
			aHolder.unlockCanvasAndPost(canvas);
			changedDir = false;
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void drawPlayground(){
		prevDir = snakeTail.get(0).dir;
		for(SnakeFields b : snakeTail){
			if (b == snakeHead){
	
				switch(b.dir){
				case 0:
					canvas.drawBitmap(head, null, b.rect, null);
					break;
				case 1: //up
					canvas.drawBitmap(head, null, b.rect, null);
					break;
				case 2: // down
					canvas.drawBitmap(RotateBitmap(head, 180), null, b.rect, null);
					break;
				case 3: // left
					canvas.drawBitmap(RotateBitmap(head, 270), null, b.rect, null);
					break;
				case 4: // right
					canvas.drawBitmap(RotateBitmap(head, 90), null, b.rect, null);
					break;
				}
			}else{
				if(prevDir != b.dir){
					if((prevDir == 1 && b.dir == 4)||(prevDir == 3 && b.dir == 2)){
							canvas.drawBitmap(RotateBitmap(tail_curve, 270), null, b.rect, null);
						}else if((prevDir == 4 && b.dir == 2)||(prevDir == 1 && b.dir == 3)){
							canvas.drawBitmap(tail_curve, null, b.rect, null);
						}else if((prevDir == 2 && b.dir == 4)||(prevDir == 3 && b.dir == 1)){
							canvas.drawBitmap(RotateBitmap(tail_curve, 180), null, b.rect, null);
						}else if((prevDir == 2 && b.dir == 3)||(prevDir == 4 && b.dir == 1)){
							canvas.drawBitmap(RotateBitmap(tail_curve, 90), null, b.rect, null);
							
							
						}
				}else if(b.dir == 1 || b.dir == 2){
				
					canvas.drawBitmap(tail_epmty, null, b.rect, null);
				}else{
					canvas.drawBitmap(RotateBitmap(tail_epmty, 90), null, b.rect, null);
				}
				
			}
			prevDir = b.dir;
		}
		for(SnakeFields d : dinnerTable){
			canvas.drawBitmap(ant,null,d.rect, null);
		}
	} 
	
	public static Bitmap RotateBitmap(Bitmap source, float angle){
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}
	
	private void makePlayground(boolean b){
		if(!b) return;
		
		initVar=false;
		jump = getWidth()/20;
		left = (getWidth()/2);
		top = (canvas.getHeight()-(11*jump));
		right = (getWidth()/2)+jump;
		for(int i=0 ; i<24 ; i++){
			for(int j=0 ; j<20 ; j++){
				Rect a = new Rect(j*jump,i*jump,(j*jump)+jump,(i*jump)+jump);
				playground[i][j].rect = a;
			}
		}
		for(int i=3 ; i>=0 ; i--){
			playground[10+i][10].dir = 1;
			playground[10+i][10].snake = true;
			playground[10+i][10].empty = false;
			playground[10+i][10].paint = snakePaint;
			snakeTail.add(playground[10+i][10]);
		}
		snakeHead = playground[10][10];
		snakeButt = playground[13][10];
		
		
	}
	
	public void rollTheDinner(){
		int cc = 0;
		while(numberOfDinners<dinnerAmount) {
		
			
			Random rand = new Random();
			while(true){
				cc ++;
				numberOfDinners ++;
				dinnerX = rand.nextInt(24);
				dinnerY = rand.nextInt(20);
				
				if( playground[dinnerX][dinnerY].snake == false &&
					playground[dinnerX][dinnerY].empty == true  &&
					playground[dinnerX][dinnerY].dinner == false )
				
				break;
			}
		
		Log.d("rollTheDinner()", "losowanie nr"+cc+"   new dinner: "+dinnerX+" "+dinnerY);
		playground[dinnerX][dinnerY].dinner = true;
		playground[dinnerX][dinnerY].empty = false;
		dinnerExists = true;
		dinnerTable.add(playground[dinnerX][dinnerY]);
		Log.d("rollTheDinner()", "zapisano");
		}
	}
}
