import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

// Item class with fields like name price and quantity. Also has getters for each and a method to increase quantity and get total price.
class Item{
    private String name;
    private double price;
    private int quantity = 0;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public boolean setQuantity(int count){
        quantity=count;
        return true;
    }
    public boolean increaseQuantity(int count){
        quantity+=count;
        return true;
    }
    
    public double getTotalPrice(){
        return price*quantity;
    }
}


enum ORDERSTATUS{
    PLACING,PLACED,DELIVERED,CANCELLED
}

//Order class Manages items and has a unique order ID And features to retrieve information.
class Order{
    private int orderID;
    private ORDERSTATUS status = ORDERSTATUS.PLACING;
    private Map<String, Item> items = new HashMap<>();
    
    public Order(int orderID){
        this.orderID = orderID;
    }
    
    
    
    
    
    // GETTERS 
    
    public int getOrderID(){
        return orderID;
    }
    
    public ORDERSTATUS getOrderStatus(){
        return status;
    }        
    
    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>(items.keySet());
        return itemNames;
    }
    
    public List<Item> getItemObjects(){
        List<Item> itemObjects = new ArrayList<>(items.values());
        return itemObjects;
    }
    
    public Map<String, Item> getItemsHashMap(){
        return new HashMap<>(items);
    }
    
    public boolean orderItem(Item itemObj){
        if (items.keySet().contains(itemObj.getName())){
            items.get(itemObj.getName()).increaseQuantity(itemObj.getQuantity());
            return true;
        }
        else{
            items.put(itemObj.getName(), itemObj);
            return true;
        }
    }
    
     public boolean removeItem(String itemName){
         if (items.keySet().contains(itemName)){
             items.remove(itemName);
             return true;
         }
         return false;
     }
     
     public boolean placeOrder(){
         if (!status.equals(ORDERSTATUS.PLACING)){
             return false;
         }
         status = ORDERSTATUS.PLACED;
         return true;
     }
     
     public boolean deliverOrder(){
         if (!status.equals(ORDERSTATUS.PLACED)){
             return false;
         }
         else{
             status = ORDERSTATUS.DELIVERED;
             return true;
         }
     }
     
     public boolean cancelOrder(){
         if (status.equals(ORDERSTATUS.CANCELLED) || status.equals(ORDERSTATUS.DELIVERED)){
             return false;
         }
         else{
             status = ORDERSTATUS.CANCELLED;
             return true;
         }
     }
     
     public double getTotal(){
         int total = 0;
         for (Item item : items.values()){
             total+=item.getTotalPrice();
         }
         return total;
     }     
     
}

class Restaurant{
    private Order currentOrder = new Order(101);
    private Map<Integer, Order> orders = new HashMap<>();
    
    Map<String, Item> menu = new HashMap<>();
    
    public Restaurant() {
        orders.put(currentOrder.getOrderID(), currentOrder);
        menu.put("burger", new Item("burger", 60));
        menu.put("pizza", new Item("pizza", 250));
        menu.put("fries", new Item("fries", 50));
        menu.put("pasta", new Item("pasta", 140));
        menu.put("sandwich", new Item("sandwich", 80));
        menu.put("noodles", new Item("noodles", 110));
        menu.put("fried rice", new Item("fried rice", 130));
        menu.put("paneer tikka", new Item("paneer tikka", 200));
        menu.put("biryani", new Item("biryani", 220));
        menu.put("ice cream", new Item("ice cream", 70));
        menu.put("coke", new Item("coke", 40));
    }
    
    public int getCurrentOrderID(){
        return currentOrder.getOrderID();
    }
    public List<Integer> getOrderIDS(){
        return new ArrayList<>(orders.keySet());
    }
    
    public List<Item> getMenuItems(){
        List<Item> items = new ArrayList<>(menu.values());
        return items;
    }
    
    public List<Item> getCurrentOrderItems(){
        return currentOrder.getItemObjects();
    }
    
    public List<Order> getOrderObjects(){
        List<Order> orderObjs = new ArrayList<>(orders.values());
        return orderObjs;
    }
    
