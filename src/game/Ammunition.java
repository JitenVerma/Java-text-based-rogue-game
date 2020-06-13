package game;

import edu.monash.fit2099.engine.Item;

/**
 * An ammunition box which allows players to pick up ammunition for their ranged weapons
 * 
 * @author jiten
 *
 */
public class Ammunition extends Item{

	public Ammunition() {
		super("Ammunition", 'A', false);
		this.allowableActions.add(new TakeAmmoAction(this));
	}
}
