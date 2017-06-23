import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class customerList extends JFrame{
	JTextField field1;
	JTextField field2;
	JTextField field3;
	
	JButton button1;
	JButton button2;
	JButton button3;
	
	JPanel panle;
	JPanel panle1;
	
	public static void main(String[] args) {
		customerList test = new customerList();
		test.setRecord();
		test.BuildInterface();
	}
	public void BuildInterface(){
		setTitle("CustomerList");
		setSize(870,760);
		setLocation(500,200);
		setVisible(true);
		panle1 = new JPanel();
		button3 = new JButton("add");
		field3 = new JTextField("User's name");
		panle1.add(button3);
		panle1.add(field3);
		setLayout(new BorderLayout(100,100));
		add(panle1,BorderLayout.SOUTH);
		
	}
		

	
	public void setRecord(){
	User[] usersArray = (User[]) Library.Users.toArray();
	for(int i = 0; i < usersArray.length; i++){
		field1 = new JTextField(usersArray[i].getUserName());
		if(usersArray[i].overDueCheck()){
			 field2 = new JTextField("overdue");
		}
		else{
			field2 = new JTextField("not overdue");
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
