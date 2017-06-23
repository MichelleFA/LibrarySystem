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

//import AdminTab.LogOutListener;
public class UserTab  {
	final static String BUTTONPANEL = "History";
    final static String TEXTPANEL = "Book Availibility";
    final static String ISRENTING = "isRenting";
    final static String RENTBOOK = "recentlyAddBook";
    final static int extraWindowWidth = 100;
    String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
 //GUI declare
    //top level GUI
    JFrame frame;
    JTabbedPane tabbedPane;
    JPanel UserPane;
    JPanel booksPane;
    JPanel isRentingPane;
    JPanel rentlyBook;
    UserTab current;
    
  //book history pane
    JPanel ulpane ;
    JPanel []bhitem;
    JTextField []ufield1;//book title
	JTextField []ufield2;//rent date
	JTextField []ufield3;//return date
	JButton logout1;
	
	//book availibilty pane
	JPanel blpane;
	JPanel bmpane;
	JButton []button1;//rent a book
	JButton []button2;//return a book
	JTextField []field1;//book name
	JTextField []field2;//book avai
	JPanel []blitem;
	JButton logout2;
	ImageIcon[] img;
	
	//book isRenting pane
	JPanel bipane;
	JTextField []bookName;
	JTextField []rentDate;
	JTextField []dueDate;
	JPanel []biitem;
	JButton logout3;
	
	//rently add Book pane
	JPanel rbpane;
	JTextField []newbookName;
	JTextField []bookCatogary;
	JPanel []  rbitem;

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
//build the top interface
    public void addComponentToPane(Container pane) {
    	// user pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(850,600));
        tabbedPane.setSize(870,760);
        rentlyBook = new JPanel();
        initHistory();
        initBookava();
        initRent();
        tabbedPane.addTab(BUTTONPANEL, UserPane);
        tabbedPane.addTab(TEXTPANEL, booksPane);
        tabbedPane.addTab(ISRENTING, isRentingPane);
        tabbedPane.addTab(RENTBOOK,rentlyBook );
        pane.add(tabbedPane, BorderLayout.CENTER);
       	
    }
    
