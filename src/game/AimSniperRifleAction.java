package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Allows the player to spend a turn aiming at the target to increase the damage they inflict
 * @author jiten
 *
 */
public class AimSniperRifleAction extends Action{
	/**
	 * The actor to be attacked
	 */
	private Actor target;
	/**
	 * The number of turns Player has spent aiming
	 */
	private int timeSpentAiming;
	
	/**
	 * Constructor 
	 * 
	 * @param target the Actor to be attacked
	 * @param timeSpentAiming the Integer value of the number of turns Player spent aiming
	 */
	public AimSniperRifleAction(Actor target, int timeSpentAiming) {
		this.target = target;
		this.timeSpentAiming = timeSpentAiming;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Player player = (Player)actor;
		player.setTimeSpentAiming(timeSpentAiming + 1);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " aims at " + this.target + " with a sniper rifle";
	}

}
