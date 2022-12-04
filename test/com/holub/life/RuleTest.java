package com.holub.life;


import com.holub.life.factory.ClockUI;
import com.holub.life.factory.GOLFactory;
import com.holub.life.factory.GameCell;
import com.holub.life.factory.UI;
import com.holub.ui.MenuSite;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;

class TestCell extends GameCell {

    Rule stayLiveRule;
    Rule reLiveRule;
    public TestCell(int stayLive1, int stayLive2, int reLive) {
        stayLiveRule = new DefaultRule(new ArrayList<Integer>(Arrays.asList(stayLive1, stayLive2)));
        reLiveRule = new DefaultRule(new ArrayList<Integer>(Arrays.asList(reLive)));
        outermostCell = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(stayLiveRule, reLiveRule)
                    )
            );
    }
}

class TestFactory implements GOLFactory {
    public UI createUI(JFrame mainFrame, GameCell gc) {
        return new ClockUI(mainFrame, gc);
    }
    public GameCell createCell() {
        return new TestCell(2,3,3);
    }
}

class TestRuleFrame extends JFrame {

    GOLFactory factory;
    GameCell gc;
    UI ui;

    TestRuleFrame(GOLFactory golFactory)
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

public class RuleTest {
    @Test
    void testRule(){
        JFrame testFrame = new TestRuleFrame(new TestFactory());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}