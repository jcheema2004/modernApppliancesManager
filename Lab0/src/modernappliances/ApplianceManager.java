package modernappliances;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import modernappliances.model.*;

public class ApplianceManager {

	private List<Appliance> appliances = new ArrayList<>();
	//Scanner gets input from user....
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		ApplianceManager appliance = new ApplianceManager();
		appliance.loadAppliances();
		appliance.displayMenu();

	}

	private void loadAppliances() {

		// read appliances.txt and create objects..
		String fileName = "appliances.txt";
		// try with resources automatically closes the file when done...
		// bufferedReader reads text efficiently..
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			String line;
			// reads line until the end of the file..
			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split(";");
				// skips lines that don't have enough data..
				if (parts.length < 8)
					continue;

				try {

					Appliance appliance = createApplianceFromParts(parts);

					if (appliance != null) {

						appliances.add(appliance);

					}
				} catch (Exception e) {

					System.err.println(" Error parsing line: " + line);
					System.err.println(" Error : " + e.getMessage());

				}

			}
			// displays about and inform that so and so number of appliances are loaded
			// successfully....
			System.out.println("Loaded successfully " + appliances.size() + " appliances!");

		} catch (IOException e) {

			System.err.println("Error reading file: " + e.getMessage());

		}
	}

	private Appliance createApplianceFromParts(String[] parts) {
		if (parts[0].isEmpty())
			return null;

		// charAt returns the char value at specified index....
		char firstDigit = parts[0].charAt(0);

		try {

			switch (firstDigit) {
			case '1':
				return new Refrigerator(parts);
			case '2':
				return new Vacuum(parts);
			case '3':
				return new Microwave(parts);
			case '4':
			case '5':
				return new Dishwasher(parts);
			default:
				System.err.println("Unknown appliance type for item " + parts[0]);
				return null;

			}
		} catch (Exception e) {

			System.err.println("Failed to create a appliance for item = " + parts[0]);
			System.err.println("Error: " + e.getMessage());

			return null;
		}

	}

	private void displayMenu() {

//		Scanner scanner = new Scanner(System.in);
		//this.scanner = new Scanner(System.in);
		int choice;

		do {

			System.out.println("\nWelcome to Modern Appliances!");
			System.out.println("How may we assist you?");
			System.out.println("1 - Check out appliance");
			System.out.println("2 - Find appliances by brand");
			System.out.println("3 - Display appliances by type");
			System.out.println("4 - Produce random appliance list");
			System.out.println("5 - Save and exit");
			System.out.println("Enter option: ");

			try {

				// scanner.nextLine() listens to whatever the user types and gets it as text....
				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {

				case 1 -> checkoutAppliance();
				case 2 -> findAppliancesByBrand();
				case 3 -> displayAppliancesByType();
				case 4 -> produceRandomApplianceList();
				case 5 -> {

					saveAndExit();
					System.out.println("Goodbye!");

				}
				default -> {

					System.out.println("Not appropriate option! please enter 1-5.");
					choice = 0;

				}
				}
			} catch (NumberFormatException e) {

				System.out.println("Please enter a valid number (1-5)!");
				choice = 0;

			}

		} while (choice != 5);

		//scanner.close();

	}

	private void checkoutAppliance() {

		// Option 1..
		// Scanner gets input from user
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the item number of an appliance: ");

		String itemNumber = this.scanner.nextLine().trim();

		boolean found = false;

		for (Appliance appliance : appliances) {

			if (appliance.getItemNumber().equals(itemNumber)) {

				// flag to track if we found the appliance....
				found = true;

				if (appliance.getQuantity() > 0) {

					// decreasing quantity by 1....
					appliance.setQuantity(appliance.getQuantity() - 1);

					System.out.println("Appliance \"" + itemNumber + "\" has been checked out. ");
					System.out.println("Remaining quantity: " + appliance.getQuantity());

				} else {

					System.out.println("The appliance is not available to be checked out.");
					System.out.println("Current quantity: 0");

				}

				// exit loop since found the appliance....
				break;

			}

		}

		if (!found) {

			System.out.println("No appliances found with the item number " + itemNumber + "!");

		}

	}

	private void findAppliancesByBrand() {

		// Option 2..
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Enter brand to search for: ");

		String searchBrand = this.scanner.nextLine().trim();

		if (searchBrand.isEmpty()) {

			System.out.println("Brand name cannot be empty!");
			return;

		}

		System.out.println("Matching Appliances:\n");

		boolean found = false;

		for (Appliance appliance : appliances) {

			// equalsIgnoreCase() makes the search case-insensitive
			if (appliance.getBrand().equalsIgnoreCase(searchBrand)) {

				System.out.println(appliance.toString());
				System.out.println();

				// sets value to true if we found at least one match....
				found = true;

			}

		}

		if (!found) {

			System.out.println("No appliances found for brand: " + searchBrand);

		}

	}

	// ...............................................................................
	private void displayAppliancesByType() {

		// Option 3..
		//Scanner scanner = new Scanner(System.in);

		System.out.println("\nAppliance Types");
		System.out.println("1 - Refrigerators");
		System.out.println("2 - Vacuums");
		System.out.println("3 - Microwaves");
		System.out.println("4 - Dishwashers");
		System.out.println("Enter type of appliance: ");

		try {

			int typeChoice = Integer.parseInt(this.scanner.nextLine());

			switch (typeChoice) {
			case 1 -> displayRefrigerators();
			case 2 -> displayVacuums();
			case 3 -> displayMicrowaves();
			case 4 -> displayDishwashers();
			default -> System.out.println("Invalid appliance type! Please enter 1-4.");

			}

		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number (1-4)!");
		}

	}

	private void displayRefrigerators() {

		//Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of doors: 2 (double doors), 3 (three doors) or 4 (four doors): ");

		try {

			int doors = Integer.parseInt(this.scanner.nextLine());

			if (doors < 2 || doors > 4) {

				System.out.println("Invalid number of doors! Please enter 2, 3, or 4.");
				return;

			}

			// converting numbers to word representation for searching..
			String doorSearchTerm = switch (doors) {

			case 2 -> "Double doors";
			case 3 -> "Three doors";
			case 4 -> "Four doors";
			default -> "";

			};

			System.out.println("\nMatching refrigerators:");
			boolean found = false;

			for (Appliance appliance : appliances) {
				// "instanceof" checks if appliance is of specific type....
				// for example here is it really a dishwasher...
				if (appliance instanceof Refrigerator) {
					// (Refrigerator) casts the general appliance to a specific type so type
					// specific methods could be called....
					Refrigerator fridge = (Refrigerator) appliance;

					if (fridge.getNumOfDoors().equalsIgnoreCase(doorSearchTerm)) {

						System.out.println(fridge.toString());
						System.out.println("------------------------------------------------------");

						found = true;

					}
				}
			}

			if (!found) {

				System.out.println("No refrigerators found with " + doors + " doors.");

			}

		} catch (NumberFormatException e) {

			System.out.println("Please enter a valid number!");

		}

	}

	private void displayVacuums() {

		//Scanner scanner = new Scanner(System.in);
		System.out.println("Enter battery voltage value. 18 V (low) or 24 V (high): ");

		String voltageUserInput = this.scanner.nextLine().trim();

		String voltageType;

		if (voltageUserInput.equals("18")) {

			voltageType = "Low";

		} else if (voltageUserInput.equals("24")) {

			voltageType = "High";

		} else {

			System.out.println("Invalid voltage! Please enter 18 or 24.");

			return;

		}

		System.out.println("\nMatching vacuums: ");
		boolean found = false;

		for (Appliance appliance : appliances) {

			if (appliance instanceof Vacuum) {

				Vacuum vacuum = (Vacuum) appliance;

				if (vacuum.getBatteryVoltageOfVacuum().equals(voltageType)) {

					System.out.println(vacuum.toString());
					System.out.println("-------------------------------------------------");

					found = true;

				}
			}
		}

		if (!found) {

			System.out.println("No vacuums found with " + voltageType + " battery voltage.");

		}

	}

	private void displayMicrowaves() {

		//Scanner scanner = new Scanner(System.in);
		System.out.println("Room where the microwave will be installed: K (kitchen) or W (work site): ");

		String roomCode = this.scanner.nextLine().trim().toUpperCase();
		String roomType;

		if (roomCode.equals("K")) {

			roomType = "Kitchen";

		} else if (roomCode.equals("W")) {

			roomType = "Work Site";

		} else {

			System.out.println("Invalid room type! Please enter K or W.");
			return;

		}

		System.out.println("\nMatching microwaves: ");
		boolean found = false;

		for (Appliance appliance : appliances) {

			if (appliance instanceof Microwave) {

				Microwave microwave = (Microwave) appliance;

				if (microwave.getRoomType().equals(roomType)) {

					System.out.println(microwave.toString());
					System.out.println("-------------------------------------------------");

					found = true;

				}

			}
		}

		if (!found) {

			System.out.println("No microwaves found with " + roomType + " installation.");

		}

	}

	private void displayDishwashers() {

		//Scanner scanner = new Scanner(System.in);
		System.out.println(
				"Enter the sound rating of the dishwasher: Qt (Quietest), Qr (Quieter), Qu (Quiet) or M (Moderate): ");

		String soundCode = this.scanner.nextLine().trim();
		String soundRating;

		switch (soundCode.toUpperCase()) {
		case "QT" -> soundRating = "Quietest";
		case "QR" -> soundRating = "Quieter";
		case "QU" -> soundRating = "Quiet";
		case "M" -> soundRating = "Moderate";
		default -> {

			System.out.println("Invalid sound rating! Please enter Qt, Qr, Qu , or M.");
			return;

		}
		}

		System.out.println("\nMatching dishwashers: ");
		boolean found = false;

		for (Appliance appliance : appliances) {

			if (appliance instanceof Dishwasher) {

				Dishwasher dishwasher = (Dishwasher) appliance;

				if (dishwasher.getSoundRatingOfDishwasher().equals(soundRating)) {

					System.out.println(dishwasher.toString());
					System.out.println("-------------------------------------------------");

					found = true;

				}
			}
		}

		if (!found) {

			System.out.println("No dishwashers found with " + soundRating + " sound rating.");

		}

	}

	// ..................................................
	private void produceRandomApplianceList() {

		// Option 4 on menu....
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of appliances: ");

		try {

			int count = Integer.parseInt(this.scanner.nextLine());

			if (count <= 0) {

				System.out.println("Please enter a positive number > 0!");
				return;

			}

			// checking availability....
			if (count > appliances.size()) {

				System.out.println("Warning! Only " + appliances.size() + " appliances available. Showing all.");
				count = appliances.size();

			}

			System.out.println("\nRandom appliances: ");

			// creating a copy sort of list to avoid modifying the original list....
			List<Appliance> shuffledAppliances = new ArrayList<>(appliances);

			// shuffling or rearranging the list randomly....
			Collections.shuffle(shuffledAppliances);

			// this will show the first count appliance from shuffled list....
			for (int i = 0; i < count; i++) {
				System.out.println(shuffledAppliances.get(i).toString());

				System.out.println("-----------------------------------------------------");

			}

		} catch (NumberFormatException e) {

			System.out.println("Please enter a valid number!");

		}

	}

	private void saveAndExit() {

		// Option 5..
		String fileName = "appliances.txt";

		// PrintWriter that can write to files....
		// try-catch automatically closes file when done....
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

			for (Appliance appliance : appliances) {

				writer.println(appliance.toFileString());

			}

			System.out.println(
					"Data is saved successfully! " + appliances.size() + " appliances are saved to " + fileName + "!");

		} catch (IOException e) {

			System.err.println("Error saving file: " + e.getMessage());
			System.err.println("Your changes may not have been saved properly.");

		}

	}

}
