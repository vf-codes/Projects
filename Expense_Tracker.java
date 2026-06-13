import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

class Expense {
	private String title;
	private int amount;
	private LocalDate date;

	public Expense(String title, int amount, LocalDate date) {
		this.title = title;
		this.amount = amount;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public int getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String toString() {
		return (title + " | " + "₹" + amount + " | " + date);
	}
}

class Category {
	private List<Expense> expenses = new ArrayList<>();
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public void addExpense(String title, int amount, LocalDate date) {
		expenses.add(new Expense(title, amount, date));
		System.out.println("Expense Added Successfully!");
	}


	public String getName() {
		return name;
	}

	public void getTotal() {
		int total = 0;
		for (Expense expense : expenses) {
			total += expense.getAmount();
		}
		System.out.println("Total : ₹" + total);
	}

	public int giveTotal() {
		int total = 0;
		for (Expense expense : expenses) {
			total += expense.getAmount();
		}
		return total;

	}
	public void filterByAscendingOrderOfExpense() {
		List<Expense> tempExpenses = new ArrayList<>(expenses);
		Comparator<Expense> comp = (current, next)->(Integer.compare(current.getAmount(), next.getAmount()));
		Collections.sort(tempExpenses, comp);
		System.out.println("Filtered List Of Expenses(Ascending Amount) ->");
		System.out.println();
		for (Expense expense : tempExpenses) {
			System.out.println(expense);
		}
	}

	public void filterByAscendingOrderOfDate() {
		List<Expense> tempExpenses = new ArrayList<>(expenses);
		Comparator<Expense> comp = (current, next)->(current.getDate().isAfter(next.getDate())) ? 1 : -1;
		Collections.sort(tempExpenses, comp);
		System.out.println("Expenses Filtered By Ascending Order Of Dates->");
		System.out.println();
		for (Expense expense : tempExpenses) {
			System.out.println(expense);
		}
	}

	public void showExpenses() {
		for (Expense expense : expenses) {
			System.out.println(expense);
		}
	}

	public List<Expense> getExpenseList() {
		return expenses;
	}
}

class Tracker {
	List<Category> categories = new ArrayList<>(
		Arrays.asList(
			new Category("Food"),
			new Category("Bills"),
			new Category("Commute")
		)
	);

	public void displayCategories() {
		for (int i = 0; i < categories.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + categories.get(i).getName());
		}
	}

	public boolean validateCategoryIndex(int categoryIndex) {
		if (categoryIndex < 0 || categoryIndex >= categories.size()) {
			return false;
		}
		return true;
	}

	public void addCategory(String name) {
		for (Category category : categories) {
			if (category.getName().equalsIgnoreCase(name)) {
				System.out.println("Category already exists");
				return;
			}
		}
		categories.add(new Category(name));
		System.out.println("Category added successfully!");

	}

