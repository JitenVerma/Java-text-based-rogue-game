package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/** @author Ayesha
 * Special Action that allows Mambo Marie to chant which adds 5 zombies at random locations.
 */
public class ChantAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= 5; i++) {
			int x = (int)(Math.random() * map.getXRange().max());
			int y = (int)(Math.random() * map.getYRange().max());
			Location newLocation = map.at(x, y);
			if (!(newLocation.canActorEnter(actor))) {
				x = (int)(Math.random() * map.getXRange().max());
				y = (int)(Math.random() * map.getYRange().max());
				
			}
			map.at(x, y).addActor(new Zombie("MarioZombie"));
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " chants";
	}

}
