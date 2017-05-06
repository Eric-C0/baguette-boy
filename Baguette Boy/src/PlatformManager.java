import java.util.ArrayList;

import entities.*;
import processing.core.PApplet;

public class PlatformManager {

	private ArrayList<BoxPlatform> platforms;
	private Manager manager;
	
	public PlatformManager(Manager m)
	{
		platforms = new ArrayList<BoxPlatform>();
		manager = m;
		platforms.add(new BoxPlatform(-200, 450, 1000, 50));
		platforms.add(new BoxPlatform(250, 350, 100, 100));
		platforms.add(new BoxPlatform(350, 300, 100, 150));
		platforms.add(new BoxPlatform(-20, 0, 40, 600));


	}
	
	public int checkCollision(int x, int y, int side)
	{
		boolean collides = false;
		int min = 20000;
		for(BoxPlatform obj: platforms)
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
		return min;
		
	}
	
	public void draw(PApplet g)
	{
		for(BoxPlatform obj: platforms)
		{
			obj.draw(g);
		}
	}
	
	
	
}
