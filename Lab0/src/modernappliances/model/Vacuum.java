/**
 * 
 */
package modernappliances.model;

/**
 * 
 */
public class Vacuum extends Appliance {

	private String grade;
	private String batteryVoltageOfVacuum;
	
	public Vacuum(String[] part) {
		
		super(part[0], part[1], Integer.parseInt(part[2]), Integer.parseInt(part[3]), part[4], Double.parseDouble(part[5]));
		
		if(part[6] == null || part[6].trim().isEmpty()) {
			System.err.println("Warning! Input for grade is not appropiate!");
			this.grade = "Unknown";
		}else {
			this.grade = part[6];
		}
		/*
		 * this.battteryVoltageOfVacuum = part[7].equals("18") ? "Low" :"High"; //
		 * condition ? value if True : value if False
		 */
		
		try {
			int voltageOfVacuum = Integer.parseInt(part[7].trim());
			
			if(voltageOfVacuum == 18) {
				this.batteryVoltageOfVacuum = "Low";
				
			}else if(voltageOfVacuum == 24) {
				this.batteryVoltageOfVacuum = "High";
				
			}else {
				throw new IllegalArgumentException(
						"Voltage for vacuum should either be 18 or 24, not " + voltageOfVacuum
						);
			}
		}catch (NumberFormatException ex) {
			throw new IllegalArgumentException(
					"Battery voltage for vacuum must be a number. You typed " + part[7]);
		}
		
		}

	public String getGrade() {
		return grade;
	}

	public String getBatteryVoltageOfVacuum() {
		return batteryVoltageOfVacuum;
	}
	
	@Override 
	public String toString() {
		
		return super.toString() + "\n" + "Grade: " + grade + "\n" + "Battery Voltage: " + batteryVoltageOfVacuum;
 	}
	
	@Override 
	public String toFileString() {
		//If the battery voltage is "Low", then set valueOfVoltage to "18", otherwise set it to "24".
		String valueOfVoltage = batteryVoltageOfVacuum.equals("Low") ? "18" : "24";
		
		return String.join(";", getItemNumber(),getBrand(), String.valueOf(getQuantity()),String.valueOf(getWattage()),
				getColor(), String.valueOf(getPrice()), grade, valueOfVoltage);
	}
}
