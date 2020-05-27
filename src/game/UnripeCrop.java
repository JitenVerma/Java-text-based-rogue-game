package game;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/** @author Ayesha
 * Class representing UnripeCrop, extends Item as it is non-portable.
 */
public class UnripeCrop extends Item {
	/**
	 * The number of turns in which a crop ripens
	 */
	int turns = 20;
	/**
	 * Constructor.
	 */
	public UnripeCrop() {
		super("Unripecrop",'U', false);
	}
	/**
	 * Fertilise the crop by decreasing the number of turns by time
	 */
	public void executeFertilize() {
		this.turns -= 10;
	}
	/**
	 * Get number of turns. 
	 * @return unmodifiable version of turns.
	 */
	public int getTurns() {
		return this.turns;
	}
	
	@Override
	public void tick(Location currentLocation) {
		this.turns -= 1;
		//System.out.println(this.turns);
		if (this.turns <= 0) {
			currentLocation.addItem(new RipeCrop());
			currentLocation.removeItem(this);
		}
	}

}

