import java.util.ArrayList;

import processing.core.PApplet;

/**
 * 
 * @author Eric Cheng
 *
 */
public class CombatManager implements DestroyListener{
	private ArrayList<Hitbox> hitboxes;
	private ArrayList<Item> items;

	private Manager manager;
	
	
	

	/**
	 * Creates a new CombatManager to manage combat interactions
	 * @param m General Manager that holds the CombatManager
	 */
	public CombatManager(Manager m)
	{
		hitboxes = new ArrayList<Hitbox>();
		items = new ArrayList<Item>();
		manager = m;
	}
	
	/**
	 * Adds items to the CombatManager
	 */
	public void addItems() {
		
		//System.out.println("items");
		MeleeWeapon temp = new MeleeWeapon(4660, 170, "sword");
		items.add(temp);
		
		Potion temp1 = new Potion(1200, 300, 25);
		items.add(temp1);
		
		RangedWeapon temp2 = new RangedWeapon(800, 300, "gun");
		items.add(temp2);
		
		Potion potion2 = new Potion(6800, 350, 50);
		items.add(potion2);
	}
	
	/**
	 * Calls the act method of all objects regulated by the CombatManager
	 */
	public void actObjects()
	{	
		for (int i = hitboxes.size()-1; i>= 0; i--) {
			hitboxes.get(i).act();
		}
	}
	
	/**
	 * Draws all objects held by the CombatManager
	 * @param g Initialized PApplet drawing surface
	 */
	public void drawObjects(PApplet g)
	{
		
		for(Hitbox obj: hitboxes)
		{
			obj.draw(g);
		}
		for (Item itm: items) {
			itm.draw(g);
		}
	}
	
	/**
	 * Checks collision for all hitboxes
	 */
	public void checkHits() {
		ArrayList<Damagable> list = manager.getgObjects().getDmg();
		
//		if (hitboxes.size() >0)
//		hitboxes.get(0).collide(manager.getPlayer());
		for (int i = hitboxes.size()-1; i >= 0; i--) {
			Hitbox a = hitboxes.get(i);
			if (!a.getFriendly()) {
				a.collide(manager.getPlayer());
			} else {
				for (int j = list.size()-1; j>= 0; j--) {
					a.collide(list.get(j));
				}
			}
		}
		
		for (int i = items.size()-1; i>= 0; i--) {
			if (items.get(i).collide(manager.getPlayer())) {
				items.remove(i);
			}
		}
	}

	
	/**
	 * Destroys a given hitbox object
	 */
	public void destroy(Object a) {
		for (int i = hitboxes.size()-1; i >= 0; i--) {
			if (a == hitboxes.get(i))
				hitboxes.remove(i);
		}
	}
	
	/**
	 * Adds a new hitbox to the CombatManager
	 * @param a Hitbox to add
	 */
	public void addHitbox(Hitbox a) {
		hitboxes.add(a);
	}
}
