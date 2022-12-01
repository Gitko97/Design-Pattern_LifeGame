package com.holub.life.jiho;

import com.holub.io.Files;
import com.holub.life.Cell;
import com.holub.life.Clock;
import com.holub.life.Neighborhood;
import com.holub.life.Resident;
import com.holub.life.Storable;
import com.holub.ui.MenuSite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * The Universe is a mediator that sits between the Swing
 * event model and the Life classes. It is also a singleton,
 * accessed via Universe.instance(). It handles all
 * Swing events and translates them into requests to the
 * outermost Neighborhood. It also creates the Composite
 * Neighborhood.
 *
 * @include /etc/license.txt
 */



public class TestPanel extends JPanel
{


    private JButton leftButton;
    private JButton rightButton;
    private JTextField textField;

    public TestPanel(ActionListener left, ActionListener right)
    {
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

    public void setTextField(int text){
        textField.setText(String.valueOf(text));
    }


    /** Singleton Accessor. The Universe object itself is manufactured
     *  in Neighborhood.createUniverse()
     */


}
