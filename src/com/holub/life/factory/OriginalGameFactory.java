package com.holub.life.factory;

public class OriginalGameFactory implements GOLFactory {

	public UI createUI(GameCell gc) {
		return new OriginalUI(gc);
	}
	

	public GameCell createCell() {
		return new OriginalCell();
	}

}
