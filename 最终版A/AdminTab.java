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
    public static String photopath= System.getProperty("user.dir").replaceAll("\\\\", "/")+"/files/bookphoto/";
    ArrayList<Book> rentedBooks;
    ArrayList<User> overdueUsers;
    ArrayList<Book> availableBooks;
    JFrame frame;
    String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
    String[] checkStrings = {"ALL","books rented out","books available"};
    String[] userStrings = {"All","Overdue"};
//GUI declare
    JTabbedPane tabbedPane;
    JPanel UserPane;
    JPanel booksPane;
    
  //user listpane and manage pane
    JScrollPane ul;
    JPanel ulpane ;
    JPanel utpane;
    JComboBox userCat;
    String[] temp1;
    JPanel[] ulitem;
    JTextField[] ufield1;
	JTextField[] ufield2;
	JButton[] ubutton1;
	JButton[] ubutton2;
//user management pane
    JPanel umpane;
	JTextField ufield3;
	JPasswordField ufield4;
	JButton ubutton3;
	JButton ulogout;
//book list
	 JScrollPane bl;
	 JPanel blpane;
	 JPanel tpane;
	 JComboBox bookcat;
	 String[] temp2 ;
	 JPanel[] blitem ;
	 JTextField[] field1;
	 JTextField[] field2;
	 JButton[] button1;
	 JButton[] button2;
	 ImageIcon[] img;
//book management
	JPanel bmpane;
	JTextField field3;
	JButton button3;
	JButton logout;
	JComboBox ucat;
	JButton button4;
	FileDialog photo;
	String pathnew;

    public void addComponentToPane(Container pane) {
    	
    	// admin pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000,600));
        tabbedPane.setSize(1000,760);
        initUser(Library.Users);
        initBook();
        tabbedPane.addTab(BUTTONPANEL, UserPane);
        tabbedPane.addTab(TEXTPANEL, booksPane);
        frame.add(tabbedPane, BorderLayout.CENTER);
       // pane.add(tabbedPane, BorderLayout.CENTER);
    }
    //delete user
    
    public void initUser(ArrayList<User> arr){
        UserPane = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };	
        //user listpane and manage pane
        utpane = new JPanel();
        ulpane = new JPanel();
        ulpane.setPreferredSize(new Dimension(500,1200));
        userCat = new JComboBox(userStrings);
        userCat.addItemListener(new ItemListener(){
       		public void itemStateChanged(ItemEvent e){
       			if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    String s=(String)userCat.getSelectedItem();
                    System.out.println(s);
                    if(s.equals("All"))
                    	repaintUser(ulpane,Library.Users);
                    else if(s.equals("Overdue"))
                    	repaintUser(ulpane,getOverdueUsers());       
                }
       		}
       	   });
        utpane.add(userCat);
        initUserList(arr);
        
        ul = new JScrollPane(ulpane);
        ul.setPreferredSize(new Dimension(480,600));
        utpane.add(ul);
        initUserManage();
		UserPane.setLayout(new GridLayout(1,2));
		UserPane.add(utpane);
		UserPane.add(umpane);
		
    	
    }
    public void initUserList(ArrayList<User> arr){
    	temp1 = new String[arr.size()];
        ulitem = new JPanel[arr.size()];
        ufield1 = new JTextField[arr.size()];
   	 	ufield2 =new JTextField[arr.size()];
   	 	ubutton1 =new JButton[arr.size()];
   	 	ubutton2 = new JButton[arr.size()];
       
       for(int i = 0;i<arr.size();i++){
       	ulitem[i]= new JPanel();
       	ulitem[i].setAlignmentX(0);
       	ulitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
       	ufield1[i] = new JTextField(arr.get(i).getUserName());
           temp1[i] = arr.get(i).getUserName();
           if(arr.get(i).getIsOverDue())
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
       
       
    }
    public void initUserManage(){
    	umpane = new JPanel();
		ufield3 = new JTextField("user name");
		ufield4 = new JPasswordField("password");
		ubutton3 = new JButton("add");
		ulogout = new JButton("log out");
		umpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		umpane.add(ufield3);
		umpane.add(ufield4);
		umpane.add(ubutton3);
		umpane.add(ulogout);
		
		
		//addlistener to add user
		ubutton3.addActionListener(new AddUserListenser());
		ulogout.addActionListener(new LogOutListener());
    	
    }
    public void initBook(){
       booksPane = new JPanel();
        //booksListpane 
       tpane = new JPanel();
       tpane.setPreferredSize(new Dimension(550,600));
       blpane = new JPanel();
       blpane.setPreferredSize(new Dimension(480,1200));
   	   bookcat = new JComboBox(checkStrings); 
   	   bookcat.addItemListener(new ItemListener(){
   		public void itemStateChanged(ItemEvent e){
   			if(e.getStateChange() == ItemEvent.SELECTED)
            {
                String s=(String)bookcat.getSelectedItem();
                System.out.println(s);
                if(s.equals("books rented out")){
                	repaintBook(blpane,getAllRentedBooks());
                }
                else if(s.equals("All")){
                	repaintBook(blpane,Library.Books);
                }
                else{
                	repaintBook(blpane,getAllAvailableBooks());
                }
                   
            }
   		}
   	   });
   	   
   	   tpane.add(bookcat,BorderLayout.NORTH);
   	   initBookList(Library.Books);
   	   bl = new JScrollPane(blpane);
   	   bl.setPreferredSize(new Dimension(480,600));
   	   tpane.add(bl);
   	   initBookManage();
       booksPane.setLayout(new GridLayout(1,2));
       booksPane.add(tpane);
       booksPane.add(bmpane);
       
    	
    }
    
    public void initBookList(ArrayList<Book> arr){
    	temp2 = new String[arr.size()];
    	   blitem = new JPanel[arr.size()];
    	   field1 = new JTextField[arr.size()];
    	   field2 =new JTextField[arr.size()];
    	   button1 =new JButton[arr.size()];
    	   button2 = new JButton[arr.size()];
    	   img= new ImageIcon[arr.size()];
         //blpane.setLayout(new BoxLayout(blpane,BoxLayout.Y_AXIS));
         for(int i = 0;i<arr.size();i++){
         blitem[i]= new JPanel();
         blitem[i].setAlignmentX(0);
     	 blitem[i].setLayout(new FlowLayout(FlowLayout.LEFT));
     	 //img[i] = new ImageIcon(photopath+i+".jpg");
     	 img[i] = arr.get(i).getImageIcon();
     	 field1[i] = new JTextField(arr.get(i).getName());
     	 temp2[i] = arr.get(i).getName();
         if(arr.get(i).getifRendted())
         	field2[i] = new JTextField("rented");
         else
         	field2[i] = new JTextField("available");
  		button1[i] = new JButton("update");
  		button2[i] = new JButton("delete");
  		blitem[i].add(new JLabel(img[i]));
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
    	
    }
    public void initBookManage(){
		bmpane = new JPanel();
		button3 = new JButton("add");
		field3 = new JTextField("book name");
		ucat = new JComboBox(bookStrings);
		button4 = new JButton("image for book");
		logout = new JButton("log out");
		photo = new FileDialog(frame,"open",FileDialog.LOAD);
		logout.addActionListener(new LogOutListener());
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				photo.setVisible(true);	
				pathnew = photo.getDirectory()+photo.getFile();
				
			}
		});
		bmpane.setLayout(new FlowLayout(FlowLayout.LEFT));
		bmpane.add(field3);
		bmpane.add(button3);
		bmpane.add(button4);
		bmpane.add(ucat);
		bmpane.add(logout);
		
		//addlistener to add book
		button3.addActionListener(new AddBookListenser());
    	
    }
    public void repaintUser(JPanel lists,ArrayList<User> arr){
    	 lists.removeAll();
    	 initUserList(arr);
    	 lists.revalidate();
    	 lists.repaint();
    }
    public void repaintBook(JPanel lists,ArrayList<Book> arr){
   	 lists.removeAll();
   	 initBookList(arr);
   	 lists.revalidate();
   	 lists.repaint();
   }
   
    class AddUserListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
