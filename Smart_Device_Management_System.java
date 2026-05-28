import java.util.Scanner;

abstract class Device{
    protected String brand;
    protected boolean powerStatus = false;
    
    public Device(String brand){
        this.brand = brand;
    }
    
    abstract void turnOff();
    abstract void turnOn();
    abstract void deviceInfo();
    abstract void showPowerStatus();
}


class SmartTV extends Device{
    private String[] channels;
    private int volume = 10;
    private String currentChannel;
    
    public SmartTV(String brand, String[] channels){
        super(brand);
        this.channels = channels;
        this.currentChannel = channels[0];
    }
    public void turnOff(){
        if (!powerStatus){
            System.out.println("TV already off!");
        }
        else{
            powerStatus = false;
            System.out.println("TV turned off!");
        }
    }
    
    public void turnOn(){
        if (!powerStatus){
            powerStatus=true;
            System.out.println("TV turned on");
        }
        else{
            System.out.println("Tv is already On");
        }
    }
    public void showPowerStatus(){
        if (!powerStatus){
            System.out.println("TV is off");
        }
        else{
            System.out.println("TV is on");
        }
    }    
    
    public void deviceInfo(){
        System.out.println("=====Tv Device Info=====");
        System.out.println("TV brand name : "+brand);
        if (!powerStatus){
            System.out.println("TV power status: OFF");           
        }
        else{
            System.out.println("TV power status : ON");
        }
        System.out.println("TV volume : "+volume);
        System.out.println("Current channel viewing - "+currentChannel);
        System.out.println("--TV channels--");
        int count = 1;
        for (String channel: channels){
            if (channel == null){
                break;
            }
            else{
                System.out.println(count+"."+channel);
                count++;
            }
        }
    }
    
    public void setVolume(int volume){
        if (volume < 0 || volume > 30){
            System.out.println("Volume cannot be below zero or above 30!");
        }
        else{
            this.volume = volume;
            System.out.println("TV Volume set to "+this.volume);
        }
    }
    
    public void displayChannels(){
        System.out.println("===TV channels===");
        int count = 1;
        for (String channel: channels){
            if (channel == null){
                break;
            }
            else{
                System.out.println(count+"."+channel);
                count++;
            }
        }
    }
    
    public void decreaseVolume() {
        if (volume==0){
            System.out.println("Volume is 0. Cannot decrease more!");
        }
        else{
            System.out.println("Volume decreased");
            volume-=1;
            System.out.println("Current Volume : "+volume);
        }
    }
    
    public void increaseVolume() {
        if (volume == 30){
            System.out.println("Max Vol. 30. Cannot increase more");
        }
        else{
            volume++;
            System.out.println("Volume increased!");
            System.out.println("Current Volume : "+volume);
        }
    }
    
    public void mute(){
        volume=0;
        System.out.println("TV muted!");
    }
    
    public void changeChannel(int channel){
        if (channel < 1 || channel > (channels.length)){
            System.out.println("Invalid channel No.");
        }
        else{
            currentChannel=channels[channel-1];
            System.out.println("Channel changed to "+channels[channel-1]);
        }
    }
}

class Fan extends Device{
    private int speed;
    
    public Fan(String brand){
        super(brand);
    }
    
    public void turnOn(){
        if (!powerStatus){
            System.out.println("Fan turned on!");
            powerStatus = true;
            speed = 2;
            System.out.println("Fan speed set to 2");
        }
        else{
            System.out.println("Fan is already on!");
        }
    }
    
    public void turnOff(){
        if (powerStatus){
            System.out.println("Fan turned off!");
            powerStatus = false;
        }
        else{
            System.out.println("Fan already off!");
        }
    }
    
    public void showPowerStatus(){
        if (!powerStatus){
            System.out.println("Fan is off");
        }
        else{
            System.out.println("Fan is on");
        }
    }
    
    public void deviceInfo(){
        System.out.println("=====Fan info=====");
        System.out.println("Fan brand : :"+brand);
        System.out.println("Fan speed : "+ speed);
        if (powerStatus){
            System.out.println("Fan power status : On");
        }
        else{
            System.out.println("Fan power status : Off");
        }
    }
    
    public void increaseSpeed(){
        if (speed==5){
            System.out.println("Fan speed is 5. Cannot increase more.");
        }
        else{
            speed++;
            System.out.println("Fan speed increased to "+speed);
        } 
    }
    
