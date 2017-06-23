package librarySystem;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class bookList extends JFrame{
	JTextField field1;
	JTextField field2;
	JTextField field3;
	JTextField field4;
	
	JButton button1;
	JButton button2;
	JButton button3;
	
	JPanel panle;
	JPanel panle1;
	
	public static void main(String[] args) {
		bookList test = new bookList();
		test.setRecord();
		test.BuildInterface();
	}
	public void BuildInterface(){
		setTitle("BookList");
		setSize(870,760);
		setLocation(500,200);
		setVisible(true);
		panle1 = new JPanel();
		button3 = new JButton("add");
		field3 = new JTextField("book name");
		field4 = new JTextField("libary name");
		panle1.add(button3);
		panle1.add(field3);
		panle1.add(field4);
		setLayout(new BorderLayout(100,100));
		add(panle1,BorderLayout.SOUTH);
		
	}
		

	
	public void setRecord(){
	Book[] bookArray = (Book[]) Library.Books.toArray();
	for(int i = 0; i < bookArray.length; i++){
		field1 = new JTextField(bookArray[i].getName());
		if(bookArray[i].getifRendted()){
			 field2 = new JTextField("available");
		}
		else{
			field2 = new JTextField("not available");
		}
		button1 = new JButton("update");
		button2 = new JButton("delete");
		panle = new JPanel();
		panle.add(field1);
		panle.add(field2);
		panle.add(field1);
		panle.add(field2);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(panle);
	}
}
	

}
