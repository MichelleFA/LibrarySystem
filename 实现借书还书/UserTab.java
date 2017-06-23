import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    
    UserTab current;
    
  //user listpane and manage pane
    JPanel ulpane ;
    JPanel []bhitem;
    JPanel booksPane;
    JTextField []ufield1;//book title
	JTextField []ufield2;//rent date
	JTextField []ufield3;//return date
	
	
	//book management pane
	
    JPanel blpane;
	JButton []button1;//rent a book
	JButton []button2;//return a book
	JTextField []field1;//book name
	JTextField []field2;//book avai
	JPanel []blitem;
	

	
	


// According to the loginName, get the current user object;
	public static User getCurrentUser(){
//		Library.setUsers();
		for(int i = 0; i < Library.Users.size();i++){
			if(Library.Users.get(i).getUserName().equals(Library.getLogInUserName())){
				System.out.println("get users");
				return Library.Users.get(i);
			}
		}
		System.out.println("dont get users");
		return null;
	}
	
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
        
      
        
        
        booksPane = new JPanel();//whole book panel
        //set the book list 
        
        blitem = new JPanel[Library.Books.size()];//for each record panel;
    	button1 = new JButton[Library.Books.size()];
    	button2 = new JButton[Library.Books.size()];
    	field1 = new JTextField[Library.Books.size()];
    	field2 = new JTextField[Library.Books.size()];
    	blpane = new JPanel();
 
        blpane.setLayout(new GridLayout(Library.Books.size(),1));
        for(int i = 0; i < Library.Books.size(); i++){
        	blitem[i] = new JPanel();
            button1[i] = new JButton("rent");
            button2[i] = new JButton("return");
        	field1[i] = new JTextField(Library.Books.get(i).getName());
	        field1[i].setEditable(false);


	        if(!Library.Books.get(i).getifRendted()){
	        field2[i] = new JTextField("available");
     		field2[i].setEditable(false);
            blitem[i].add(field1[i]);
            blitem[i].add(field2[i]);
            blitem[i].add(button1[i]);
     		}
	        else {
	        field2[i] = new JTextField("not available");
	        field2[i].setEditable(false);
            blitem[i].add(field1[i]);
            blitem[i].add(field2[i]);
            if(Library.Transactions.get(i).getUsername().equals(getCurrentUser().getUserName())&&
            		Library.Transactions.get(i).getBookTitle().equals(Library.Books.get(i).getName())){
            	blitem[i].add(button2[i]);//users name from transaction equals current userame and at the same time, the bookname 
            	//from  transaction equals current bookname, this is the user who rent the book, and he have access to return this
            	//book , otherwise, other people can have acess to return this book.
            }
            	
	        }
     		blitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
     		blpane.add(blitem[i]);
     		
     		button1[i].addActionListener(new RentActionListener());
     		 
     		button2[i].addActionListener(new ReturnActionListener());

        }
        
        //book management pane
        JPanel bmpane = new JPanel();
		bmpane.setLayout(new GridLayout(2,2));
		
        booksPane.add(blpane,BorderLayout.WEST);
        booksPane.add(bmpane,BorderLayout.EAST);
        
 
        tabbedPane.addTab(BUTTONPANEL, UserPane);
        tabbedPane.addTab(TEXTPANEL, booksPane);
        
        pane.add(tabbedPane, BorderLayout.CENTER);
        
        ufield1 = new JTextField[getCurrentUser().getHistory().size()];//book title
    	ufield2 = new JTextField [getCurrentUser().getHistory().size()];//rent date
    	ufield3 = new JTextField [getCurrentUser().getHistory().size()];//return date
    	bhitem = new JPanel [getCurrentUser().getHistory().size()];
    	//set the history
        	ulpane = new JPanel();
        	for(int i= 0; i < getCurrentUser().getHistory().size(); i++){
        	bhitem[i] = new JPanel();
        	ufield1[i] = new JTextField(Library.Transactions.get(i).getBookTitle());
    		ufield2[i] = new JTextField("rent date:" + getCurrentUser().getHistory().get(i).getRentdate().toString());
    		ufield1[i].setEditable(false);
    		ufield2[i].setEditable(false); 	
    		ulpane.setLayout(new GridLayout(Library.Transactions.size(),3));
    		bhitem[i].add(ufield1[i]);
    		bhitem[i].add(ufield2[i]);
    		
     		if(getCurrentUser().getHistory().get(i).getReturndate()!=null){
    			ufield3[i] = new JTextField("return date:"+getCurrentUser().getHistory().get(i).getReturndate().toString());
    			ufield3[i].setEditable(false);
    			ulpane.add(ufield3[i]);
    		}
    		else{
    			ufield3[i] = new JTextField("not return yet");
    			ufield3[i].setEditable(false);
    			ulpane.add(ufield3[i]);
    		}
    		bhitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
    		ulpane.add(bhitem[i]);
    		UserPane.add(ulpane,BorderLayout.WEST);
    		System.out.println("creat one record!");
        }

    }
    
    class RentActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int current = Arrays.asList(button1).indexOf(e.getSource());
			System.out.println(field1[current].getText());
				System.out.println(field2[current].getText());
				getCurrentUser().rentBook(field1[current].getText());
			
		}
    	
    }
    
    class ReturnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int current = Arrays.asList(button2).indexOf(e.getSource());
			System.out.println(field1[current].getText());
				System.out.println(field2[current].getText());
				getCurrentUser().returnBook(field1[current].getText());
			
		}
    	
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
        
        //create user to operate 
        
    }
    
   
}

