package com.holub.life.jiho;

import com.holub.ui.MenuSite;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*******************************************************************
 * An implemenation of Conway's Game of Life.
 *
 * @include /etc/license.txt
 */

public final class LifeJiho extends JFrame
{
	private static JComponent universe;

	public static void main( String[] arguments )
	{	new LifeJiho();
	}

	private LifeJiho()
	{	super( "The Game of Life. "
					+"(c)2003 Allen I. Holub <http://www.holub.com>");

		// Must establish the MenuSite very early in case
		// a subcomponent puts menus on it.
		MenuSite.establish( this );		//{=life.java.establish}
		setDefaultCloseOperation	( EXIT_ON_CLOSE 		);
		getContentPane().setLayout	( new BorderLayout()	);
		getContentPane().add( UniverseJiho.instance(), BorderLayout.CENTER); //{=life.java.install}
		getContentPane().add(UniverseJiho.instance().subPanel,BorderLayout.SOUTH);
		pack();
		setVisible( true );
	}
}
