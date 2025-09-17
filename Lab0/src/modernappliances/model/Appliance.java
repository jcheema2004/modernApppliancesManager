package modernappliances.model;

public abstract class Appliance {

	private String itemNumber;
	private String brand;
	private int quantity;
	private int wattage;
	private String color;
	private double price;
	
	public Appliance (String itemNumber, String brand, int quantity, int wattage, String color, double price) {
		
		if (itemNumber == null || itemNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("Item number cannot be null or Empty!!!!");
		}
		
		if (brand == null || brand.trim().isEmpty()) {
			throw new IllegalArgumentException("Brand cannot be null or Empty!!!!");
		}
		
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative!!!!");
		}
		
		if (wattage < 0) {
			throw new IllegalArgumentException("Wattage cannot be negative!!!!");
		}
		
		if (color == null || color.trim().isEmpty()) {
			throw new IllegalArgumentException("Color cannot be null or Empty!!!!");
		}
		
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative!!!!");
		}
		
		this.itemNumber = itemNumber;
		this.brand = brand;
		this.quantity = quantity;
		this.wattage = wattage;
		this.color = color;
		this.price = price;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}

	/*
	 * public void setItemNumber(String itemNumber) { this.itemNumber = itemNumber;
	 * }
	 */

	public String getBrand() {
		return brand;
	}

	/*
	 * public void setBrand(String brand) { this.brand = brand; }
	 */

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getWattage() {
		return wattage;
	}

	/*
	 * public void setWattage(int wattage) { this.wattage = wattage; }
	 */

	public String getColor() {
		return color;
	}

	/*
	 * public void setColor(String color) { this.color = color; }
	 */

	public double getPrice() {
		return price;
	}

	/*
	 * public void setPrice(double price) { this.price = price; }
	 */
	
	@Override
	public String toString() {
		
		String priceString;
		if (price % 1 == 0) {
			// if it is a whole number , output would be an integer....
			priceString = String.valueOf((int)price);
			
		}else {
			
			// if it is a decimal , output would remain a decimal 
			priceString = String.valueOf(price);
			
		}
		
		return "Item Number: " + itemNumber + "\n" +
	               "Brand: " + brand + "\n" +
	               "Quantity: " + quantity + "\n" +
	               "Wattage: " + wattage + "\n" +
	               "Color: " + color + "\n" +
	               "Price: " + priceString;
		
	}
	
	public abstract String toFileString();

}
