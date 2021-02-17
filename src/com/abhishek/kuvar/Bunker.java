package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.Graphics;

public class Bunker implements Drawable {
	private int x;
	private int y;
	private int damage = 0;
	private Color color = Color.RED;
	private static final int WIDTH = 80;
	private static final int HEIGHT = 20;
	
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

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public Bunker(int x,int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void damage() {
		if(damage <= 5) {
			damage+=1;
			color = color.darker();
		}
	}
	
	public boolean isDamaged() {
		return damage >= 6;
	}
	
}
