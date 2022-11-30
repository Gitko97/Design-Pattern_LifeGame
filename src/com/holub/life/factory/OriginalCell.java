package com.holub.life.factory;

import com.holub.life.Cell;

import com.holub.life.Neighborhood;
import com.holub.life.Resident;



public class OriginalCell implements GameCell {
	
	private 		Cell  	outermostCell;

	private static final int  DEFAULT_GRID_SIZE = 8;
	
	public Cell makeCell()
	{	
		outermostCell = new Neighborhood
						(	DEFAULT_GRID_SIZE,
							new Neighborhood
							(	DEFAULT_GRID_SIZE,
								new Resident()
							)
						);
		
		return outermostCell;
	}
}
