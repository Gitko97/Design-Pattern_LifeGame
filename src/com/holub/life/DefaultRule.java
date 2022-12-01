package com.holub.life;

import java.util.ArrayList;

public class DefaultRule implements Rule {

	private ArrayList<Integer> conditions;
	
	public DefaultRule(ArrayList<Integer> conditions) {
		this.conditions = conditions;
	}
	
	@Override
	public boolean canLive(int numNeighbors) {
		// TODO Auto-generated method stub
		
		for (Integer condition : conditions) {
			if (condition == numNeighbors) {
				return true;
			}
		}
		
		return false;
	}

}
