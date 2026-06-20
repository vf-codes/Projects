import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

//Enum for attendance states
enum ATTENDANCESTATES {
	ABSENT, PRESENT
}

/*
main student class for maintaing student and their attendance records.
Has getters and setters for all fields.

*/
class Student {
    //Class fields
	private String name;
	private String className;
	private int id;
	private HashMap<LocalDate, ATTENDANCESTATES>attendanceRecord = new HashMap<>();

    //constructor for student class
	public Student(String name, String className, int id) {
		this.name = name;
		this.className = className;
		this.id = id;
	}
	
	public String getName(){
	    return name;
	}
    
    public String getClassName(){
        return className;
    }
    
    public int getID(){
        return id;
    }
    
    //GETTERS AND SETTERS
    public HashMap<LocalDate, ATTENDANCESTATES> getAttendanceHashMap(){
        return attendanceRecord;
    }
    
    public void setName(String newName){
        name = newName;
        System.out.println("Name Changed Successfully!");
    }
    
    public void setClassName(String newClassName){
        className = newClassName;
        System.out.println("Name Changed Successfully!");
    }
    
    public int getAttendancePercentage(){
        if (attendanceRecord.isEmpty()){
            return 0;
        }
        int presentCount = 0;
        int totalDays = 0;
        
        for (LocalDate day : attendanceRecord.keySet()){
            totalDays++;
            if (attendanceRecord.get(day) == ATTENDANCESTATES.PRESENT){
                presentCount++;
            }
        }
        return (int)((double)(presentCount/totalDays)*100);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Student other = (Student) obj;
    
        return this.id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
    @Override
    public String toString(){
        return id+" | "+name+" | "+className+" | "+getAttendancePercentage()+"%";
    }
    
    public boolean addAttendance(LocalDate date, ATTENDANCESTATES state){
        if (attendanceRecord.keySet().contains(date)){
            return false;
        }
        else{
            attendanceRecord.put(date, state);
            return true;
        }
   }
   
   public void deleteAttendanceRecord(){
       attendanceRecord.clear();
   }     
}


/*
Attendance manager class to handle attendance related tasks like lookups, reporting, adding students marking attendances. Has an hashmap of id as key and student class object as value.
*/
class AttendanceManager{
    
    //hashmap of unique student id as key and student object as value
    
    HashMap<Integer, Student> students = new HashMap<>();
    
    
    /*
    
    STUDENT
    MANAGEMENT
    SECTION
    CODE
    
    */
    
    
    //Getter for the students hashmap
    public HashMap<Integer, Student> getStudentsHashMap(){
        return students;
    }
    
    public List<Student> getStudentObjects(){
        List<Student> studentObjects = new ArrayList<>(students.values());
        return studentObjects;
    }
    //student adding method that avoids duplicates by checking id. name and class name can be same but id should be unique
    
    public boolean addStudent(Student student){
        if (students.values().contains(student)){
            return false;
        }
        else{
            students.put(student.getID(), student);
            return true;
        }
    }
    
    //method to remove student while checking if student exists or not
    public boolean removeStudent(int id){
        if (students.isEmpty()){
            return false;
        }
        
        if (students.keySet().contains(id)){
            students.remove(id);
            return true;
        }
        return false;
    }
    
    //method to update name of a student. first checks if student exists and then updates name.
    public boolean updateStudentName(int id, String newName){
        if (students.isEmpty()){
            return false;
        }
        
        if (students.keySet().contains(id)){
            students.get(id).setName(newName);
            return true;
        }
        return false;
    }
    
    //method to update class of a student
    public boolean updateStudentClass(int id, String newClassName){
        if (students.isEmpty()){
            return false;
        }
        
        if (students.keySet().contains(id)){
            students.get(id).setClassName(newClassName);
            return true; 
        }
        return false;
    }
    
    //Student exists method to check if student is there using id
    public boolean studentExists(int id){
        if (students.isEmpty()){
            return false;
        }
        if (students.keySet().contains(id)){
            return true;
        }
        return false;
    }
    
    //Student exists method to check if student is there using name of student
    public boolean studentExists(String name){
        if (students.isEmpty()){
            return false;
        }
        boolean studentFound = false;
        for (Student student : students.values()){
            if (student.getName().equals(name)){
                studentFound = true;
                break;
            }
        }
        return studentFound;
    }
    
