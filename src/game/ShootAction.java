package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

public class ShootAction extends Action{
	protected Location shootLocation;
	protected String direction;
	protected String hotKey;
	
	public ShootAction(Location moveToLocation, String direction, String hotKey) {
		this.shootLocation = shootLocation;
		this.direction = direction;
		this.hotKey = hotKey;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		Location currentLocation = map.locationOf(actor);
		if (this.direction == "North") {
			
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoots " + direction;
	}
	
	public void shootNorth(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation) {
		
	}
}
