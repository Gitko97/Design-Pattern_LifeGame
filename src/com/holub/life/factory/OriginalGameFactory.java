package com.holub.life.factory;

public class OriginalGameFactory implements GOLFactory {
	
	public UI createUI() {
		return new OriginalUI();
	}
	
	public GameCell createCell() {
		return new OriginalCell();
	}

}
