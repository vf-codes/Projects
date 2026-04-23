import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		boolean exit = false;
		System.out.println("Welcome to Contacts App");
		String[] contact_names = new String [200];
		long[] contact_no = new long [200];
		int current_contacts = 0;
		Scanner sc = new Scanner(System.in);
		    
		
		while (exit == false){
		    System.out.println("------------------------------------------------");
		    System.out.println("[1]Add new Contact");
		    System.out.println("[2]Delete existing Contact");
		    System.out.println("[3]View All contacts");
		    System.out.println("[4]Search contacts");
		    System.out.println("[5]Exit");
		    System.out.println("");
		    
		    
		    try{
		    int menu_choice = sc.nextInt();
		    if (menu_choice>5 || menu_choice<1) {
		        System.out.println("Please choose a number from the given options");
		        continue;
		    }
		    switch (menu_choice){
		        case 1:{
		        System.out.println("Enter contact name");
		        sc.nextLine();
		        String name = sc.nextLine();
		        
		        System.out.println("Enter phone no.:");
		        long number = sc.nextLong();
		        contact_names[current_contacts] = name;
		        contact_no[current_contacts] = number;
		        current_contacts+=1;
		        System.out.println("Contact created successfully");
		        break;
		        }
		        case 2:{
		            System.out.println("Enter contact name");
		            sc.nextLine();
		            String deletion_name = sc.nextLine();
		            
		            boolean name_found = false;
		            int name_index = 0;
		            for (int i = 0; i < current_contacts; i++) {
    if (contact_names[i] != null && contact_names[i].equals(deletion_name)) {
        name_found = true;
        name_index = i;
        break;
    }
}
                    
		             if (name_found == false){
    System.out.println("Contact not found");
    break;
}
else{
    for (int i = name_index; i < current_contacts - 1; i++) {
        contact_names[i] = contact_names[i + 1];
        contact_no[i] = contact_no[i + 1];
    }

    contact_names[current_contacts - 1] = null;
    current_contacts--;
}
		        
                     
		             System.out.println("Contact deleted successfully");     
		             break;
		        }
		        case 3:{
		                for (int i = 0; i < current_contacts;i++){
		                    System.out.print(contact_names[i]);
		                    System.out.println("    "+contact_no[i]);
		                    }
		                System.out.println(""); 
		                System.out.println(" NOTE : Blank results indicate No contacts have been created");
		                System.out.println("");
		                break;
		            }
		        case 4:{
		            sc.nextLine();
		            System.out.println("Enter contact name:");
		            
		            String name = sc.nextLine();
		            boolean name_found = false;
		            int name_index = 0;
		            for (String i:contact_names){
		                if (i != null && i.equals(name)){
		                    name_found = true;
		                    break;
		                    }
		                else{
		                    name_index++;
		                    continue;
		                    }
		            }
		            
                    
		             if (name_found == false){
		                 System.out.println("Contact not found");
		                 break;
		             }
		             else{
		                 System.out.println();
		                 System.out.println(contact_names[name_index] + "      " + contact_no[name_index]);
		                 break;
		                 }
		             
		            }
		        case 5:{
		             exit = true;
		             break;}
		             
		            }		        		    
		    }	
		    	    
		    catch (Exception e){
		        System.out.println("Please enter a valid input");
		        System.out.println("Error Message:");
		        System.out.println(e);
		        sc.nextLine();
		        continue;
		    }
	}	
	}
}