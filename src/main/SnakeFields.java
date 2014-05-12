package main;

import android.graphics.Paint;
import android.graphics.Rect;

public class SnakeFields {
	public int posX, posY, dir;
	public boolean empty, snake, dinner;
	public Paint paint;
	public Rect rect;


	public int getPosX(SnakeFields sF){
		int a = sF.posX;
		return a;
	}
	
	public int getPosY(SnakeFields sF){
		int a = sF.posY;
		return a;
	}
	
	public Rect getRect(SnakeFields sF){
		Rect r = new Rect();
		return r;
	}
}