

import processing.core.PApplet;

/*
 * Properties of this object:
 *   -affected by gravity
 *   -collides with all platforms
 */
public abstract class GravitisedObj {
	public static final int GRAVITY_POWER = 2;

	public final int mass;
	protected int x, y;
	protected int width, height;
	protected double xSpeed, ySpeed;
	public boolean grounded;
	private Manager m;

	/*
	 * x and y from top left corner of box
	 * width is left side to right side
	 * height is top to bottom
	 */
	public GravitisedObj(int x, int y, int mass, int width, int height, Manager m) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.mass = mass;
		this.width = width;
		this.height = height;
		xSpeed = 0;
		ySpeed = 0;
		grounded = false;
	}

	public void posUpdate(double ratio) {
		
		int horizontalPlatform = 500;
		if(xSpeed>0)
		{
			horizontalPlatform = Math.min(m.checkPlatformCollision(x+width, y+90*height/100,2),m.checkPlatformCollision(x+width, y+5*height/100,2));
			if(horizontalPlatform<0)
			{
				x+=-1;
			}
		}
		else if(xSpeed<0)
		{
			horizontalPlatform = -Math.min(m.checkPlatformCollision(x, y+90*height/100,2),m.checkPlatformCollision(x, y+5*height/100,2));
			if(horizontalPlatform>0)
			{
				x+=1;
			}
		}
		if(Math.abs(horizontalPlatform)<Math.abs(xSpeed))
		{
			if(horizontalPlatform>5)
			x+=horizontalPlatform;
			xSpeed = 0;
		}
		else
		x += xSpeed*ratio;
		
		int closestPlatform = Math.min(m.checkPlatformCollision(x+5*width/100, y+height,2),m.checkPlatformCollision(x+95*width/100, y+height,2)); //1 - horizontal, 2-vertical
		if(closestPlatform<0)
		{
			ySpeed = 0;
			grounded = true;
		}
		else
		{
			//System.out.println(ySpeed);
			if(closestPlatform<ySpeed*ratio)
			{
				
				y+=closestPlatform;
				grounded= true;
				ySpeed = 0;
				//grounded = true;
			}
			else
			{
				//System.out.println(ySpeed);
				y += ySpeed*ratio;
			}
		}
		if(m.checkPlatformCollision(x+width/2, y+height,2)>2)
		{
			grounded=false;
		}
		
		if (!grounded)
			ySpeed += GRAVITY_POWER*ratio;
	}
	public abstract void draw(PApplet g);
	
	public abstract void act(double ratio);

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

}
