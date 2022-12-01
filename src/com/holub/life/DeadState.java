package com.holub.life;

public class DeadState implements CellState {

	private final Resident cell;
	private final Rule rule;
	
	public DeadState(Resident cell, Rule rule) {
		this.cell = cell;
		this.rule = rule;
	}
	
	@Override
	public void changeFutureState() {
		// TODO Auto-generated method stub
		if (rule.canLive(cell.getNumNeighbors())) {
			cell.setFutureState(cell.liveState);
		} else {
			cell.setFutureState(cell.deadState);
		}
	}

	@Override
	public void transitionCurrentState() {
		// TODO Auto-generated method stub
		cell.setCurrentState(cell.getFutureState());
	}

	@Override
	public void changeCurrentState() {
		// TODO Auto-generated method stub
		cell.setCurrentState(cell.liveState);
	}

}
