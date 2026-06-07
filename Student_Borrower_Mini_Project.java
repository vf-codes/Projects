import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


class BorrowManager{
    private HashMap<String,ArrayList<String>> borrowList = new HashMap<>();
    
    public void borrowBook(String name, String book){
        if (borrowList.containsKey(name)){
            borrowList.get(name).add(book);
            System.out.println("Book Borrowed Successfully");
        }
        else{
            ArrayList<String> books = new ArrayList<>();
            books.add(book);
            borrowList.put(name, books);
            System.out.println("Book Borrowed Successfully!");
        }
    }
    
    public void returnBook(String name, String book){
        if (borrowList.containsKey(name)){
            if (borrowList.get(name).contains(book)){
                borrowList.get(name).remove(book);
                if (borrowList.get(name).isEmpty()){
                    borrowList.remove(name);
                }
                System.out.println("Book Returned Successfully!");
            }
            else{
                System.out.println("Borrower "+name+" did not borrow book "+book);
            }
        }
        else{
            System.out.println("Borrower didnt borrow any books");
        }
    }
    
    public void displayBorrowers(){
        System.out.println("All Borrowers:-");
        for (String key : borrowList.keySet()){
            System.out.println(key+" : "+borrowList.get(key).size()+" books");
        }
    }
    
    public void displayBorrower(String name){
        if (borrowList.containsKey(name)){
            System.out.println(name+" 's Borrowed Books : ");     
            ArrayList<String> books = borrowList.get(name);       
            for (String book :
            books){
                System.out.println(book);
            }
        }
        else{
            System.out.println("Borrower Did Not Borrow Any Books");
        }
    }
}

class Starter{
    public static void start(){
        System.out.println("=====STUDENT BORROWER TRACKER=====");
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        BorrowManager manager = new BorrowManager();
        while(!exit){
            System.out.println("================");
            System.out.println("[1] Borrow Book");
            System.out.println("[2] Return Book");
            System.out.println("[3] Display All Borrowers");
            System.out.println("[4] Display Borrowed Books Of A Student");
            System.out.println("[5] Exit");
            try{
                System.out.print("Your Choice : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 :{
                        System.out.print("Enter Borrower Name : ");
                        sc.nextLine();
                        String name = sc.nextLine().toLowerCase();
                        System.out.print("Enter Book Name : ");
                        String book = sc.nextLine().toLowerCase();
                        manager.borrowBook(name,book);
                        break;
                    }
                    case 2:{
                        System.out.print("Enter Borrower Name : ");
                        sc.nextLine();
                        String name = sc.nextLine().toLowerCase();
                        System.out.print("Enter Book Borrowed : ");
                        String book = sc.nextLine().toLowerCase();
                        manager.returnBook(name, book);
                        break;
                    }
                    case 3:{
                        manager.displayBorrowers();
                        break;
                    }
                    case 4:{
                        System.out.print("Enter Borrower Name : ");
                        sc.nextLine();
                        String name = sc.nextLine().toLowerCase();
                        manager.displayBorrower(name);
                        break;
                    }
                    case 5:{
                        exit = true;
                        System.out.println("Thank You For Using...");
                        break;
                    }
                    default:{
                        System.out.println("Enter number between 1-5 only!");
                        break;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Please enter a valid input!");
                sc.nextLine();
            }
        }
    }
}
public class Main {
	public static void main(String[] args) {
		Starter.start();
	}
}