    class RentActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int current = Arrays.asList(button1).indexOf(e.getSource());
			System.out.println(field1[current].getText());
				System.out.println(field2[current].getText());
				getCurrentUser().rentBook(field1[current].getText());
				historyRefresh();
				isRentingRefresh();
				button1[current].setVisible(false);
				button2[current].setVisible(true);
			
		}
    	
    }
    
    class ReturnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int current = Arrays.asList(button2).indexOf(e.getSource());
			System.out.println(field1[current].getText());
				System.out.println(field2[current].getText());
				getCurrentUser().returnBook(field1[current].getText());
				historyRefresh();
				isRentingRefresh();
				button1[current].setVisible(true);
				button2[current].setVisible(false);
			
		}
    	
    }
    // initial history tab panel
    public void initHistory(){
    	 UserPane = new JPanel() {
             public Dimension getPreferredSize() {
                 Dimension size = super.getPreferredSize();
                 size.width += extraWindowWidth;
                 return size;
             }
         };
        ulpane = new JPanel();
    	logout2 = new JButton("log out");
    	logout2.addActionListener(new LogOutListener());
        UserPane.add(logout2);
	    inithistoryList();
	    UserPane.add(ulpane,BorderLayout.WEST);
    }
   //initial history panel
     public void inithistoryList(){
    	 ufield1 = new JTextField[getCurrentUser().getHistory().size()];//book title
    	 ufield2 = new JTextField [getCurrentUser().getHistory().size()];//rent date
    	 ufield3 = new JTextField [getCurrentUser().getHistory().size()];//return date
    	 bhitem = new JPanel [getCurrentUser().getHistory().size()];
 	 	for(int i= 0; i < getCurrentUser().getHistory().size(); i++){
 		    bhitem[i] = new JPanel();
 		    ufield1[i] = new JTextField(getCurrentUser().getHistory().get(i).getBookTitle());
 		    ufield2[i] = new JTextField("rent date:" + getCurrentUser().getHistory().get(i).getRentdate().toString());
 		    ulpane.setLayout(new GridLayout(getCurrentUser().getHistory().size(),3));		
 		    if(getCurrentUser().getHistory().get(i).getReturndate()!=null){
 		    	ufield3[i] = new JTextField("return date:"+getCurrentUser().getHistory().get(i).getReturndate().toString());
 		    }
	    	else{
	    		ufield3[i] = new JTextField("not return yet");
	    		}
 		    bhitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
 		    bhitem[i].add(ufield1[i]);
 		    bhitem[i].add(ufield2[i]);
 		    bhitem[i].add(ufield3[i]);
 		    ufield2[i].setEditable(false); 	
 		    ufield1[i].setEditable(false);
 		    ufield3[i].setEditable(false);
 		    ulpane.add(bhitem[i]);
 		    System.out.println("creat one record!");       
 	        }
    	 
     }
   //init bookava tab panel
     public void initBookava(){
    	booksPane = new JPanel();
    	blpane = new JPanel();
//    	bmpane = new JPanel();
//        bmpane.setLayout(new GridLayout(2,2));
        logout1 = new JButton("log out");
        booksPane.add(logout1);
        logout1.addActionListener(new LogOutListener());
    	initAvaList();
    	booksPane.add(blpane,BorderLayout.WEST);
        //booksPane.add(bmpane,BorderLayout.EAST);
        
    	
    }
   //init bookava panel
     public void initAvaList(){
    	 blitem = new JPanel[Library.Books.size()];//for each record panel;
    	 button1 = new JButton[Library.Books.size()];
    	 button2 = new JButton[Library.Books.size()];
    	 field1 = new JTextField[Library.Books.size()];
    	 field2 = new JTextField[Library.Books.size()];
    	 img= new ImageIcon[Library.Books.size()];
    	 blpane.setLayout(new GridLayout(Library.Books.size(),1));
    	 for(int i = 0; i < Library.Books.size(); i++){
         	blitem[i] = new JPanel();
            button1[i] = new JButton("rent");
            button2[i] = new JButton("return");
         	field1[i] = new JTextField(Library.Books.get(i).getName());
         	field2[i] = new JTextField("");
 	        field1[i].setEditable(false);
 	        field2[i].setEditable(false);
 	       img[i] = Library.Books.get(i).getImageIcon();
 	        if(!Library.Books.get(i).getifRendted()){
 	        field2[i].setText("available");
      		button1[i].setVisible(true);
      		button2[i].setVisible(false);
      		}
 	        else {
 	        	button1[i].setVisible(false);
           		button2[i].setVisible(false);
 	        field2[i].setText(" Not Available");
            if(Library.Transactions.get(i).getUsername().equals(getCurrentUser().getUserName())&&
             	Library.Transactions.get(i).getBookTitle().equals(Library.Books.get(i).getName())){
            	//button1[i].setVisible(false);
           		button2[i].setVisible(true);
             	//users name from transaction equals current userame and at the same time, the bookname 
             	//from  transaction equals current bookname, this is the user who rent the book, and he have access to return this
             	//book , otherwise, other people can have acess to return this book.
             }	
 	        }
 	        blitem[i].add(field1[i]);
 	        blitem[i].add(new JLabel(img[i]));
 	        blitem[i].add(field2[i]);
 	        blitem[i].add(button1[i]);
 	        blitem[i].add(button2[i]);
      		blitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
      		blpane.add(blitem[i]);
      		button1[i].addActionListener(new RentActionListener()); 
      		button2[i].addActionListener(new ReturnActionListener());

         }
    	 
     }
     
     //init isRenting tab panel
     public void initRent(){
    	isRentingPane = new JPanel();
    	bipane = new JPanel();
    	logout3 = new JButton("log out");
	 	isRentingPane.add(logout3);
        logout3.addActionListener(new LogOutListener());
		initrentingList();
		isRentingPane.add(bipane,BorderLayout.WEST);
	
    }
   //init isRenting  panel
    public void initrentingList(){
    	bookName = new JTextField[getCurrentUser().getHistory().size()];
    	rentDate = new JTextField[getCurrentUser().getHistory().size()];
    	dueDate = new JTextField[getCurrentUser().getHistory().size()];
    	biitem = new JPanel[getCurrentUser().isRenting().size()];
    	for(int i = 0; i < getCurrentUser().isRenting().size(); i++ ){
			 biitem[i] = new JPanel();
			 bookName[i] = new JTextField(getCurrentUser().getHistory().get(i).getBookTitle());
			 rentDate[i] = new JTextField("rent date:" + getCurrentUser().getHistory().get(i).getRentdate().toString());
			 dueDate[i] = new JTextField("due date:" + getCurrentUser().getHistory().get(i).getDuedate().toString());
			 bookName[i].setEditable(false);
			 rentDate[i].setEditable(false);
			 dueDate[i].setEditable(false); 	
			 bipane.setLayout(new GridLayout(getCurrentUser().getHistory().size(),3));
			 biitem[i].add(bookName[i]);
			 biitem[i].add(rentDate[i]);
			 biitem[i].add(dueDate[i]);
			 bipane.add(biitem[i]);
		}
    	
    }
    
    
    public void historyRefresh(){
    	 ulpane.removeAll();
    	 inithistoryList();
    	 ulpane.revalidate();
       	 ulpane.repaint();
    	
    }
    public void isRentingRefresh(){
    	bipane.removeAll();
    	initrentingList();
    	bipane.revalidate();
    	bipane.repaint();
    }
   
    class LogOutListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		frame.dispose();
    		Library frameTabel = new Library();
    	}
    }
    
 

    		
  
	
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public  void createAndShowuUserGUI() {
        //Create and set up the window.
    	
        frame = new JFrame("TabUser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 50);
        //Create and set up the content pane.
//        UserTab demo = new UserTab();
        addComponentToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        //create user to operate 
        
    }
    
   
}

