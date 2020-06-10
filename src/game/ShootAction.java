package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
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
		ArrayList<Action> actions = new ArrayList<Action>();
		AttackAction attackAction = null;
		if (this.direction == "North") {
			actions = shootNorth(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "North-East") {
			actions = shootNorthEast(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "East") {
			actions = shootEast(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "South-East") {
			actions = shootSouthEast(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "South") {
			actions = shootSouth(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "South-West") {
			actions = shootSouthWest(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "West") {
			actions = shootWest(actor, map, xRange, yRange, currentLocation, actions);
		}
		else if (this.direction == "North-West") {
			actions = shootNorthWest(actor, map, xRange, yRange, currentLocation, actions);
		}
		for (Action action: actions) {
			System.out.println(action.execute(actor, map));
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoots " + direction;
	}
	
	public ArrayList<Action> shootNorth(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		int y = currentLocation.y() - 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 1; x <= currentLocation.x() + 1; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		y -= 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 2; x <= currentLocation.x() + 2; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		y -= 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 3; x <= currentLocation.x() + 3; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	
	public ArrayList<Action> shootEast(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		
		int x = currentLocation.x() + 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 1; y <= currentLocation.y() + 1; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		x += 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 2; y <= currentLocation.y() + 2; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		x += 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 3; y <= currentLocation.y() + 3; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	
	public ArrayList<Action> shootWest(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		int x = currentLocation.x() - 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 1; y <= currentLocation.y() + 1; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		x -= 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 2; y <= currentLocation.y() + 2; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		x -= 1;
		if (yRange.contains(x)) {
			for (int y = currentLocation.y() - 3; y <= currentLocation.y() + 3; y++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	
	public ArrayList<Action> shootSouth(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		int y = currentLocation.y() + 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 1; x <= currentLocation.x() + 1; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		y += 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 2; x <= currentLocation.x() + 2; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		y += 1;
		if (yRange.contains(y)) {
			for (int x = currentLocation.x() - 3; x <= currentLocation.x() + 3; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	
	public ArrayList<Action> shootNorthEast(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		for (int y = currentLocation.y() - 3; y <= currentLocation.y() + 1; y++) {
			for (int x = currentLocation.x(); x <= currentLocation.x() + 4; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	
	public ArrayList<Action> shootNorthWest(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		for (int y = currentLocation.y() - 3; y <= currentLocation.y() + 1; y++) {
			for (int x = currentLocation.x() - 3; x <= currentLocation.x() + 1; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	public ArrayList<Action> shootSouthEast(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		for (int y = currentLocation.y(); y <= currentLocation.y() + 4; y++) {
			for (int x = currentLocation.x(); x <= currentLocation.x() + 4; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}
	public ArrayList<Action> shootSouthWest(Actor actor, GameMap map, NumberRange xRange, NumberRange yRange, Location currentLocation, ArrayList<Action> actions) {
		for (int y = currentLocation.y(); y <= currentLocation.y() + 4; y++) {
			for (int x = currentLocation.x() - 3; x <= currentLocation.x() + 1; x++) {
				Action attackAction = checkLocation(map, xRange, y, x);
				if (attackAction != null) {
					actions.add(attackAction);
				}
			}
		}
		return actions;
	}

	public AttackAction checkLocation(GameMap map, NumberRange xRange, int y, int x) {
		if (xRange.contains(x)) {
			Location checkLocation = new Location(map, x, y);
			if (map.isAnActorAt(checkLocation)) {
				if (map.getActorAt(checkLocation).hasCapability(ZombieCapability.UNDEAD)) {
					return new AttackAction(map.getActorAt(checkLocation));
				}
			}
		}
		return null;
	}
}
