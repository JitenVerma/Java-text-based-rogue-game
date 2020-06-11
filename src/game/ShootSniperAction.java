package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

public class ShootSniperAction extends Action{
	
	private Actor target;
	private int timeSpentAiming;
	
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
		attackAction.execute(actor, map);
		
		//Reset aim
		player.setTimeSpentAiming(0);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + this.target + " with sniper rifle";
	}

}
