

//import davidmc.shapes.Rectangle;
//import davidmc.shapes.RegularPolygon;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * 
 * @author David McAllister, Eric Cheng
 *
 */
public class DrawingSurface extends PApplet implements MouseWheelListener{

	//private	RegularPolygon geoff;
	//private DemoObject demo;
	//private BoxPlatform dooder;
	//private GObjectManager objM;
	private long lastTime = 0;
	private Camera cam;
	private static PImage sky;
	//private Player donkey;
	private Manager m;
	private int targetHP;
	private int currentHP;
	private Main mainClass;
	private boolean playerStatus;
	private int temp;
	private boolean reset;
	private float prevFPS;
	
	private boolean sprites;
	


	public DrawingSurface(Main mainClass) {
		//demo = new DemoObject();
		//dooder = new BoxPlatform(300, 0, 600, 20);
		//objM = new GObjectManager();
		//donkey = new Player(250, 300, 5, 100, 100);
		m= new Manager();
		currentHP=100;
		targetHP=100;
		cam = new Camera(width, height,m);
		playerStatus = true;
		reset = false;
		temp=0;
		prevFPS=60;

		this.mainClass = mainClass;

		sprites = true;
		
		runSketch();


	}

	public void init() {
		reset = true;
		sprites = true;
	}


	public void setup()
	{
		m.setUpSprites(this);
		sky=this.loadImage("sky.jpg");

	}

	private void drawSky()
	{
		for(int i=0;i<52;i++)
		{
			fill(96+3*i,46+i,146-2*i);
			//96,46,146
			//244,98,36
			//image(sky,0,0,1920,1080);
			//fill(i*15,255-i*5,255-i*4);
			//fill(100,100,255);
			//tint(50,50);
			rect(0,i*27,2400,27);
		}



	}


	public void draw() { 

		if (reset) {
			m= new Manager();
			currentHP=100;
			targetHP=100;
			cam = new Camera(width, height,m);
			playerStatus = true;
			reset = false;
			m.setUpSprites(this);

			
			
			sendSize(mainClass.getWindowWidth(), mainClass.getWindowHeight());
		}
		
		if(sprites) {
			m.getCombat().addItems();
			sprites = false;
		}

		this.background(30);
		noStroke();
		drawSky();
		//System.out.println("Health: " +m.getBossHealth());
		
		scale(cam.getZoom());
		cam.sendPlayerPos(m.getPlayerX(), m.getPlatformY());
		this.translate(cam.getX(), cam.getY());

		m.draw(this);
		long time = System.currentTimeMillis()-lastTime;
		double ratio = 16.0/time;
		this.translate(-cam.getX(),-cam.getY());
		fill(30);
		int temp2 = (int)(600*ratio);
		float currentFPS =(float)(temp2/10.0); 
		text("FPS: "+(currentFPS+prevFPS)/2,20,20);
		prevFPS=currentFPS;
		fill(255,0,0);
		targetHP=m.getHP();
		if(targetHP<currentHP)
		{
			currentHP--;
		}
		else if (targetHP > currentHP) {
			currentHP++;
		}
		this.rect(1250, 20, 3*currentHP, 80);
		this.pushStyle();
		fill(0);
		PFont pf = new PFont();

		pf=this.createFont("Arial", 25);
		textFont(pf);
		//Font font = new Font("Arial", Font.PLAIN, 60);
		//if(currentHP<100)
			this.text(""+currentHP, 1240+3*currentHP-35, 92);
		this.popStyle();

		ArrayList<Item> items = m.getPlayer().getInv();
		if (!items.isEmpty()) {
			if (items.size()==1)
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 220);
			else if (items.size() == 2) {
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 220);
				if (m.getPlayer().getInvSpot() == 1)
					items.get(0).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
				else
					items.get(1).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
			} else {
				items.get(m.getPlayer().getInvSpot()).display(this, super.displayWidth/2 - 300, 100, 1, 220);
				items.get((m.getPlayer().getInvSpot() + 1) % items.size()).display(this, super.displayWidth/2 - 300 - 200, 80, 0.6f, 95);
				items.get((m.getPlayer().getInvSpot() - 1 + items.size()) % items.size()).display(this, super.displayWidth/2 - 300 + 200, 80, 0.6f, 95);
			}
		}


		//System.out.println(m.getHP());

		//text(""+60.0/ratio,m.getPlayerX()-450, m.getPlatformY()-550);
		//popMatrix();
		//translate(-cam.getX(),-cam.getY())
		m.act(ratio);
		cam.act(ratio);
		//demo.act(time);
		//this.objM.actObjects(ratio);
		//donkey.act(ratio);
		lastTime = System.currentTimeMillis();
		//demo.draw(this);
		stroke(0);
		noFill();
		
		if(m.getBossHealth()<1)
		{
			temp++;
		}
		//System.out.println("Temp: " + temp);
		if(temp<255)
		{
		fill(0,temp);
		}
		else
		{
			fill(0,255);
			
		}
		
		rect(0,0,2500,2000);

		fill(255,temp-255);
		if(temp>255)
		{
			this.textAlign(this.CENTER);
			//this.textAlign();

			text("thank you.",760,420);
		}
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
			m.mouseWheelMoved(-1);
			//cam.changeZoom(1);
		}
		else
		{
			m.mouseWheelMoved(1);
			//cam.changeZoom(-1);
		}

	}


	@Override
	public void keyTyped() {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyPressed() {
//		if (key == CODED) {
//			m.sendSpecialKeyCode(keyCode);
//		}
//		else {
//			m.sendKeyCode(key);
//			if(key=='j')
//			{
//				cam.leftMove(true);;
//			}
//			if(key=='l')
//			{
//				cam.rightMove(true);
//			}
//			if(key=='i')
//			{
//				cam.upMove(true);
//			}
//			if(key=='k')
//			{
//				cam.downMove(true);
//			}
//		}
		m.sendSpecialKeyCode(keyCode);
	}


	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		m.releaseSpecialKeyCode(keyCode);
		
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










