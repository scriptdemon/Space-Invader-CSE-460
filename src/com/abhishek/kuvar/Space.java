package com.abhishek.kuvar;

import java.util.List;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Space extends JPanel{
	private Player player;
	private List<Alien> aliens;
	private List<Bullet> bullets;
	private List<Bullet> alienBullets;
	private List<Bunker> bunkers;
	private int length;
	private int width;
	private List<Drawable> drawables;
	
	public Space(List<Drawable> drawables,int length,int width) {
		this.drawables = drawables;
		this.length = length;
		this.width = width;
	}
	
	/*public Space(Player player,List<Alien> aliens,int length,int width, List<Bullet> bullets,List<Bullet> alienBullets,List<Bunker> bunkers) {
		this.player = player;
		this.aliens = aliens;
		this.length = length;
		this.width = width;
		this.bullets = bullets;
		this.bunkers = bunkers;
		this.alienBullets = alienBullets;
		elems[0] = this.player;
	}*/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,length,width);
		/*player.draw(g);
		for(Bullet b:bullets) {
			b.draw(g);
		}
		for(Alien a:aliens) {
			a.draw(g);
		}
		for(Bullet b:alienBullets) {
			b.draw(g);
		}
		for(Bunker bunker: bunkers) {
			bunker.draw(g);
		}*/
		for(Drawable d:drawables)
			d.draw(g);
	}

}

