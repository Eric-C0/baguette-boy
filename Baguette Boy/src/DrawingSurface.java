

//import davidmc.shapes.Rectangle;
//import davidmc.shapes.RegularPolygon;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;


public class DrawingSurface extends PApplet implements MouseWheelListener{

	//private	RegularPolygon geoff;
	//private DemoObject demo;
	//private BoxPlatform dooder;
	//private GObjectManager objM;
	private long lastTime = 0;
	private Camera cam;
	//private Player donkey;
	private Manager m;
	private int targetHP;
	private int currentHP;
	private Main mainClass;
	private boolean playerStatus;


	public DrawingSurface(Main mainClass) {
		//demo = new DemoObject();
		//dooder = new BoxPlatform(300, 0, 600, 20);
		//objM = new GObjectManager();
		//donkey = new Player(250, 300, 5, 100, 100);
		m= new Manager();
		this.mainClass = mainClass;
		currentHP=100;
		targetHP=100;
		cam = new Camera(width, height);
		runSketch();
		playerStatus = true;

	}


	public void setup()
	{

	}


	public void draw() { 
		noStroke();
		fill(210);
		this.background(30);


		scale(cam.getZoom());
		cam.sendPlayerPos(m.getPlayerX(), m.getPlatformY());
		this.translate(cam.getX(), cam.getY());

		m.draw(this);
		long time = System.currentTimeMillis()-lastTime;
		double ratio = 16.0/time;
		this.translate(-cam.getX(),-cam.getY());

		text(""+60.0/ratio,20,20);
		fill(255,0,0);
		targetHP=m.getHP();
		if(targetHP<currentHP)
		{
			currentHP-=2;
		}
		this.rect(1250, 20, 3*currentHP, 80);
		this.pushStyle();
		fill(0);
		PFont pf = new PFont();

		pf=this.createFont("Arial", 25);
		textFont(pf);
		//Font font = new Font("Arial", Font.PLAIN, 60);
		if(currentHP<100)
			this.text(""+currentHP, 1250+3*currentHP-35, 92);
		this.popStyle();

		ArrayList<Item> items = m.getPlayer().getInv();
		if (!items.isEmpty()) {
			if (items.size()==1)
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 175);
			else if (items.size() == 2) {
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 175);
				if (m.getPlayer().getInvSpot() == 1)
					items.get(0).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
				else
					items.get(1).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
			} else {
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 175);
				items.get((m.getPlayer().getInvSpot() - 1 + items.size()) % items.size()).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
				items.get((m.getPlayer().getInvSpot() + 1) % items.size()).display(this, super.displayWidth/2 - 300 + 200, 80, 0.6f, 95);
			}
		}


		//System.out.println(m.getHP());

		//text(""+60.0/ratio,m.getPlayerX()-450, m.getPlatformY()-550);
		//popMatrix();
		m.act(ratio);
		cam.act(ratio);
		//demo.act(time);
		//this.objM.actObjects(ratio);
		//donkey.act(ratio);
		lastTime = System.currentTimeMillis();
		//demo.draw(this);
		stroke(0);
		noFill();
		//geoff = new RegularPolygon(300,300,5,50);

		//geoff.draw(this);

		if (playerStatus && m.getPlayer().getHP() <= 0) {
			playerStatus = false;
			mainClass.changePanelTo("death");
		}
		if (playerStatus && m.getPlayer().getY() >= 2000) {
			playerStatus = false;
			mainClass.changePanelTo("death");
		}
	}

	public void sendSize(int width, int height)
	{
		cam.setZoom((float)(height/900.0));
		//size(width, height);
	}


	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hi");
		if(e.getWheelRotation()<0)
		{
			m.mouseWheelMoved(1);
			//cam.changeZoom(1);
		}
		else
		{
			m.mouseWheelMoved(-1);
			//cam.changeZoom(-1);
		}

	}


	@Override
	public void keyTyped() {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyPressed() {
		//System.out.println("keypressed");
		m.sendKeyCode(key);
		if(key=='j')
		{
			cam.leftMove(true);;
		}
		if(key=='l')
		{
			cam.rightMove(true);
		}
		if(key=='i')
		{
			cam.upMove(true);
		}
		if(key=='k')
		{
			cam.downMove(true);
		}
		//System.out.println("key pressed");

	}


	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		m.releaseKeyCode(key);
		if(key=='j')
		{
			cam.leftMove(false);;
		}
		if(key=='l')
		{
			cam.rightMove(false);
		}
		if(key=='i')
		{
			cam.upMove(false);
		}
		if(key=='k')
		{
			cam.downMove(false);
		}
	}




}










