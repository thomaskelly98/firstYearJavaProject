package part01;

import java.util.Scanner;

import java.util.ArrayList;

public class Menu {

	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Supplier> allSuppliers = new ArrayList<Supplier>();
	
	public static void main(String[] args) {	
		boolean userFinished = false;
				
		Supplier pcWorld = GenData.genPCWorld();
		Supplier argos = GenData.genArgos();

		allSuppliers.add(pcWorld);
		allSuppliers.add(argos);		
		
		do {
			//Prints the menu options
			printMenu();					
		}while (!userFinished);		
		
	}
	
	static void printMenu() {				
		int userChoice = 0;		
		
		System.out.println("-----MAIN MENU-----");
		System.out.println("\nPlease choose from the following options and input the relevant number:\n");
		System.out.println("1. Print all products." + "\n2. Add new supplier." + "\n3. Add new product." + "\n4. Exit application.");
		
		boolean userChoiceValid = false;	
		
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				userChoice = input.nextInt();	
				
				switch (userChoice) {
				case 1:
					printAllProducts();	userChoiceValid = true; break;
				case 2: 
					addSupplier(); userChoiceValid = true; break;
				case 3: 
					addProduct(); userChoiceValid = true; break;
				case 4: 
					System.out.println("Application Closed."); System.exit(0);
				default: 
					System.out.println("Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("Please enter a valid choice.");
	            input.next();
	            continue;
	        }							
		}						
	}
	
	static void printAllProducts() {
		System.out.println("-----All Products-----\n");
		for (int i = 0; i < allSuppliers.size(); i++) {
			allSuppliers.get(i).printProductList(); }
	}
	
	static Product addProduct() {	
		boolean proDiscontinued = false;
		boolean codeValid = false, priceValid = false, qtyValid = false;
	
		System.out.println("Please enter details of the new product.");		
		
		System.out.println("Product Code:");
		int proCode = 0;
		while (!codeValid) {
			try {
				proCode = input.nextInt();
				input.nextLine();
				codeValid = true;
			}
			catch (Exception InputMismatchException) {
				System.out.println("ERROR. Please enter a valid product code.");
				input.nextLine();
				continue;
			}
		}
		
		System.out.println("Product Make:");
		String proMake = input.nextLine();
		
		System.out.println("Product Model:");
		String proModel = input.nextLine();
		
		System.out.println("Product Price:");
		double proPrice = 0;
		while (!priceValid) {
			try {
				proPrice = input.nextDouble();
				priceValid = true;
			}
			catch (Exception InputMismatchException) {
				System.out.println("ERROR. Please enter a valid price.");
				input.nextLine();
				continue;
			}
		}
		
		System.out.println("Quantity available:");
		int proQtyAvailable = 0;
		while (!qtyValid) {
			try {
				proQtyAvailable = input.nextInt();
				input.nextLine();
				qtyValid = true;
			}
			catch (Exception InputMismatchException) {
				System.out.println("ERROR. Please enter a valid quantity.");
				input.nextLine();
				continue;
			}
		}
		
		System.out.println("Product Discontinued: (Y/N)");
		String proDiscStr = input.nextLine();
		
		if (proDiscStr.equalsIgnoreCase("N")) {
			proDiscontinued = false;
		}
		else {
			proDiscontinued = true;
		}
		
		Product newProduct = new Product(proCode, proMake, proModel, proPrice, proQtyAvailable, proDiscontinued);		

		System.out.println("Which supplier would you like to assign the product to?");
		for (int i = 0; i < allSuppliers.size(); i++) {
			System.out.println(allSuppliers.get(i).getSupCode() + ". " + allSuppliers.get(i).getSupName());
		}
		
		boolean supNoValid = false;
		System.out.println("Please enter supplier number:");
		while (!supNoValid) {	
			try {		
				int supNo = input.nextInt()-1; input.nextLine();
				if (supNo < allSuppliers.size()) {				
					Supplier userSup = allSuppliers.get(supNo);
					userSup.getSupProducts().add(newProduct);
					supNoValid = true;
				}
				else {
					System.out.println("ERROR. Please enter a valid supplier number.");
					continue;
				}
			}
			catch (Exception InputMismatchException) {
				System.out.println("ERROR. Please enter a valid supplier number.");
				input.nextLine();
				continue;
			}
		}
		
		System.out.println("\n----Details of new Product----\n");
		System.out.println(newProduct.getProductDetails() + "\n");
		
		return newProduct;			
	}
	
	
	static Supplier addSupplier() {
	
		ArrayList<Product> newSupProducts = new ArrayList<Product>();
		boolean regionValid = false;
		SupRegion supRegion = null;	
		
		System.out.println("Please enter details of the new supplier.");
		input.nextLine();
		System.out.println("Supplier Name:");
		String supName = input.nextLine();		
		System.out.println("Supplier Code:");
		int supCode = 0;
		try {
			supCode = Integer.parseInt(input.nextLine());		
		}
		catch (Exception NumberFormatException) {
			System.out.println("ERROR. Please enter a valid supplier code.");
			supCode = Integer.parseInt(input.nextLine());		
		}
		System.out.println("Supplier Address.");
		System.out.println("Building Number:");
		int bldNum = 0;
		try {
			bldNum = Integer.parseInt(input.nextLine());
		}
		catch (Exception NumberFormatException) {
			System.out.println("ERROR. Please enter a valid building number.");
			supCode = Integer.parseInt(input.nextLine());	
		}
		System.out.println("Street Name:");
		String bldStreet = input.nextLine();	
		System.out.println("Town:");
		String bldTown = input.nextLine();	
		System.out.println("Postcode:");
		String bldPcode = input.nextLine();	
		System.out.println("Country:");
		String bldCountry = input.nextLine();	
		System.out.println("Region (Select from one of the following):" + "\n1. United Kingdom" + "\n2. Europe" + "\n3. Outside EU");
		int regionInt = 0;		
		while (!regionValid) {
			try {
				regionInt = Integer.parseInt(input.nextLine());
				switch (regionInt) {
					case 1: supRegion = SupRegion.UNITED_KINGDOM; regionValid = true; break;
					case 2: supRegion = SupRegion.EUROPE; regionValid = true; break;
					case 3: supRegion = SupRegion.OUTSIDE_EU; regionValid = true; break;
					default: System.out.println("ERROR. Choice invalid. Please choose from the options provided."); continue;		
				}
			}
			catch (Exception NumberFormatException){
				System.out.println("ERROR. Please choose from the options provided.");
				continue;
			}
		} 
	
		Address newSupAddress = new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);
		
		Supplier newSupplier = new Supplier(supCode, supName, newSupAddress, supRegion, newSupProducts);
		
		allSuppliers.add(newSupplier);
		
		System.out.println("Supplier " + newSupplier.getSupName() + " has been added.");
		
		return newSupplier;
	}
	

}
