package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.SowAction;
import java.util.ArrayList;

/** @author Ayesha
 * A class that figures out when the ripe crop can be harvested by farmer
 */
public class SowBehaviour implements Behaviour {



	@Override
	public Action getAction(Actor actor, GameMap map) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(100);
		ArrayList<Location> validLocations = validGroundAroundActor(actor, map);
		if (validLocations.size() > 0) {
			if (randomNumber >= 0 && randomNumber <= 33) {
				return new SowAction(validLocations.get(0));
			}
		}
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * This method checks possible location in which a crop can be sown 
	 * @param actions list of allowable actions for human
	 * @param map map of the game
	 */
	private ArrayList<Location> validGroundAroundActor(Actor actor, GameMap map) {
		ArrayList<Location> validLocations = new ArrayList<>();
		Location locationOfActor = map.locationOf(actor);
		int xA = locationOfActor.x();
		int yA = locationOfActor.y();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				Location newLocation = map.at(xA + x, yA + y);
				if (newLocation.canActorEnter(actor)) {
					if (newLocation.getItems().size() == 0) {
						if (x != 0 && y != 0) {
							validLocations.add(newLocation);
						}
					}
				}
			}
		}
		
		return validLocations;
		
	}

}
