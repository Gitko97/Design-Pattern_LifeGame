package com.holub.life;


import com.holub.life.factory.ClockGameFactory;
import com.holub.life.factory.GOLFactory;
import com.holub.life.factory.GameCell;
import com.holub.life.factory.OriginalGameFactory;
import com.holub.life.factory.UI;
import com.holub.ui.MenuSite;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import org.junit.jupiter.api.Test;


class TestFrame extends JFrame{

    GOLFactory factory;
    GameCell gc;
    UI ui;

    TestFrame(GOLFactory golFactory)
    {
        super( "The Game of Life. "
        +"(c)2003 Allen I. Holub <http://www.holub.com>");
        MenuSite.establish( this );		//{=life.java.establish}
        setDefaultCloseOperation	( EXIT_ON_CLOSE 		);
        getContentPane().setLayout	( new BorderLayout()	);
        factory = golFactory;
        gc = factory.createCell();
        ui = factory.createUI(this ,gc);
        pack();
        setVisible( true );
    }
}

class FactoryPatternTest {

    @Test
    void makeOriginalUI(){
        JFrame testFrame = new TestFrame(new OriginalGameFactory());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void makeClockUI(){
        JFrame testFrame = new TestFrame(new ClockGameFactory());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}