package com.holub.life.factory;

public class Universe {
	public void Universe() {
		
		GOLFactory factory = new OriginalGameFactory();
		
		factory.createUI();
		factory.createCell();
		
	}
}
