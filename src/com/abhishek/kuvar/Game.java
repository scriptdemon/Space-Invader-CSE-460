package com.abhishek.kuvar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener,KeyListener {
	private Player player;
	private List<Alien> aliens;
	private Space space;
	private List<Bullet> bullets;
	private List<Bullet> alienBullets;
	private List<Bunker> bunkers;
	private List<Drawable> drawables;
	private Score score;
	private static final int LENGTH = 750;
	private static final int WIDTH = 600;
	private static final int PLAYER_BULLET_DIRECTION = -1;
	private static final int ALIEN_BULLET_DIRECTION = 1;
	private static final int ALIEN_ROWS = 5;
	private static final int ALIEN_COLUMNS = 11;
	private static final int ALIEN_START_X = 20;
	private static final int ALIEN_END_X = ALIEN_START_X + ALIEN_COLUMNS*40; //replace 40 with margin and width
	private static final int ALIEN_START_Y = 100;
	private static final int ALIEN_END_Y = ALIEN_START_Y + ALIEN_ROWS*40;
	private static final int BUNKER_START_X = 20;
	private static final int BUNKER_Y = 500;
	private static final int NUMBER_OF_BUNKERS = 3;
	private static final Color ALIEN_BULLET_COLOR = Color.BLUE;
	private static final Color PLAYER_BULLET_COLOR = Color.YELLOW;
	private static final int SCORE_X = 100;
	private static final int SCORE_Y = 40;
	private static int alien_direction = 1;
	private boolean isAlienMoveDown = false;
	private boolean areAliensReachedEnd = false;
	private int timeElapsed = 0;
	private int lastShot = 0;
	private Timer timer;

	public Game() {
		drawables = new ArrayList<Drawable>();
		
		player = new Player(LENGTH/2,WIDTH);
		drawables.add(player);
		
		aliens = new ArrayList<Alien>();
		
		for(int u=ALIEN_START_X; u <= ALIEN_END_X; u+=40) {
			for(int v=ALIEN_START_Y; v<=ALIEN_END_Y; v+=40) {
				Alien alien = new Alien(u,v);
				aliens.add(alien);
				drawables.add(alien);
			}
		}
		
		bullets = new ArrayList<Bullet>();
		
		alienBullets = new ArrayList<Bullet>();
		
		bunkers = new ArrayList<Bunker>();
		for(int u=0;u<3;u++) {
			Bunker bunker = new Bunker(BUNKER_START_X + u*(710/NUMBER_OF_BUNKERS), BUNKER_Y);
			bunkers.add(bunker);
			drawables.add(bunker);
		}
		
		score = new Score(SCORE_X,SCORE_Y);
		drawables.add(score);
		
		//space = new Space(player,aliens,LENGTH,WIDTH,bullets,alienBullets,bunkers);
		space = new Space(drawables,LENGTH,WIDTH);
		this.getContentPane().add(space);
		this.addKeyListener(this);
	}
	public static void main(String[] args) {
		Game win = new Game();
		win.setSize(LENGTH,WIDTH);
		win.setVisible(true);
		win.setTitle("Space Invader");
		win.startTimer();
	}
	
	public void startTimer() {
		timer = new Timer(100,this);
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			if(lastShot == 0 || (timeElapsed - lastShot >= 5)) {
			lastShot = timeElapsed;
			System.out.println("lastShot: "+lastShot+" time elapsed: "+timeElapsed);
			Bullet bullet = new Bullet(player.getX(),player.getY(),PLAYER_BULLET_DIRECTION,PLAYER_BULLET_COLOR);
			bullets.add(bullet);
			drawables.add(bullet);
			}
			break;
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		updateTimeElapsed();
		addAlienBullets();
		updateBulletLocations(bullets);
		updateBulletLocations(alienBullets);
		updateAlienLocations();
		killAliens();
		damageBunkers();
		if(isPlayerDead())
			timer.stop();
		space.repaint();
	}
	
	
	public void updateBulletLocations(List<Bullet> bulletList) {
		List<Bullet> removeBullets = new ArrayList<Bullet>();
		for(Bullet b:bulletList) {
			if(b.getY() == 0 || b.getY() >= WIDTH) {
				removeBullets.add(b);
			}
			else {
			b.shootUp();
			}
		}
		bulletList.removeAll(removeBullets);
		drawables.removeAll(removeBullets);
	}
	
	public void updateAlienLocations() {
		int minX = Integer.MAX_VALUE ,maxX=Integer.MIN_VALUE ,minY=Integer.MAX_VALUE ,maxY=Integer.MIN_VALUE;
		for(Alien a:aliens) {
			minX = Math.min(a.getPositionX(), minX);
			maxX = Math.max(a.getPositionX(), maxX);
			minY = Math.min(a.getPositionY(), minY);
			maxY = Math.max(a.getPositionY(), maxY);
		}
		
		if ((minX < 20 || maxX > 710)) {
			alien_direction*=-1;
			if (maxY <= 420)
				isAlienMoveDown = true;
		}
		if (minX >= 20 && maxX <= 710)
			isAlienMoveDown = false;
		
		for(Alien a:aliens) {
			if(isAlienMoveDown)
				a.moveDown();
			Alien.setDir(alien_direction);
			a.moveHorizontal();
		}
	}
	
	public void killAliens() {
		List<Alien> killedAliens = new ArrayList<Alien>();
		List<Bullet> destroyedBullets = new ArrayList<Bullet>();
		
		for(Bullet b:bullets) {
			for(Alien a:aliens) {
				if(b.getX() >= a.getPositionX() && b.getX()<=a.getPositionX()+20 && b.getY() >= a.getPositionY() && b.getY() <= a.getPositionY()+20)
				{
					killedAliens.add(a);
					destroyedBullets.add(b);
					score.updateScore();
				}
			}
		}
		aliens.removeAll(killedAliens);
		bullets.removeAll(destroyedBullets);
		drawables.removeAll(killedAliens);
		drawables.removeAll(destroyedBullets);
	}
	
	public void damageBunkers() {
		List<Bullet> destroyedBullets = new ArrayList<Bullet>();
		List<Bunker> damagedBunkers = new ArrayList<Bunker>();
		for(Bullet b:bullets) {
			for(Bunker bn:bunkers) {
				if(b.getX()>=bn.getX() && b.getX()<=bn.getX()+80 && b.getY()>=bn.getY() && b.getY()<=bn.getY()+20) {
					destroyedBullets.add(b);
					if(bn.isDamaged())
						damagedBunkers.add(bn);
					else
						bn.damage();
				}
			}
		}
		
		bullets.removeAll(destroyedBullets);
		bunkers.removeAll(damagedBunkers);
		drawables.removeAll(destroyedBullets);
		drawables.removeAll(damagedBunkers);
		
		destroyedBullets = new ArrayList<Bullet>();
		damagedBunkers = new ArrayList<Bunker>();
		
		for(Bullet b:alienBullets) {
			for(Bunker bn:bunkers) {
				if(b.getX()>=bn.getX() && b.getX()<=bn.getX()+80 && b.getY()>=bn.getY() && b.getY()<=bn.getY()+20) {
					destroyedBullets.add(b);
					if(bn.isDamaged())
						damagedBunkers.add(bn);
					else
						bn.damage();
				}
			}
		}
		alienBullets.removeAll(destroyedBullets);
		bunkers.removeAll(damagedBunkers);
		drawables.removeAll(destroyedBullets);
		drawables.removeAll(damagedBunkers);
	}
	
	public void addAlienBullets() {
		Random random = new Random();
		int chanceInt = random.nextInt(10);
		int size = aliens.size();
		if(chanceInt > 8)
		{
			for(int i=0;i<1;i++) {
				int alienIndex = random.nextInt(size);
				int x = aliens.get(alienIndex).getPositionX();
				int y = aliens.get(alienIndex).getPositionY();
				Bullet alienBullet = new Bullet(x,y,ALIEN_BULLET_DIRECTION,ALIEN_BULLET_COLOR);
				alienBullets.add(alienBullet);
				drawables.add(alienBullet);
			}
		}
	}
	
	public boolean isPlayerDead() {
		for(Bullet b:alienBullets) {
			if(b.getX() >= player.getX() && b.getX() <= player.getX()+20 && b.getY() >= player.getY() && b.getY() <= player.getY()+20)
				return true;
		}
		return false;
	}
	
	public void updateTimeElapsed() {
		timeElapsed+=1;
	}

}
