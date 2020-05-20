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
	protected int heal;
	protected int accuracy;
	
	public Punch(String verb, int damage, int heal, int accuracy) {
		super(damage, verb);
		this.heal = heal;
		this.accuracy = accuracy;
	}
	
	public int heal() {
		return heal;
	}
	
	public int accuracy() {
		return accuracy;
	}
}
