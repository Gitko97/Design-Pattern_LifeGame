package com.holub.life.factory;


import com.holub.life.Cell;
import com.holub.life.Storable;
import com.holub.life.Container.CellContainer;
import com.holub.ui.MenuSite;
import com.holub.life.Clock;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class ClockUI extends UI {
    private final CellContainer cellContainer = CellContainer.getInstance();
    public JPanel subPanel;
    private static final int DEFAULT_CELL_SIZE = 8;

    public ClockUI(JFrame mainFrame, GameCell gc) {
        super(gc);
        mainFrame.getContentPane().add(this, BorderLayout.CENTER);
        mainFrame.getContentPane().add(subPanel, BorderLayout.SOUTH);
    }


    public void makeUI() {

        subPanel = new ButtonSubPanel(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clock.instance().startTicking(0);
                Storable previousCell = cellContainer.getPrevious();
                ((ButtonSubPanel) subPanel).setTextField(cellContainer.getCurrentCount());
                if (previousCell == null) {
                    return;
                }
                gamecell.getCurrentOuterMostCell().transfer(previousCell, new Point(0, 0), Cell.LOAD);
                refreshNow();
            }
        }, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clock.instance().startTicking(0);
                Clock.instance().tick();
            }
        });

        final Dimension PREFERRED_SIZE =
            new Dimension
                (gamecell.getCurrentOuterMostCell().widthInCells() * DEFAULT_CELL_SIZE,
                    gamecell.getCurrentOuterMostCell().widthInCells() * DEFAULT_CELL_SIZE
                );


        setBackground(Color.white);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setOpaque(true);

        addMouseListener                    //{=Universe.mouse}
            (new MouseAdapter() {
                 public void mousePressed(MouseEvent e) {
                     Rectangle bounds = getBounds();
                     bounds.x = 0;
                     bounds.y = 0;
                     gamecell.getCurrentOuterMostCell().userClicked(e.getPoint(), bounds);
                     repaint();
                 }
             }
            );

        MenuSite.addLine(this, "Grid", "Clear",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gamecell.getCurrentOuterMostCell().clear();
                    repaint();
                }
            }
        );

        MenuSite.addLine            // {=Universe.load.setup}
            (this, "Grid", "Load",
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        doLoad();
                    }
                }
            );

        MenuSite.addLine
            (this, "Grid", "Store",
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        doStore();
                    }
                }
            );

        MenuSite.addLine
            (this, "Grid", "Exit",
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
            );

        Clock.instance().addClockListener //{=Universe.clock.subscribe}
            (new Clock.Listener() {
                 public void tick() {
                     cellContainer.storeCurrent(gamecell.getCurrentOuterMostCell());
                     ((ButtonSubPanel) subPanel).setTextField(cellContainer.getCurrentCount());
                     if (gamecell.getCurrentOuterMostCell().figureNextState
                         (Cell.DUMMY, Cell.DUMMY, Cell.DUMMY, Cell.DUMMY,
                             Cell.DUMMY, Cell.DUMMY, Cell.DUMMY, Cell.DUMMY
                         )
                     ) {
                         if (gamecell.getCurrentOuterMostCell().transition())
                             refreshNow();
                     }
                 }
             }
            );

        ActionListener modifier =                                    //{=startSetup}
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = ((JMenuItem) e.getSource()).getName();
                    char toDo = name.charAt(0);

                    if (toDo == 'T')
                        Clock.instance().tick();                      // single tick
                    else
                        Clock.instance().startTicking(toDo == 'A' ? 500 :      // agonizing
                            toDo == 'S' ? 150 :      // slow
                                toDo == 'M' ? 70 :      // medium
                                    toDo == 'F' ? 30 : 0); // fast
                }
            };
        // {=midSetup}
        MenuSite.addLine(this, "Go", "Halt", modifier);
        MenuSite.addLine(this, "Go", "Tick (Single Step)", modifier);
        MenuSite.addLine(this, "Go", "Agonizing", modifier);
        MenuSite.addLine(this, "Go", "Slow", modifier);
        MenuSite.addLine(this, "Go", "Medium", modifier);
        MenuSite.addLine(this, "Go", "Fast", modifier); // {=endSetup}
    }


    public class ButtonSubPanel extends JPanel {


        private JButton leftButton;
        private JButton rightButton;
        private JTextField textField;

        public ButtonSubPanel(ActionListener left, ActionListener right) {
            setLayout(new FlowLayout());
            leftButton = new JButton("previous");
            leftButton.addActionListener(left);
            rightButton = new JButton("next");
            rightButton.addActionListener(right);
            textField = new JTextField();
            leftButton.setPreferredSize(new Dimension(100, 50));
            rightButton.setPreferredSize(new Dimension(100, 50));
            textField.setPreferredSize(new Dimension(100, 50));
            textField.setEditable(false);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setText("0");
            add(leftButton);
            add(textField);
            add(rightButton);
        }

        public void setTextField(int text) {
            textField.setText(String.valueOf(text));
        }
    }
}
