package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * @author jiten
 * When a zombie loses a leg, it falls to the ground as a weapon
 */
public class ZombieLeg extends WeaponItem{
	
	/**
	 * Constructor
	 */
	public ZombieLeg() {
		super("Zombie Leg", '[', 15, "whacks");
	}

}
