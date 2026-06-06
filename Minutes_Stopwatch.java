import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter minutes : ");
		int minutes = sc.nextInt();
		System.out.println("Started stop watch!");
		System.out.println(minutes+" : "+"00");
		for (int i = minutes;i>0;){
		    i--;
		    for (int j = 59; j>=0;j--){
		        if (i==0 && j!=0){
		            System.out.println("00 : "+j);
		        }
		        else if(i!=0&&j==0){
		            System.out.println(i+" : 00");
		        }
		        else if (i==0&&j==0){
		            System.out.println("00 : 00");
		        }
		        else{
		            System.out.println(i+" : "+j);
		        }
		        try{
		            Thread.sleep(1000);
		        }
		        catch (Exception e){
		        }
		    }
		}
	}
}