/**
 * 
 */
package modernappliances.model;

/**
 * 
 */
public class Microwave extends Appliance {
	

    private String capacity;
    private String roomType;

	public Microwave(String[] part) {
		
		super(part[0],part[1],Integer.parseInt(part[2]),Integer.parseInt(part[3]),part[4],Double.parseDouble(part[5]));
		
		/*
		 * this.soundRatingOfDishwasher = validateSoundRating(part[7]); this.feature =
		 * part[7];
		 */
		if (part[6] == null || part[6].trim().isEmpty()) {
			
			System.err.println("Warning! invalid value for capacity : value cannot be null or empty!");
			 this.capacity = "Standard";
		}else {
			
			this.capacity = part[6];
		}
		this.roomType = validateRoomType(part[7], part [0]);
		
	}
		private String validateRoomType(String roomTypeUserInput, String itemNumber) {
			
		if(roomTypeUserInput == null || roomTypeUserInput.trim().isEmpty()) {
			System.err.print("Warning: Empty room type for item  and thus using default 'K' as in 'Kitchen'" + itemNumber);
			
			return "Kitchen";
		}
		
		String trimmedRoom = roomTypeUserInput.trim();
		
		return switch (trimmedRoom.toUpperCase()) {
		//if value is "K" or "Kitchen" return "Kitchen"..
		case "K", "KITCHEN" -> "Kitchen";
		case "W", "WORK SITE", "WORKSITE" -> "Work Site";
		default -> {
			
			System.err.println("Warning! : not appropriate room type choosen " + roomTypeUserInput + "'for the item Number"+ itemNumber + ".. Thus using default : Kitchen");
			/* '->' used when for simple expression but for multiple things in single case like 
			 *  in default , have to print warning message and return a value as well....
			 *  */
			yield "Kitchen";
			 // sets the value of the string expression depending on the value of the result for that case if applicable..
			 
		}
		};
	}
		public String getCapacity() {
			return capacity;
		}
		public String getRoomType() {
			return roomType;
		}
		
		@Override 
		public String toString() {
			
			return super.toString()+ "\n" + "Capacity: "+ capacity + "\n" + "Room Type: "+ roomType;
 		
		}
		
		@Override
		public String toFileString() {
			
			String roomTypeForFile = switch (roomType) {
			case "Kitchen" -> "K";
			case "Work Site" -> "W";
			default -> "K";
			// just to be safe set the default in case validation does not work 
			};
			
			return String.join(";",
					getItemNumber(), getBrand(), String.valueOf(getQuantity()),String.valueOf(getWattage()), getColor(), String.valueOf(getPrice()), String.valueOf(capacity), roomTypeForFile);
		}

}
