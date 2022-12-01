package com.holub.life.factory;

import javax.swing.*;

public class ClockGameFactory implements GOLFactory {

	public UI createUI(JFrame mainFrame, GameCell gc) {
		return new ClockUI(mainFrame, gc);
	}

	public GameCell createCell() {
		return new OriginalCell();
	}

}
