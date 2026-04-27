import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
		Object[][] students = new Object[10][3];
		int current_students = 0;
		System.out.println("Welcome To Student Manager");
		while (!exit) {
			System.out.println("");
			System.out.println("--------------------------------------");
			System.out.println("[1]Add Student");
			System.out.println("[2]Search Student");
			System.out.println("[3]View All Records");
			System.out.println("[4]Exit");
			try {
				int choice = sc.nextInt();
				System.out.println("");
				switch (choice) {
				case 4: {
					System.out.println("Thank You For Using");
					exit = true;
					break;
				}
				case 1: {
					System.out.println("");
					System.out.print("Enter Students name:");
					sc.nextLine();
					String student = sc.nextLine();
					System.out.print("Enter roll number:");
					int roll_no = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter students marks:");
					int marks = sc.nextInt();
					students[current_students][0] = roll_no;
					students[current_students][1] = student;
					students[current_students][2] = marks;
					System.out.println("Student Details Added successfully");
					current_students++;
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.print("Enter students roll number:");
					int roll_no = sc.nextInt();
					boolean student_found = false;
					for (int i = 0; i < current_students; i++) {
						if (roll_no == (int)students[i][0]) {
							student_found = true;
							System.out.println("Student name:" + students[i][1]);
							System.out.println("Roll no.:" + roll_no);
							System.out.println("Marks:" + students[i][2]);
							break;
						}
					}
					if (!student_found) {
						System.out.println("Student not found!");
						break;
					}
					break;
				}
				case 3: {
					System.out.println("Roll no" + "     " + "Student Name" + "            " + "Marks");
					for (int i = 0; i < current_students; i++) {
						System.out.println(students[i][0] + " " + students[i][1] + " " + students[i][2]);
						System.out.println("");
					}
					if (current_students == 0) {
						System.out.println("No Students Found!");
						break;
					}
				}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
				System.out.println("Error message" + e);
			}


		}
	}
}