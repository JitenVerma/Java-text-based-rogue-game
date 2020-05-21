package game;

import java.util.ArrayList;
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
	private int arms;
	private int legs;
	private int minLimbs;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		this.arms = 2;
		this.legs = 2;
		this.minLimbs = 0;
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

	public ArrayList<String> tryToDropLimbs(GameMap map) {
		//Stores all the dropped limbs
		ArrayList<String> limbsDropped = new ArrayList<String>();
		
		//If the zombie has no arms and legs then just return as they cannot drop any more
		if (this.arms == this.minLimbs && this.legs == this.minLimbs) {
			return limbsDropped;
		}
		
		//25% chance when zombie gets hurt he will drop some limbs
		int randInt = new Random().nextInt(100);
		if (randInt < 25) {
			int totalLimbs = this.arms + this.legs;
			
			//Integer to determine how many limbs need to be dropped
			int randInt2 = new Random().nextInt(20);
			
			//Drop 1 limb
			if (randInt2 < 10) {
				limbsDropped = dropLimbs(limbsDropped, map);
			}
			//Drop 2 limbs
			else if (randInt2 < 17) {
				if (totalLimbs < 2) {
					for (int i = 0; i < totalLimbs; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
				else {
					for (int i = 0; i < 2; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
			}
			//Drop 3 limbs
			else if (randInt2 < 19) {
				if (totalLimbs < 3) {
					for (int i = 0; i < totalLimbs; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
				else {
					for (int i = 0; i < 3; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
			}
			//Drop all 4 limbs
			else if (randInt2 == 19) {
				if (totalLimbs < 4) {
					for (int i = 0; i < totalLimbs; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
				else {
					for (int i = 0; i < 4; i++) {
						limbsDropped = dropLimbs(limbsDropped, map);
					}
				}
			}
		}
		return limbsDropped;
	}
	
	public ArrayList<String> dropLimbs(ArrayList<String> limbsDropped, GameMap map) {
		if (this.arms != 0 && this.legs != 0) {
			boolean randBoolean = new Random().nextBoolean();
			if (randBoolean) {
				loseArm(map);
				limbsDropped.add("an arm");
			} else {
				loseLeg(map);
				limbsDropped.add("a leg");
			}
		} else if (this.arms == this.minLimbs) {
			loseLeg(map);
			limbsDropped.add("a leg");
		} else if (this.legs == this.minLimbs) {
			loseArm(map);
			limbsDropped.add("an arm");
		}
		return limbsDropped;
	}
	
	public void loseArm(GameMap map) {
		this.arms -= 1;
		if (this.arms < this.minLimbs) {
			this.arms = this.minLimbs;
		}
	}
	
	public void loseLeg(GameMap map) {
		this.legs -= 1;
		if (this.legs < minLimbs) {
			this.legs = this.minLimbs;
		}
	}
}
