package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * 
 * @author jiten
 * When a zombie loses an arm, it falls to the ground as a weapon
 */
public class ZombieArm extends WeaponItem{
	
	/**
	 * Constructor
	 * 
	 */
	public ZombieArm() {
		super("Zombie Arm", '|', 10, "whacks");
	}
}
