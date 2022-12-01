package com.holub.life.factory;


import com.holub.life.Cell;
import com.holub.ui.MenuSite;
import com.holub.life.Clock;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;


public class OriginalUI extends UI {

	
	private static final int  DEFAULT_CELL_SIZE = 8;

	public OriginalUI(GameCell gc) {
		super(gc);
		makeUI();
	}


	public void makeUI() {

		final Dimension PREFERRED_SIZE =
				new Dimension
				(  super.gamecell.getCurrentOuterMostCell().widthInCells() * DEFAULT_CELL_SIZE,
						gamecell.getCurrentOuterMostCell().widthInCells() * DEFAULT_CELL_SIZE
				);
		
		
		setBackground	( Color.white	 );
		setPreferredSize( PREFERRED_SIZE );
		setMaximumSize	( PREFERRED_SIZE );
		setMinimumSize	( PREFERRED_SIZE );
		setOpaque		( true			 );

		addMouseListener					//{=Universe.mouse}
		(	new MouseAdapter()
			{	public void mousePressed(MouseEvent e)
				{	Rectangle bounds = getBounds();
					bounds.x = 0;
					bounds.y = 0;
					gamecell.getCurrentOuterMostCell().userClicked(e.getPoint(),bounds);
					repaint();
				}
			}
		);
		
		MenuSite.addLine( this, "Grid", "Clear",
			new ActionListener()
			{	public void actionPerformed(ActionEvent e)
				{	gamecell.getCurrentOuterMostCell().clear();
					repaint();
				}
			}
		);
		
		MenuSite.addLine			// {=Universe.load.setup}
		(	this, "Grid", "Load",
			new ActionListener()
			{	public void actionPerformed(ActionEvent e)
				{	doLoad();
				}
			}
		);

		MenuSite.addLine
		(	this, "Grid", "Store",
			new ActionListener()
			{	public void actionPerformed(ActionEvent e)
				{	doStore();
				}
			}
		);

		MenuSite.addLine
		(	this, "Grid", "Exit",
			new ActionListener()
			{	public void actionPerformed(ActionEvent e)
		        {	System.exit(0);
		        }
			}
		);
		
		Clock.instance().addClockListener //{=Universe.clock.subscribe}
		(	new Clock.Listener()
			{	public void tick()
				{	if( gamecell.getCurrentOuterMostCell().figureNextState
						   ( Cell.DUMMY,Cell.DUMMY,Cell.DUMMY,Cell.DUMMY,
							 Cell.DUMMY,Cell.DUMMY,Cell.DUMMY,Cell.DUMMY
						   )
					  )
					{	if( gamecell.getCurrentOuterMostCell().transition() )
							refreshNow();
					}
				}
			}
		);

		createMenus();
	}
	
	
	//Clock에 있던거
	public void createMenus()
	{
		// First set up a single listener that will handle all the
		// menu-selection events except "Exit"

		ActionListener modifier =									//{=startSetup}
			new ActionListener()
			{	public void actionPerformed(ActionEvent e)
				{
					String name = ((JMenuItem)e.getSource()).getName();
					char toDo = name.charAt(0);

					if( toDo=='T' )
						Clock.instance().tick();				      // single tick
					else
						Clock.instance().startTicking(   toDo=='A' ? 500:	  // agonizing
										toDo=='S' ? 150:	  // slow
										toDo=='M' ? 70 :	  // medium
										toDo=='F' ? 30 : 0 ); // fast
				}
			};
																	// {=midSetup}
		MenuSite.addLine(this,"Go","Halt",  			modifier);
		MenuSite.addLine(this,"Go","Tick (Single Step)",modifier);
		MenuSite.addLine(this,"Go","Agonizing",	 	  	modifier);
		MenuSite.addLine(this,"Go","Slow",		 		modifier);
		MenuSite.addLine(this,"Go","Medium",	 	 	modifier);
		MenuSite.addLine(this,"Go","Fast",				modifier); // {=endSetup}
	}
	
}
