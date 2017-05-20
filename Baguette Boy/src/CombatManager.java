import java.util.ArrayList;

import processing.core.PApplet;

public class CombatManager implements DestroyListener{
	private ArrayList<Hitbox> hitboxes;
	private ArrayList<Item> items;

	private Manager manager;
	
	
	

	
	public CombatManager(Manager m)
	{
		hitboxes = new ArrayList<Hitbox>();
		items = new ArrayList<Item>();
		manager = m;
		
		MeleeWeapon temp = new MeleeWeapon(4660, 170, "sword");
		items.add(temp);
		
		Potion temp1 = new Potion(1200, 300, 25);
		items.add(temp1);
		
		Item temp2 = new Item("mp", 800, 300);
		items.add(temp2);

	}
	
	public void actObjects()
	{	
		for (int i = hitboxes.size()-1; i>= 0; i--) {
			hitboxes.get(i).act();
		}
		System.out.println(hitboxes.size());
	}
	
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

	
	public void destroy(Object a) {
		for (int i = hitboxes.size()-1; i >= 0; i--) {
			if (a == hitboxes.get(i))
				hitboxes.remove(i);
		}
	}
	
	public void addHitbox(Hitbox a) {
		hitboxes.add(a);
	}
}
