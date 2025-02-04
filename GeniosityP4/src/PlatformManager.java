import java.util.ArrayList;

import processing.core.PApplet;

/**
 * 
 * @author David McAllister
 *
 */
public class PlatformManager implements DestroyListener{

	private ArrayList<BoxPlatform> platforms;
	private Manager manager;
	private boolean finalPlatforms;

	
	/**
	 * Constructs the world built of platforms
	 * @param m - General manager
	 */
	public PlatformManager(Manager m)
	{
		finalPlatforms = false;
		platforms = new ArrayList<BoxPlatform>();
		manager = m;
		platforms.add(new BoxPlatform(-200, 450, 1170, 300));
		//platforms.add(new BoxPlatform(1040, 350, 100, 100));
		//platforms.add(new BoxPlatform(350, 300, 100, 150));
		//platforms.add(new BoxPlatform(300,0,400,100));
		//platforms.add(new RoundedPlatform(450, 150, 100, 150));

		platforms.add(new BoxPlatform(-20, 0, 40, 600));
		platforms.add(new BoxPlatform(1020,450,400,300));

		int tempY = 710;
		int x = 600;
		int tempX = 600;
		int width = 400;
		int height = 300;
		for(int i=0;i<40;i++)
		{
			double angle = Math.acos(-1*(x+(double)width/2-tempX)/width);
			tempX+=10;
			//System.out.println(angle);
			platforms.add(new BoxPlatform(tempX, (int)(tempY-Math.sin(angle)*height), 20, 32+(int)(Math.sin(angle)*height)));

		}
		
		x=1220;
		tempY=450;
		for(int i=0;i<60;i++)
		{
			platforms.add(new BoxPlatform(x+20*i,tempY-2*i,40,400));
		}
		
		//platforms.add(new BoxPlatform(tempX+800, 410, 200,500));
		platforms.add(new BoxPlatform(tempX+1400, 600, 1000,130));
		platforms.add(new BoxPlatform(tempX+2390, 330, 3000,400));
		platforms.add(new BoxPlatform(tempX+3400, 300, 600,30,160,160,160));
		platforms.add(new BoxPlatform(5350, 200, 100,800));//temp

		platforms.add(new BoxPlatform(5800, 500, 590,800));

		platforms.add(new BoxPlatform(6500, 500, 600,30));
		platforms.add(new BoxPlatform(7300, 700, 600,30));
		platforms.add(new BoxPlatform(8100, 900, 600,30));
		platforms.add(new BoxPlatform(9000, 1150, 2000,30));






		


	}


	/**
	 * Checks a points collision with all platforms.
	 * @param otherX - Point's x
	 * @param otherY - Point's y
	 * @param side- 1 if horizontal detection, 2 if vertical detection
	 * @param isPlayer - whether the object calling this method is a player
	 * @return Distance to nearest side, negative if colliding
	 */
	public int checkCollision(int x, int y, int side,boolean isPlayer)
	{
		if(!this.finalPlatforms&&manager.getPlayerX()>9000)
		{
			finalPlatforms=true;
			platforms.add(new BoxPlatform(8500, 0, 500,1500,0,0,0,true));
			platforms.add(new BoxPlatform(10590, 0, 500,1600,0,0,0,true));

		}
		boolean collides = false;
		int min = 20000;
		for(BoxPlatform obj: platforms)
		{
			if(Math.abs(x-obj.getMiddleX())<obj.getWidth())
			{
				int current = obj.collideTest(x, y, side);
				if(current<min)
				{
					min=current;
				}
				if(current<0)                                                                                                                                                                                       
				{
					return -1;
				}
			}
		}
		if(side==2&&min<1000&&isPlayer)
		{
			//System.out.println(min+y);
			manager.sendPlatformY(min+y);
		}
		return min;

	}

	/**
	 * Draws all platforms in the world
	 * @param g
	 */
	public void draw(PApplet g)
	{
		for(BoxPlatform obj: platforms)
		{
			obj.draw(g);
		}
	}

	
	/**
	 * Destroys the platform matching the parameter.
	 */
	public void destroy(Object a) {
		for (int i = platforms.size()-1; i >= 0; i--) {
			if (a == platforms.get(i))
				platforms.remove(i);
		}
	}



}
