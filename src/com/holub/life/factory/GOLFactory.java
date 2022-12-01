package com.holub.life.factory;

public interface GOLFactory {
	public UI createUI(GameCell gc);
	public GameCell createCell();
}
