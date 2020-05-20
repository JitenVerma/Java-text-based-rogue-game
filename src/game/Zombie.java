package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	private String ZombieNoise[] =  {"braaaaaaaains", "blooooooood", "*spooky laughter*"};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		int randInt = new Random().nextInt(10);
		if (randInt < 5) {
			return new Punch(10, "Punches");
		}
		else {
			return new Bite(15, "Bite");
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		int randInt = new Random().nextInt(10);
		if (randInt == 1) {
			int randInt2 = new Random().nextInt(ZombieNoise.length - 1);
			System.out.println(ZombieNoise[randInt2]);
		}
		pickUpWeapons(actions, map);
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	public void pickUpWeapons(Actions actions, GameMap map) {
		for(Action action: actions) {
			if (action instanceof PickUpItemAction) {
				System.out.println(action.execute(this,  map));
			}
		}
	}
}
