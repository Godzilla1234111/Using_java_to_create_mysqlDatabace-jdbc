package connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) throws Exception {
		createTable();
		addItemToDb();
		getItemFromDb();
	}
	
	public static void getItemFromDb() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement getItems = con.prepareStatement("SELECT * FROM Products");
			
//			Execute the SQL statement and store the results in the Result object
			ResultSet result = getItems.executeQuery();
						
//			Iterate over the result and print out the data for each row
			while(result.next()){
																
				int productId = result.getInt("ProductID");
		        String productName = result.getString("ProductName");
		        String category = result.getString("Category");
		        double price = result.getDouble("Price");
		        int quantity = result.getInt("Quantity");
				
		        System.out.println("---------------------");		
				System.out.println("Product ID: " + productId);
		        System.out.println("Product Name: " + productName);
		        System.out.println("Category: " + category);
		        System.out.println("Price: " + price);
		        System.out.println("Quantity: " + quantity);
		        System.out.println("---------------------");
		        				        
			}
			System.out.println("all records have been selected");
			
		} catch(Exception e){
			System.out.println(e);
			}		
	}
			
	public static void addItemToDb() throws Exception {
		
//      Define variables for the product information
		final int productId = 4;
		final String productName = "cocomilk";
		final String category = "Dairy";
		final double price = 3.99;
		final int quantity = 53;
		
		try {
			
//			function to insert product to database
			Connection con = getConnection();
			PreparedStatement insertToDb = con.prepareStatement(
					"INSERT INTO Products (ProductID,"
					+ " ProductName, Category, Price, Quantity) "
					+ "VALUES ('"+productId+"', '"+productName+"', '"+category+"', '"+price+"', '"+quantity+"')");
			
//			Execute the PreparedStatement to insert the new row
			insertToDb.executeUpdate();
			
		} catch(Exception e){
			System.out.println(e);
			}
		finally{
			System.out.println("Insert Completed");
			};		
	}
	
	public static void createTable() throws Exception {
		try {
			Connection con = getConnection();
//			function to crate table
			PreparedStatement create = con.prepareStatement("CREATE TABLE Products (" +
		            "ProductID INT NOT NULL," +
		            "ProductName VARCHAR(50) NOT NULL," +
		            "Category VARCHAR(50) NOT NULL," +
		            "Price DECIMAL(10, 2) NOT NULL," +
		            "Quantity INT NOT NULL," +
		            "PRIMARY KEY (ProductID)" +
		            ")");
			
//			Execute the SQL statement to create the Products table
			create.executeUpdate();
						
		} catch(Exception e){
			System.out.println(e);
			}		
		finally{
			System.out.println("Function has been completed and table created");
			};		
	}
	
 	public static Connection getConnection() throws Exception {
		try {
//			Define variables for connection to the database server
			String driver = "com.mysql.jdbc.Driver";
			String url =  "jdbc:mysql://localhost:3306/productinventory";
			String username = "add your mysql username";
			String password = "add your mysql password";
			Class.forName(driver);
					
//			establish connection
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the Databace");
			
			return conn;
						
		} catch(Exception e){
			System.out.println(e);
			}
		
		return null;				
	}

}
