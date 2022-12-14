package com.holub.life.factory;

import javax.swing.*;

public class OriginalGameFactory implements GOLFactory {

	public UI createUI(JFrame mainFrame, GameCell gc) {
		return new OriginalUI(mainFrame, gc);
	}
	

	public GameCell createCell() {
		return new OriginalCell();
	}

}
