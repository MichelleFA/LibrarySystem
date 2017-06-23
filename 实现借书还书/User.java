
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
	private String userName;
    private String passWord;
	private boolean isAdmin;
    private boolean isOverDue;
    private boolean a;

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
	public void rentBook(String bookName){
		Transaction t = new Transaction(this.userName,bookName);
//		t.setRentdate(new Date());
		for(int i = 0; i < Library.Books.size();i++){
			if( Library.Books.get(i).getName().equals(bookName)){
				Library.Books.get(i).Rented();
			}
		}
		Library.Transactions.add(t);
        Library.saveObject(Library.Books,"books.dat");
		Library.saveObject(Library.Transactions, "transactions.dat");

	}
//return a book
	public void returnBook(String bookName){

        for(int i = 0; i < Library.Books.size();i++){
            if( Library.Books.get(i).getName().equals(bookName)){
                Library.Books.get(i).Returned();
            }
        }

		for(int i=0;i< Library.Transactions.size();i++){
            //transaction book title equals the book returning
			if(Library.Transactions.get(i).getBookTitle().equals(bookName)){
                Library.Transactions.get(i).setReturndate(new Date());
                Library.Transactions.get(i).Returned();
			}
		}
        Library.saveObject(Library.Books,"books.dat");
		Library.saveObject(Library.Transactions, "transactions.dat");
		
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
	public  ArrayList isRenting(){
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
	public ArrayList searchCatogary(String keyword){
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
            if (((int) (((Library.Transactions.get(i).getReturndate().getTime() - Library.Transactions.get(i).getRentdate().getTime()) / (24 * 60 * 60 * 1000) + 1) * 2) > 60)&&(Library.Transactions.get(i).getUsername().equals(this.getUserName()))) {
                this.isOverDue = true;
                return isOverDue;
            }
        }
        return false;
    }
    //show new books

}