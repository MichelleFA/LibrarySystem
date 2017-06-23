import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class AdminTab  {
	final static String BUTTONPANEL = "User Management";
    final static String TEXTPANEL = "Book Management";
    final static int extraWindowWidth = 100;
    JFrame frame;
    String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
 //GUI declare
    JTabbedPane tabbedPane;

    JPanel UserPane;
    JPanel booksPane;
    
  //user listpane and manage pane
    JPanel ulpane ;
    JPanel[] ulitem = new JPanel[Library.Users.size()];
    String[] temp1 = new String[Library.Users.size()];
    JTextField[] ufield1 = new JTextField[Library.Users.size()];
	JTextField[] ufield2 =new JTextField[Library.Users.size()];
	JButton[] ubutton1 =new JButton[Library.Users.size()];
	JButton[] ubutton2 = new JButton[Library.Users.size()];
//user management pane
    JPanel umpane;
	JTextField ufield3;
	JPasswordField ufield4;
	JButton ubutton3;
//book list
	 JPanel blpane;
	 JPanel[] blitem = new JPanel[Library.Books.size()];
	 String[] temp2 = new String[Library.Books.size()];
	 JTextField[] field1 = new JTextField[Library.Books.size()];
	 JTextField[] field2 =new JTextField[Library.Books.size()];
	 JButton[] button1 =new JButton[Library.Books.size()];
	JButton[] button2 = new JButton[Library.Books.size()];
//book management
	JPanel bmpane;
	JTextField field3;
	JButton button3;
	JComboBox ucat;

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
        for(int i = 0;i<Library.Users.size();i++){
        	ulitem[i]= new JPanel();
        	ulitem[i].setAlignmentX(0);
        	ulitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
        	ufield1[i] = new JTextField(Library.Users.get(i).getUserName());
            temp1[i] = Library.Users.get(i).getUserName();
            if(Library.Users.get(i).getIsOverDue())
            	ufield2[i] = new JTextField("overdue");
            else
            	ufield2[i] = new JTextField("no overdue");
     		ubutton1[i] = new JButton("update");
     		ubutton2[i] = new JButton("delete");
     		
     		ulitem[i].add(ufield1[i]);
     		ulitem[i].add(ufield2[i]);
     		ulitem[i].add(ubutton1[i]);
     		ulitem[i].add(ubutton2[i]);
        	ulpane.add(ulitem[i]);
        	
        	//add listenner to update user
        	ubutton1[i].addActionListener(new UpdateUserListenser());
     		//add listenner to delete user
     		ubutton2[i].addActionListener(new DeleteUserListenser());
        }		
        umpane = new JPanel();
		ufield3 = new JTextField("user name");
		ufield4 = new JPasswordField("password");
		ubutton3 = new JButton("add");
		umpane.setLayout(new FlowLayout(FlowLayout.LEFT));
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
		
		UserPane.setLayout(new GridLayout(1,2));
		UserPane.add(ulpane);
		UserPane.add(umpane);
 
        
         booksPane = new JPanel();
        //booksListpane 
         blpane = new JPanel();
         //blpane.setLayout(new BoxLayout(blpane,BoxLayout.Y_AXIS));
         for(int i = 0;i<Library.Books.size();i++){
         blitem[i]= new JPanel();
         blitem[i].setAlignmentX(0);
     	 blitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
     	 field1[i] = new JTextField(Library.Books.get(i).getName());
     	 field1[i].setSize(100, 50);
         temp2[i] = Library.Books.get(i).getName();
         if(Library.Books.get(i).getifRendted())
         	field2[i] = new JTextField("rented");
         else
         	field2[i] = new JTextField("available");
  		button1[i] = new JButton("update");
  		button2[i] = new JButton("delete");
  		
  		blitem[i].add(field1[i]);
  		blitem[i].add(field2[i]);
  		blitem[i].add(button1[i]);
  		blitem[i].add(button2[i]);
     	blpane.add(blitem[i]);
     	
     	//add listenner to update book
     	button1[i].addActionListener(new UpdateBookListenser());
  		//add listenner to delete book
  		button2[i].addActionListener(new DeleteBookListenser());
        	 
         }
		//book management pane
		bmpane = new JPanel();
		field3 = new JTextField("book name");
		button3 = new JButton("add");
		ucat = new JComboBox(bookStrings);
		bmpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		bmpane.add(field3);
		bmpane.add(button3);
		bmpane.add(ucat);
		
		//addlistener to add book
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(field3.getText());
				System.out.println((String)ucat.getSelectedItem());
				addBook(field3.getText(),(String)ucat.getSelectedItem());
			}
		});
		
		booksPane.setLayout(new GridLayout(1,2));
        booksPane.add(blpane);
        booksPane.add(bmpane);
        
 
        tabbedPane.addTab(BUTTONPANEL, UserPane);
        tabbedPane.addTab(TEXTPANEL, booksPane);
 
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
    //delete user
    
    class DeleteUserListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int current = Arrays.asList(ubutton2).indexOf(e.getSource());
    		System.out.println(current);
    		deleteUser(ufield1[current].getText());
    		
    	}
    }
    
    //update users
    class UpdateUserListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int current = Arrays.asList(ubutton1).indexOf(e.getSource());
    		updateUser(temp1[current],ufield1[current].getText());
    			System.out.println(Library.Users.get(current).getUserName());
    		
    	}
    }
    //upadete book
    class UpdateBookListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int current = Arrays.asList(button1).indexOf(e.getSource());
    		updateBook(temp2[current],field1[current].getText());
    		//System.out.println(Library.Books.get(current).getName());
    		
    	}
    }
    //delete book
    class DeleteBookListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int current = Arrays.asList(button2).indexOf(e.getSource());
    		deleteBook(field1[current].getText());
    		//System.out.println(Library.Books.get(current).getName());
    	}
    }
 
    public void createAndShowGUI() {
        //Create and set up the window.
        frame= new JFrame("TabAdmin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200,50);
        //Create and set up the content pane.
       
        addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        //create admin to operate 
        
    }
    
    
    /*These are methods of Admin*/
    public void addBook(String name, String catogary){
		Book newBook = new Book(name, catogary);
        newBook.setAddDate();
        Library.Books.add(newBook);
		Library.saveObject(Library.Books,"books.dat");
		
	}
	
	public void addUsers(String userName,String password){
		User newUser = new User(userName,password);
		Library.Users.add(newUser);
		Library.saveObject(Library.Users,"users.dat");
	}
	public void updateBook(String oldBook,String bookname){
		for(int i = 0; i < Library.Books.size(); i++){
			if(Library.Books.get(i).getName().equals(oldBook)){
				Library.Books.get(i).setName( bookname);
			}
		}
		Library.saveObject(Library.Books,"books.dat");
		
	}
	public void updateUser(String oldUserName,String username){
		for(int i = 0; i < Library.Users.size(); i++){
			if(Library.Users.get(i).getUserName().equals(oldUserName)){
				Library.Users.get(i).setUserName(username);
				}
			}
        Library.saveObject(Library.Users,"users.dat");
		
	}
	
	public void deleteBook(String name){
		for(int i = 0; i < Library.Books.size(); i++){
			if(Library.Books.get(i).getName().equals(name)){
				Library.Books.remove(i);
				}
			}
        Library.saveObject(Library.Books,"books.dat");

	}
	
	public void deleteUser(String name){
		for(int i = 0; i < Library.Users.size(); i++){
			if(Library.Users.get(i).getUserName().equals(name)){
				Library.Users.remove(i);
				}
			}
		Library.saveObject(Library.Users,"users.dat");
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
