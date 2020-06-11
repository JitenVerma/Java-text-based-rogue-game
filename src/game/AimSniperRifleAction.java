package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class AimSniperRifleAction extends Action{
	private Actor target;
	private int timeSpentAiming;
	
	
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
