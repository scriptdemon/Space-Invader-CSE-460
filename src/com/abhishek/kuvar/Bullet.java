package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Drawable {

	
	private int x;
	private int y;
	private final static int WIDTH = 3;
	private final static int HEIGHT = 10;
	private final static int VELOCITY = 10;
	private Color color;
	private int direction;
	
	public Bullet(int x,int y,int direction,Color color) {
		// x = x + (player_width / 2)
		this.x = x + 10;
		this.y = y;
		this.direction = direction;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		g.fillRect(x, y, WIDTH, HEIGHT);
		//System.out.println("Bullet Reached at x:"+x+" y:"+y);
	}
	
	public void shootUp() {
		if(y > 0)
			y+=(VELOCITY*direction);
	}

}
