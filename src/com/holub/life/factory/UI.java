package com.holub.life.factory;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.holub.io.Files;
import com.holub.life.Cell;
import com.holub.life.Clock;
import com.holub.life.Storable;

public abstract class UI extends JPanel {
	
	public GameCell gamecell;
	
	public void UI(GameCell gc) {
		this.gamecell = gc;
	}
	
	public void makeUI() {}
	
	//Clock에 있던거
	public void createMenus() {}

	
	public void paint(Graphics g)
	{
		Rectangle panelBounds = getBounds();
		Rectangle clipBounds  = g.getClipBounds();

		// The panel bounds is relative to the upper-left
		// corner of the screen. Pretend that it's at (0,0)
		panelBounds.x = 0;
		panelBounds.y = 0;
		gamecell.getCurrentOuterMostCell().redraw(g, panelBounds, true);		//{=Universe.redraw1}
	}
	
	
	public void refreshNow()
	{	SwingUtilities.invokeLater
		(	new Runnable()
			{	public void run()
				{	Graphics g = getGraphics();
					if( g == null )		// Universe not displayable
						return;
					try
					{
						Rectangle panelBounds = getBounds();
						panelBounds.x = 0;
						panelBounds.y = 0;
						gamecell.getCurrentOuterMostCell().redraw(g, panelBounds, false); //{=Universe.redraw2}
					}
					finally
					{	g.dispose();
					}
				}
			}
		);
	}
	

	public void doLoad() {
		try
		{
			FileInputStream in = new FileInputStream(
			   Files.userSelected(".",".life","Life File","Load"));

			Clock.instance().stop();		// stop the game and
			gamecell.getCurrentOuterMostCell().clear();			// clear the board.

			Storable memento = gamecell.getCurrentOuterMostCell().createMemento();
			memento.load( in );
			gamecell.getCurrentOuterMostCell().transfer( memento, new Point(0,0), Cell.LOAD );

			in.close();
		}
		catch( IOException theException )
		{	JOptionPane.showMessageDialog( null, "Read Failed!",
					"The Game of Life", JOptionPane.ERROR_MESSAGE);
		}
		repaint();		
	}
	
	
	public void doStore() {
		try
		{
			FileOutputStream out = new FileOutputStream(
				  Files.userSelected(".",".life","Life File","Write"));

			Clock.instance().stop();		// stop the game

			Storable memento = gamecell.getCurrentOuterMostCell().createMemento();
			gamecell.getCurrentOuterMostCell().transfer( memento, new Point(0,0), Cell.STORE );
			memento.flush(out);

			out.close();
		}
		catch( IOException theException )
		{	JOptionPane.showMessageDialog( null, "Write Failed!",
					"The Game of Life", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
