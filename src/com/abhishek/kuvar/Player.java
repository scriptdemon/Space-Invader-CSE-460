package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.Graphics;

public class Player implements Drawable{
	private int x;
	private int y;
	
	public Player(int x,int y) {
		this.x = x;
		this.y = y - 40;
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
		// Replace with (Length - 
		if(x > 710) x = 710;
		if(x < 0) x = 0;
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 20, 20);
		//System.out.println("I am called with x:"+x+" y:"+y);
	}
	
	public void moveRight() {
		x+=10;
		//System.out.println("Reached Right");
	}
	
	public void moveLeft() {
		x-=10;
		//System.out.println("Reached Left");
	}
	
}
