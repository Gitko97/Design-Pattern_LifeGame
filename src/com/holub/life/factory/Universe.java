package com.holub.life.factory;

public class Universe {
	
	GOLFactory factory = new OriginalGameFactory();
	
	factory.createUI();
	factory.createCell();

}
