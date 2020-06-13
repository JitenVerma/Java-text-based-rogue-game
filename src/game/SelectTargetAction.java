package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action which allows the player to select a target to attack with the sniper rifle
 * @author jiten
 *
 */
public class SelectTargetAction extends Action {
	/**
	 * The actor to be attacked
	 */
	private Actor target;
	
	/**
	 * Constructor 
	 * 
	 * @param target the actor to be attacked
	 */
	public SelectTargetAction(Actor target) {
		this.target = target;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Select " + getTarget();
	}
	
	/**
	 * A getter for target
	 * @return The target who will be attacked as an Actor
	 */
	public Actor getTarget() {
		return this.target;
	}

}
