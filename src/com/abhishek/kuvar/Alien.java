package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.Graphics;

public class Alien implements Drawable{
	private int positionX;
	private int positionY;
	private static int dir = 1;

	/**
	 * @return the dir
	 */
	public static int getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public static void setDir(int dir) {
		Alien.dir = dir;
	}

	Alien(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(positionX, positionY, 20, 20);
	}

	public void moveHorizontal() {
		positionX += this.dir * 2;
	}

	public void moveDown() {
		positionY += 20;

	}

}
