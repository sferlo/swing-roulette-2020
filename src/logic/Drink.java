package logic;

public class Drink {
	
	private String reference;
	private String name;
	private boolean alcoholic;
	private double price;
	private boolean available;
	
	public Drink(String reference, String name, int alcoholic, double price, boolean stock) {
		super();
		setReference(reference);
		setName(name);
		setPrice(price);
		if (alcoholic == 1) 
			this.alcoholic = false;
		else
			this.alcoholic = true;
		setAvailable(stock);
	}
	
	public String getReference() {
		return reference;
	}
	private void setReference(String reference) {
		this.reference = reference;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	private void setPrice(double price) {
		this.price = price;
	}
	public boolean isAlcoholic() {
		return alcoholic;
	}
	public String toString() {
		return this.getName() + " - " + this.getPrice() + " €";
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
