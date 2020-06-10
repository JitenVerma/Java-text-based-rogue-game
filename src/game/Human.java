package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;




/**
 * Class representing an ordinary human.
 * 
 * 
 * @author Ayesha
 *
 */
public class Human extends ZombieActor {
	private Behaviour[] behaviours = {
			new WanderBehaviour()
	};
									
	
	/**								
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (this.hitPoints < this.maxHitPoints) {
				pickUpFood(actions,map);
			}
			
			if (action != null) {
				return action;
			}
			
			
		}
		
		return new DoNothingAction();	
	}
				
	/**
	 * This allows us to human to pick up food if it is on same location
	 * and get some hitPoints, after consuming it is removed from inventory
	 * This method only called when human is damaged
	 * @param actions list of allowable actions for human
	 * @param map map of the game
	 */
		public void pickUpFood(Actions actions, GameMap map) {
			for(Action action: actions) {
				if (action instanceof PickUpItemAction) {
					System.out.println(action.execute(this,  map));
					
			for (int i = 0; i < this.getInventory().size();i++) {
				if(this.getInventory().get(i) instanceof HarvestedCrop) {
					this.heal(20);
					this.removeItemFromInventory(this.getInventory().get(i));
					System.out.println(this.name + " consumes harvested crop to heal 20 hitpoints");
					
				}
			}
					
				}
			}
		
		}
		public void endGame(Actions actions, GameMap map) {
			for(int i = 0;i < map.getXRange().max() ;i++) {
				for(int j = 0;j < map.getYRange().max() ;j++) {
					if (!(map.at(i,j).getActor() instanceof Human)){
						System.out.println("Game Over");
						System.exit(0);
					}
				}
			}
		}
}

	





