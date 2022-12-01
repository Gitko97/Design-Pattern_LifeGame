package com.holub.life.factory;

import com.holub.life.Cell;
import com.holub.life.Neighborhood;
import com.holub.life.Resident;

public abstract class GameCell {

	public Cell  	outermostCell;
	private static final int  DEFAULT_GRID_SIZE = 8;

	public GameCell(){
		outermostCell = new Neighborhood
			(	DEFAULT_GRID_SIZE,
				new Neighborhood
					(	DEFAULT_GRID_SIZE,
						new Resident()
					)
			);
	}

	public Cell getCurrentOuterMostCell() {
		return outermostCell;
	}
}