    public void decreaseSpeed(){
        if (speed==0){
            System.out.println("Fan Speed is already zero. Cannot increase more");
        }
        else{
            speed--;
            System.out.println("Speed decreased to "+speed);
        }
    }
    
    public void setSpeed(int speed){
        if (speed > 5 || speed < 0){
            System.out.println("Speed cannot be less than zero or more than 5");
        }
        else{
            this.speed = speed;
            System.out.println("Fan speed set to : "+speed);
        }
    }
}

class SmartLight extends Device{
    String[] colors;
    int brightness = 0;
    String currentColor;
    
    public SmartLight(String brand, String[] colors){
        super(brand);
        this.colors = colors;
        currentColor = colors[0];
    }
    
    public void turnOn(){
        if (!powerStatus){
            powerStatus=true;
            System.out.println("Lights were turned on!");
        }
        else{
            System.out.println("Lights are already on!");
        }
    }
    
    public void turnOff(){
        if (powerStatus){
            System.out.println("Lights were turned off");
        }
        else{
            System.out.println("Lights are already off");
        }
    }
    
    public void showPowerStatus(){
        if (powerStatus){
            System.out.println("Lights power status : ON");
        }
        else{
            System.out.println("Lights power status : OFF");
        }
    }
    
    public void deviceInfo(){
        System.out.println("=====Lights INFO=====");
        System.out.println("Lights brand : "+brand);
        System.out.println("Light brightness : "+ brightness);
        System.out.println("current Lights color : "+currentColor);
        System.out.println("All Light colors -");
        int count = 1;
        for (String color : colors){
            if (color == null){
                break;
            }
            else{               
                System.out.println(count+"."+color);
                count++;
            }
        }
    }
    
    public void increaseBrightness(){
        if (brightness == 10){
            System.out.println("Brightness already at max level 10. Cannot increase more.");
        }
        else{
            brightness++;
            System.out.println("Current brightness : "+brightness);
        }
    }
    
    public void decreaseBrightness(){
        if (brightness == 0){
            System.out.println("Brightness at 0. Cannot decrease more.");
        }
        else{
            brightness--;
            System.out.println("Brightness decreased to "+brightness);
        }
    }
    
    public void setBrightness(int brightness){
        if (brightness < 1 || brightness > 10){
            System.out.println("Brightness cannot be less than zero or more than 10!");
        }
        else{
            this.brightness = brightness;
            System.out.println("Brightness set to : "+this.brightness);
        }
    }
    
