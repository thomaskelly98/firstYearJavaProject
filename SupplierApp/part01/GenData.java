package part01;

import java.util.ArrayList;

public class GenData {
	
	public static Supplier genPCWorld() {
		
		Address pcWorldAddress = new Address(51, "Church Street", "Liverpool", "L1 3AY", "England");
		
		ArrayList<Product> pcWorldProducts = new ArrayList<>();
		pcWorldProducts.add(genProducts().get(0));
		pcWorldProducts.add(genProducts().get(1));
		pcWorldProducts.add(genProducts().get(5));
		
		Supplier pcWorld = new Supplier(1, "PC World", pcWorldAddress, SupRegion.UNITED_KINGDOM, pcWorldProducts);
		
		return pcWorld;
	}
	
	public static Supplier genArgos() {
		
		Address argosAddress = new Address(499, "Avebury Boulevard", "Milton Keynes", "MK9 3JT", "England");
		
		ArrayList<Product> argosProducts = new ArrayList<>();		
		argosProducts.add(genProducts().get(2));
		argosProducts.add(genProducts().get(3));
		argosProducts.add(genProducts().get(4));
		
		Supplier argos = new Supplier(2, "Argos", argosAddress, SupRegion.UNITED_KINGDOM, argosProducts);
		
		return argos;
	}
	
	public static ArrayList<Product> genProducts() {
		
		Product dellinspironi3 = new Product(1, "Dell", "Inspiron Core i3", 499.95, 0, true);
		Product dellxpsi7 = new Product(2, "Dell", "XPS Core i7", 1199.95, 50, false);
		Product asuszenbooki5 = new Product(3, "Asus", "ZenBook Core i5", 849.95, 0, true);
		Product asusvivobooki3 = new Product(4, "Asus", "VivoBook Core i3", 299.95, 225, false);
		Product hpspectrei7 = new Product(5, "HP", "Spectre Core i7", 1439.50, 15, false);
		Product aceraspirei3 = new Product(6, "Acer", "Aspire Core i3", 219.95, 200, false);
		
		ArrayList<Product> allProducts = new ArrayList<>();
		allProducts.add(dellinspironi3);
		allProducts.add(dellxpsi7);		
		allProducts.add(asuszenbooki5);
		allProducts.add(asusvivobooki3);
		allProducts.add(hpspectrei7);
		allProducts.add(aceraspirei3);
		
		return allProducts;
	}

}
