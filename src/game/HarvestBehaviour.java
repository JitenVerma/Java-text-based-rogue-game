package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**@author Ayesha
 * A class that figures out when the ripe crop can be harvested by farmer
 */
public class HarvestBehaviour implements Behaviour {



	@Override
	public Action getAction(Actor actor, GameMap map) {
	
		ArrayList<Location> possibleLocations = possibleLocation(actor, map);
		if (possibleLocations.size() > 0) {
				return new HarvestAction(possibleLocations.get(0));	
		}
		return null;
	}
	/**
	 * This method checks possible locations in which a crop can be sown
	 * @param actions list of allowable actions for human
	 * @param map map of the game
	 */
	private ArrayList<Location> possibleLocation(Actor actor, GameMap map) {
	ArrayList<Location> validLocations = new ArrayList<>();
	Location actorLocation = map.locationOf(actor);
	int xA = actorLocation.x();
	int yA = actorLocation.y();
	for (int x = -1; x < 2; x++) {
		for (int y = -1; y < 2; y++) {
			Location newLocation = map.at(xA + x, yA + y);
			if (newLocation.canActorEnter(actor)) {
				if (newLocation.getItems().size() != 0) {
					for(int i = 0; i < newLocation.getItems().size();i++) {
						if(RipeCrop.class.isInstance(newLocation.getItems().get(i))) {
							newLocation.removeItem(newLocation.getItems().get(i));
							validLocations.add(newLocation);
						}
					}
						
					
				}
			}
		}
	}
	return validLocations;
	}
}
	
	
