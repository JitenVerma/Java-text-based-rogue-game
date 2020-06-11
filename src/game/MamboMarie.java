package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
/** Class representing Mambo Marie and related functionality
 * @author Ayesha
 */
public class MamboMarie extends ZombieActor {
	private GameMap mamboMap;
	private WanderBehaviour behaviours = new WanderBehaviour();
	private ChantBehaviour behaviour = new ChantBehaviour();
	private int turns = 1;
	
	public MamboMarie(GameMap mamboMap) {	
		super("MamboMarie", 'M', 100, ZombieCapability.UNDEAD);
		this.addCapability(ZombieCapability.WITCH);
		this.mamboMap = mamboMap;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		this.turns += 1;
		if (this.turns == 30 & this.isConscious()) {
			MoveActorAction moveActorAction = new MoveActorAction(this.mamboMap.at(1,1), "disappears");
			moveActorAction.execute(this, map);
			System.out.println("Mambo Marie has been removed");
			resetTurns();
			
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
	
	public void resetTurns() {
		this.turns = 0; 
	}
}
