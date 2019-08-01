package part01;

public class Product {
	
	private int proCode;
	private String proMake, proModel;
	private double proPrice;
	private int proQtyAvailable;
	private boolean proDiscontinued = false;
	
	public Product(int proCode, String proMake, String proModel, double proPrice, int proQtyAvailable, boolean proDiscontinued) {
		this.proCode = proCode;
		this.proMake = proMake;
		this.proModel = proModel;
		this.proPrice = proPrice;
		this.proQtyAvailable = proQtyAvailable;
		this.proDiscontinued = proDiscontinued;		
	}
	
	public String getProductDetails() {
		//get product details
		String returnString;
		String proDiscString;
		
		if (!proDiscontinued) {
			proDiscString = "No";
		}
		else {
			proDiscString = "Yes";
		}
		
		returnString = "Code: " + Integer.toString(proCode) + "\nMake: " + proMake.toString() + "\nModel: " + proModel.toString() + "\nPrice: £" 
		+ String.format("%.2f", proPrice) + "\nQuantity available: " + Integer.toString(proQtyAvailable) + "\nDiscontinued: " + proDiscString;
				
		return returnString;
	}

	public int getProCode() {
		return proCode;
	}

	public void setProCode(int proCode) {
		this.proCode = proCode;
	}

	public String getProMake() {
		return proMake;
	}

	public void setProMake(String proMake) {
		this.proMake = proMake;
	}

	public String getProModel() {
		return proModel;
	}

	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	public double getProPrice() {
		return proPrice;
	}

	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}

	public int getProQtyAvailable() {
		return proQtyAvailable;
	}

	public void setProQtyAvailable(int proQtyAvailable) {
		this.proQtyAvailable = proQtyAvailable;
	}

	public boolean isProDiscontinued() {
		return proDiscontinued;
	}

	public void setProDiscontinued(boolean proDiscontinued) {
		this.proDiscontinued = proDiscontinued;
	}
	
	

}
