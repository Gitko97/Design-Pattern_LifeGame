package com.holub.life.factory;

import java.awt.Graphics;

import javax.swing.JFrame;
import com.holub.life.Cell;

public class UI extends JFrame {
	//JFrame으로 변경
	
	private GameCell gamecell;
	public Cell outermostCell = gamecell.makeCell();
	
	
	public void makeUI() {};
	
	//Clock에 있던거
	public void createMenus() {};

	
	public void paint(Graphics g) {};
	
	public void refreshNow() {};
	

	public void load() {};
	public void store() {};
	
}
