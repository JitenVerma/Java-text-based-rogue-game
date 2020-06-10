package game;

import java.util.Random;

import edu.monash.fit2099.engine.Location;
/** Class representing Corpse which extends PortableItem, it represents dead human
 * @author Ayesha
 */
public class Corpse extends PortableItem {

	public Corpse() {
		super("Corpse", '%');
		/**
		 * Random number generator for the number of turns required to convert corpse to zombie
		 */
		this.setReviveTurns(5 + (int)(Math.random() * ((10 - 5) + 1)));
		// TODO Auto-generated constructor stub
	}
	/**
	 * turns store the number of turns since object was initialised
	 */
	private int turns = 0;
	private int reviveTurns = 0;
	@Override
	public void tick(Location location) {
		super.tick(location);
		this.turns++;
		if (this.turns == reviveTurns) {
			location.addActor(new Zombie("ZombieC"));
			location.removeItem(this);
		}
	}
	/**
	 * Sets number of turns. 
	 * @param reviveTurns current number of turns
	 */
	public void setReviveTurns(int reviveTurns) {
		this.reviveTurns = reviveTurns;
	}
}
