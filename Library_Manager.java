import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to VF Library Manager");
		String[] book_names = new String[10];
		int[] book_id = new int[10];
		String[] status = new String[10];
		boolean exit = false;
		int current_books = 0;
		int issued_books = 0;
		while (!exit) {
			System.out.println(" ");
			System.out.println("-------------------------------");
			System.out.println("  ");
			System.out.println("[1]Add New Book");
			System.out.println("[2]Issue Book");
			System.out.println("[3]Return Book");
			System.out.println("[4]View All Books");
			System.out.println("[5]Exit");
			try {
				int choice = sc.nextInt();
				switch (choice) {
				case 5: {
					System.out.println(" ");
					System.out.println("Thank You For Using");
					exit = true;
					break;
				}
				case 1: {
					System.out.println(" ");
					if (current_books == 10) {
						System.out.println("Shelf Capacity reached!");
						System.out.println("Max capacity:10");
						System.out.println("Current capacity:10");
					} else {
						System.out.print("Enter book name:");
						sc.nextLine();
						String book_name = sc.nextLine();

						System.out.print("Enter book ID:");
						boolean unique_book_id = false;
						int new_book_id = 0;
						while (!unique_book_id) {
							new_book_id = sc.nextInt();
							if (current_books == 0) {
								unique_book_id = true;
							} else {
								boolean duplicate_found = false;
								for (int i : book_id) {
									if (new_book_id == i) {
										System.out.println("Please enter a unique book ID");
										duplicate_found = true;
										break;
									}
								}
								if (duplicate_found == false) {
									unique_book_id = true;
								}
							}
						}
						book_names[current_books] = book_name;
						book_id[current_books] = new_book_id;
						status[current_books] = "Not Issued";
						current_books += 1;
						System.out.println("Book Added to Library!");
						break;
					}
				}
				case 4: {
					System.out.println(" ");
					if (current_books == 0) {
						System.out.println("No books have been added to the library yet.");
					} else {
						System.out.println("ID Name Status");
						for (int i = 0; i < current_books; i++) {
							System.out.println(book_id[i] + " " + book_names[i] + " " + status[i]);
						}
					}
					break;
				}
				case 2: {
					System.out.println(" ");
					System.out.println(" Available books:");
					if (current_books == 0) {
						System.out.println("No books have been added to the library yet.");
					} else {
						for (int i = 0; i < current_books; i++) {
							if (status[i].equals("Issued")) {
								continue;
							} else {
								System.out.println(book_names[i] + " " + book_id[i]);
							}
						}
						boolean id_match = false;
						int issue_id = 0;
						while (!id_match) {
							System.out.print("Enter book Id:");
							issue_id = sc.nextInt();
							for (int i : book_id) {
								if (issue_id == i){
									id_match = true;
									break;
								}
							}
							if (!id_match) {
								System.out.println("Please choose a valid Book ID");
							}
						}
						int id_index = 0;
						for (int i = 0; i < current_books; i++) {
							if (book_id[i] == issue_id) {
								break;
							} else {
								id_index++;
							}
						}
						status[id_index] = "Issued";
						System.out.println("Book Issued Successfully!");
						issued_books++;
					}
					break;
				}
				case 3: {
					if (current_books == 0 || issued_books == 0) {
						System.out.println("No books have been added/issued to/from the library yet.");
					} else {
						System.out.println("Issued Books:");
						for (int i = 0; i < current_books; i++) {
							if (status[i].equals("Not Issued")) {
								continue;
							} else {
								System.out.println(book_names[i] + " " + book_id[i]);
							}
						}
					}
					    boolean id_there = false;
					    boolean id_issued = false;
						int return_id = 0;
						while (!id_issued) {
							System.out.print("Enter book Id:");
							return_id = sc.nextInt();
							for (int i : book_id) {
								if (return_id == i) {
									id_there = true;
									break;
								}
							}
							if (!id_there) {
								System.out.println("Please choose a valid Book ID");
								continue;
							}
							else{
							    int id_index = 0;
							    for (int i = 0; i < current_books; i++) {
							if (book_id[i] == return_id) {
								break;
							} else {
								id_index++;
							}
						}
						if (status[id_index].equals("Not Issued")){
							    id_issued = false;
							    System.out.println("The book with entered ID is not issued yet! Please enter a valid id");
							    }
						else{
							    status[id_index] = "Not Issued";
							    System.out.println("Book returned successfully");
							    id_issued = true;
							    }
							    }
						}
					break;
				}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
				System.out.println("Error message-" + e);
				System.out.println("");
			}
		}
	}
}