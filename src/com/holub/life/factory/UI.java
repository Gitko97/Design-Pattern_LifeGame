package com.holub.life.factory;

import java.awt.Graphics;

import javax.swing.JFrame;
import com.holub.life.Cell;

public class UI extends JFrame {
	//JFrame���� ����
	
	private GameCell gamecell;
	public Cell outermostCell = gamecell.makeCell();
	
	
	public void makeUI() {};
	
	//Clock�� �ִ���
	public void createMenus() {};

	
	public void paint(Graphics g) {};
	
	public void refreshNow() {};
	

	public void load() {};
	public void store() {};
	
}
