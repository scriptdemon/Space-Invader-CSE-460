package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score implements Drawable{
	private int score = 0;
	private int x;
	private int y;
	
	public Score(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void updateScore() {
		score+=1;
	}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Score: "+Integer.toString(score), x, y);
	}
}
