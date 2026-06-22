import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Download extends Thread{
    private String name;
    private int downloadPercentage = 0; 
    private volatile boolean paused = false;
    private volatile boolean running = true;
    
    public Download(String name){
        this.name = name;
    }
    
    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        paused = false;
    }

    public void stopThread() {
        running = false;
        interrupt();
}
    
    public boolean isPaused(){
        if (paused){
            return true;
        }
        return false;
    }
    
    public String getDownloadName(){
        return name;
    }
    
    public int getDownloadPercentage(){
        return downloadPercentage;
    }
    
   @Override
    public void run() {
    
        while (running && downloadPercentage < 100) {
    
            if (paused) {
                continue;
            }
    
            try {
    
                Thread.sleep(2000);
    
                downloadPercentage++;
    
            } catch (InterruptedException e) {
                break;
            }
        }
    }        
}

class DownloadManager{
    HashMap<String, Download> downloads = new HashMap<>();
    
    public boolean addDownload(String name){       
        if (downloads.keySet().contains(name)){
            return false;
        }        
            Download download = new Download(name);
            downloads.put(name, download);
            download.start();
            return true;        
    }
    
    public List<Download> getDownloads(){
        List<Download> downs = new ArrayList<>(downloads.values());
        return downs;
    }
    
    public boolean pauseDownload(String name){
        if (!downloads.keySet().contains(name)){
            return false;
        }
        if (!downloads.get(name).isPaused()){
            downloads.get(name).pauseThread();
            return true;
        }
        return false;
    }
    
    public boolean resumeDownload(String name){
        if (!downloads.keySet().contains(name)){
            return false;
        }
        if (downloads.get(name).isPaused()){
            downloads.get(name).resumeThread();
            return true;
        }
        return false;
    }
}

class UserInterface{
    
    DownloadManager downloadManager = new DownloadManager();
    Scanner sc = new Scanner(System.in);    
    
    public void startProgram(){
        System.out.println("=======VF DOWNLOAD MANAGER=======");
        boolean exit = false;
        while(!exit){
            System.out.println();
            System.out.println("[1] ADD NEW DOWNLOAD");
            System.out.println("[2] VIEW ALL DOWNLOADS");
            System.out.println("[3] PAUSE DOWNLOAD");
            System.out.println("[4] RESUME DOWNLOAD");
            System.out.println("[5] EXIT");
            System.out.println();
            System.out.print("Your Choice (1-5) : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:{
                    addNewDownload();
                    break;
                }
                case 2:{
                    viewAllDownloads();
                    break;
                }
                case 3:{
                    pauseDownload();
                    break;
                }
                case 4:{
                    resumeDownload();
                    break;
                }
                default:{
                    System.out.println("Thank You For Using...");
                    stopAllThreads();
                    exit = true;
                }
            }            
        }        
    }
    
    private void resumeDownload(){
        System.out.print("Enter Name Of Download : ");
        sc.nextLine();
        String name = sc.nextLine().toLowerCase();
        boolean status = downloadManager.resumeDownload(name);
        if (status){
            System.out.println("Download Resumed");
        }
        else{
            System.out.println("Download could not be resumed. Check If download exists or already resumed");
        }
    }
    private void pauseDownload(){
        System.out.print("Enter Name Of Download : ");
        sc.nextLine();
        String name = sc.nextLine().toLowerCase();
        boolean status = downloadManager.pauseDownload(name);
        if (status){
            System.out.println("Download Paused");
        }
        else{
            System.out.println("Download could not be paused. Check If download exists or already paused");
        }
    }
    
    private void addNewDownload(){
        System.out.print("Enter Name Of Download : ");
        sc.nextLine();
        String name = sc.nextLine().toLowerCase();
        boolean status = downloadManager.addDownload(name);
        if (status){
            System.out.println("Download Started...");                  
        }  
        else{
            System.out.println("Download Already Exists");
        }
    }
    
    private void viewAllDownloads(){
        List<Download> downloads = downloadManager.getDownloads();
        if (downloads.isEmpty()){
            System.out.println("No Downloads Found!");
            return;
        }
        for (Download download : downloads){
            String status = "Downloading...";
            if (download.isPaused()){
                status = "Paused.";
            }
            if (download.getDownloadPercentage() == 100){
                status = "Completed";
            }
            System.out.println(download.getDownloadName()+" : "+download.getDownloadPercentage()+"% "+status);
        }       
    }
    
    private void stopAllThreads(){
        List<Download> downloads = downloadManager.getDownloads();
        for (Download download : downloads){
            download.stopThread();
        }
    }
}
public class Main {
	public static void main(String[] args) {
	    UserInterface ui = new UserInterface();
	    ui.startProgram();
	}
}