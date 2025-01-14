

import processing.core.PApplet;

/**
 * 
 * @author David McAllister
 *
 */
public class BoxPlatform{
	private int x, y;
	private int width, height;
	private int r,g,b;
	private boolean transparent;

	/*
	 * x and y is upper left corner
	 * width is width from left side to right
	 * height is from top to bottom
	 */
	/**
	 * Constructs a new BoxPlatform object
	 * @param x Top left x
	 * @param y Top left y
	 * @param width Pixel width
	 * @param height Pixel height
	 */
	public BoxPlatform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		r=0;
		g=255;
		b=0;
		transparent=false;
	}
	
	/**
	 * Constructs a new BoxPlatform object
	 * @param x Top left x
	 * @param y Top left y
	 * @param width Pixel width
	 * @param height Pixel height
	 * @param r Red value for drawing (0-255)
	 * @param g Green value for drawing (0-255)
	 * @param b Blue value for drawing (0-255)
	 */
	public BoxPlatform(int x, int y, int width, int height, int r, int g, int b) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r=r;
		this.g=g;
		this.b=b;
		transparent=false;
	}
	
	/**
	 * Constructs a new BoxPlatform object
	 * @param x Top left x
	 * @param y Top left y
	 * @param width Pixel width
	 * @param height Pixel height
	 * @param r Red value for drawing (0-255)
	 * @param g Green value for drawing (0-255)
	 * @param b Blue value for drawing (0-255)
	 * @param transparent Whether the platform should be drawn
	 */
	public BoxPlatform(int x, int y, int width, int height, int r, int g, int b, boolean transparent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r=r;
		this.g=g;
		this.b=b;
		this.transparent=transparent;
		
	}
	
	/**
	 * Returns the x midpoint of the platform
	 * @return Midpoint
	 */
	public int getMiddleX()
	{
		return x+width/2;
	}

	//boolean is important
	/*
	 * in collision checking, have bool change to true if obj collides with any platform (given by this method)
	 * if bool stays false, grounded turns off and gravity re-enabled
	 */
	/**
	 * Checks the collision of the given point with the BoxPlatform Object
	 * @param otherX X Value to check
	 * @param otherY Y Value to check
	 * @param side Whether collision is vertical or horizontal (1 for horizontal, 2 for vertical)
	 * @return Distance to this platform (negative if within platform)
	 */
	public int collideTest(int otherX, int otherY, int side) {
		//check collision
		//check other's location relative to this (ex: y above certain amount and collided:  grounded = true)
		int min = 20000;
		if(otherX>x&&otherX<x+width&&otherY>y&&otherY<y+height)
		{
			if(side==2);
			return -1;
		}
		int current = Math.abs(x-otherX);
		if(side==1)
		{
			if(current<min&&otherY>y&&otherY<y+height)
			{
				min=current;
			}
			current = Math.abs(x-otherX+width);
			if(current<min&&otherY>y&&otherY<y+height)
			{
				min = current;
			}
			//System.out.println(min);
		}
		else
		{
			current = Math.abs(y-otherY);
			if(current<min&&otherX>x&&otherX<x+width)
			{
				min = current;
			}
			current = Math.abs(y-otherY+height);
			if(current<min&&otherX>x&&otherX<x+width)
			{
				min = current;
			}
		}
		
		return min;
	}


	/**
	 * Draws the platform
	 * @param g Initialized PApplet
	 */
	public void draw(PApplet g)
	{
		if(!transparent)
		{
		g.pushStyle();
		g.fill(r,this.g,b);
		if(width<40)
		{
			
			g.fill(139,69,19);
		}
		g.rect(x, y, width, height);
		if(width<40)
		{
			g.fill(40);
			g.rect(x, y, width/4, height);
			g.rect(x+3*width/4, y, width/4, height);

		}
		g.popStyle();
		}
	}

	
	
	/**
	 * Gets the width of the platform
	 * @return width
	 */
	public int getWidth()
	{
		return width;
	}

	


}
