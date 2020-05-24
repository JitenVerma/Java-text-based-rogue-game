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
	private String zombieNoise[] =  {"braaaaaaaains", "blooooooood", "*spooky laughter*"};
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
			int randInt2 = new Random().nextInt(zombieNoise.length - 1);
			System.out.println(zombieNoise[randInt2]);
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
	
	/**
	 * If a Zombie is standing on an weapon, then the PickUpItemAction would be executed and the 
	 * weapon would be placed into the zombie's inventory. The Zombie can now use the weapon instead 
	 * of punches/bites.
	 * 
	 * @param actions list of possible Actions
	 * @param map the map where the current Zombie is
	 */
	//Method for picking up weapons
	public void pickUpWeapons(Actions actions, GameMap map) {
		for(Action action: actions) {
			if (action instanceof PickUpItemAction) {
				System.out.println(action.execute(this,  map));
			}
		}
	}

	//When attacked, Zombie may drop some limbs
	/**
	 * When a zombie is attacked, there is a chance that it may drop some of its limbs as weapons
	 * for humans/player to pick up and use
	 * 
	 * @param map the map where the current Zombie is
	 * @return limbsDropped an ArrayList of strings of the limbs the zombie has dropped
	 */
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
	
	/**
	 * Causes zombie to lose a limb at random
	 * If a zombie at least has both an arm and a leg, then there exists a 50% chance to an arm and 
	 * 50% chance to lose a leg.
	 * If a zombie does not have an arm or it does not have a leg, then there exists a 100% chance
	 * for the zombie to simply drop the limb it still possesses. 
	 * 
	 * @param limbsDropped an ArrayList of strings of the limbs the zombie has dropped
	 * @param map the map where the current Zombie is
	 * @return limbsDropped an ArrayList of strings of the limbs the zombie has dropped
	 */
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
	
	/**
	 * An arm is one of the limbs that the zombie can lose
	 * 
	 * @param map the map where the current Zombie is
	 */
	public void loseArm(GameMap map) {
		if (this.arms != this.minLimbs) {
			this.arms -= 1;
		}
		
		if (this.arms < this.minLimbs) {
			this.arms = this.minLimbs;
		}
		spawnArm(map);
		accountForLostArm(map);
	}
	
	/**
	 * A leg is one of the limbs that the zombie can lose
	 * 
	 * @param map the map where the current Zombie is
	 */
	public void loseLeg(GameMap map) {
		if (this.legs != this.minLimbs) {
			this.legs -= 1;
		}
		
		if (this.legs < minLimbs) {
			this.legs = this.minLimbs;
		}
		spawnLeg(map);
		accountForLostLeg(map);
	}
	
	/**
	 * When a zombie loses an arm, he has a slight chance to drop weapons he is holding
	 * When a zombie loses both of his arms, he can no longer carry weapons and must drop them all
	 * 
	 * @param map the map where the current Zombie is
	 */
	public void accountForLostArm(GameMap map) {
		String message = this + " has dropped: ";
		if (this.arms == 1) {
			//Change of zombie punching is reduced to 25
			this.chancePunch = 25;
			//Chance that the first weapon in the inventory is dropped
			int randInt = new Random().nextInt(100);
			if (randInt < 10) {
				List<Item> myInventory = this.getInventory();
				if (myInventory.size() > 0) {
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
				else {
					message += "No weapons to drop";
					System.out.println(message);
				}
			}
		}
		else if(this.arms == 0) {
			//Zombie can no longer punch
			this.chancePunch = 0;
			//All items from inventory which are weapons are dropped
			List<Item> myInventory = this.getInventory();
			if (myInventory.size() > 0) {
				boolean firstEntry = true;
				for (int i = 0; i < myInventory.size(); i++) {
					if (myInventory.get(i) instanceof Weapon) {
						if (firstEntry) {
							message += myInventory.get(i);
							firstEntry = false;
						}
						else {
							message += ", " + myInventory.get(i);
							DropItemAction dropAction = new DropItemAction(myInventory.get(i));
							dropAction.execute(this, map);
						}
					}
				}
				System.out.println(message);
			}
			else {
				message += "No weapons to drop";
				System.out.println(message);
			}
		}
	}
	
	/**
	 * When a zombie loses one leg, he is only able to walk at half of his original speed
	 * When a zombie loses both legs, he is now unable to move at all
	 * @param map the map where the current Zombie is
	 */
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
	
	/**
	 * When a Zombie loses an arm, it drops as a weapon for humans/player to pick up and use 
	 * 
	 * @param map the map where the current Zombie is
	 */
	public void spawnArm(GameMap map) {
		//Determine the location to spawn the arm
		Location locationOfArm = getLocationToSpawnLimb(map);
		//Spawn zombie arm at this location
		map.at(locationOfArm.x(), locationOfArm.y()).addItem(new ZombieArm());
	}
	
	/**
	 * When a Zombie loses a leg, it drops as a weapon for humans/player to pick up and use
	 * 
	 * @param map the map where the current Zombie is
	 */
	public void spawnLeg(GameMap map) {
		//Determine the location to spawn leg
		Location locationOfLeg = getLocationToSpawnLimb(map);
		//Spawn zombie leg at this location
		map.at(locationOfLeg.x(), locationOfLeg.y()).addItem(new ZombieLeg());
		
	}
	
	/**
	 * This allows us to determine which locations next to a zombie can an item be dropped to
	 * Multiple items may be dropped onto the one location on the map
	 * When a human/player walks near it, it will have the option to select which weapon to pick up
	 * 
	 * @param map the map where the current Zombie is
	 * @return locationOfLimb the randomly selected location to spawn the dropped limb
	 */
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
