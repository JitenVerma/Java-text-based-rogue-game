package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

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

		Weapon weapon = actor.getWeapon();
		String result = "";
		
		String verb = weapon.verb();
		if (verb == "Bite") {
			int randInt = new Random().nextInt(10);
			if (randInt > 3) {
				return actor + " misses " + target + ".";
			}
		}
		else if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}
		

		int damage = weapon.damage();
		
		result = result + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		if (verb == "Bite") {
			int heal = 5;
			actor.heal(heal);
			result = result + System.lineSeparator() + actor + " has replenished " + heal + " hitpoints";
		}

		target.hurt(damage);
		
		//When hurting zombies
		if (target.getDisplayChar() == 'Z') {
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
