import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your password:");
		String password = sc.nextLine();
		int password_strength = 0;
		//for checking if password length is greater than 8 words
		if (password.length() >= 8){
		    password_strength+=1;
		} 
		
		char [] password_char = password.toCharArray();
        char [] upper_chars = {'A', 'B', 'C', 'D', 'E','F','G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U','V', 'W', 'X', 'Y', 'Z'};
    
    boolean has_upper_char = false;
    for (char i:password_char){
        for (char j:upper_chars){
            if (j==i){
                has_upper_char = true;
                break;
            }
    }
    }
    if (has_upper_char==true){
        password_strength+=1;
    }
    
    char[] lower_chars = {
    'a','b','c','d','e','f','g','h','i','j','k','l','m',
    'n','o','p','q','r','s','t','u','v','w','x','y','z'
};
    boolean has_lower_char = false;
    for (char i:password_char){
        for (char j:lower_chars){
            if (j==i){
                has_lower_char = true;
                break;
            }
    }
    }
    
    if (has_lower_char==true){
        password_strength+=1;
    }
    

    char[] digits = {'0','1','2','3','4','5','6','7','8','9'};    
    boolean has_numbers = false;
    for (char i:password_char){
        for (char j:digits){
            if (j==i){
                has_numbers = true;
                break;
            }
    }
    }
    
    if (has_numbers==true){
        password_strength+=1;
        }
        
    char[] specialChars = {
    '!', '@', '#', '$', '%', '^', '&', '*',
    '(', ')', '-', '_', '=', '+',
    '[', ']', '{', '}', ';', ':',
    '\'', '"', ',', '.', '<', '>',
    '/', '?', '\\', '|', '`', '~',
    '€', '£', '¥', '₹', '©', '®', '™'
};

    boolean has_special_character = false;
    for (char i:password_char){
        for (char j:specialChars){
            if (j==i){
                has_special_character = true;
                break;
            }
    }
    }
    
    if (has_special_character==true){
        password_strength+=1;
    }
    
    
    System.out.println("Password Score:"+password_strength);
    
    switch (password_strength){
        case 0:
        System.out.println("Rating:Empty Password?");
        break;
        case 1:
        System.out.println("Rating:Weak");
        break;
        case 2:
        System.out.println("Rating:vulnerable");
        break;
        case 3:
        System.out.println("Rating:Ok");
        break;
        case 4:
        System.out.println("Rating:Strong");
        break;
        case 5:
        System.out.println("Rating:Very Strong");
        break;
        default:
        System.out.println("?");       
        break;         
    }
	}
}