
import java.util.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.text.DateFormat;
public class Transaction implements Serializable{
    private static final long serialVersionUID = 1L;
	static final int duration = 60;
	private String username;
	private String bookTitle;
	private Date rentDate;
	private Date dueDate;
	private Date returnDate;
	private int penaltyAmount=0;
	
	
	
	public Transaction(String username,String bookTitle){
		this.username=username;
		this.bookTitle = bookTitle;
		this.rentDate=new Date();   
		//duedate
		GregorianCalendar gc=new GregorianCalendar(); 
		//SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(this.rentDate);
		gc.add(5, duration);
		this.dueDate = gc.getTime();
		if(this.returnDate!=null){
			this.penaltyAmount = (int)(this.returnDate.getTime() -this.dueDate.getTime())/(24*60*60*1000)*2;
			}
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}


	public Date getRentdate() {
		return rentDate;
	}


	public void setRentdate(Date rentDate) {
		this.rentDate = rentDate;
		
	}


	public Date getReturndate() {
		return returnDate;
	}


	public void setReturndate(Date returnDate) {
		this.returnDate = returnDate;
		setPenaltyAmount();
	}


	public void setDuedate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDuedate(){
		return dueDate;
	}
	
	
	public int getPenaltyAmount() {
		return  penaltyAmount;
	}
	public void setPenaltyAmount() {
		if(returnDate!=null)
		penaltyAmount = (int)(((this.returnDate.getTime() -this.dueDate.getTime())/(24*60*60*1000)+1)*2);
	}
	
	public static void main(String[] args){
		Transaction t1 =  new Transaction("deng","Harry Potter");
		SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyy-MM-dd");   
		Date date;
		try {
			date = sdfsdf.parse("2014-10-15");
			t1.setReturndate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		System.out.println(t1.getReturndate());
		System.out.println(t1.getRentdate());
		System.out.println(t1.getDuedate());
		System.out.println(t1.getPenaltyAmount());
		//System.out.println(t1.getPenaltyAmount());
	}
	
	

}
