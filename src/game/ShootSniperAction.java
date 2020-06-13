package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Allows the player to shoot the targetted Actor with a sniper rifle
 * @author jiten
 *
 */
public class ShootSniperAction extends Action{
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
	 * @param timeSpentAiming the Integer value of the number of turns the Player has spent aiming
	 */
	public ShootSniperAction(Actor target, int timeSpentAiming) {
		this.target = target;
		this.timeSpentAiming = timeSpentAiming;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//Decrement ammo by 1
		Player player = (Player)actor;
		player.setAmmunition(player.getAmmunition() - 1);
		
		//Fire the Sniper Rifle
		WeaponItem sniperRifle = null;
		if (this.timeSpentAiming == 0) {
			sniperRifle = new SniperRifle(40);
		}
		else if (this.timeSpentAiming == 1) {
			sniperRifle = new SniperRifle(80);
		}
		else if(this.timeSpentAiming == 2) {
			sniperRifle = new SniperRifle(200);
		}
		AttackAction attackAction = new AttackAction(this.target, sniperRifle);
		System.out.println(attackAction.execute(actor, map));
		
		//Reset aim
		player.setTimeSpentAiming(0);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + this.target + " with sniper rifle";
	}

}
