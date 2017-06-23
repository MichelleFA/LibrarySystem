import java.io.Serializable;
import java.util.*;

import javax.swing.ImageIcon;
public class Book implements Serializable {

	private String bookName;
	private String catogary;
	private boolean isRented;
    private Date addDate;
    private ImageIcon img;
    
    public Book(String name, String catogary){
		this.bookName = name;
		this.catogary = catogary;
		this.img = new ImageIcon(System.getProperty("user.dir").replaceAll("\\\\", "/")+"/files/bookphoto/1.jpg");
		isRented = false;
	}
	
	public Book(String name, String catogary,ImageIcon img){
		this.bookName = name;
		this.catogary = catogary;
		this.img = img;
		isRented = false;
	}
	
	
    public void setAddDate(){
        this.addDate = new Date();
    }

	public void setName(String name){
		this.bookName = name;
	}
	
	public void setCatogary(String catogary){
		this.catogary = catogary;
	}
	
	public void Rented(){
		this.isRented = true;
	}
	
	public void Returned(){
		this.isRented = false;
	}
	
	public String getName(){
		return this.bookName;
	}
	
	public String getCatogary(){
		return this.catogary;
	}
	
	public boolean getifRendted(){
		return this.isRented;
	}
	public ImageIcon getImageIcon(){
		return this.img;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
