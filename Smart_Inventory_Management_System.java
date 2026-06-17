import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

class Product {
	private String name;
	private int price;
	private int sales;
	private int stock;
	private String category;
	
	public Product(String name, String category, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}
    
    @Override
    public boolean equals(Object obj){
        Product pro = (Product) obj;
        return this.name.equals(pro.name);
    }
    
    public String getCategory(){
        return category;
    }
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getSales() {
		return sales;
	}

	public int getStock() {
		return stock;
	}
	public int getRevenue() {
		return sales * price;
	}

	public boolean addSales(int noOfSales) {
		if (stock < noOfSales) {
			return false;
		} else {
			sales += noOfSales;
			stock -= noOfSales;
			return true;
		}
	}

	public boolean addStock(int noOfStock) {
		if (noOfStock <= 0) {
			return false;
		}
		stock += noOfStock;
		return true;
	}

	public boolean setName(String newName) {
		name = newName;
		return true;
	}

	public boolean setPrice(int newPrice) {
		if (newPrice < 0) {
			return false;
		}
		price = newPrice;
		return true;
	}
}

class Inventory{
    private HashMap<String, Product> products = new HashMap<>();
    
    public boolean addProduct(Product product){
        if (products.containsKey(product.getName())){
            return false;
        }
        else{
            products.put(product.getName(),product);
            return true;
        }
    }
    
