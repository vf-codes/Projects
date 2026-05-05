import java.util.Random;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome To The Memory Game");
		System.out.println("--------------------------------");
		System.out.println("Repeat the sequence correctly. The sequence gets longer everytime. Beat your High Score");
		int score = 0;
		Random rand = new Random();
		String sequence = "";
		for (int i = 0; i < 3; i++){
		    sequence += rand.nextInt(10);
		    }
		boolean gameOver = false;
	    Scanner sc = new Scanner(System.in);
	    while (!gameOver){
	        System.out.println("");
		System.out.println("--------------------------------");	        
	        System.out.println("");
	        System.out.println("Sequence:"+sequence);
	        System.out.print("Your sequence:");
	        String userSequence = sc.nextLine();
	        if (userSequence.equals(sequence)){
	            System.out.println("Nice! Your sequence matched!");
	            score+=1;
	            System.out.println("Current score:"+score);
	            sequence += rand.nextInt(10);
	            }
	        else{
	            System.out.println("Your sequence didnt match!");
	            System.out.println("Your score:"+score);
	            System.out.println("GAME OVER");
	            gameOver = true;
	            }
	        }
		
	}
}