import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

class Question{
    private String question;
    private int correctAnswerIndex;
    private String[] options;
    
    public Question(String question, String[] options, int correctAnswerIndex){
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public String[] getOptions(){
        return options;
    }
    
    public int getCorrectAnswerIndex(){
        return correctAnswerIndex;
    }
    
    
}

class Exam{
    private List<Question> questions = new ArrayList<>();
    Random random = new Random();
    
    public Exam(List<Question> questions){
        this.questions = questions;
    }
    
    
    public Question getQuestionObject(){

    if (questions.isEmpty()){
        return null;
    }

    int questionIndex = random.nextInt(questions.size());

    Question questionObj = questions.get(questionIndex);

    questions.remove(questionIndex);

    return questionObj;
}
    
    public String getQuestionTitle(Question question){
        return question.getQuestion();
    }
    
    public String[] getQuestionOptions(Question question){
        return question.getOptions();
    }
    
    public int getCorrectAnswerIndex(Question question){
        return question.getCorrectAnswerIndex();
    }
    
    public boolean validateAnswer(int answer, int correctAnswer){
        if (answer == correctAnswer){
            return true;
        }
        return false;
    }
        
}

class UserInterface{
    
        private HashMap<String,Integer> userScores = new HashMap<>();
        private Scanner sc = new Scanner(System.in);
        String[] question1Options = {"Narendra Modi","Rahul Gandhi", "Jawaharlal Nehru","Michael Lobo"};
        
        Question question1 = new Question("Who is the prime Minister of India?", question1Options,0);
        
        String[] question2Options = {"Milan","Rome", "Turin","Pisa"};
        
        Question question2 = new  Question("What is the capital of Italy?", question2Options,1);
        
        String[] question3Options = {"Eagle","Kingfisher", "Woodpecker","Peacock"};
        Question question3 = new Question("What is the national bird of India?", question3Options,3);
        
        String[] question4Options = {"5","9", "7","4"};
        Question question4 = new Question("What is square root of 49?", question4Options,2);
        
        String[] question5Options = {"Ag","Na", "Cu","Au"};
        Question question5 = new  Question("What is chemical symbol of Gold?", question5Options,3);
        
        List<Question> questions = new ArrayList<>(
    Arrays.asList(question1, question2, question3, question4, question5)
);        
            
    public void delay(int time){
        try{
            Thread.sleep(time);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void showMainMenu(){
        System.out.println("==================");
        System.out.println("[1] Take New Test");
        System.out.println("[2] See Exam Records");
        System.out.println("[3] Exit");
        System.out.println();
        System.out.print("Your Choice(1-3) : ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:{
                takeExam();
                break;
            }
            case 2:{
                viewExamRecords();
                showMainMenu();
                break;
            }
            case 3:{
                System.out.println("THANK YOU FOR USING... :)");
                break;
            }
            default:{
                System.out.println("Please select a number between 1 and 3 only!");
                showMainMenu();
            }
        }
    }
        
    private void takeExam(){                        
        sc.nextLine();
        Exam examiner = new Exam(new ArrayList<>(questions));
        System.out.print("Enter Your Name : ");
        String name= sc.nextLine();
        System.out.println("Starting exam..");
        System.out.println("You Get 1 Minute for the exam");
        System.out.println("Exam consists of 5 questions");
        System.out.println("Every correct answer gives 1 mark! No negatives");
        delay(4000);
        startExam(name, examiner);
        showMainMenu();
    }
    
    private void startExam(String name, Exam examiner){
        int score = 0;
        int questionCount = 0;
        long startTime = System.currentTimeMillis();
        
        System.out.println("Exam Starts Now!");        
        while (true){
            System.out.println("=============");
            long elapsedTime = (System.currentTimeMillis()-startTime)/1000;
            if (elapsedTime >=60){
                System.out.println("Times up!");
                delay(1000);
                System.out.println(name+"'s score : "+score);
                userScores.put(name, score);
                System.out.println("Thank You For taking the exam!");
                break;
            }
            questionCount++;            
            try{
                delay(1000);
                Question question = examiner.getQuestionObject();
                
                if (question == null){
    System.out.println("Exam Over! You attempted all questions!");
    System.out.println(name+"'s score : "+score);
    userScores.put(name, score);
    System.out.println("Thank you for taking the exam!");
    break;
    }
                System.out.println("["+questionCount+"] "+examiner.getQuestionTitle(question));
                
                String[] options = examiner.getQuestionOptions(question);
                
                for (int i=0;i<=3;i++){
                    System.out.println("["+(i+1)+"] "+options[i]);
                    delay(500);
                }
                System.out.print("Your Answer (1-4) : ");
                int answer = sc.nextInt()-1;
                if (answer < 0 || answer > 3){
                    System.out.println("Invalid answer choice!");
                    System.out.println("Exam Forfeited!");
                    System.out.println("Exam need to be retaken!");
                    break;                    
                }
                
                int correctAnswerIndex = examiner.getCorrectAnswerIndex(question);
                if (examiner.validateAnswer(answer, correctAnswerIndex)){
                    score++;
                    System.out.println();
                    delay(1000);
                }
                                
            }            
            catch (Exception e){
                System.out.println("System Failure : Invalid input!");
                System.out.println("Error message : "+e);
                sc.nextLine();       
                System.out.println("Exam need to be retaken!");                         
                break;
            }
        }
        System.out.println("Shifting to Main menu!");
    }
    private void viewExamRecords(){
        System.out.println("ALL EXAM RECORDS :-");
        System.out.println("(BLANK RESULTS INDICATE NO EXAM HAS BEEN TAKEN!)");
        int srNo = 1;
        for (String key : userScores.keySet()){
            System.out.println("["+srNo+"] "+key+" : " +userScores.get(key));
        }
    }
}
public class Main {
	public static void main(String[] args) {
		System.out.println("======VF ONLINE EXAM=======");
		UserInterface ui = new UserInterface();
		ui.showMainMenu();
	}
}