import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Created by franklin on 8/12/14.
 */
public class Library extends JFrame implements Serializable{
	//static ArrayLists for all data
    public static ArrayList<Book> Books=new ArrayList<Book>();
    public static ArrayList<Transaction> Transactions=new ArrayList<Transaction>();
    public static ArrayList<User> Users=new ArrayList<User>();
    private static String logInName;
   
    public static FileInputStream fileIn;
    public static FileOutputStream fileOut;
    public static ObjectInputStream ios;
    public static ObjectOutputStream oos;
    //GUI
    private JButton blogin = new JButton("Login");
    private JPanel panel = new JPanel();
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    //user current path
    public static String path= System.getProperty("user.dir").replaceAll("\\\\", "/");
    
    public static void main(String[] args) {
           	setUsers();
           	setTransactions();
            setBook();
            Library frameTabel = new Library();
            System.out.println(path);

    	}
    public Library(){
    	  super("LIBRARY");
          setSize(870,760);
          setLocation(500,50);
          panel.setLayout (null);
          panel.setBackground(Color.WHITE);


          txuser.setBounds(350,150,150,20);
          pass.setBounds(350,185,150,20);
          blogin.setBounds(390,220,80,20);

          panel.add(blogin);
          panel.add(txuser);
          panel.add(pass);

          getContentPane().add(panel);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setVisible(true);
          actionlogin();
    	
    }
    public void actionlogin(){
    	blogin.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ae) {
    	String puname = txuser.getText();
    	String ppaswd = pass.getText();
    	if(puname.equals("admin") && ppaswd.equals("12345")){
    		//newframe1 regFace =new newframe1();
    		javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AdminTab admin = new AdminTab();
                    admin.createAndShowGUI();
                  
                }
            });
	    	dispose();
    	}
    	else if(isUser(puname,ppaswd)){
    		//newframe1 regFace =new newframe1();
    		setLogInName(puname);
    		javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	UserTab user = new UserTab();
                    user.createAndShowuUserGUI();
                    System.out.println(getLogInUserName());
                    setVisible(false);
                }
            });
//	    	dispose();
    	}
    	else {
    	JOptionPane.showMessageDialog(null,"Wrong Password / Username");
    	txuser.setText("");
    	pass.setText("");
    	txuser.requestFocus();
    	}

    	}
    	});
    	}
    public static String getLogInUserName(){
    	return Library.logInName;
    }
    
    public static void setLogInName(String logInName){
    	Library.logInName = logInName;
    }
    public boolean isUser(String name,String password){
    	int i=0;
    	while(i<Users.size()){
    		if(name.equals(Users.get(i).getUserName())&&password.equals(Users.get(i).getPassWord()))
    			return true;
    		i++;
    	}
    	return false;
    }

    public static void setBook(){
        try {
            String bookpath = path+"/files/books.dat";
            File booklist = new File(bookpath);
            fileIn = new FileInputStream(booklist);
            ios = new ObjectInputStream(fileIn);
            Books=(ArrayList<Book>)ios.readObject();
            fileIn.close();
            ios.close();
        }catch(EOFException e){
            System.out.println("read book file finished!");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void setUsers(){
        try {
            String userpath = path+"/files/users.dat";
            File userlist = new File(userpath);
            fileIn = new FileInputStream(userlist);
            //fileIn = new FileInputStream(userpath);
            ios = new ObjectInputStream(fileIn);
            Users=(ArrayList<User>)ios.readObject();
            fileIn.close();
            ios.close();
        }
        catch(EOFException e){
            System.out.println("read users file finished!");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void setTransactions(){
        try {
            String transpath = path+"/files/transactions.dat";
            //transpath.replaceAll("\\\\", "/");
            File translist = new File(transpath);
            fileIn = new FileInputStream(translist);
            ios = new ObjectInputStream(fileIn);
            Transactions=(ArrayList<Transaction>)ios.readObject();
            fileIn.close();
            ios.close();
        }catch(EOFException e){
            System.out.println("read transaction file finished!");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void saveObject(ArrayList arr, String filename){
        try {
            String filepath = path+"/files/"+filename;
            File fileToWrite = new File(filepath);
            fileOut = new FileOutputStream(fileToWrite);
            oos = new ObjectOutputStream(fileOut);
            oos.writeObject(arr);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
