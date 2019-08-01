package part02;

import java.util.ArrayList;

public class Supplier {
	
	private int supCode;
	private String supName;
	private Address supAddress;
	private SupRegion supRegion;
	private ArrayList<Product> supProducts = new ArrayList<Product>();
	
	public Supplier(int supCode, String supName, Address supAddress, SupRegion supRegion, ArrayList<Product> supProducts) {
		this.supCode = supCode;
		this.supName = supName;
		this.supAddress = supAddress;
		this.supRegion = supRegion;
		this.supProducts = supProducts;
	}
	
	public void printProductList() {
		//Print all products grouped by supplier
		System.out.println("----" + supName.toString() + "----" + "\n");
		
		for (int i = 0; i < supProducts.size(); i++) {
			System.out.println(supProducts.get(i).getProductDetails() + "\n");
		}
	}
	
	public int getSupCode() {
		return supCode;
	}

	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public Address getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(Address supAddress) {
		this.supAddress = supAddress;
	}

	public SupRegion getSupRegion() {
		return supRegion;
	}

	public void setSupRegion(SupRegion supRegion) {
		this.supRegion = supRegion;
	}

	public ArrayList<Product> getSupProducts() {
		return supProducts;
	}

	public void setSupProducts(ArrayList<Product> supProducts) {
		this.supProducts = supProducts;
	}
	
	

}
