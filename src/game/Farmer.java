package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**@author Ayesha
 * Class representing the Farmer.
 */
public class Farmer extends Human {
	/**
	 * Constructor.
	 * @param name the name of the Farmer
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
		
	}
	private Behaviour[] behaviours = {
			new SowBehaviour(),
			new FertiliseNewBehaviour(),
			new HarvestBehaviour(),
			new WanderBehaviour()
	};

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}




