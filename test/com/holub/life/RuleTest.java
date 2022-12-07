package com.holub.life;


import com.holub.life.factory.ClockUI;
import com.holub.life.factory.GOLFactory;
import com.holub.life.factory.GameCell;
import com.holub.life.factory.UI;
import com.holub.ui.MenuSite;
import java.awt.BorderLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;

class TestCell extends GameCell {
    Rule stayLiveRule;
    Rule reLiveRule;
    public TestCell(ArrayList<Integer> stayLiveArray, ArrayList<Integer> reLiveArray) {
        stayLiveRule = new DefaultRule(stayLiveArray);
        reLiveRule = new DefaultRule(reLiveArray);
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

    private ArrayList<Integer> stayLiveArray;
    private ArrayList<Integer> reLiveArray;
    TestFactory(ArrayList<Integer> stayLiveArray, ArrayList<Integer> reLiveArray){
        this.stayLiveArray = stayLiveArray;
        this.reLiveArray = reLiveArray;
    }
    public UI createUI(JFrame mainFrame, GameCell gc) {
        return new ClockUI(mainFrame, gc);
    }
    public GameCell createCell() {
        return new TestCell(stayLiveArray,reLiveArray);
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
        JFrame testFrame = new TestRuleFrame(new TestFactory(
                                    new ArrayList<Integer>(Arrays.asList(2,3)),
                                    new ArrayList<Integer>(Arrays.asList(3))));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRule1(){
        JFrame testFrame = new TestRuleFrame(new TestFactory(
            new ArrayList<Integer>(Arrays.asList(2,4,5,6,7,8)),
            new ArrayList<Integer>(Arrays.asList(3))));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}