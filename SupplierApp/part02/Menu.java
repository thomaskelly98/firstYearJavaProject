package part02;

import java.util.Scanner;

import java.util.ArrayList;

public class Menu {

	//Create scanner to get user input
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
		System.out.println("1. Print all products." + "\n2. Add new supplier." + "\n3. Add new product." + "\n4. Search products." 
				+ "\n5. Modify / Delete product or supplier." + "\n6. Check product stock." + "\n7. Produce quote." 
				+ "\n0. Exit application.");
		
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
					searchProducts(); userChoiceValid = true; break;
				case 5:
					changeObject(); userChoiceValid = true; break;
				case 6:
					checkStock(); userChoiceValid = true; break;	
				case 7:
					produceQuote(); userChoiceValid = true; break;
				case 0: 
					System.out.println("Application Closed."); System.exit(0);
				default: 
					System.out.println("ERROR. Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("ERROR. Please enter a valid choice.");
	            input.next();
	            continue;
	        }							
		}						
	}
	
	static void printAllProducts() {
		System.out.println("-----All Products-----\n");
		for (int i = 0; i < allSuppliers.size(); i++) {
			allSuppliers.get(i).printProductList();
		}
	}
	
	static Product addProduct() {
		String regNum = "\\d+";
		boolean proDiscontinued = false;
		boolean codeValid = false, makeValid = false, modelValid = false, priceValid = false, 
				qtyValid = false, proDiscValid = false;
	
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
		String proMake = null; 
		while (!makeValid) {
			proMake = input.nextLine();
			if (proMake.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid product make.");
				continue;
			}
			makeValid = true;
		}
		
		System.out.println("Product Model:");
		String proModel = null; 
		while (!modelValid) {
			proModel = input.nextLine();
			if (proModel.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid product model.");
				continue;
			}
			modelValid = true;
		}
		
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
		String proDiscStr = null; 
		while (!proDiscValid) {
			proDiscStr = input.nextLine();
		
			if (proDiscStr.equalsIgnoreCase("N")) {
				proDiscontinued = false;
				proDiscValid = true;
			}
			else if (proDiscStr.equalsIgnoreCase("Y")) {
				proDiscontinued = true;
				proDiscValid = true;
			}
			else {
				System.out.println("ERROR. Please enter 'Y' or 'N'.");
				continue;
			}
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
		String regNum = "\\d+";
		boolean supNameValid = false, codeValid = false, bldNumValid = false, streetValid = false, 
				townValid = false, countryValid = false, regionValid = false;
		SupRegion supRegion = null;	
		
		System.out.println("Please enter details of the new supplier.");
		input.nextLine();
		
		System.out.println("Supplier Name:");
		String supName = null; 
		while (!supNameValid) {
			supName = input.nextLine();		
			if (supName.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid supplier name.");
				continue;
			}
			supNameValid = true;
		}
		
		System.out.println("Supplier Code:");
		int supCode = 0;
		while (!codeValid) {
			try {
				supCode = Integer.parseInt(input.nextLine());		
				codeValid = true;
			}
			catch (Exception NumberFormatException) {
				System.out.println("ERROR. Please enter a valid supplier code.");
				continue;	
			}
		}
		
		System.out.println("Supplier Address.");
		System.out.println("Building Number:");
		int bldNum = 0;
		while (!bldNumValid) {
			try {
				bldNum = Integer.parseInt(input.nextLine());
				bldNumValid = true;
			}
			catch (Exception NumberFormatException) {
				System.out.println("ERROR. Please enter a valid building number.");
				continue;
			}
		}
		
		System.out.println("Street Name:");
		String bldStreet = null; 
		while (!streetValid) {			
			bldStreet = input.nextLine();
			if (bldStreet.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid street name.");
			}
			streetValid = true;
		}
		
		System.out.println("Town:");
		String bldTown = null; 
		while (!townValid) {
			bldTown = input.nextLine();	
			if (bldTown.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid town name.");
				continue;
			}
			townValid = true;
		}
		
		System.out.println("Postcode:");
		String bldPcode = null;
		bldPcode = input.nextLine();
			
		
		System.out.println("Country:");		
		String bldCountry = null; 
		while (!countryValid) {
			bldCountry = input.nextLine();	
			if (bldCountry.matches(regNum)) {
				System.out.println("ERROR. Please enter a valid country.");
				continue;
			}
			countryValid = true;
		}
		
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
	
	static void searchProducts() {
		ArrayList<Product> allProducts = new ArrayList<Product>();		
		
		for (int i = 0; i < allSuppliers.size(); i++) {
			for (int j =0; j < allSuppliers.get(i).getSupProducts().size(); j++) {
				if (!allProducts.contains(allSuppliers.get(i).getSupProducts().get(j))) {
					allProducts.add(allSuppliers.get(i).getSupProducts().get(j));
				}
			}
		}
		
		System.out.println("Please type the number of your choice from the following options.");
		System.out.println("1. Search by price range." + "\n2. Print all discontinued products." + "\n3. Print all products from a manufacturer.");
		
		int userChoice = 0;
		boolean userChoiceValid = false;
		double minValue, maxValue = 0;		
		
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				userChoice = input.nextInt();	
				
				switch (userChoice) {
				case 1:
					System.out.println("Please enter minimum value:");
					minValue = input.nextDouble();
					System.out.println("Please enter maximum value:");
					maxValue = input.nextDouble();
					for (int i = 0; i < allProducts.size(); i++) {						
						if (allProducts.get(i).getProPrice() < maxValue && allProducts.get(i).getProPrice() > minValue) {
							System.out.println(allProducts.get(i).getProductDetails() + "\n---");
						}
					}
					userChoiceValid = true; break;
				case 2: 
					System.out.println("All discontinued products:\n");
					for (int i = 0; i < allProducts.size(); i++) {						
						if (allProducts.get(i).isProDiscontinued()) {
							System.out.println(allProducts.get(i).getProductDetails() + "\n---");
						}
					}
					userChoiceValid = true; break;
				case 3: 
					System.out.println("Please enter manufacturer:");
					input.nextLine();
					String userMan = input.nextLine();
					System.out.println("All products from " + userMan + ":\n---");
					for (int i = 0; i < allProducts.size(); i++) {						
						if (allProducts.get(i).getProMake().equalsIgnoreCase(userMan)) {
							System.out.println(allProducts.get(i).getProductDetails() + "\n---");
						}
					}
					userChoiceValid = true; break;
				default: 
					System.out.println("ERROR. Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("ERROR. Please enter a valid choice.");
	            input.next();
	            continue;
	        }							
		}	
		
	}
	
	static void changeObject() {
		int userChoice = 0;
		boolean userChoiceValid = false;
		
		System.out.println("Would you like to modify or delete data? (Enter relevant number):");
		System.out.println("1. Modify Data. \n2. Delete data.");
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				userChoice = input.nextInt();	
				
				switch (userChoice) {
				case 1:
					System.out.println("Would you like to modify a Product or Supplier?");
					System.out.println("1. Product." + "\n2. Supplier.");
					modifyObject();
					userChoiceValid = true; break;
				case 2: 
					System.out.println("Would you like to delete a Product or Supplier?");
					System.out.println("1. Product." + "\n2. Supplier.");
					deleteObject();
					userChoiceValid = true; break;
				default: 
					System.out.println("ERROR. Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("ERROR. Please enter a valid choice.");
	            input.next();
	            continue;
	        }							
		}
	}
	
	static void deleteObject() {
		int userChoice = 0;
		boolean userChoiceValid = false;	
		
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				userChoice = input.nextInt();	
				
				switch (userChoice) {
				case 1:
					System.out.println("Please select the Product you would like to delete:\n---");
					for (int i = 0; i < allSuppliers.size(); i++) {
						for (int j = 0; j < allSuppliers.get(i).getSupProducts().size(); j++ ) {
							System.out.println(allSuppliers.get(i).getSupProducts().get(j).getProductDetails() + "\n---");	
						}
					}
					if (input.hasNextInt()) {
						int product = input.nextInt();
						for (int j = 0; j < allSuppliers.size(); j++) {
							for (int k = 0; k < allSuppliers.get(j).getSupProducts().size(); k++) {
								int code = allSuppliers.get(j).getSupProducts().get(k).getProCode();
								Product thisProduct = allSuppliers.get(j).getSupProducts().get(k);
								if (product == code) {
									allSuppliers.get(j).getSupProducts().remove(thisProduct);
									System.out.println("Product: " + thisProduct.getProMake() + " " + thisProduct.getProModel() + " has been removed.");
								}
							}
						}
					}
					else {
						break;
					}		
					userChoiceValid = true; break;
				case 2: 
					System.out.println("Please enter the code of the Supplier you would like to delete:\n---");
					for (int i = 0; i < allSuppliers.size(); i++) {
						System.out.println(allSuppliers.get(i).getSupCode() + ". " + allSuppliers.get(i).getSupName() + "\n---");						
					}
					if (input.hasNextInt()) {
						int supplier = input.nextInt();
						for (int i = 0; i < allSuppliers.size(); i++) {
							int code = allSuppliers.get(i).getSupCode();
							Supplier thisSup = allSuppliers.get(i);
							if (supplier == code) {
								allSuppliers.remove(thisSup);
								System.out.println("Supplier: " + thisSup.getSupName() + " has been removed.");
							}
						}
					}
					else {
						break;
					}
					userChoiceValid = true; break;
				default: 
					System.out.println("ERROR.Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("ERROR.Please enter a valid choice.\n---");
	        	input.nextLine(); input.nextLine();
	        	changeObject();
	        	break;
	        }							
		}	
	}
	
	static void modifyObject() {
		ArrayList<Product> allProducts = new ArrayList<Product>();
		ArrayList<Product> changeSupProducts = new ArrayList<Product>();
		int userChoice = 0;
		boolean userChoiceValid = false;
		boolean done = false;	
		String regNum = "\\d+";
		boolean bldNumValid = false, streetValid = false, townValid = false, countryValid = false;
		
		for (int i = 0; i < allSuppliers.size(); i++) {
			for (int j =0; j < allSuppliers.get(i).getSupProducts().size(); j++) {
				if (!allProducts.contains(allSuppliers.get(i).getSupProducts().get(j))) {
					allProducts.add(allSuppliers.get(i).getSupProducts().get(j));
				}
			}
		}
		
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				userChoice = input.nextInt();	
				
				switch (userChoice) {
				case 1:
					System.out.println("Please select the Product you would like to modify:\n---");
					for (int i = 0; i < allSuppliers.size(); i++) {
						for (int j = 0; j < allSuppliers.get(i).getSupProducts().size(); j++) {
							System.out.println(allSuppliers.get(i).getSupProducts().get(j).getProductDetails() + "\n---");						
						}
					}
					if (input.hasNextInt()) {
						int product = input.nextInt();
						for (int k = 0; k < allSuppliers.size(); k++) {
							for (int i = 0; i < allSuppliers.get(k).getSupProducts().size(); i++) {
								int code = allSuppliers.get(k).getSupProducts().get(i).getProCode();
								Product thisProduct = allSuppliers.get(k).getSupProducts().get(i);							
								if (product == code) {
									System.out.println("Please enter the type of data you would like to modify:");
									System.out.println("1. Product Code:");								
									System.out.println("2. Product Make:");								
									System.out.println("3. Product Model:");								
									System.out.println("4. Product Price:");
									System.out.println("5. Quantity Available:");
									System.out.println("6. Product Discontinued: (Y/N)");
									if (input.hasNextInt()) {
										int modChoice = input.nextInt();
										
										switch (modChoice) {
										case 1:
											System.out.println("Please enter the new product code:");
											if (input.hasNextInt()) {
												thisProduct.setProCode(input.nextInt());
												System.out.println("Product code for " + thisProduct.getProMake() + " " + thisProduct.getProModel() 
												+ " has been set to " + thisProduct.getProCode());
											}										
											userChoiceValid = true; break;
										case 2: 
											System.out.println("Please enter the product make:");
											input.nextLine();
											if (input.hasNextLine()) {
												thisProduct.setProMake(input.nextLine());
												System.out.println("Product make has been set to " + thisProduct.getProMake());											
											}	
											userChoiceValid = true; break;
										case 3: 
											System.out.println("Please enter the new product model:");
											input.nextLine();
											if (input.hasNextLine()) {
												thisProduct.setProModel(input.nextLine());
												System.out.println("Product model has been set to " + thisProduct.getProModel());
											} 										
											userChoiceValid = true; break;
										case 4: 
											System.out.println("Please enter the new product price:");										
											if (input.hasNextDouble()) {
												thisProduct.setProPrice(input.nextDouble());
												System.out.println("Product code for " + thisProduct.getProMake() + " " + thisProduct.getProModel() 
												+ " has been set to " + thisProduct.getProPrice());
											} 
											userChoiceValid = true; break;
										case 5:
											System.out.println("Please enter the current quantity available:");
											if (input.hasNextInt()) {
												thisProduct.setProQtyAvailable(input.nextInt());
												System.out.println("Quantity available for " + thisProduct.getProMake() + " " + thisProduct.getProModel() 
												+ " has been set to " + thisProduct.getProQtyAvailable());
											}
											userChoiceValid = true; break;
										case 6:
											System.out.println("Is the product currently dicontinued? (Yes/No):");
											input.nextLine();
											if (input.hasNextLine()) {
												if (input.nextLine().equalsIgnoreCase("No")) {
													thisProduct.setProDiscontinued(false);
													System.out.println(thisProduct.getProMake() + " " + thisProduct.getProModel() 
													+ " has been marked as in stock");
												}
												else {
													thisProduct.setProDiscontinued(true);
													System.out.println(thisProduct.getProMake() + " " + thisProduct.getProModel() 
													+ " has been marked as discontinued");
												}											
											} 
											userChoiceValid = true; break;
										default: 
											System.out.println("Please enter a valid choice."); userChoiceValid = false; break;
										}
									}
								}
							}
						}
					}
					userChoiceValid = true; break;
				case 2: 
					System.out.println("Please select the Supplier you would like to modify:\n---");
					for (int i = 0; i < allSuppliers.size(); i++) {
						System.out.println(allSuppliers.get(i).getSupCode() + ". " + allSuppliers.get(i).getSupName() + "\n---");						
					}
					if (input.hasNextInt()) {
						int supplier = input.nextInt();
						for (int i = 0; i < allSuppliers.size(); i++) {
							int code = allSuppliers.get(i).getSupCode();
							Supplier thisSup = allSuppliers.get(i);
							if (supplier == code) {
								System.out.println("Please enter the type of data you would like to modify:");
								System.out.println("1. Supplier Code:");								
								System.out.println("2. Supplier Name:");								
								System.out.println("3. Supplier Address:");							
								System.out.println("4. Supplier Products:");
								if (input.hasNextInt()) {
									int modChoice = input.nextInt();
									
									switch (modChoice) {
									case 1:
										System.out.println("Please enter the new supplier code:");
										if (input.hasNextInt()) {
											thisSup.setSupCode(input.nextInt());
										}
										System.out.println("Supplier code for " + thisSup.getSupName() + " has been amended.");
										userChoiceValid = true; break;
									case 2: 
										System.out.println("Please enter the new supplier name:");
										input.nextLine();
										if (input.hasNextLine()) {
											thisSup.setSupName(input.nextLine());
										}
										System.out.println("Supplier name has been set to " + thisSup.getSupName() + ".");
										userChoiceValid = true; break;
									case 3: 
										System.out.println("Please enter the new supplier address:");
										input.nextLine();
										System.out.println("Building Number:");
										int bldNum = 0;
										while (!bldNumValid) {
											try {
												bldNum = Integer.parseInt(input.nextLine());
												bldNumValid = true;
											}
											catch (Exception NumberFormatException) {
												System.out.println("ERROR. Please enter a valid building number.");
												continue;
											}
										}
										
										System.out.println("Street Name:");
										String bldStreet = null; 
										while (!streetValid) {			
											bldStreet = input.nextLine();
											if (bldStreet.matches(regNum)) {
												System.out.println("ERROR. Please enter a valid street name.");
											}
											streetValid = true;
										}
										
										System.out.println("Town:");
										String bldTown = null; 
										while (!townValid) {
											bldTown = input.nextLine();	
											if (bldTown.matches(regNum)) {
												System.out.println("ERROR. Please enter a valid town name.");
												continue;
											}
											townValid = true;
										}
										
										System.out.println("Postcode:");
										String bldPcode = null;
										bldPcode = input.nextLine();											
										
										System.out.println("Country:");		
										String bldCountry = null; 
										while (!countryValid) {
											bldCountry = input.nextLine();	
											if (bldCountry.matches(regNum)) {
												System.out.println("ERROR. Please enter a valid country.");
												continue;
											}
											countryValid = true;
										}
										Address changeAddress = new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);
										thisSup.setSupAddress(changeAddress);		
										System.out.println("Address for " + thisSup.getSupName() + " has been amended.");
										userChoiceValid = true; break;
									case 4: 
										System.out.println("Please enter the new supplier products:");
										System.out.println("Enter the product codes that are available from this supplier:\n---");
										for (int j = 0; j < allProducts.size(); j++) {
											System.out.println(allProducts.get(j).getProductDetails() + "\n---");
										}
										input.nextLine();
										while (!done) {
											System.out.println("Please enter the relevant codes and press Q when finished:");											
											String codeStr = input.nextLine(); 
											if (codeStr.equalsIgnoreCase("Q")) { //checks if that string holds a Q
												done = true; //sets the value of done to true
												continue; //continues to the end of the while loop
											}
											int thisCode = Integer.parseInt(codeStr);
											changeSupProducts.add(allProducts.get(thisCode-1));
										}
										thisSup.setSupProducts(changeSupProducts);
										System.out.println("Products for " + thisSup.getSupName() + " have been amended.");
										userChoiceValid = true; break;
									default: 
										System.out.println("ERROR. Please enter a valid choice."); userChoiceValid = false; break;
									}
								}
							}
						}
					}
					userChoiceValid = true; break;
				default: 
					System.out.println("ERROR. Please enter a valid choice."); userChoiceValid = false; break;
				}
			}		            
	        else {
	        	System.out.println("ERROR. Please enter a valid choice.");
	            input.next();
	            continue;
	        }							
		}	
	}
	
	static void checkStock() {
		
		ArrayList<Product> allProducts = new ArrayList<Product>();
		
		for (int i = 0; i < allSuppliers.size(); i++) {
			for (int j =0; j < allSuppliers.get(i).getSupProducts().size(); j++) {
				if (!allProducts.contains(allSuppliers.get(i).getSupProducts().get(j))) {
					allProducts.add(allSuppliers.get(i).getSupProducts().get(j));
				}
			}
		}		
		
		System.out.println("Please enter the code for the product you would like to check the stock of." + "\n---");	
		for (int i = 0; i < allProducts.size(); i++) {
			System.out.println(allProducts.get(i).getProCode() + ". " + allProducts.get(i).getProMake() + " " + allProducts.get(i).getProModel() + "\n---");
		}
				
		int userChoice = 0;
		boolean userChoiceValid = false;			
		
		while (!userChoiceValid) {
			if (input.hasNextInt()) {
				try {
					userChoice = input.nextInt();		
					
					for (int k = 0; k < allSuppliers.size(); k++) {
						for (int j = 0; j < allSuppliers.get(k).getSupProducts().size(); j++) {
							if (userChoice == allSuppliers.get(k).getSupProducts().get(j).getProCode()) {
								System.out.println(allSuppliers.get(k).getSupProducts().get(j).getProMake() + " " + allSuppliers.get(k).getSupProducts().get(j).getProModel() 
								+ " has " + allSuppliers.get(k).getSupProducts().get(j).getProQtyAvailable() + " in stock.\n");
								userChoiceValid = true;
							}					
						}
					}
				}
				catch (Exception e) {
					System.out.println("ERROR. Please enter a valid choice.");
		            input.next();
		            continue;
				}
			}
			else {
	        	System.out.println("ERROR. Please enter a valid choice.");
	            input.next();
	            continue;
	        }
		}	
	}
	
	static void produceQuote() {		
		
		System.out.println("Please enter the products that you require:\n---");
		
		boolean done = false;
		ArrayList<Product> allProducts = new ArrayList<Product>();
		
		for (int k = 0; k < allSuppliers.size(); k++) {
			allProducts.addAll(allSuppliers.get(k).getSupProducts());
		}
		
		for (int k = 0; k < allSuppliers.size(); k++) {
			for (int i = 0; i < allSuppliers.get(k).getSupProducts().size(); i++) {
				System.out.println(allSuppliers.get(k).getSupProducts().get(i).getProCode() + ". " + allSuppliers.get(k).getSupProducts().get(i).getProMake() + " " + allSuppliers.get(k).getSupProducts().get(i).getProModel() 
				+ "\nStock: " + allSuppliers.get(k).getSupProducts().get(i).getProQtyAvailable() + "\n---");
			}
		}
		
		System.out.println("Please enter the relevant codes and press Q when finished:");
		String codeStr = null;
		
		while (!done) {
			if (input.hasNextLine() && input.nextLine() != "") {
				codeStr = input.nextLine(); 
			}
			if (codeStr.equalsIgnoreCase("Q")) { //checks if that string holds a Q
				done = true; //sets the value of done to true
				continue; //continues to the end of the while loop
			}
			else {
				try {
					int code = Integer.parseInt(codeStr);	
					if (code <= allProducts.size()+1) {
						for (int i = 0; i < allProducts.size(); i++) {
							if (code == allProducts.get(i).getProCode()) {
								System.out.println("How many of this product would you like?");
								int addQty = input.nextInt();
								int currentQty = allProducts.get(i).getProQtyAvailable();
								int totalQty = currentQty + addQty;
								allProducts.get(i).setProQtyAvailable(totalQty);
								System.out.println("You placed an order for " + addQty + " " + allProducts.get(i).getProMake() + " " + allProducts.get(i).getProModel());
								System.out.println("The total stock for this item has been set to " + totalQty + "\n---");
								System.out.println("Please enter any other relevant codes or press Q if finished:");
								continue;
							}
						}	
					}
					else {
						System.out.println("ERROR. Please enter a number from the list.\n---");
						continue;
					}
				}
				catch (Exception IndexOutOfBoundsException) {
					System.out.println("ERROR. Please enter a number from the list.\n---");
					continue;
				}
			}
		}			
	}
}