	public boolean validateDate(int date, int month, int year) {
		try {
			LocalDate.of(year, month, date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void addExpense(int categoryIndex, String title, int amount, LocalDate date) {
		
		if (categoryIndex < 0 || categoryIndex >= categories.size()) {
			System.out.println("Invalid category number!");
		} else {
			categories.get(categoryIndex).addExpense(title, amount, date);
		}
	}

	public void getTotal() {
		int total = 0;
		for (Category category : categories) {
			total += category.giveTotal();
		}

		System.out.println("Total across all categories : ₹" + total);
	}

	public void filterByExpense(int categoryIndex) {
		if (!validateCategoryIndex(categoryIndex)) {
			System.out.println("Invalid category number!");
		} else {
			categories.get(categoryIndex).filterByAscendingOrderOfExpense();
		}
	}


	public void filterByDate(int categoryIndex) {
		if (!validateCategoryIndex(categoryIndex)) {
			System.out.println("Invalid category number!");
		} else {
			categories.get(categoryIndex).filterByAscendingOrderOfDate();
		}
	}

	public void getTotalOfMonth(int month, int year) {
		int total = 0;
		if (validateDate(1, month, year)) {
			for (Category category : categories) {
				for (Expense expense : category.getExpenseList()) {
					if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
						total += expense.getAmount();
					}
				}
			}
			if (total == 0) {
				System.out.println("No expenses were made during that month!");
			} else {
				System.out.println("Total Expenses of " + month + "/" + year + " are ₹" + total);
			}
		} else {
			System.out.println("Invalid Date Entered!");
		}
	}

	public void getCategoryExpenses(int categoryIndex) {
		if (validateCategoryIndex(categoryIndex)) {
			System.out.println(categories.get(categoryIndex).getName() + " Expenses :-");
			categories.get(categoryIndex).showExpenses();
		} else {
			System.out.println("Invalid category number!");
		}
	}

	public void getAllExpenses() {
		for (Category category : categories) {
			System.out.println();
			System.out.println(category.getName() + " Expenses :-");
			System.out.println();
			category.showExpenses();
		}
		System.out.println();
		System.out.println("BLANK RESULTS INDICATE NO EXPENSES!");
	}
}

class Starter {

	public static void start() {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		Tracker tracker = new Tracker();
		System.out.println("==========VF EXPENSE TRACKER==========");
		while (!exit) {
			System.out.println();
			System.out.println("[1] ADD EXPENSE");
			System.out.println("[2] ADD CATEGORY");
			System.out.println("[3] VIEW CATEGORY EXPENSES");
			System.out.println("[4] VIEW ALL EXPENSES");
			System.out.println("[5] GET TOTAL OF EXPENSES OF A CATEGORY'S MONTH");
			System.out.println("[6] GET TOTAL OF EXPENSES");
			System.out.println("[7] GET FILTERED EXPENSES");
			System.out.println("[8] EXIT");
			System.out.println();
			try {
				System.out.print("Your Choice (1-8) : ");
				int choice = sc.nextInt();
				switch (choice) {
				case 1: {
					tracker.displayCategories();
					System.out.println();
					System.out.print("Enter a value against the desired categrory : ");
					int categoryIndex = sc.nextInt() - 1;
					System.out.println();
					sc.nextLine();
					System.out.print("Enter a title for the expense : ");
					String title = sc.nextLine();
					System.out.println();
					System.out.print("Enter amount : ");
					int amount = sc.nextInt();
					if (amount < 0){
					    System.out.println("Amount Cannot be negative!");
					    break;
					}
					System.out.println();
					System.out.print("Enter Date : ");
					int date = sc.nextInt();
					System.out.println();
					System.out.println("Enter Month (in number) : ");
					int month = sc.nextInt();
					System.out.println();
					System.out.print("Enter year : ");
					int year =  sc.nextInt();
					System.out.println();
					if (tracker.validateDate(date, month, year)){
					    tracker.addExpense(categoryIndex, title, amount, LocalDate.of(year, month, date));
					}
					else{
					    System.out.println("Invalid date");
					}
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.print("Enter name of New Category : ");
					String name = sc.nextLine();
					tracker.addCategory(name);
					break;
				}
				case 3: {
					tracker.displayCategories();
					System.out.print("Enter the number of the desired category : ");
					int categoryIndex = sc.nextInt() - 1;
					tracker.getCategoryExpenses(categoryIndex);
					break;
				}
				case 4: {
					tracker.getAllExpenses();
					break;
				}
				case 5: {
					System.out.println();
					System.out.print("Enter The number of the month (1-12) : ");
					int month = sc.nextInt();
					System.out.print("Enter the year of the month : ");
					int year = sc.nextInt();
					tracker.getTotalOfMonth(month, year);
					break;
				}
				case 6: {
					tracker.getTotal();
					break;
				}
				case 7: {
					System.out.println();
					System.out.println(" [1] FILTER CATEGORY EXPENSES BY EXPENSES(ASCENDING)");
					System.out.println("[2] FILTER CATEGORY EXPENSES BY DATE(EARLIEST->LATEST)");
					System.out.println();
					System.out.print("Your Choice(1-2) : ");
					int choice2 = sc.nextInt();
					System.out.println();
					tracker.displayCategories();
					System.out.println();
					System.out.print("Enter the number against the desired category : ");
					int categoryIndex = sc.nextInt() - 1;
					switch (choice2) {
					case 1: {
						tracker.filterByExpense(categoryIndex);
						break;
					}
					case 2: {
						tracker.filterByDate(categoryIndex);
						break;
					}
					default: {
						System.out.println(categoryIndex);
						System.out.println("Invalid Choice Of Filtering");
					}
					}
					break;
				}
				case 8: {
					exit = true;
					System.out.println("THANK YOU FOR USING! :)");
					break;
				}
				default: {
					System.out.println("PLEASE CHOOSE A VALUE BETWEEN 1-8 ONLY!");
				}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input!");
				System.out.println("Error Message : " + e);
				sc.nextLine();
			}
		}
	}
}
public class Main {
	public static void main(String[] args) {
		Starter.start();
	}
}