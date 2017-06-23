
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
	private String userName;
    private String passWord;
	private boolean isAdmin;
    private boolean isOverDue;

    public User(){
    	
    }
	public User(String userName,String passWord){
		this.userName = userName;
        this.passWord =passWord;
	}

    public String getPassWord(){
        return this.passWord;
    }

    public String getUserName(){
        return this.userName;
    }

    public boolean getIsOverDue(){
        return this.isOverDue;
    }

	public void setUserName(String name)
    {
		this.userName = name;
	}

	public void setAsAdmin()
    {
		this.isAdmin = true;
	}
//rent a book
	public void rentBook(Book b){
		Transaction t = new Transaction(this.userName,b.getName());
		b.getifRendted();
		Library.Transactions.add(t);

	}
//return a book
	public void returnBook(Book b){
		for(int i=0;i< Library.Transactions.size();i++){
            //transaction book title equals the book returning
			if(Library.Transactions.get(i).getBookTitle().equals(b.getName())){
                Library.Transactions.get(i).setReturndate(new Date());
			}
		}
	}
    //all the transactions
	public ArrayList<Transaction> getHistory(){
        ArrayList<Transaction> history= new ArrayList<Transaction>();
        for(int i=0;i< Library.Transactions.size();i++){
            //transaction book title equals the book returning
            if(Library.Transactions.get(i).getUsername().equals(this.getUserName())){
                history.add(Library.Transactions.get(i));
            }

        }
		return history;
	}
    //book that user is renting
	public  ArrayList<Transaction> isRenting(){
        ArrayList<Transaction> renting= new ArrayList<Transaction>();
        for(int i=0;i< Library.Transactions.size();i++){
            //transaction book title equals the book returning
            if((Library.Transactions.get(i).getUsername().equals(this.getUserName()))&&(Library.Transactions.get(i).getReturndate()==null)){
                renting.add(Library.Transactions.get(i));
            }
        }
        return renting;
	}

    //filter of catogary
	public ArrayList<Book> searchCatogary(String keyword){
		ArrayList<Book> listBooks = new ArrayList<Book>();
        for(int i=0;i< Library.Books.size();i++){
            //transaction book title equals the book returning
            if((Library.Books.get(i).getCatogary().equals(keyword))){
                listBooks.add(Library.Books.get(i));
            }
        }
		return listBooks;
	}
    //check overdue
    public boolean overDueCheck(){
        for(int i=0;i< Library.Transactions.size();i++) {
            if ((getDuration(Library.Transactions.get(i).getRentdate(),Library.Transactions.get(i).getReturndate())>=60 &&(Library.Transactions.get(i).getUsername().equals(this.getUserName()))) {
                this.isOverDue = true;
            }
        }
        return false;
    }
    //show new books
    public ArrayList<Book> getRecentBooks(){
        ArrayList<Book> listBooks = new ArrayList<Book>();
        for(int i=0;i< Library.Books.size();i++){
            if(getDuration(Library.Books.get(i).getAddDate(),new Date())<=5){
                listBooks.add(Library.Books.get(i));
            }
        }
        return listBooks;
    }

    public static long getDuration(Date d1,Date d2){
        long diffMinutes= 0;
        try {
            long diff = d2.getTime() - d1.getTime();
            diffMinutes = diff / (60 * 1000) % 60;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffMinutes;
    }

}