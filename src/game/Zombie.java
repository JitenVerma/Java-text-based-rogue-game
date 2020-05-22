package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Weapon;

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
	private int chancePunch = 50;
	private boolean canMove = true;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		this.arms = 2;
		this.legs = 2;
		this.minLimbs = 0;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		int randInt = new Random().nextInt(100);
		if (randInt < chancePunch) {
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
		//Zombie makes random noise
		//randInt
		int randInt = new Random().nextInt(10);
		if (randInt == 1) {
			int randInt2 = new Random().nextInt(ZombieNoise.length - 1);
			System.out.println(ZombieNoise[randInt2]);
		}
		//Zombie picks up items and weapons he is standing near
		pickUpWeapons(actions, map);
		
		//Restrict zombie movements if he is crippled
		if (this.legs == 1) {
			if (this.canMove) {
				this.canMove = false;
			}
			else {
				this.canMove = true;
			}
		}
		
		for (Behaviour behaviour : behaviours) {
			if (this.canMove) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
			else if (this.canMove == false) {
				if(!(behaviour instanceof WanderBehaviour)) {
					if(!(behaviour instanceof HuntBehaviour)) {
						Action action = behaviour.getAction(this, map);
						if (action != null)
							return action;
					}
				}
			}
		}
		return new DoNothingAction();	
	}
	
	
	//Method for picking up weapons
	public void pickUpWeapons(Actions actions, GameMap map) {
		for(Action action: actions) {
			if (action instanceof PickUpItemAction) {
				System.out.println(action.execute(this,  map));
			}
		}
	}

	//When attacked, zombie may drop some limbs
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
	
	//Drop limbs
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
	
	//Drop arm
	public void loseArm(GameMap map) {
		this.arms -= 1;
		if (this.arms < this.minLimbs) {
			this.arms = this.minLimbs;
		}
		spawnArm(map);
		accountForLostArm(map);
	}
	
	//Drop leg
	public void loseLeg(GameMap map) {
		this.legs -= 1;
		if (this.legs < minLimbs) {
			this.legs = this.minLimbs;
		}
		spawnLeg(map);
		accountForLostLeg(map);
	}
	
	public void accountForLostArm(GameMap map) {
		String message = this + " has dropped: ";
		if (this.arms == 1) {
			this.chancePunch = 25;
			int randInt = new Random().nextInt(100);
			if (randInt < 10) {
				List<Item> myInventory = this.getInventory();
				for (int i = 0; i < myInventory.size(); i++) {
					if (myInventory.get(i) instanceof Weapon) {
						message += myInventory.get(i);
						DropItemAction dropAction = new DropItemAction(myInventory.get(i));
						dropAction.execute(this, map);
						System.out.println(message);
						return;
					}
				}
			}
		}
		else if(this.arms == 0) {
			this.chancePunch = 0;
			List<Item> myInventory = this.getInventory();
			boolean firstEntry = true;
			for (int i = 0; i < myInventory.size(); i++) {
				if (myInventory.get(i) instanceof Weapon) {
					if (firstEntry) {
						message += myInventory.get(i);
						firstEntry = false;
					}
					message += ", " + myInventory.get(i);
					DropItemAction dropAction = new DropItemAction(myInventory.get(i));
					dropAction.execute(this, map);
				}
			}
			System.out.println(message);
		}
	}
	
	public void accountForLostLeg(GameMap map) {
		if (this.legs == 1) {
			//Must be true as it will be inverted in playTurn
			this.canMove = true;
		}
		else if (this.legs == 0) {
			//Must be false and will remain false
			this.canMove = false;
		}
	}
	
	public void spawnArm(GameMap map) {
		//Determine the location to spawn the arm
		Location locationOfArm = getLocationToSpawnLimb(map);
		//Spawn zombie arm at this location
		map.at(locationOfArm.x(), locationOfArm.y()).addItem(new ZombieArm());
	}

	public void spawnLeg(GameMap map) {
		//Determine the location to spawn leg
		Location locationOfLeg = getLocationToSpawnLimb(map);
		//Spawn zombie leg at this location
		map.at(locationOfLeg.x(), locationOfLeg.y()).addItem(new ZombieLeg());
		
	}
	
	public Location getLocationToSpawnLimb(GameMap map) {
		//Determine possible exits for the zombie
		Location location = map.locationOf(this);
		List<Exit> exits =  location.getExits();
		
		//Iterate through exits and collate all locations of exits
		ArrayList<Location> possibleLocations = new ArrayList<Location>();
		for (Exit exit: exits) {
			Location destination = exit.getDestination();
			possibleLocations.add(destination);
		}
		//Select the location of one exit at random
		int randInt = new Random().nextInt(possibleLocations.size());
		Location locationOfLimb = possibleLocations.get(randInt);
		return locationOfLimb;
	}
}
