/**
 * 
 */
package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;

/**
 * @author jiten
 * Special intrinsic attack action which allows zombies to Bite
 *
 */
public class Bite extends IntrinsicWeapon implements Weapon{
	
	public Bite(int damage, String verb) {
		super(damage, verb);
	}
}
