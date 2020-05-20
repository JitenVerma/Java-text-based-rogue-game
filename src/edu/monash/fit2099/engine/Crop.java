package edu.monash.fit2099.engine;

import java.util.List;
public class Crop extends Item {
	private int turns;
	public Crop(String name, char displayChar, boolean portable,Integer turns) {
		super(name, displayChar, portable);
		this.turns = turns;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tick(Location currentLocation) {
		this.setTurns(this.getTurns() - 1);
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
}