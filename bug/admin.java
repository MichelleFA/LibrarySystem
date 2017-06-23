
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class admin extends User {
	//GUI
	JFrame Adminframe;
	String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
	JLabel welcome = new JLabel("Welcome admin");
	JButton bl = new JButton ("Books List");
	JButton cl = new JButton ("Customers List ");
	JComboBox cat = new JComboBox(bookStrings);
	JPanel panel = new JPanel();
//	ArrayList users = new ArrayList();
//	ArrayList books = new ArrayList();
	
	public admin(){
		Adminframe = new JFrame("Admin");
		Adminframe.setSize(870,760);
		Adminframe.setLocation(500,200);
		panel.setLayout (null);


		cl.setBounds(350,120,140,20);
		bl.setBounds(350,160,140,20);
		welcome.setBounds(365,50,150,60);
		cat.setBounds(350, 210, 140, 20);

		panel.add(welcome);
		panel.add(bl);
		panel.add(cl);
		panel.add(cat);

		Adminframe.getContentPane().add(panel);
		Adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Adminframe.setVisible(true);
	}
	public void addBook(String name, String catogary){
		Book newBook = new Book(name, catogary);
        newBook.setAddDate();
        Library.Books.add(newBook);
		Library.saveObject(Library.Books,"books.txt");
		
	}
	
	public void addUsers(String userName,String password){
		User newUser = new User(userName,password);
		Library.Users.add(newUser);
        Library.saveObject(Library.Users,"users.txt");
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
            if(Library.Users.get(i).getIsOverDue()){
                overdueUsers.add(Library.Users.get(i));
            }
        }
        return overdueUsers;
    }

	public static void main(String[] args) {
	
	}

}
