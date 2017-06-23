package librarySystem;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class AdminTab  {
	final static String BUTTONPANEL = "User Management";
    final static String TEXTPANEL = "Book Management";
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
        
        //user listpane and manage pane
        ulpane = new JPanel();
        ufield1 = new JTextField("hanxiao");
		ufield2 = new JTextField("overdude");
		ubutton1 = new JButton("update");
		ubutton2 = new JButton("delete");
		ulpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		ulpane.add(ufield1);
		ulpane.add(ufield2);
		ulpane.add(ubutton1);
		ulpane.add(ubutton2);
		ulpane.setBackground(Color.WHITE);
		
		//book management pane
        umpane = new JPanel();
		ufield3 = new JTextField("user name");
		ufield4 = new JPasswordField("password");
		ubutton3 = new JButton("add");
		umpane.setLayout(new GridLayout(2,2));
		umpane.setBackground(Color.WHITE);
		umpane.add(ufield3);
		umpane.add(ufield4);
		umpane.add(ubutton3);
		
		//addlistener to add user
		
		ubutton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(ufield3.getText());
				System.out.println(ufield4.getText());
				addUsers(ufield3.getText(),ufield4.getText());
				
			}
		});
		
		
		UserPane.add(ulpane,BorderLayout.WEST);
		UserPane.add(umpane,BorderLayout.EAST);
		UserPane.setBackground(Color.WHITE);
		
		
        JPanel booksPane = new JPanel();
        //booksListpane 
        JPanel blpane = new JPanel();
        JTextField field1 = new JTextField("Harry Potter");
		JTextField field2 = new JTextField("available");
		JButton button1 = new JButton("update");
		JButton button2 = new JButton("delete");
		blpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		blpane.setBackground(Color.WHITE);
		blpane.add(field1);
		blpane.add(field2);
		blpane.add(button1);
		blpane.add(button2);
		
		//book management pane
        JPanel bmpane = new JPanel();
		JTextField field3 = new JTextField("book name");
		JButton button3 = new JButton("add");
		JComboBox ucat = new JComboBox(bookStrings);
		bmpane.setLayout(new GridLayout(2,2));
		bmpane.setBackground(Color.WHITE);
		bmpane.add(field3);
		bmpane.add(button3);
		bmpane.add(ucat);
		
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
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabAdmin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        AdminTab demo = new AdminTab();
        demo.addComponentToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(500,200);
        //create admin to operate 
        
    }
    
    
    /*These are methods of Admin*/
    public void addBook(String name, String catogary){
		Book newBook = new Book(name, catogary);
        newBook.setAddDate();
        Library.Books.add(newBook);
		Library.saveObject(Library.Books,"books.txt");
		
	}
	
	public void addUsers(String userName,String password){
		User newUser = new User(userName,password);
		Library.setUsers();
		Library.Users.add(newUser);
        //Library.saveObject(Library.Users,"users.dat");
		Library.saveObject(Library.Users,"F:/rencent/LibrarySystem/files/users.dat");
	}
	
	public void deleteBook(String name){
		for(int i = 0; i < Library.Books.size(); i++){
			if(Library.Books.get(i).getName().equals(name)){
				Library.Books.remove(i);
				}
			}
        Library.saveObject(Library.Books,"books.txt");

	}
	
	public void deleteUser(String name){
		for(int i = 0; i < Library.Users.size(); i++){
			if(Library.Users.get(i).getUserName().equals(name)){
				Library.Users.remove(i);
				}
			}
		Library.saveObject(Library.Users,"users.txt");
	}

    public ArrayList<Book> getAllBooks(){
        return Library.Books;
    }

    public ArrayList<User> getAllUsers(){
        return Library.Users;
    }
    public ArrayList<Book> getAllAvailableBooks(){
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        for(int i = 0; i < Library.Books.size(); i++){
            if(!Library.Books.get(i).getifRendted()){
                availableBooks.add(Library.Books.get(i));
            }
        }
        return availableBooks;
    }

    public ArrayList<Book> getAllRentedBooks(){
        ArrayList<Book> rentedBooks = new ArrayList<Book>();
        for(int i = 0; i < Library.Books.size(); i++){
            if(!Library.Books.get(i).getifRendted()){
                rentedBooks.add(Library.Books.get(i));
            }
        }
        return rentedBooks;
    }
    public ArrayList<User> getOverdueUsers(){
        ArrayList<User> overdueUsers = new ArrayList<User>();
        for(int i = 0; i < Library.Users.size(); i++){
            if(Library.Users.get(i).overDueCheck()){
                overdueUsers.add(Library.Users.get(i));
            }
        }
        return overdueUsers;
    }
	
}