    //Method to Print student details(Prints toString of student object)
    
    public void getStudentDetails(int id){
         System.out.println(students.get(id));
    }
    
    
    /*
    
    ATTENDANCE
    MANAGEMENT 
    SECTION
    CODE
    
    */
    
    
    //Method to check validity of date if it is in future or just technically invalid
    
    public boolean checkDateValidity(int date, int month, int year){
        try{
            LocalDate enteredDate = LocalDate.of(year, month, date);
            if (enteredDate.isAfter(LocalDate.now())){
                System.out.println("Date Cannot Be In future");
                return false;
            }
            return true;
        }
        catch (DateTimeException e) {
            System.out.println("Invalid Date Format!");
            return false;
        }
    }
    
    // Returns a list of student objects who were present on the given day. so that we can view attendance by date. the user interface will directly loop over the class and print the student objects toString.
    
    public List<Student> getAttendanceOnDate(LocalDate date){
        List<Student> filteredStudents = students.values().stream().filter(student->student.getAttendanceHashMap().keySet().contains(date)).filter(student-> student.getAttendanceHashMap().get(date).equals(ATTENDANCESTATES.PRESENT)).collect(Collectors.toList());
        return filteredStudents;
        
    }
    
    
    //Method to return the student object when given an id. this object will be used by the user interface to give individual report of that student. it will get the hashmap attendance record and display it to the user while also giving other details of the student.
    
    public Student getStudentObject(int id){
        return students.get(id);
    }
    
    /*method to delete attendance record
    checks if student exists 
    if no then return false 
    if yes then call deleteAttendanceRecord method of the student object and clear its hashmap of attendanceRecord
    return true
    */
    public boolean deleteAttendanceRecord(int id){
        if (studentExists(id)){
            students.get(id).deleteAttendanceRecord();
            return true;
        }
        return false;
    }
    
    //gets list of students who have attendance percentage below 75 from students hashmap
    
    public List<Student> getDefaultersList(){
        return students.values().stream().filter(student->student.getAttendancePercentage() < 75).collect(Collectors.toList());
    }
    
    //returns list of student objects of a particular class as provided by user input
    
    public List<Student> getClassStudents(String className){
        return students.values().stream().filter(student-> student.getClassName().equals(className)).collect(Collectors.toList());
    }
    
    //returns the attendance percentage on a particular date using the previously defined method of getAttendanceOnDate to get the student objects list present on that date and then diving that size of the list by the size of  students list and * by 100
    
    public int getAttendancePercentageOfADate(LocalDate date){
        List<Student> presentStudents = getAttendanceOnDate(date);
        return (int)((double)(presentStudents.size()/students.size())* 100);
    }
    
}



/*


                                USER
                                INTERFACE
                                SECTION
                                CODE


*/


//User interface class manages user Input and operations


class UserInterface{
    
    //  necessary fields for user input taking and for checking if user exits or not also variable to refer to attendance manager class object 
    
    Scanner sc = new Scanner(System.in);
    boolean exit = false;
    AttendanceManager attendanceManager = new AttendanceManager();
    
    //method to start the program or user input flow.
    
    public void startProgram(){
        while (!exit){
            System.out.println();
            System.out.println("=============================");
            System.out.println("MAIN MENU");
            System.out.println("=============================");
            System.out.println();
            System.out.println("[1] STUDENT MANAGEMENT");
            System.out.println("[2] ATTENDANCE MANAGEMENT");
            System.out.println("[3] REPORTS & ANALYTICS");
            System.out.println("[4] SEARCH & FILTERS");
            System.out.println("[5] EXIT");
            System.out.println();
            System.out.print("Your Choice(1-5) : ");
            try{
                int choice = sc.nextInt();
                switch (choice){
                    case 1:{
                        showStudentManagementMenu();
                        break;
                    }
                    case 2:{
                        showAttendanceManagementMenu();
                        break;
                    }
                    case 3:{
                        showReportsAnalyticsMenu();
                        break;
                    }
                    case 4:{
                        showSearchFilterMenu();
                        break;
                    }
                    case 5:{
                        System.out.println("Thank You For Using!");
                        exit = true;
                        break;
                    }
                    default:{
                        System.out.println("Please enter number between 1-5 only!");                        
                    }
                }
            }
            catch (Exception e){
                System.out.println("Invalid Input!");
                System.out.println("Error Message : "+e);
                sc.nextLine();
            }
        }
    }    
    //Method to show student management menu and taking user input and then calling the respective method
    
