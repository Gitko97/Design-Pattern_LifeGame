package com.holub.life.factory;

import javax.swing.*;

public interface GOLFactory {
	public UI createUI(JFrame mainFrame, GameCell gc);
	public GameCell createCell();
}
