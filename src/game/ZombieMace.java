package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * 
 * @author jiten
 * Weapon which can be crafted if a player possesses a Zombie Leg
 */
public class ZombieMace extends WeaponItem{
	/**
	 * Constructor
	 */
	public ZombieMace() {
		super("Zombie Mace", 'M', 35, "Smashes");
	}

}