    public boolean orderExists(int orderID){
        if (orders.keySet().contains(orderID)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Order getOrderObject(int orderID){
        return orders.get(orderID);
    }
    
    public void getOrderBill(int orderID){
        Order order = getOrderObject(orderID);
        System.out.println("BILL FOR ORDER ID - "+orderID);
        System.out.println("==================");
        System.out.println();
        int count = 1;
        for (Item item : order.getItemObjects()){
            System.out.println(count+". "+item.getName()+"   "+item.getQuantity()+" units   ₹"+item.getTotalPrice());
            count++;
        }
        System.out.println("==================");
        System.out.println("TOTAL          ₹"+order.getTotal());
        System.out.println("==================");
    }
    
    public boolean addItemToOrder(String itemName, int quantity, double price){
        Item newItem = new Item(itemName, price);
        newItem.setQuantity(quantity);
        currentOrder.orderItem(newItem);
        return true;
    }
    
    public boolean removeItemFromOrder(String itemName){
        currentOrder.removeItem(itemName);
        return true;
    }
    
    public int placeOrder(){
        currentOrder.placeOrder();
        int newOrderID = currentOrder.getOrderID()+1;
        currentOrder = new Order(newOrderID);
        orders.put(newOrderID, currentOrder);
        return newOrderID;
    }
    
   public boolean deliverOrder(int orderID){
       return orders.get(orderID).deliverOrder();
   } 
   
   public boolean cancelOrder(int orderID){
       return orders.get(orderID).cancelOrder();
   }      
}

class UserInterface{
    Scanner sc = new Scanner(System.in);
    Restaurant restraunt = new Restaurant();
    boolean exit = false;
    
    public void startProgram(){
        System.out.println("====== VF RESTRAUNT =====");
        System.out.println("====================");
        while (!exit){
            System.out.println();
            System.out.println("[1] ORDER ITEM");
            System.out.println("[2] REMOVE ITEM FROM ORDER");
            System.out.println("[3] PLACE ORDER");
            System.out.println("[4] DELIVER ORDER");
            System.out.println("[5] CANCEL ORDER");
            System.out.println("[6] VIEW ALL ORDERS");
            System.out.println("[7] VIEW ORDER INFO");
            System.out.println("[8] EXIT");
            System.out.println();
            try{
                System.out.print("Enter Choice(1-8) : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 : {
                        showOrderingInterface();
                        break;
                    }
                    case 2:{
                        showRemovingItemInterface();
                        break;
                    }
                    case 3:{
                        showPlacingOrderInterface();
                        break;
                    }
                    case 4:{
                        showDeliveringOrderInterface();
                        break;
                    }
                    case 5:{
                        showCancellingOrderInterface();
                        break;
                    }
                    case 6:{
                        showAllOrders();
                        break;
                    }
                    case 7:{
                        showViewOrderInterface();
                        break;
                    }
                    default:{
                        System.out.println();
                        System.out.println("Thank You For Using..");
                        exit = true;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Error Occurred");
                System.out.println("Error message : "+e);
                sc.nextLine();
            }
        }
    }
    
    private void showViewOrderInterface(){
        System.out.println();
        System.out.println(" All Orders :- ");
        System.out.println("==================");
        System.out.println();
        List<Integer> orderIDS= new ArrayList<>();
        System.out.println("ORDER-ID   STATUS");
        System.out.println("==================");
        for (Order order : restraunt.getOrderObjects()){
            System.out.println(order.getOrderID()+"        "+order.getOrderStatus());
            orderIDS.add(order.getOrderID());            
        }    
        System.out.println("==================");
        System.out.println();
        System.out.println("Enter Order ID : ");
        int orderID = sc.nextInt();
        if (!orderIDS.contains(orderID)){
            System.out.println("Invalid Order ID");
            return;
        }
        System.out.println();
        restraunt.getOrderBill(orderID);
    }
    
    private void showAllOrders(){
        System.out.println("ALL ORDERS :-");
        System.out.println("==================");
        System.out.println("ORDER-ID   STATUS");
        for (Order order : restraunt.getOrderObjects()){
            System.out.println(order.getOrderID()+"        "+order.getOrderStatus());
        }
        System.out.println();
        System.out.println("==================");
    }
    
    private void showCancellingOrderInterface(){
        if (restraunt.getOrderObjects().size() == 1){
            System.out.println("No Orders Placed So Far!");
            return;
        }
        System.out.println("PLACED Orders :-");
        List<Integer> orderIDS = new ArrayList<>();
        int placedOrders = 0;
        for (Order order : restraunt.getOrderObjects()) {
            if (order.getOrderStatus() == ORDERSTATUS.PLACED) {
                placedOrders++;
                System.out.println("ORDER  "+order.getOrderID());
                orderIDS.add(order.getOrderID());
            }
        }
        if (placedOrders<1){
            System.out.println("NO ORDERS PLACED YET!");
            return;
        }
        System.out.print("Enter Order ID : ");
        int orderID = sc.nextInt();
        if (!orderIDS.contains(orderID)){
            System.out.println("Invalid Order ID!");
            return;
        }
        restraunt.cancelOrder(orderID);
        System.out.println("Order Cancelled Successfully!");
    }
    
    
    private void showDeliveringOrderInterface(){        
        if (restraunt.getOrderObjects().size() == 1){
            System.out.println("No Orders Placed So Far!");
            return;
        }
        System.out.println("PLACED Orders :-");
        List<Integer> orderIDS = new ArrayList<>();
        for (Order order : restraunt.getOrderObjects()) {
            if (order.getOrderStatus() == ORDERSTATUS.PLACED) {
                System.out.println("ORDER  "+order.getOrderID());
                orderIDS.add(order.getOrderID());
            }
        }
        System.out.print("Enter Order ID : ");
        int orderID = sc.nextInt();
        if (!orderIDS.contains(orderID)){
            System.out.println("Invalid Order ID!");
            return;
        }
        restraunt.deliverOrder(orderID);
        System.out.println("ORDER DELIVERED SUCCESSFULLY!");
    }
    
    
    private void showPlacingOrderInterface(){
        List<Item> orderedItems = restraunt.getCurrentOrderItems();
        if (orderedItems.isEmpty()){
            System.out.println("No Item Ordered Yet!");
            System.out.println("Cannot Order With No Items");
            return;
        }
        System.out.println("ORDER WITH ID "+restraunt.getCurrentOrderID()+" Placed Successfully!");
        restraunt.getOrderBill(restraunt.getCurrentOrderID());
        System.out.println();
        int newOrderID = restraunt.placeOrder();
        System.out.println("New Order ID : "+newOrderID);
        
    }
    
    private void showRemovingItemInterface(){
        List<Item> orderedItems = restraunt.getCurrentOrderItems();
        if (orderedItems.isEmpty()){
            System.out.println("No Item Ordered Yet!");
            return;
        }
        int count = 1;
        System.out.println("Current Order Items :-");
        for (Item item : orderedItems){
            System.out.println(count+". "+item.getName());         
            count++;   
        }
        System.out.println();
        System.out.print("Enter Number Against Item : ");
        int itemNo = sc.nextInt()-1;
        if (itemNo < 0 || itemNo > orderedItems.size()-1){
            System.out.println("Invalid Item No.");
            return;
        }
        restraunt.removeItemFromOrder(orderedItems.get(itemNo).getName());
        System.out.println("Item Removed From Order Successfully!");        
    }
    
    
    private void showOrderingInterface(){
        List<Item> menu = restraunt.getMenuItems();
        System.out.println("===== MENU =====");
        int count = 1;
        for (Item item : menu){
            System.out.println(count+". "+item.getName()+"  ₹"+item.getPrice());
            count++;
        }
        System.out.println();
        System.out.print("Enter Number Against Item : ");
        int itemNo = sc.nextInt()-1;
        if (itemNo < 0 || itemNo>menu.size()-1){
            System.out.println("Invalid Item No.");
            return;
        }
        System.out.println();
        System.out.print("Enter Quantity : ");
        int quantity = sc.nextInt();
        if (quantity < 1){
            System.out.println("Invalid Quantity Number!");
            return;
        }
        restraunt.addItemToOrder(menu.get(itemNo).getName(), quantity, menu.get(itemNo).getPrice());
        System.out.println();
        System.out.println("Item Added To Order Successfully!");
    }
    
    
}
public class Main {
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.startProgram();
	}
}