import java.awt.Color;
import java.awt.Font;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * 
 * @author David McAllister, Eric Cheng
 *
 */
public class FinalBoss extends Enemy{

	private int jumpCounter;
	private Manager m;
	private int stunTimer;
	public static PImage baguette;

	public static final double bulletSpd = 30;
	public static final int ATT_DMG = 10;
	public static final int ATT_DELAY = 2000;
	private long lastHit;

	//private Player player;

	/**
	 * Creates a new FinalBoss object
	 * @param x X of boss
	 * @param y Y of boss
	 * @param mass Mass value of boss(affects falling physics)
	 * @param width Pixel width of boss
	 * @param height Pixel height of boss
	 * @param m Manager object
	 */
	public FinalBoss(int x, int y, int mass, int width, int height, Manager m)
	{
		super(x, y, 2, width, height, m, 360);
		this.m=m;
		stunTimer=200;

		lastHit = 0;
	}


	/**
	 * Acts the FinalBoss
	 * @param ratio Frame ratio (Based on 60 FPS)
	 */
	public void act(double ratio)
	{
		if (hp > 180) {
			if (lastHit + ATT_DELAY < System.currentTimeMillis()) {
				int playerX = super.getManager().getPlayerX();
				int playerY = super.getManager().getPlayerY();
				double hyp = Math.sqrt(Math.pow(playerX-super.x, 2) + Math.pow(playerY - super.y, 2));
				double xV = (playerX-super.x)/(hyp/bulletSpd);
				double yV = (playerY-super.y)/(hyp/bulletSpd);


				Hitbox test = new Hitbox(false, ATT_DMG, (float)super.getX(), (float)super.getY(), width, height, 70, (float) xV, (float)yV, null);
				test.addDestroyListener(super.getManager().getCombat());
				super.getManager().getCombat().addHitbox(test);

				lastHit = System.currentTimeMillis();
			}
		} else {
			if (lastHit + 2*ATT_DELAY/3 < System.currentTimeMillis()) {
				double xT = 0; 
				double yT = 0;
				for (int i = 0; i < 8; i++) {
					xT = 20*Math.cos((double)i*Math.PI/4.0) + super.x;
					yT = -20*Math.sin((double)i*Math.PI/4.0) + super.y;

					double hyp = Math.sqrt(Math.pow(xT-super.x, 2) + Math.pow(yT - super.y, 2));
					double xV = (xT-super.x)/(hyp/bulletSpd);
					double yV = (yT-super.y)/(hyp/bulletSpd);


					Hitbox test = new Hitbox(false, ATT_DMG, (float)super.getX(), (float)super.getY()+height-width - 10, width, width, 1000, (float) xV/10f, (float)yV/10f);
					test.addDestroyListener(super.getManager().getCombat());
					super.getManager().getCombat().addHitbox(test);
				}

				if (Math.abs(m.getPlayerX()-super.x) < 600) {
					int ran = (int) (Math.random()*20);
					if (ran == 0) {
						m.getgObjects().addObj(new Turret(super.x + 100, super.y -100, 10, 100, 100, m));
					}
				}

				lastHit = System.currentTimeMillis();
			}
		}

		if((stunTimer>0&&stunTimer%10==0)||super.grounded)
		{
			if(xSpeed>0)
			{
				xSpeed--;
			}
			else if(xSpeed<0)
			{
				xSpeed++;
			}
		}
		if(m.getPlayerX()>8900)
		{
			stunTimer--;

			if(stunTimer<1)
			{
				if(hp>200)
				{
					if(jumpCounter<3)
					{
						stunTimer=90;
						ySpeed=-16;
						jumpCounter++;
						if(x>9800)
						{
							xSpeed=-10;
						}
						else if(x<9200)
						{
							xSpeed=10;
						}
						else
						{
							if(Math.random()>0.5)
							{
								xSpeed=10;
							}
							else
							{
								xSpeed=-10;
							}
						}
					}
					else
					{
						stunTimer=20;
						jumpCounter=0;
					}
				}
				else
				{
					if(jumpCounter<3)
					{
						stunTimer=90;
						ySpeed=-20;
						jumpCounter++;
						if(x>9800)
						{
							xSpeed=-15;
						}
						else if(x<9200)
						{
							xSpeed=15;
						}
						else
						{
							if(Math.random()>0.5)
							{
								xSpeed=15;
							}
							else
							{
								xSpeed=-15;
							}
						}
					}
					else
					{
						stunTimer=90;
						jumpCounter=0;
					}
				}



			}

		}






		super.posUpdate(ratio);
	}
	//System.out.println("acting");


	/**
	 * Draws the boss
	 * @param g Initialized PApplet
	 */
	public void draw(PApplet g)
	{

		g.text("Geoff: " + hp,x, y-20);
		if (hp <200 && hp > 180) {
			g.pushStyle();
			g.textSize(36);
			g.text("You've done well, but now you've made me angry!", x-350, y-50);
			g.popStyle();
		}
		else if (hp <180 && hp > 140) {
			g.pushStyle();
			g.textSize(36);
			g.text("Take this!", x-60, y-50);
			g.popStyle();
		}
		//g.fill(255,0,0);
		//g.rect(x, y, width, height);
		//System.out.println("draw");
		g.image(baguette, x, y,width,height);

	}

	/**
	 * Gets the current health of the boss
	 * @return Integer representation of health
	 */
	public int getHealth()
	{
		return hp;
	}




}
