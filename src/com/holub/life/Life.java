package com.holub.life;

import java.awt.*;
import javax.swing.*;

import com.holub.life.factory.GOLFactory;
import com.holub.life.factory.GameCell;
import com.holub.life.factory.OriginalGameFactory;
import com.holub.life.factory.UI;
import com.holub.ui.MenuSite;

/*******************************************************************
 * An implemenation of Conway's Game of Life.
 *
 * @include /etc/license.txt
 */

public final class Life extends JFrame
{	
	private static JComponent universe;

	public static void main( String[] arguments )
	{	new Life();
	}

	private Life()
	{	super( "The Game of Life. "
					+"(c)2003 Allen I. Holub <http://www.holub.com>");

		// Must establish the MenuSite very early in case
		// a subcomponent puts menus on it.

		MenuSite.establish( this );		//{=life.java.establish}

		setDefaultCloseOperation	( EXIT_ON_CLOSE 		);
		getContentPane().setLayout	( new BorderLayout()	);

		GOLFactory factory = new OriginalGameFactory();
		GameCell gc = factory.createCell();
		UI ui = factory.createUI(gc);

		getContentPane().add( ui, BorderLayout.CENTER); //{=life.java.install}

		pack();
		setVisible( true );
	}
}
