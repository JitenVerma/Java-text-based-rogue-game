package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class TakeAmmoAction extends Action{
	private Ammunition ammunition;
	
	public TakeAmmoAction(Ammunition ammunition) {
		this.ammunition = ammunition;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Player player = (Player)actor;
		player.setAmmunition(player.getAmmunition() + 15);
		map.locationOf(actor).removeItem(this.ammunition);
		return menuDescription(player);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " picks up 15 ammunition";
	}
}
