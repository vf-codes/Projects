import java.util.Scanner;

class Cinema {

	int [][][]seats = {
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		},
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		},
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		}
	};

	public void displaySeats() {
		int floor_count = 1;
		for (int floor[][] : seats) {
			System.out.println();
			System.out.println();
			System.out.println("Floor " + floor_count);
			floor_count++;
			System.out.println("__________________________");
			System.out.println("__________________________");
			for (int row[] : floor) {
				System.out.println();
				for (int seat : row) {
					System.out.print(seat + " ");
				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("0-Not booked");
		System.out.println("1-Booked");
	}

	public String countBookedSeats() {
		int booked_seats = 0;

		for (int floor[][] : seats) {
			for (int row[] : floor) {
				for (int seat : row) {
					if (seat == 1) {
						booked_seats++;
					}
				}
			}
		}

		return ("Booked seats:" + booked_seats);
	}

	public void bookSeat(int floor, int row, int column) {
		if (seats[floor][row][column] == 1) {
			System.out.println("Seat already booked!");
		} else {
			seats[floor][row][column] = 1;
			System.out.println("Seat booked!");
		}
	}

}


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Cinema cine = new Cinema();
		boolean exit = false;
		System.out.println("Welcome to Cinema Booker!");
		while (!exit) {
			try {
				System.out.println("___________________");
				System.out.println("___________________");
				System.out.println("[1]Book seat");
				System.out.println("[2]Display All Seats");
				System.out.println("[3]Count Booked Seats");
				System.out.println("[4]Exit");
				System.out.println();
				System.out.print("Your choice : ");
				int choice = sc.nextInt();

				switch (choice) {
				case 4: {
					System.out.println("Thank You for using!");
					exit = true;
					break;
				}
				case 2: {
					cine.displaySeats();
					break;
				}
				case 3: {
					System.out.println(cine.countBookedSeats());
					break;
				}
				case 1: {
					System.out.println("All Seats:");
					cine.displaySeats();
					System.out.print("Enter floor no. : ");
					int input_floor = sc.nextInt();
					if (input_floor > 3 || input_floor < 1) {
						System.out.println("Please enter a valid floor number!");
						break;
					} else {
						input_floor--;
						System.out.print("Enter row no. : ");
						int input_row = sc.nextInt();
						if (input_row > 2 || input_row < 1) {
							System.out.println("Choose row 1 or 2 only!");
							break;
						} else {
							input_row--;
							System.out.println("Choose Seat from 1-5");
							System.out.print("Enter seat no. : ");
							int input_seat = sc.nextInt();
							if (input_seat > 5 || input_seat < 1) {
								System.out.println("Choose seat from 1-5 only!");
								break;
							} else {
								input_seat--;
								cine.bookSeat(input_floor, input_row, input_seat);
								break;
							}
						}
					}
				}
				default: {
					System.out.println("Enter numbers 1-4 only!");
				}
				}
			} catch (Exception e) {
				sc.next();
				System.out.println("Enter valid input!");
			}
		}

	}
}