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
		platforms.add(new BoxPlatform(0, 450, 600, 50));
	}
	
	public int checkCollision(int x, int y)
	{
		boolean collides = false;
		int min = 20000;
		for(BoxPlatform obj: platforms)
		{
			int current = obj.collideTest(x, y);
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