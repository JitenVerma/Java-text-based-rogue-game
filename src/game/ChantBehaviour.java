package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantBehaviour implements Behaviour {

	/** @author Ayesha
	 * A behaviour that returns chantAction
	 */

	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return new ChantAction();
	}

}
