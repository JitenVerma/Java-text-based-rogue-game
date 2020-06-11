package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/** Class representing Mambo Marie and related functionality
 * @author Ayesha
 */
public class MamboMarie extends ZombieActor {

	public MamboMarie() {
		
		super("MamboMarie", 'M', 100, ZombieCapability.UNDEAD);
		// TODO Auto-generated constructor stub
	}
	private WanderBehaviour behaviours = new WanderBehaviour();
	private ChantBehaviour behaviour = new ChantBehaviour();
	private int turns = 1;

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		this.turns += 1;
		if (this.turns == 30 & this.isConscious()) {
			map.removeActor(this);
			System.out.println("Mambo Marie has been removed");
			
		}
		if (this.turns % 10 == 0) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
					return action;
		}
		Action action = behaviours.getAction(this, map);
		if (action != null)
				return action;
		return new DoNothingAction();
			
	}
	

}
