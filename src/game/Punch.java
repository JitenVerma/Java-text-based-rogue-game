/**
 * 
 */
package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;

/**
 * @author jiten
 *
 */
public class Punch extends IntrinsicWeapon implements Weapon{
	
	public Punch(int damage, String verb) {
		super(damage, verb);
	}
}
