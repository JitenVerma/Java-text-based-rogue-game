package edu.monash.fit2099.engine;

import java.util.List;
public class Crop extends Item {
	private int turns;
	public Crop(String name, char displayChar, boolean portable,Integer turns) {
		super(name, displayChar, portable);
		this.turns = turns;
		// TODO Auto-generated constructor stub
	}
	//keeps tracks of turns
	@Override
	public void tick(Location currentLocation) {
		//if turns is not 0 then decrease by 1 at each turn
		if(this.turns != 0) {
			this.setTurns(this.getTurns() - 1);
		}
		
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
}