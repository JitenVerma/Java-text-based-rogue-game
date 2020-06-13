package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

public class ShootAction extends Action{
	/**
	 * The direction to fire the shotgun in
	 */
	private String direction;
	
	/**
	 * Constructor
	 * 
	 * @param direction the direction to fire the shotgun in
	 */
	public ShootAction(String direction) {
		this.direction = direction;
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
	
	/**
	 * If the player selected to fire north, shotgun bullets should be fired North West, North and North East 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire East, shotgun bullets should be fired North East, East and South East 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire West, shotgun bullets should be fired North West, West and South West 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire South, shotgun bullets should be fired South West, South and South East 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire North East, shotgun bullets should be fired North, North East and East
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire North West, shotgun bullets should be fired North, North West and West 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire South East, shotgun bullets should be fired South, South East and East 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * If the player selected to fire South West, shotgun bullets should be fired West, South West and South 
	 * for the 90 degree area damage
	 * 
	 * @param actor Actor who is firing the shotgun
	 * @param map The map which the actor belongs to 
	 * @param xRange The x coordinates for the map
	 * @param yRange The y coordinates for the map
	 * @param currentLocation the current location of the actor
	 * @param actions empty Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 * @return Array List of actions to contain AttackAction for each enemy Actor caught within the firezone
	 */
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
	
	/**
	 * Checks the location being checked if an actor exists and if the act is an enemy
	 * 
	 * @param map the map which the shotgun is being fired on
	 * @param xRange set of x coordinates on the map
	 * @param y y coordinate being checked
	 * @param x x coordinate being checked
	 * @return attack action if an enemy Actor exists on the location being checked, null otherwise
	 */
	public AttackAction checkLocation(GameMap map, NumberRange xRange, int y, int x) {
		if (xRange.contains(x)) {
			Location checkLocation = new Location(map, x, y);
			if (map.isAnActorAt(checkLocation)) {
				if (map.getActorAt(checkLocation).hasCapability(ZombieCapability.UNDEAD)) {
					return new AttackAction(map.getActorAt(checkLocation), new Shotgun(30));
				}
			}
		}
		return null;
	}
}
