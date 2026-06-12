import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Movie{
    protected String name;
    protected String timing;
    private int[][][] seats = {
        {
            {0,0,0,0,0},
            {0,0,0,0,0}
        },
        {
            {0,0,0,0,0},
            {0,0,0,0,0}
        },
        {
            {0,0,0,0,0},
            {0,0,0,0,0}
        }
    };
    
    public Movie(String name, String timing){
        this.name = name;
        this.timing = timing;
    }
    
    public void bookSeat(int floor, int row, int column){
        if (seats[floor][row][column] == 1) {
			System.out.println("RECEPTIONIST : Seat already booked!");
		} else {
			seats[floor][row][column] = 1;
			System.out.println("RECEPTIONIST : Seat booked!");
		}
    }
    
    public void cancelBooking(int floor, int row,int column){
        if (seats[floor][row][column] == 0) {
			System.out.println("RECEPTIONIST : Seat Is Not Booked!");
		} else {
			seats[floor][row][column] = 0;
			System.out.println("RECEPTIONIST : Seat Booking Cancelled Successfully!");
		}
    }
    
    public void displayMovieDetails(){
        System.out.println(name+", Timing : "+timing);
    }
    
    public void displaySeats() {
        System.out.println("All Seats :-");
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
    public void checkAvailableSeats(){
        displaySeats();
        System.out.println();
        System.out.println("Available Seats:");
        for (int floor = 0;floor<3;floor++){
            for (int row=0;row<2;row++){
                for (int column=0;column<5;column++){
                    if (seats[floor][row][column] == 0){
                        System.out.println("Floor :"+(floor+1)+", Row : "+(row+1)+",Column : "+(column+1));
                    }
                }
            }
        }
    }
            
}
class Cinema{
    
    private List<Movie> movies = Arrays.asList(new Movie("Dune 2","9:30 A.M"), new Movie("Avengers: Assemble","12:30 A.M"), new Movie("Summer","3:00 P.M"), new Movie("Dabangg", "5:15 P.M"), new Movie("Oddyssey","7:00 P.M"));
    
    public String getMovieName(int movieIndex){
        return movies.get(movieIndex).name;
    }
    
    public int getMoviesCount(){
        return movies.size();
    }
    
    public boolean validateSeat(int floor, int row, int column){
        if (floor > 2|| floor < 0){
            System.out.println("Invalid floor Number");
            return false;
        }
        if (row > 1|| row<0){
            System.out.println("Invalid Row Number!");
            return false;
        }
        if (column > 4|| column < 0){
            System.out.println("Invalid seat No.");
            return false;
        }
        return true;
    }
    public void
    viewMovieNameAndTimings(){
        for (int i=0;i<movies.size();i++){
            System.out.print("["+(i+1)+"] ");
            movies.get(i).displayMovieDetails();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void bookTicket(int movieIndex, int floor, int row,int column){
        movies.get(movieIndex).bookSeat(floor, row, column);
    }
    
    public void displayMovieSeats(int movieIndex){
        movies.get(movieIndex).displaySeats();
    }
    
    public void cancelTicket(int movieIndex, int floor, int row, int column){
        movies.get(movieIndex).cancelBooking(floor, row, column);
    }
    
    public void giveAvailableSeats(int movieIndex){
        movies.get(movieIndex).checkAvailableSeats();
    }
       
}

class Receptionist{
    
    public static void start(){
        Cinema cinema = new Cinema();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("=======WELCOME TO VF CINEMA=======");
        System.out.println("RECEPTIONIST : Hello, I am your receptionst. Choose one of the below options");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        while(!exit){
            System.out.println();
            System.out.println("=====================");
            System.out.println();
            System.out.println("[1] View Movies And Timings ");
            System.out.println("[2] Book Ticket ");
            System.out.println("[3] Cancel Booking");
            System.out.println("[4] Check Available Seats ");
            System.out.println("[5] Exit");
            System.out.println();
            System.out.print("Enter Your Choice(1-5) : ");
            try{
                int choice = sc.nextInt();
                switch (choice){
                    case 1:{
                        System.out.println("RECEPTIONIST : Given below are the movies and their respective timings");
                        System.out.println();
                        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
                        cinema.viewMovieNameAndTimings();
                        break;
                    }
                    case 2:{
                        System.out.println("RECEPTIONIST : Please enter the number against the movie you want to book tickets for");
                        System.out.println();
                        cinema.viewMovieNameAndTimings();
                        System.out.println();
                        System.out.print("Your Choice :(1-"+cinema.getMoviesCount()+") : ");
                        int movieIndex = sc.nextInt()-1;
                        if (movieIndex < 0 || movieIndex >= cinema.getMoviesCount()){
                            System.out.println("RECPETIONIST : Please enter a valid movie number!");
                            break;
                        }
                        else{
                            System.out.println();
                            System.out.println("RECEPTIONIST : Here are the seats for the movie : ");
                            try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
                            System.out.println();
                            cinema.displayMovieSeats(movieIndex);
                            System.out.println();
                            System.out.print("RECEPTIONIST : Enter floor No.(1-3):");
                            int floor = sc.nextInt()-1;
                            System.out.println();
                            System.out.print("Receptionist : Enter Row no.(1-2) :");         
                            int row = sc.nextInt()-1;
                            System.out.println();
                            System.out.print("Receptionist : Enter seat No.(1-5) : ");
                            int column = sc.nextInt()-1;
                            System.out.println();
                            if (cinema.validateSeat(floor, row, column)){
                                cinema.bookTicket(movieIndex, floor, row, column);
                                break;
                            }
                            else{
                                System.out.println("RECEPTIONIST : Please enter a valid input!");
                                break;
                            }             
                        }
                    }
                    case 3:{
                        System.out.println();
                        System.out.println("RECEPTIONIST : Please enter the number against the movie you want to cancel tickets for");
                        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
                        System.out.println();
                        cinema.viewMovieNameAndTimings();
                        System.out.println();
                        System.out.print("Your Choice :(1-"+cinema.getMoviesCount()+") : ");
                        int movieIndex = sc.nextInt()-1;
                        if (movieIndex < 0 || movieIndex >= cinema.getMoviesCount()){
                            System.out.println();
                            System.out.println("RECPETIONIST : Please enter a valid movie number!");
                            break;
                        }
                        else{
                            System.out.println("RECEPTIONIST : Here are the seats for the movie, Enter Your Seat to cancel booking!");                           
                            try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
                            cinema.displayMovieSeats(movieIndex);
                            System.out.println();
                            System.out.print("RECEPTIONIST : Enter floor No.(1-3):");
                            int floor = sc.nextInt()-1;
                            System.out.println();
                            System.out.print("Receptionist : Enter Row no.(1-2) :");         
                            int row = sc.nextInt()-1;
                            System.out.println();
                            System.out.print("Receptionist : Enter seat No.(1-5) : ");
                            int column = sc.nextInt()-1;
                            if (cinema.validateSeat(floor, row, column)){
                                cinema.cancelTicket(movieIndex, floor, row, column);
                                break;
                            }
                            else{
                                System.out.println();
                                System.out.println("RECEPTIONIST : Please enter a valid input!");
                                break;
                            }             
                        }
                    }
                    case 4:{
                        sc.nextLine();       
                        System.out.println("RECEPTIONIST : Please enter the integer against the respected movie name, you would like to check the available seats!");
                        System.out.println();
                        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e);
        }                 
                        cinema.viewMovieNameAndTimings();
                        System.out.println();
                        System.out.print("Your Choice (1-"+cinema.getMoviesCount()+") : ");
                        int movieIndex = sc.nextInt()-1;
                        if (movieIndex < 0 || movieIndex >= cinema.getMoviesCount()){
                            System.out.println("RECEPTIONIST : Invalid Movie Number! Please enter a valid number!");
                        }
                        else{
                            System.out.println("RECPETIONIST : Here are available Seats for movie "+cinema.getMovieName(movieIndex));
                            try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
                           cinema.giveAvailableSeats(movieIndex);                       
                        }
                        break;
                    }
                    case 5:{
                        System.out.println();
                        System.out.println("RECEPTIONIST : Thank You For Visiting VF CINEMA! Hope You come again! :)");
                        exit = true;
                        break;
                    }
                    default:{
                        System.out.println("RECEPTIONIST : Please enter a number between 1 and 5 only!");                      
                    }
                }                
            }
            catch (Exception e){
                System.out.println("PLEASE ENTER A VALID INPUT!");
                System.out.println("Error message : "+e);
                sc.nextLine();
            }
        }
        sc.close();
    }
}
public class Main {
	public static void main(String[] args) {
		Receptionist.start();
				
	}
}