    private void showStudentManagementMenu(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("STUDENT MANAGEMENT");
        System.out.println("=============================");      
        System.out.println();
        System.out.println("[1] ADD STUDENT");
        System.out.println("[2] REMOVE STUDENT");
        System.out.println("[3] UPDATE STUDENT DETAILS");
        System.out.println("[4] VIEW ALL STUDENTS");
        System.out.println("[5] MAIN MENU");
        System.out.print("Your Choice(1-5) : ");
        try{
            int choice2 = sc.nextInt();
            switch (choice2){
                case 1:{
                    addStudent();
                    break;
                }
                case 2:{
                    removeStudent();
                    break;
                }
                case 3:{
                    updateStudentDetails();
                    break;
                }
                case 4:{
                    viewAllStudents();
                    break;
                }
                default:{
                    System.out.println("Redirecting to Main Menu");
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println("Error message : "+e);
            sc.nextLine();                      
        }
        
    }
    
    //method to view description or details of all students on record
    private void viewAllStudents(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("ALL STUDENTS");
        System.out.println("=============================");
        List<Student> students = attendanceManager.getStudentObjects();
        if (students.isEmpty()){
            System.out.println("No Students Added Yet!");
            return;
        }
        System.out.println("ID | NAME | CLASS NAME | ATTENDANCE%");
        for (Student student : students){
            System.out.println(student);
        }        
    }
    
    //method to know if user wants to update name or class name of student
    private void updateStudentDetails(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("UPDATE DETAILS");
        System.out.println("=============================");
        System.out.println("[1] UPDATE NAME OF STUDENT");
        System.out.println("[2] UPDATE CLASS NAME OF STUDENT");
        int choice3 = sc.nextInt();
        switch (choice3){
            case 1:{
                updateNameOfStudent();
                break;
            }
            case 2:{
                updateClassNameOfStudent();
                break;
            }
            default:{
                System.out.println("REDIRECTING TO MAIN MENU");
            }
        }
    }
    
    //updating class Name of student by taking id and validating if student exists and then updating .
    private void updateClassNameOfStudent(){
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        System.out.print("Enter New Class Name Of Student : ");
        sc.nextLine();
        String className = sc.nextLine().toLowerCase();
        boolean status = attendanceManager.updateStudentClass(id, className);
        if (status){
            System.out.println("Student Class Name Updated Successfully!");
        }
        else{
            System.out.println("Student Doesnt Exist");
        }
    }
    
    //method to update name of student by taking id and validating if student exists and then updating
    private void updateNameOfStudent(){
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        if (attendanceManager.studentExists(id)){
            sc.nextLine();                       
            System.out.print("Enter New Name Of Student : ");
            String newName = sc.nextLine().toLowerCase();
            attendanceManager.updateStudentName(id, newName);
            System.out.println("Student Name Update Successfully!");                
        }
        else{
            System.out.println("Student Doesnt Exist!");
        }
    }
    
    // method to remove student by taking id and then validating if student exists and then removing him.
    private void removeStudent(){
            System.out.print("Enter ID Of Student : ");
            int id = sc.nextInt();
            boolean status = attendanceManager.removeStudent(id);
            if (status){
                System.out.println("Student Removed Successfully!");
            }
            else{
                System.out.println("Student Couldnt Be removed! Check If student exists or if Student List Is empty");
            }        
    }
    
    //Method to add student by taking necessary inputs and validating if student already exists or not
    private void addStudent(){
        System.out.print("Enter Name Of Student : ");
        sc.nextLine();
        String name = sc.nextLine().toLowerCase();
        System.out.print("Enter Class Of Student : ");
        String className = sc.nextLine().toLowerCase();
        System.out.print("Enter Unique ID Of Student : ");
        int id = sc.nextInt();
        Student studObj = new Student(name, className, id);
        boolean status = attendanceManager.addStudent(studObj);
        if (status){
            System.out.println("Student Added Successfully!");           
        }
        else{
            System.out.println("Student Already Exists!");
        }
    }
    
    
    
    
    
    //Method to show attendance management menu and taking user input and then calling the respective method
    
    private void showAttendanceManagementMenu(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("ATTENDANCE MANAGEMENT");
        System.out.println("=============================");
        System.out.println();
        System.out.println("[1] MARK ATTENDANCE OF A STUDENT");
        System.out.println("[2]MARK ATTENDANCE OF ALL STUDENTS");
        System.out.println("[3] VIEW ATTENDANCE BY DATE");
        System.out.println("[4] VIEW ATTENDANCE OF STUDENT");
        System.out.println("[5] DELETE ATTENDANCE RECORD OF A STUDENT");
        System.out.println("[6] MAIN MENU");       
        System.out.print("Your Choice(1-6) : ");
        try{
            int choice2 = sc.nextInt();
            switch (choice2){
                case 1:{
                    markAttendance();
                    break;
                }
                case 2:{
                    markAttendanceOfAllStudents();
                    break;
                }
                case 3:{
                    viewAttendanceByDate();
                    break;
                }
                case 4:{
                    viewAttendanceOfAStudent();
                    break;
                }
                case 5:{
                    deleteAttendanceOfAStudent();
                    break;
                }
                default:{
                    System.out.println("Redirecting To Main Menu");
                }
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println("Error message : "+e);
            sc.nextLine();                      
        }
    }
        
    //Method to delete attendance of a student by id validation
    
    private void deleteAttendanceOfAStudent(){
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        boolean status = attendanceManager.deleteAttendanceRecord(id);
        if (status){
            System.out.println("Student Attendance Record Deleted Successfully!");
        }
        else{
            System.out.println("Student Doesnt Exist!");
        }
    }
    
    
    //Method to view all attendance entries of a student
    
    private void viewAttendanceOfAStudent(){
        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();
        if (attendanceManager.studentExists(id)){
            Student student = attendanceManager.getStudentObject(id);
            HashMap<LocalDate, ATTENDANCESTATES> attendanceEntries = student.getAttendanceHashMap();
            if (attendanceEntries.isEmpty()){
                System.out.println("No Attendance Record Of Student Found!");
                return;
            }
            for (LocalDate key : attendanceEntries.keySet()){
                System.out.println(key+" : "+attendanceEntries.get(key));
            }
        }
        else{
            System.out.println("Student Doesnt Exist");
        }
    }
    
    //Method to view attendance of a date that is it shows the students present on that day.
    private void viewAttendanceByDate(){
        System.out.print("Enter Date : ");
            int date = sc.nextInt();
            System.out.print("Enter Month(1-12) : ");
            int month = sc.nextInt();
            System.out.print("Enter Year : ");
            int year = sc.nextInt();
            boolean validDate = attendanceManager.checkDateValidity(date, month, year);
            if (validDate){
                LocalDate inputDate = LocalDate.of(year, month, date);
                List<Student> students = attendanceManager.getAttendanceOnDate(inputDate);
                if (students.isEmpty()){
                    System.out.println("No Students Present On That Day!");
                    return;
                }
                System.out.println("=============================");
                System.out.println("ALL STUDENTS PRESENT");
                System.out.println("=============================");
                for (Student student : students){
                    System.out.println(student.getName());
                }
            }
            else{
                System.out.println("Redirecting To Main Menu!");
            }
    }
    
    
    //method to mark attendance of all students also put many validation points for date and student id as well as avoiding duplicate attendance on a same date.
    
    private void markAttendanceOfAllStudents(){
        List<Student> students = attendanceManager.getStudentObjects();
        if (students.isEmpty()){
            System.out.println("Please Add Students First To Mark Attendance");
            return;
        }
        System.out.print("Enter Date : ");
            int date = sc.nextInt();
            System.out.print("Enter Month(1-12) : ");
            int month = sc.nextInt();
            System.out.print("Enter Year : ");
            int year = sc.nextInt();
            boolean validDate = attendanceManager.checkDateValidity(date, month, year);
            if (validDate){
                LocalDate inputDate = LocalDate.of(year, month, date);
                for (Student student : students){
                    System.out.println("Mark "+student.getName()+"'s Attendance : ");
                    System.out.println("[1] PRESENT");
                System.out.println("[2] ABSENT");
                System.out.print("Enter Choice (1-2) : ");
                int attendanceChoice = sc.nextInt();
                switch (attendanceChoice){
                    case 1:{
                        boolean status = student.addAttendance(inputDate, ATTENDANCESTATES.PRESENT);
                        if (status){
                            System.out.println("Attendance Marked Sucessfully!");
                        }
                        else{
                            System.out.println("Student Attendance Already Marked On The Given Date!");
                        }
                        break;
                    }
                    case 2:{
                        boolean status = student.addAttendance(inputDate, ATTENDANCESTATES.ABSENT);
                        if (status){
                            System.out.println("Attendance Marked Sucessfully!");
                        }                        
                        else{
                            System.out.println("Student Attendance Already Marked On The Given Date!");
                        }
                        break;
                    }
                    default:{
                        System.out.println("INVALID INPUT! Failed To Mark "+student.getName()+"'s attendance");
                    }
                }
                }
            }
            else{
                System.out.println("Invalid Date");
                System.out.println("Redirecting to main menu!");
            }
    }
    
    
    //method to mark attendance of a student also put many validation points for date and student id as well as avoiding duplicate attendance on a same date.
    private void markAttendance(){
        System.out.println();
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        if (attendanceManager.studentExists(id)){
            Student student = attendanceManager.getStudentObject(id);
            System.out.print("Enter Date : ");
            int date = sc.nextInt();
            System.out.print("Enter Month(1-12) : ");
            int month = sc.nextInt();
            System.out.print("Enter Year : ");
            int year = sc.nextInt();
            boolean validDate = attendanceManager.checkDateValidity(date, month, year);
            if (validDate){
                LocalDate inputDate = LocalDate.of(year, month, date);
                System.out.println("Mark Attendance : ");
                System.out.println("[1] PRESENT");
                System.out.println("[2] ABSENT");
                System.out.print("Enter Choice (1-2) : ");
                int attendanceChoice = sc.nextInt();
                switch (attendanceChoice){
                    case 1:{
                        boolean status = student.addAttendance(inputDate, ATTENDANCESTATES.PRESENT);
                        if (status){
                            System.out.println("Attendance Marked Sucessfully!");
                        }
                        else{
                            System.out.println("Student Attendance Already Marked On The Given Date!");
                        }
                        break;
                    }
                    case 2:{
                        boolean status = student.addAttendance(inputDate, ATTENDANCESTATES.ABSENT);
                        if (status){
                            System.out.println("Attendance Marked Sucessfully!");
                        }                        
                        else{
                            System.out.println("Student Attendance Already Marked On The Given Date!");
                        }
                        break;
                    }
                    default:{
                        System.out.println("INVALID INPUT! REDIRECTING TO MAIN MENU!");
                    }
                }
            }
        }
        else{
            System.out.println("Student Doesnt Exist");
        }
    }
    
    
    
    
    
    
    
    //Method to show reports and analytics menu and taking user input and then calling the respective method
    
    private void showReportsAnalyticsMenu(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("REPORTS & ANALYTICS");
        System.out.println("=============================");
        System.out.println();
        System.out.println("[1] GENERATE STUDENT ATTENDANCE REPORT");
        System.out.println("[2] SHOW ATTENDANCE PERCENTAGE OF STUDENT");
        System.out.println("[3] SHOW ATTENDANCE PERCENTAGE ON A DATE");
        System.out.println("[4] SHOW DEFAULTERS ");
        System.out.println("[5] MAIN MENU");
        System.out.print("Your Choice(1-5) : ");
        try{
            int choice2 = sc.nextInt();
            switch (choice2){
                case 1:{
                    showAttendanceReport();
                    break;
                }
                case 2:{
                    showAttendancePercentageOfAStudent();
                    break;
                }
                case 3:{
                    showAttendancePercentageOfADate();
                    break;
                }
                case 4:{
                    showDefaulters();
                    break;
                }
                default:{
                    System.out.println("Redirecting to Main Menu...");
                }
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println("Error message : "+e);
            sc.nextLine();                      
        }
    }
    
    // method to show defaulters
    private void showDefaulters(){
        List<Student> defaulters = attendanceManager.getDefaultersList();
        System.out.println("=============================");
        System.out.println("DEFAULTERS");
        System.out.println("=============================");    
        if (defaulters.isEmpty()){
            System.out.println("No Defaulters Found");
            return;
        }
        for (Student defaulter : defaulters){
            System.out.println(defaulter);
        }
    }
    
    
    
    //method to show attendance percentage of a date
    
    private void showAttendancePercentageOfADate(){
            System.out.print("Enter Date : ");
            int date = sc.nextInt();
            System.out.print("Enter Month(1-12) : ");
            int month = sc.nextInt();
            System.out.print("Enter Year : ");
            int year = sc.nextInt();
            boolean validDate = attendanceManager.checkDateValidity(date, month, year);
            if (validDate){
                LocalDate inputDate = LocalDate.of(year, month, date);    
                System.out.println("Attendance Percentage on "+inputDate+" : "+attendanceManager.getAttendancePercentageOfADate(inputDate));
                
            }
            else{
                System.out.println("Invalid Date");
            }
    }
   
   
   
   
    //method to show attendance percentage of a student
    
    private void showAttendancePercentageOfAStudent(){
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        if (attendanceManager.studentExists(id)){
            Student student = attendanceManager.getStudentObject(id);
            System.out.println("Attendance Percentage of "+student.getName()+" : "+student.getAttendancePercentage()+"%");
        }
        else{
            System.out.println("Student doesnt exist!");
        }
    }
    
    
    //method to display attendance report of a student
    
    private void showAttendanceReport(){
        System.out.println("Enter ID Of Student : ");
        int id = sc.nextInt();        
        if (attendanceManager.studentExists(id)){
            attendanceManager.getStudentDetails(id);
            Student student = attendanceManager.getStudentObject(id);
            HashMap<LocalDate, ATTENDANCESTATES> attendanceEntries = student.getAttendanceHashMap();
            if (attendanceEntries.isEmpty()){
                System.out.println("No Attendance Record Of Student Found!");
                return;
            }
            for (LocalDate key : attendanceEntries.keySet()){
                System.out.println(key+" : "+attendanceEntries.get(key));
            }
        }
        else{
            System.out.println("Student Doesnt Exist!");
        }
    }
    
    
    
    
    
    
    //Method to show search and filter menu and taking user input and then calling the respective method
    
    private void showSearchFilterMenu(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("SEARCH & FILTERS");
        System.out.println("=============================");
        System.out.println();
        System.out.println("[1] SEARCH STUDENT BY ID");
        System.out.println("[2] SEARCH STUDENT BY NAME");
        System.out.println("[3] FILTER STUDENT BY CLASS");
        System.out.println("[4] MAIN MENU");
        System.out.print("Your Choice(1-4) : ");
        try{
            int choice2 = sc.nextInt();
            switch (choice2){       
                case 1:{
                     searchStudentByID();
                     break;
                 }       
                 case 2:{
                     searchStudentByName();
                     break;
                 }  
                 case 3:{
                     filterStudentsByClass();
                     break;
                 }
                 default:{
                     System.out.println("Redirecting to Main Menu");
                 }
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println("Error message : "+e);
            sc.nextLine();                      
        }
    }
    
    
    //Filter Students By Class and giving the details of the fitlered students
    
    private void filterStudentsByClass(){
        System.out.print("Enter Name Of Class : ");
        sc.nextLine();
        String className = sc.nextLine().toLowerCase();
        List<Student> students = attendanceManager.getClassStudents(className);
        if (students.isEmpty()){
            System.out.println("Class Doesnt Exist!");
            return;
        }
        System.out.println("=============================");
        System.out.println(className.toUpperCase()+" CLASS STUDENTS");
        System.out.println("=============================");
        for (Student student : students){
            System.out.println(student);
        }
    }
    
    
    
    //method to search student by name and giving back details also gives back duplicates if two students have same name
    
    private void searchStudentByName(){
        System.out.print("Enter Name Of Student : ");
        sc.nextLine();
        String name =  sc.nextLine().toLowerCase();
        if (attendanceManager.studentExists(name)){
            System.out.println("Student Found!");
            List<Student> students = attendanceManager.getStudentObjects();
            for (Student student : students){
                if (student.getName().equals(name)){
                    System.out.println(student);                    
                }
            }
        }
        else{
            System.out.println("Student Not Found!");
        }
    }
    
    
    // method to search student by id and giving back details
    
    private void searchStudentByID(){
        System.out.print("Enter ID Of Student : ");
        int id = sc.nextInt();
        if (attendanceManager.studentExists(id)){
            System.out.println("Student Found");
            System.out.println(attendanceManager.getStudentObject(id));
        }
        else{
            System.out.println("Student Not Found!");
        }               
    }
}

public class Main {
	public static void main(String[] args) {
        System.out.println("========VF ATTENDANCE SYSTEM=======");
        UserInterface ui = new UserInterface();
        ui.startProgram();
	}
}
