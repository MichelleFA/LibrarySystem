package librarySystem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class UserTab  {
	final static String BUTTONPANEL = "History";
    final static String TEXTPANEL = "Book Availibility";
    final static int extraWindowWidth = 100;
    String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
 //GUI declare
    JTabbedPane tabbedPane;
    JPanel UserPane;
    
  //user listpane and manage pane
    JPanel ulpane ;
    JTextField ufield1;
	JTextField ufield2;
	JButton ubutton1;
	JButton ubutton2;
	//book management pane
    JPanel umpane;
	JTextField ufield3;
	JPasswordField ufield4;
	JButton ubutton3;
	

    public void addComponentToPane(Container pane) {
    	// admin pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(850,600));
        tabbedPane.setSize(870,760);
        
        //Create the "cards".
        UserPane = new JPanel() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        
        //history list pane
        ulpane = new JPanel();
        ufield1 = new JTextField("book's name");
		ufield2 = new JTextField("rent date");
		ufield3 = new JTextField("return date");
		ufield1.setEditable(false);
		ufield2.setEditable(false);
		ufield3.setEditable(false);
		
		ulpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		ulpane.add(ufield1);
		ulpane.add(ufield2);
		ulpane.add(ufield3);
		ulpane.setBackground(Color.WHITE);
		
		
		UserPane.add(ulpane,BorderLayout.WEST);
		UserPane.setBackground(Color.WHITE);
 
        JPanel booksPane = new JPanel();
        
        //books avail List pane 
        JPanel blpane = new JPanel();
        JTextField field1 = new JTextField("Harry Potter");
        field1.setEditable(false);
		JTextField field2 = new JTextField("available");
		field2.setEditable(false);
		JButton button1 = new JButton("rent");
		blpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		blpane.setBackground(Color.WHITE);
		blpane.add(field1);
		blpane.add(field2);
		blpane.add(button1);
		//book management pane
        JPanel bmpane = new JPanel();
		bmpane.setLayout(new GridLayout(2,2));
		bmpane.setBackground(Color.WHITE);
		
        booksPane.add(blpane,BorderLayout.WEST);
        booksPane.add(bmpane,BorderLayout.EAST);
        booksPane.setBackground(Color.WHITE);
        
 
        tabbedPane.addTab(BUTTONPANEL, UserPane);
        tabbedPane.addTab(TEXTPANEL, booksPane);
 
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowuUserGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabUser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
       UserTab demo = new UserTab();
        demo.addComponentToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(500,200);
        //create user to operate 
        
    }
    
    
   
    public static void main(String[] args) {
    	
    	}
}

