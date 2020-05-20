package edu.monash.fit2099.engine;

import game.Behaviour;
import game.Farmer;

/**
 * Special Action that allows Farmers to drop ferlise.
 */
//Call execute when location of farmer and crop same
public class FertiliseNewAction extends Action {
	protected Crop crop;
	public FertiliseNewAction(Crop crop) {
		this.crop = crop;
	}
	
	@Override
	public String execute(Actor farmer, GameMap map) {
		// TODO Auto-generated method stub 
		crop.setTurns(Math.max(crop.getTurns() - 10, 0));
		return menuDescription(farmer);
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "farmer fertilises crop";
	}

}