package com.holub.life.factory;

public class OriginalGameFactory implements GOLFactory {
	
	public GameCell gc;
	public UI ui;

	public UI createUI() {
		this.ui = new OriginalUI();
		ui.UI(gc);
		return ui;
	}
	

	public GameCell createCell() {
		this.gc = new OriginalCell();
		return gc;
	}

}
