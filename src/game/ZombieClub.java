package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * @author jiten
 * Weapon which can be crafted if a player possesses a Zombie Arm
 */
public class ZombieClub extends WeaponItem{
	/**
	 * Constructor
	 */
	public ZombieClub() {
		super("Zombie Club", 'C', 25, "Smashes");
	}
}