import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] tasks = new String [10];
		String[] task_status = new String [10];
		boolean exit = false;
		System.out.println("WELCOME TO TO DO APP");
		int current_tasks = 0;
		while (exit == false) {
			System.out.println("");
			System.out.println("-------------------------------");
			System.out.println("");
			System.out.println("[1]Add task");
			System.out.println("[2]View all tasks");
			System.out.println("[3]Delete task");
			System.out.println("[4]Update task status");
			System.out.println("[5]Exit");
			try {
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 5: {
					exit = true;
					System.out.println("Thank You For using");
					break;
				}
				case 1: {
					System.out.println("Enter your task:");
					String task = sc.nextLine();
					tasks[current_tasks] = task;
					task_status[current_tasks] = "Scheduled";
					current_tasks += 1;
					System.out.println("Task Added successfully");
				}
				break;
				case 2: {
					System.out.println("Task                          Status");
					for (int i = 0; i < current_tasks; i++) {
						System.out.println(tasks[i] + "                     " + task_status[i]);
					}
					System.out.println("  ");
					System.out.println("NOTE:Blank Results indicate no tasks created");
					break;
				}
				case 4: {
					System.out.println("Enter task number to update");
					int update = sc.nextInt();
					if (update > current_tasks || update < current_tasks) {
						System.out.println("ERROR:Invalid task number");
						break;
					} else {
						task_status[update - 1] = "Finished";
						System.out.println("Task updated as finished successfully");
						break;
					}
				}
				case 3: {
					System.out.println("Enter task number to delete");
					int task_delete = sc.nextInt();
					if (task_delete > current_tasks || task_delete < current_tasks) {
						System.out.println("ERROR:Invalid task number");
						break;
					} else {
						task_delete -= 1;
						for (int i = 0; i < current_tasks; i++) {
							if (i == task_delete) {
								tasks[task_delete] = tasks[task_delete + 1];
								task_status[task_delete] = task_status[task_delete + 1];
								task_delete += 1;
							}
						}
						current_tasks -= 1;
						System.out.println("Task deleted successfully");
						break;
					}
				}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
			}
		}
	}
}