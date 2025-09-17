/**
 * 
 */
package modernappliances.model;

/**
 * 
 */
public class Refrigerator extends Appliance {

	private String numOfDoors;
	private int heightOfRefrigerator;
	private int widthOfRefrigerator;

	// takes each property as array
	public Refrigerator(String[] part) {

		super(part[0], part[1], Integer.parseInt(part[2]), Integer.parseInt(part[3]), part[4],
				Double.parseDouble(part[5]));

		this.numOfDoors = switch (part[6]) {

		case "2" -> "Double doors";
		case "3" -> "Three doors";
		case "4" -> "Four doors";
		default -> throw new IllegalArgumentException("Inappropriate value ! Value must be 2 or 3 or 4");

		};
		
		if (part[7] == null || part[7].trim().isEmpty()) {
			
			throw new IllegalArgumentException(" refrigerator height value is inappropriate! it cannot be null or empty.");
		}
		
		this.heightOfRefrigerator = Integer.parseInt(part[7].trim());
		
		if (part[8] == null || part[8].trim().isEmpty()) {
			
			throw new IllegalArgumentException(" refrigerator width value is inappropriate! it cannot be null or empty.");
		}

		this.widthOfRefrigerator = Integer.parseInt(part[8].trim());

	}

	public String getNumOfDoors() {
		return numOfDoors;
	}

	public int getHeightOfRefrigerator() {
		return heightOfRefrigerator;

	}

	public int getWidthOfRefrigerator() {

		return widthOfRefrigerator;
	}

	@Override
	public String toString() {

		return super.toString() + "\n" + "Number of Doors: " + numOfDoors + "\n" + "Height: " + heightOfRefrigerator
				+ "\n" + "Width: " + widthOfRefrigerator;
	}

	@Override
	public String toFileString() {
		
		String totalDoors = switch (numOfDoors) {
		case "Double doors" -> "2";
		case "Three doors" -> "3";
		case "Four doors" -> "4";
		default -> throw new IllegalStateException("Inappropriate number of doors!");
		};

		return String.join(";", getItemNumber(), getBrand(), String.valueOf(getQuantity()),
				String.valueOf(getWattage()), getColor(), String.valueOf(getPrice()), totalDoors,
				String.valueOf(heightOfRefrigerator), String.valueOf(widthOfRefrigerator));
	}

}
