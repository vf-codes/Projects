import java.util.Scanner;
import java.util.HashMap;

class Contacts{
    HashMap<String,Long> contacts = new HashMap<>();
    
    public void add(String name,long number){
        if (contacts.containsKey(name)){
            System.out.println("Contact Already Exists!");
        }
        else{
            contacts.put(name, number);
            System.out.println("Contact added Successfully!");
        }
    }
    
    public void search(String name){
        if (contacts.containsKey(name)){
            System.out.println(name+" : "+contacts.get(name));
        }
        else{
            System.out.println("Contact Not Found!");
        }
    }
    
    public void delete(String name){
        if (contacts.containsKey(name)){
            contacts.remove(name);
            System.out.println("Contact Deleted Successfully!");
        }
        else{
            System.out.println("Contact Not Found!");
        }
    }
    
    public void display(){
        System.out.println("All Contacts--");
        for (String key:contacts.keySet()){
            System.out.println(key+" : "+contacts.get(key));
        }
    }
}

class Starter{
    public static void start(){
        Scanner sc = new Scanner(System.in);
        Contacts manager = new Contacts();
        boolean exit = false;
        System.out.println("======CONTACTS MANAGER======");
        while(!exit){
            System.out.println("====================");
            System.out.println("[1] Add Contact");
            System.out.println("[2] Delete Contact");
            System.out.println("[3] Search Contact");
            System.out.println("[4] Display Contacts");
            System.out.println("[5] Exit");
            try{
                int choice = sc.nextInt();
                switch (choice){
                    case 1:{
                        System.out.print("Enter New Contact Name : ");
                        sc.nextLine();
                        String name = sc.nextLine().toLowerCase();
                        System.out.print("Enter Contact No. : ");
                        long number = sc.nextLong();
                        manager.add(name, number);
                        break;
                    }
                    case 2:{
                        System.out.print("Enter Contact Name To Delete : ");
                        sc.nextLine().toLowerCase();
                        String name = sc.nextLine().toLowerCase();
                        manager.delete(name);
                        break;
                    }
                    case 3:{
                        System.out.print("Enter Contact Name To Search : ");
                        sc.nextLine();
                        String name = sc.nextLine().toLowerCase();
                        manager.search(name);
                        break;
                    }
                    case 4:{
                        manager.display();
                        break;
                       
                    }
                    case 5:{
                        exit = true;
                        System.out.println("Thank You For Using...");
                        break;
                    }
                    default : {
                        System.out.println("Please choose a value between 1-5 only!");
                        break;
                    }
                }
            }catch(Exception e){
                System.out.println("Please Enter a valid input!");
                sc.nextLine();
            }
        }
    }
}
public class PhoneBook {
	public static void main(String[] args) {
		Starter.start();
	}
}