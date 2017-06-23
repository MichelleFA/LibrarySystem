package librarySystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class newframe extends JFrame {

public static void main(String[] args) {
newframe frameTabel = new newframe();
}
String[] bookStrings = {"Adventure","Art","Biography","Children","Fiction","Fantasy","Horror","History","Music","Science" };
JLabel welcome = new JLabel("Welcome USER1");
JButton rent = new JButton ("Books Renting");
JButton his = new JButton ("History");
JButton avai = new JButton ("Books Available");
JComboBox cat = new JComboBox(bookStrings);
JPanel panel = new JPanel();

newframe(){
super("USER 1");
setSize(870,760);
setLocation(500,200);
panel.setLayout (null);

rent.setBounds(350,120,140,20);
his.setBounds(350,180,140,20);
avai.setBounds(350,150,140,20);
welcome.setBounds(365,50,150,60);
cat.setBounds(350, 210, 140, 20);

panel.add(welcome);
panel.add(rent);
panel.add(his);
panel.add(avai);
panel.add(cat);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}
