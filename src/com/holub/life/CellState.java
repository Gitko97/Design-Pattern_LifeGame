package com.holub.life;

public interface CellState {
	public void changeFutureState();
	public void transitionCurrentState();
	public void changeCurrentState();
}
