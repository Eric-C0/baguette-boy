import java.awt.event.KeyEvent;

import processing.core.PApplet;

//import entities.*;

public class Manager {

	private GObjectManager gObjects;
	private PlatformManager platforms;
	private Player player;
	
	public Manager()
	{
		gObjects = new GObjectManager(this);
		platforms = new PlatformManager(this);
		player = new Player(200, 50, 10, 100, 100, this);
		
		
	}
	
	public int checkPlatformCollision(int x, int y)
	{
		return platforms.checkCollision(x, y);
	}
	
	public void draw(PApplet g)
	{
		gObjects.drawObjects(g);
		player.draw(g);
		platforms.draw(g);
	}
	
	public void act(double ratio)
	{
		player.act(ratio);
	}
	
	public void mouseWheelMoved(int i)
	{
		player.getWheelMove(i);
	}
	
	public void sendKeyCode(KeyEvent e)
	{
		player.sendKeyCode(e);
	}
	
}