    public boolean removeProduct(String productName){
        if (products.containsKey(productName)){
            products.remove(productName);
            return true;
        }
        else{
            return false;
        }
    }
    
    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList<>(products.values());
        return productList;
    }
    
    public boolean updateName(String productName, String newName){
        if (products.containsKey(productName)){
            Product product = products.remove(productName);
product.setName(newName);
products.put(newName, product);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean updatePrice(String productName, int newPrice){
        if (products.containsKey(productName)){
            products.get(productName).setPrice(newPrice);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addSales(String productName, int noOfSales){
        if (products.containsKey(productName)){
            boolean status = products.get(productName).addSales(noOfSales);
            if (status){
                System.out.println("Sales added successfully!");
            }
            else{
                System.out.println("Not sufficient stock to facilitate the required sales!");
            }
        }
        else{
            System.out.println("Product Not Found!");
        }
    }
    
    public void addStocks(String productName, int noOfStocks){
        if (products.containsKey(productName)){
            boolean status = products.get(productName).addStock(noOfStocks);
            if (status){
                System.out.println("Stocks added successfully!");
            }
            else{
                System.out.println("Invalid amount of stocks!");
            }
        }
        else{
            System.out.println("Product Not Found!");
        }
    }
    
    public Product getProduct(String productName){
        return products.get(productName);
    }
    
    public boolean productExists(String productName){
        if (products.containsKey(productName)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public HashMap<String, Product> getProducts(){
        return products;
    }
    
    public void filterBySales(){
        ArrayList<Product> productObjList = new ArrayList<>(products.values());
        if (productObjList.isEmpty()){
            System.out.println("No products have been added to the inventory yet!");
            return;
        }
        Comparator<Product> comp = (current, next)->Integer.compare(current.getSales(), next.getSales());
        Collections.sort(productObjList, comp);
        int count = 1;
        for (Product product:productObjList){
            System.out.println("================");
            System.out.println("["+count+"] Name : "+product.getName());
            System.out.println("Category : "+product.getCategory());
            System.out.println("Sales : "+product.getSales());
            System.out.println("Stock : "+product.getStock());
            System.out.println("Price : "+product.getPrice());
            System.out.println("Revenue : "+product.getRevenue());
            count++;
        }
        System.out.println("Total Revenue : "+getTotalRevenue());
    }
    
    public void filterByStock(){
        ArrayList<Product> productObjList = new ArrayList<>(products.values());
        if (productObjList.isEmpty()){
            System.out.println("No products have been added to the inventory yet!");
            return;
        }
        Comparator<Product> comp = (current, next)->Integer.compare(current.getStock(), next.getStock());
        Collections.sort(productObjList, comp);
        int count = 1;
        for (Product product:productObjList){
            System.out.println("================");
            System.out.println("["+count+"] Name : "+product.getName());
            System.out.println("Category : "+product.getCategory());
            System.out.println("Sales : "+product.getSales());
            System.out.println("Stock : "+product.getStock());
            System.out.println("Price : "+product.getPrice());
            System.out.println("Revenue : "+product.getRevenue());
            count++;
        }
        System.out.println("Total Revenue : "+getTotalRevenue());
    }
    
    public HashSet<String> getCategories(){        
        HashSet<String> categories = new HashSet<>();
        for (String key : products.keySet()){
            String category = products.get(key).getCategory();
            categories.add(category);
        }
        return categories;
    }
    
    public void displayCategoryProductsInfo(String category){
        ArrayList<Product> productList = new ArrayList<>(products.values());
        if (getCategories().contains(category)){
            int count = 1;
            int revenue = 0;
            for (Product product : productList){
                if (product.getCategory().equals(category)){
                       System.out.println("==============");
                       System.out.println("["+count+"] Name : "+product.getName());
            System.out.println("Category : "+product.getCategory());
            System.out.println("Sales : "+product.getSales());
            System.out.println("Stock : "+product.getStock());
            System.out.println("Price : "+product.getPrice());
            System.out.println("Revenue : "+product.getRevenue());
            revenue+=product.getRevenue();
            count++;
                }
            }
            System.out.println("Total Category Revenue : "+revenue);
        }
        else{
            System.out.println("Category doesnt exist!");
        }
    }
    
    public void lowStockAlert(){
        if (products.isEmpty()){
            return;
        }
        else{
            for (String key:products.keySet()){
                if (products.get(key).getStock() <=2){
                    System.out.println(products.get(key).getName()+"("+products.get(key).getStock()+") on low stock!");
                }
            }
        }
    }
    
    public int getTotalRevenue(){
        int total=0;
        for (String key : products.keySet()){
            total+=products.get(key).getRevenue();
        }
        return total;
    }
    
    public void removeCategory(String category){
        HashSet categories = getCategories();
        if (!categories.contains(category)){
            System.out.println("Category Not Found!");
            return;
        }
        Iterator<Map.Entry<String, Product>> iterator = products.entrySet().iterator();
        
        while (iterator.hasNext()){
            Map.Entry<String, Product> entry = iterator.next();
            if (entry.getValue().getCategory() == category){
                iterator.remove();
            }
        }
        
        System.out.println("All Products as well as the category has been removed!");
    }
}

class UserInterface{
    Inventory inventory = new Inventory();
    Scanner sc = new Scanner(System.in);
    
    public void startProgram(){
        boolean exit = false;
        System.out.println("=======VF SMART INVENTORY MANAGEMENT");
        while(!exit){
            System.out.println("==================");
            System.out.println("[1] Add Product");
            System.out.println("[2] Remove Product");
            System.out.println("[3] Remove Category");
            System.out.println("[4] Update Product Info");
            System.out.println("[5] Add Stock To A Product");
            System.out.println("[6] Add Sales Of A Product");
            System.out.println("[7] Search Product"); 
            System.out.println("[8] Display All Products");
            System.out.println("[9] Filter Products");
            System.out.println("[10] Display Category Products");          
            System.out.println("[11] Exit");
            System.out.println();  
            inventory.lowStockAlert(); 
            System.out.println();
            System.out.print("Your Choice : ");
            try{
                int choice = sc.nextInt();
                switch (choice){
                    case 1:{
                        addProduct();
                        break;
                    }
                    case 2:{
                        removeProduct();
                        break;
                    }
                    case 3:{
                        removeCategory();
                        break;
                    }
                    case 4:{
                        System.out.println();
                        ArrayList<Product> productList = inventory.getAllProducts();
            if (productList.isEmpty()){
                System.out.println("No Products Have Been Added To The Inventory!");
                break;    
            }
            System.out.println("All Products :-");
            
            for (Product product : productList){
                System.out.println(product.getName());
            }
            System.out.println();
            System.out.print("Enter Name of Product : ");
            sc.nextLine();            
            String name = sc.nextLine().toLowerCase();
            if (!inventory.productExists(name)){
                System.out.println("Product Doesnt Exist!");
                break;
            }
                        System.out.println("[1] Update Name Of Product");
                        System.out.println("[2] Update Price Of Product");
                        System.out.print("Your Choice(1-11) : ");
                        int choice2 = sc.nextInt();                            switch (choice2){
                            case 1:{
                                updateName(name);
                                break;
                            }
                            case 2:{
                                updatePrice(name);
                                break;
                            }
                            default:{
                                System.out.println("Invalid Input!");                                
                            }
                        }
                        break;
                    }
                    case 5:{
                        System.out.println();
                        addStocks();
                            break;
                    }
                    case 6:{
                        addSales();
                        break;
                    }
                    case 7:{
                        searchProduct();
                        break;
                    }
                    case 8:{
                        displayAllProducts();
                        break;
                    }
                    case 9:{
                        System.out.println();
                        System.out.println("[1] Filter By Stocks(Ascending)");
                        System.out.println("[2] Filter By Sales(Ascending)");
                        System.out.println();
                        System.out.print("Your choice : ");
                        
                        int choice2 = sc.nextInt();
                        switch (choice2){
                            case 1:{
                                filterByStocks();
                                break;
                            }
                            case 2:{
                                filterBySales();
                                break;
                            }
                            default:{
                                System.out.println("Invalid Input!");
                            }
                        }
                        break;
                    }
                    case 10:{
                        displayCategoryProducts();
                        break;
                    }
                    case 11:{
                        System.out.println("Thank You For Using :)");
                        exit = true;
                        break;
                    }
                    default:{
                        System.out.println("Please enter a value between 1 to 11 only!");                        
                    }
                }        
            }            
            catch (Exception e){
                System.out.println("Invalid Input! Please enter a correct input!");
                sc.nextLine();         
                                       
            }              
        }
    }
    
    private void displayCategoryProducts(){
        try{
            HashSet<String> categories = inventory.getCategories();
            if (categories.isEmpty()){
                System.out.println("No Category Found! Please Add Products To Inventory First!");
                return;
            }
            System.out.println("All Categories : ");
            for (String category : categories){
                System.out.println(category);
            }
            System.out.println();
            System.out.print("Enter Name Of Category : ");
            sc.nextLine();
            String category = sc.nextLine().toLowerCase();
            inventory.displayCategoryProductsInfo(category);
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println(e);
            sc.nextLine();
        }
    }
    
    private void filterByStocks(){
        inventory.filterByStock();
    }
    
    private void filterBySales(){
        inventory.filterBySales();
    }
    
    private void displayAllProducts(){
        ArrayList<Product> productList = inventory.getAllProducts();
            if (productList.isEmpty()){
                System.out.println("No Products Have Been Added To The Inventory!");
                return;
            }
            System.out.println("All Products :-");
            for (Product pro : productList){
                System.out.println();                
                System.out.println("Name : "+pro.getName());
                System.out.println("Category : "+pro.getCategory());
                System.out.println("Price : "+pro.getPrice());
                System.out.println("Stock : "+pro.getStock());
                System.out.println("Sales : "+pro.getSales());
                System.out.println("Revenue : "+pro.getRevenue());
            }
            
    }
    private void searchProduct(){
        ArrayList<Product> productList = inventory.getAllProducts();
            if (productList.isEmpty()){
                System.out.println("No Products Have Been Added To The Inventory!");
                return;
            }
            sc.nextLine();        
            System.out.print("Enter Product Name : ");    
            String name = sc.nextLine().toLowerCase();
            if (!inventory.productExists(name)){
                System.out.println("Product Doesnt Exist!");
            }
            else{
                System.out.println("Product Found!");
                System.out.println("Product Details :-");
                Product pro = inventory.getProduct(name);
                System.out.println("Name : "+pro.getName());
                System.out.println("Category : "+pro.getCategory());
                System.out.println("Price : "+pro.getPrice());
                System.out.println("Stock : "+pro.getStock());
                System.out.println("Sales : "+pro.getSales());
                System.out.println("Revenue : "+pro.getRevenue());
            }
    }
    private void addSales(){
        try{
            System.out.println();
                        ArrayList<Product> productList = inventory.getAllProducts();
                        if (productList.isEmpty()){
                            System.out.println("No Products Have Been Added To The Inventory!");
                            return; 
                        }
                        System.out.println("All Products :-");
                        
                        for (Product product : productList){
                            System.out.println(product.getName());
                        }
                        System.out.println();
                        System.out.print("Enter Name of Product : ");
                        sc.nextLine();            
                        String name = sc.nextLine().toLowerCase();
                        if (!inventory.productExists(name)){
                            System.out.println("Product Doesnt Exist!");
                            return;
                        }
                        
                        System.out.print("Enter No.Of Sales : ");
                        int sales = sc.nextInt();
                        inventory.addSales(name, sales);
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println(e);
            sc.nextLine();
        }
    }
    private void addStocks(){
        try{
            ArrayList<Product> productList = inventory.getAllProducts();
                            if (productList.isEmpty()){
                                System.out.println("No Products Have Been Added To The Inventory!");
                                return;
                            }
                            System.out.println("All Products :-");
                            
                            for (Product product : productList){
                                System.out.println(product.getName());
                            }
                            System.out.println();
                            System.out.print("Enter Name of Product : ");
                            sc.nextLine();            
                            String name = sc.nextLine().toLowerCase();
                            if (!inventory.productExists(name)){
                                System.out.println("Product Doesnt Exist!");
                                return;
                            }
                            System.out.print("Enter No. Of Stocks : ");
                            int stocks = sc.nextInt();
                            inventory.addStocks(name, stocks);
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println(e);
            sc.nextLine();
        }
    }
    
    private void updateName(String name){
        try{
            System.out.println("Enter New Name : ");
            String newName = sc.nextLine();
            boolean status = inventory.updateName(name, newName);
            if(status){
                System.out.println("Product Name Updated Successfully!");
            }
            else{
                System.out.println("Product Name Couldnt Be Updated!");
            }
        }
        catch (Exception e){
            System.out.println("Invalid Input!");
            System.out.println(e);
            sc.nextLine();
        }
    }
    
    private void updatePrice(String name){
        System.out.println("Enter New Price : ");
        int price = sc.nextInt();
        boolean status = inventory.updatePrice(name, price);
        if (status){
            System.out.println("Price Updated Successfully!");
            }  
        else{
            System.out.println("Price Couldnt Be Updated!");
        }
    }
    private void addProduct(){
                    try{                    
                        sc.nextLine();
                        System.out.print("Enter Product Name : ");
                        String name = sc.nextLine().toLowerCase();
                        System.out.print("Enter Product Category : ");
                        String category = sc.nextLine().toLowerCase();
                        System.out.print("Enter Product Price : ");
                        int price = sc.nextInt();
                        System.out.print("Enter Product Stock : ");
                        int stock = sc.nextInt();
                        Product product = new Product(name, category, price, stock);
                        boolean status = inventory.addProduct(product);
                        if (status){
                            System.out.println("Product added Successfully!");
                        }
                        else{
                            System.out.println("Product already exists!");
                        }
                    }
                    catch (Exception e){
                        System.out.println("Invalid Input! Please enter a valid input!");
                        System.out.println(e);
                        sc.nextLine();
                        
                    }
    }
    
    private void removeProduct(){
        try{
            ArrayList<Product> productList = inventory.getAllProducts();
            if (productList.isEmpty()){
                System.out.println("No Products Have Been Added To The Inventory!");
                return;
            }
            System.out.println("All Products :-");
            
            for (Product product : productList){
                System.out.println(product.getName());
            }
            System.out.println();
            System.out.print("Enter Name of Product : ");
            sc.nextLine();            
            String name = sc.nextLine().toLowerCase();
            boolean status = inventory.removeProduct(name);
            if (status){
                System.out.println("Product removed successfully!");
            }
            else{
                System.out.println("Product Not Found!");
            }
        }
        catch (Exception e){
            System.out.println("Error! Please enter a valid input!");
            System.out.println(e);
            sc.nextLine();            
        }
    }
    
    private void removeCategory(){
        try{
            HashSet<String> categories = inventory.getCategories();
            if (categories.isEmpty()){
                System.out.println("No Category Found! Please Add Products To Inventory First!");
                return;
            }
            System.out.println("All Categories : ");
            for (String category : categories){
                System.out.println(category);
            }
            System.out.println();
            System.out.print("Enter Name Of Category : ");
            sc.nextLine();
            String category = sc.nextLine().toLowerCase();
            inventory.removeCategory(category);
        }
        catch(Exception e){
            System.out.println("Error! Invalid Input!");
            System.out.println(e);
            sc.nextLine();                      
        }
    }
    
}
public class Main {
	public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.startProgram();
	}
}