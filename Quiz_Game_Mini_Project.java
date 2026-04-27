import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Quiz game
		Scanner sc = new Scanner(System.in);
		int score = 0;
		
		//Question 1
		System.out.println("Quiz Game");
		System.out.println("-----------------");
		System.out.println("Q.1 What is the smallest planet in our solar system?");
		System.out.println("a:Earth");
		System.out.println("b:Mercury");
		System.out.println("c:Mars");
		System.out.println("d:Jupiter");
		char answer1 = sc.next().charAt(0);
		if (answer1 == 'b'){
		    System.out.println("Your Answer is correct!");
		    score+=1;
		}
		else{
		    System.out.println("Your Answer is incorrect!");
		}
		System.out.println("--------------------------------------------------------------");
		
		//Question 2
		System.out.println("Q.2 Which country won the FIFA world cup in 2022?");
		System.out.println("a:Argentina");
		System.out.println("b:Germany");
		System.out.println("c:England");
		System.out.println("d:Spain");
		char answer2 = sc.next().charAt(0);
		if (answer2 == 'a'){
		    System.out.println("Your Answer is correct!");
		    score+=1;
		}
		else{
		    System.out.println("Your Answer is incorrect!");
		}
		System.out.println("--------------------------------------------------------------");
		
		//Question 3
		System.out.println("Q.3 In computing, what does 'CPU' refer to?");
		System.out.println("a:Random Access Memory");
		System.out.println("b:Memory");
		System.out.println("c:Central Processing Unit");
		System.out.println("d:Hard drive");
		char answer3 = sc.next().charAt(0);
		if (answer3 == 'c'){
		    System.out.println("Your Answer is correct!");
		    score+=1;
		}
		else{
		    System.out.println("Your Answer is incorrect!");
		}
		System.out.println("--------------------------------------------------------------");
		
		//Question 4
		System.out.println("Q.4 What is the chemical symbol for Sodium?");
		System.out.println("a:Hg");
		System.out.println("b:Na");
		System.out.println("c:Ps");
		System.out.println("d:Au");
		char answer4 = sc.next().charAt(0);
		if (answer4 == 'b'){
		    System.out.println("Your Answer is correct!");
		    score+=1;
		}
		else{
		    System.out.println("Your Answer is incorrect!");
		}
		System.out.println("--------------------------------------------------------------");
		
		//Question 5
		System.out.println("Q.5 Who wrote the play Romeo and Juliet");
		System.out.println("a:Narendra Modi"); 
		System.out.println("b:Will Smith");
		System.out.println("c:M.S Chauhan");
		System.out.println("d:William Shakespeare");
		char answer5 = sc.next().charAt(0);
		if (answer5 == 'd'){
		    System.out.println("Your Answer is correct!");
		    score+=1;
		}
		else{
		    System.out.println("Your Answer is incorrect!");
		}
		System.out.println("--------------------------------------------------------------");
		
		System.out.println("Final Score:"+score);
	}
}