//    		System.out.println(ufield3.getText());
//    		System.out.println(ufield4.getText());
    		addUsers(ufield3.getText(),ufield4.getText());
    		repaintUser(ulpane,Library.Users);
    		
    	}
    }
    class DeleteUserListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int current = Arrays.asList(ubutton2).indexOf(e.getSource());
    		System.out.println(current);
    		deleteUser(ufield1[current].getText());
    		repaintUser(ulpane,Library.Users);
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
    //add book
    class AddBookListenser implements ActionListener{
    	public void actionPerformed(ActionEvent e){
			addBook(field3.getText(),(String)ucat.getSelectedItem(),pathnew.replaceAll("\\\\", "/"));
			 repaintBook(blpane,Library.Books);
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
    		repaintBook(blpane,Library.Books);
    		//System.out.println(Library.Books.get(current).getName());
    		
    	}
    }
    class LogOutListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		frame.dispose();
    		Library frameTabel = new Library();
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
    public void addBook(String name, String catogary ,String imgAdd){
    	ImageIcon img = new ImageIcon(imgAdd);
		Book newBook = new Book(name, catogary,img);
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
        availableBooks = new ArrayList<Book>();
        for(int i = 0; i < Library.Books.size(); i++){
            if(!Library.Books.get(i).getifRendted()){
                availableBooks.add(Library.Books.get(i));
            }
        }
        return availableBooks;
    }

    public ArrayList<Book> getAllRentedBooks(){
    	rentedBooks = new ArrayList<Book>();
        for(int i = 0; i < Library.Books.size(); i++){
            if(Library.Books.get(i).getifRendted()){
                rentedBooks.add(Library.Books.get(i));
            }
        }
        return rentedBooks;
    }
    public ArrayList<User> getOverdueUsers(){
    	overdueUsers = new ArrayList<User>();
        for(int i = 0; i < Library.Users.size(); i++){
            if(Library.Users.get(i).overDueCheck()){
                overdueUsers.add(Library.Users.get(i));
            }
        }
        return overdueUsers;
    }
	
}
