package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

public class Vehicle extends Item{

	public Vehicle(GameMap townMap) {
		super("Vehicle", 'V', false);
		// TODO Auto-generated constructor stub
		this.allowableActions.add(new MoveActorAction(townMap.at(1,1), "to town"));
	}
}