    public void changeColor(int color){
        if (color < 1 || color > colors.length){
            System.out.println("Invalid color");
        }
        else{
            currentColor = colors[color-1];
            System.out.println("Lights color changed to "+currentColor);
        }
    }
}
public class Main {
	public static void main(String[] args) {
		String[] colors = {
    "White",
    "Warm White",
    "Blue",
    "Red",
    "Green",
    "Purple"
};
    String[] channels = {
    "Star Sports",
    "Zee TV",
    "Discovery",
    "Cartoon Network",
    "Movies Now"
};

     SmartTV tv = new SmartTV("Samsung", channels);
     Fan fan = new Fan("Havells");
     SmartLight light = new SmartLight("Jaguar", colors);
     boolean exit = false;
     Scanner sc = new Scanner(System.in);
     
     System.out.println("======SMART DEVICE MANAGEMENT SYSTEM=====");
     while (!exit){
         try{
             System.out.println("=============");
             System.out.println("[1]Manage Smart TV");
             System.out.println("[2]Manage Fan");
             System.out.println("[3]Manage Lights");
             System.out.println("[4]Exit");
             System.out.print("Your choice(1-4): ");
             int choice = sc.nextInt();
             System.out.println();
             switch (choice){
                 case 4:{
                     exit = true;
                     System.out.println("Thank You For Using!");
                     break;
                 }
                 case 1:{
                     System.out.println("======Smart TV Settings======");
                     System.out.println("[1]Turn On TV");
                     System.out.println("[2]Turn Off");
                     System.out.println("[3]View Power Status");
                     System.out.println("[4]Increase Volume");
                     System.out.println("[5]Decrease Volume");
                     System.out.println("[6]Set Custom Volume");
                     System.out.println("[7]View Channels");
                     System.out.println("[8]Get device Info.");
                     System.out.println("[9]Mute");
                     System.out.println("[10]Change Channel");
                     System.out.println("[11]Exit");
                     System.out.print("Enter your choice(1-13) : ");
                     int choice2 = sc.nextInt();
                     switch (choice2){
                         case 1:{
                             tv.turnOn();
                             break;
                         }
                         case 2:{
                             tv.turnOff();
                             break;
                         }
                         case 3:{
                             tv.showPowerStatus();     
                             break;                       
                         }
                         case 4:{
                             tv.increaseVolume();
                             break;
                         }
                         case 5:{
                             tv.decreaseVolume();
                             break;
                         }
                         case 6:{
                             System.out.println();
                             System.out.print("Enter volume(1-30) : ");
                             int volume = sc.nextInt();
                             tv.setVolume(volume);
                             break;
                         }
                         case 7:{
                             tv.displayChannels();
                             break;
                         }                                          
                         case 8:{
                             tv.deviceInfo();
                             break;
                         }
                         case 9:{
                             tv.mute();
                             break;
                         }
                         case 10:{
                             tv.displayChannels();
                             System.out.println();
                             System.out.print("Enter the number against the desire channel : ");
                             int channel = sc.nextInt();
                             tv.changeChannel(channel);
                             break;
                         }
                         case 11:{
                             break;
                         }
                         default:{
                             break;
                         }
                     }
                     break;
                 }
                 case 2:{
                     System.out.println("=====Fan Settings=====");
                     System.out.println("[1]Turn on");
                     System.out.println("[2]Turn off");
                     System.out.println("[3] Show power status");
                     System.out.println("[4]View Device Info");
                     System.out.println("[5]Increase Speed");
                     System.out.println("[6]Decrease Speed");
                     System.out.println("[7]Set Custom Speed");
                     System.out.println("[8]Exit");
                     System.out.print("Enter your choice(1-8) : ");
                     int choice2 = sc.nextInt();
                     switch (choice2){
                         case 8:{
                             break;
                         }
                         case 1:{
                             fan.turnOn();
                             break;
                         }
                         case 2:{
                             fan.turnOff();
                             break;
                         }
                         case 3:{
                             fan.showPowerStatus();
                             break;
                         }
                         case 4:{
                             fan.deviceInfo();
                             break;
                         }
                         case 5:{
                             fan.increaseSpeed();
                             break;
                         }
                         case 6:{
                             fan.decreaseSpeed();
                             break;
                         }
                         case 7:{
                             System.out.println();
                             System.out.print("Enter fan speed(1-5) : ");
                             int speed = sc.nextInt();
                             fan.setSpeed(speed);
                             break;
                         }
                         default:{
                             break;
                         }
                     }
                     break;
                 }
                 case 3:{
                     System.out.println("====Lights Settings====");
                     System.out.println("[1]Turn On");
                     System.out.println("[2]Turn Off");
                     System.out.println("[3]Show Power Status");
                     System.out.println("[4]Show device Info");
                     System.out.println("[5]Increase Brightness");
                     System.out.println("[6]Decrease Brightness");
                     System.out.println("[7]Set custom Brightness");
                     System.out.println("[8]Change Lights Color");
                     System.out.println("[9]Exit");
                     System.out.print("Enter choice (1-9) : ");
                     int choice2 = sc.nextInt();
                     switch (choice2){
                         case 1:{
                             light.turnOn();
                             break;
                         }
                         case 2:{
                             light.turnOff();
                             break;
                         }
                         case 3:{
                             light.showPowerStatus();
                             break;
                         }
                         case 4:{                          
                             light.deviceInfo();
                             break;
                         }
                         case 5:{
                             light.increaseBrightness();
                             break;
                         }
                         case 6:{
                             light.decreaseBrightness();
                             break;
                         }
                         case 7:{
                             System.out.println();
                             System.out.print("Enter brightness Level (1-10) : ");
                             sc.nextLine();
                             int bright= sc.nextInt();
                             light.setBrightness(bright);
                             break;
                         }
                         case 8:{
                             System.out.println("---Light colors---");
                             int count = 1;
                             for (String color: colors){
                                 System.out.println(count+"."+color);
                                 count++;
                             }
                             System.out.println("Enter the number against the desired color");
                             int color = sc.nextInt();
                             light.changeColor(color);
                             break;
                         }
                         case 9:{
                             break;
                         }
                         default:{
                             break;
                         }
                     }
                     break;
                 }
                 default : {
                     System.out.println("Please choose between 1 to 4");
                 }
             }
         }
         catch (Exception e){
             System.out.println("Error! please enter valid input!");
             sc.nextLine();
             System.out.println("Error message : "+e);
         }
     }
	}
}