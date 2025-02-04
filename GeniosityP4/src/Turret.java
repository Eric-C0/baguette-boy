import java.awt.Color;

/**
 * 
 * @author Eric Cheng
 *
 */
public class Turret extends Enemy{

	private final int AttTmr = 10;
	private final double bulletSpd = 20;
	private final int dmg = 5;
	private Manager m;
	private final int range = 1000;
	
	/**
	 * Creates a new turret object
	 * @param x X of turret
	 * @param y Y of turret
	 * @param mass Mass value of turret(affects falling physics)
	 * @param width Pixel width of turret
	 * @param height Pixel height of turret
	 * @param m Manager object
	 */
	public Turret(int x, int y, int mass, int width, int height, Manager m) {
		super(x, y, mass, width, height, m, 60);
		this.m=m;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Performs an action based on the turret AI
	 * @param ratio Frame ratio (based on 60 FPS)
	 */
	public void act(double ratio){
		int playerX = super.getManager().getPlayerX();
		int playerY = super.getManager().getPlayerY();
		if(Math.abs(playerX-super.x) <=range && Math.abs(playerY-super.y) <= range) {
			if (attackDelay <= 0) {
				double hyp = Math.sqrt(Math.pow(playerX-super.x, 2) + Math.pow(playerY - super.y, 2));
				double xV = (playerX-super.x)/(hyp/bulletSpd);
				double yV = (playerY-super.y)/(hyp/bulletSpd);


				Bullet test = new Bullet(false, dmg, (float)super.getX(), (float)super.getY()+20f, 10f, 10f, 1000, (float) xV, (float)yV,m, Color.BLACK);
				test.addDestroyListener(super.getManager().getCombat());
				super.getManager().getCombat().addHitbox(test);
				attackDelay = AttTmr;
			}
		}
		attackDelay--;
		
		super.posUpdate(ratio);
	}

}
