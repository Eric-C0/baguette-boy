import processing.core.PApplet;
import processing.core.PFont;

/**
 * 
 * @author David McAllister
 *
 */
public class Tutorial {

	private Manager m;
	private int displayTimer;
	private int tutorialNumber;

	/**
	 * Constructs a new tutorial manager for level 1
	 * @param man Manager to handle interactions
	 */
	public Tutorial(Manager man)
	{
		m=man;
		displayTimer = 0;
		tutorialNumber =0;
	}

	/**
	 * Makes the tutorial act based on game variables
	 */
	public void act()
	{
		displayTimer--;
		if(m.getPlayerX()>10&&m.getPlayerX()<400&&tutorialNumber!=1)
		{
			//System.out.println("tut 1");
			tutorialNumber=1;
			displayTimer = 400;
		}
		else if(m.getPlayerX()>2400&&m.getPlayerX()<3200&&tutorialNumber!=2)
		{
			//System.out.println("tut 2");
			tutorialNumber=2;
			displayTimer = 700;
		}
		else if(m.getPlayerX()>4600&&m.getPlayerX()<4800&&tutorialNumber==2)
		{
			tutorialNumber=3;
			displayTimer=250;
		}
		else if(m.getPlayerX()>5800&&m.getPlayerX()<5900&&tutorialNumber!=4)
		{
			tutorialNumber=4;
			displayTimer=300;
		}
		else if(m.getPlayerX()>9000&&tutorialNumber!=5)
		{
			tutorialNumber=5;
			displayTimer=500;
		}



	}

	/**
	 * Draws tutorial reminders on screen
	 * @param g Initialized PApplet
	 */
	public void draw(PApplet g)
	{
		g.pushStyle();
		PFont pf = new PFont();
		g.fill(255);
		pf=g.createFont("Helvetica Neue", 35);
		g.textFont(pf);
		g.textAlign(g.CENTER);
		//g.textWidth(2);
		if(displayTimer<255)
		{
			g.fill(255,displayTimer);
		}
		if(displayTimer>0)
		{
			if(tutorialNumber==1)
			{
				g.text("Use WASD keys to move, Spacebar To Use Items, Scroll to Change Items", 800, 150);
			}
			else if(tutorialNumber==2)
			{
				g.text("Use your environment to your advantage", 2900, 300);
			}
			else if(tutorialNumber==3)
			{
				g.text("Press spacebar to wield thy sword!", 4700, 100);
			}
			else if(tutorialNumber==4)
			{
				g.text("Now set forth and prove yourself in battle!", 6000, 100);
			}
			else if(tutorialNumber==5)
			{
				g.text("Good luck!", 9800, 800);
			}
		}
		g.popStyle();
	}



}
