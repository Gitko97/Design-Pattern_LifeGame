package com.holub.life.factory;

import com.holub.life.Cell;

public interface GameCell {

	public Cell makeCell();
	
	public Cell getCurrentOuterMostCell();
	
}
