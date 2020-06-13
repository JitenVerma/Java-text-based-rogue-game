package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * A rnaged weapon for the actor to use
	 */
	protected WeaponItem rangedWeapon;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * Constructor 
	 * 
	 * @param target the Actor to attack
	 * @param rangedWeapon A ranged weapon to be used by the Actor who is attacking
	 */
	public AttackAction(Actor target, WeaponItem rangedWeapon) {
		this.target = target;
		this.rangedWeapon = rangedWeapon;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		//If a ranged weapon has been given then select that
		Weapon weapon;
		if (this.rangedWeapon == null) {
			weapon = actor.getWeapon();
		}
		else {
			weapon = this.rangedWeapon;
		}
		
		String result = "";
		
		//If the weapon is bite, then it's accuracy is 40%
		//For all other weapons accuracy is 50%
		String verb = weapon.verb();
		if (weapon instanceof Bite) {
			int randInt = new Random().nextInt(10);
			if (randInt > 3) {
				return actor + " misses " + target + ".";
			}
		}
		else if (weapon instanceof Shotgun) {
			int randInt = new Random().nextInt(100);
			if (randInt > 75) {
				return actor + " misses " + target + ".";
			}
		}
		else if (weapon instanceof SniperRifle) {
			//100% chance of hitting with sniper rifle
		}
		else if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}
		

		int damage = weapon.damage();
		
		result = result + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		//If weapon is bite, then the user will regain 5 HP
		if (weapon instanceof Bite) {
			int heal = 5;
			actor.heal(heal);
			result = result + System.lineSeparator() + actor + " has replenished " + heal + " hitpoints";
		}

		target.hurt(damage);
		
		//When hurting zombies
		if (target.hasCapability(ZombieCapability.UNDEAD)) {
		//if (target.getDisplayChar() == 'Z') {
			Zombie zombie = (Zombie)target;
			ArrayList<String> limbsDropped = zombie.tryToDropLimbs(map);
			if (limbsDropped.size() != 0) {
				String message = target + " has dropped: ";
				message += limbsDropped.get(0);
				for (int i = 1; i < limbsDropped.size(); i++) {
					message += ", " + limbsDropped.get(i);
				}
				result += System.lineSeparator() + message;
			}
		}
		
		//If the target dies, then drop a corpse on the map
		if (!target.isConscious()) {
			Corpse newCorpse = new Corpse();
			map.locationOf(target).addItem(newCorpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
