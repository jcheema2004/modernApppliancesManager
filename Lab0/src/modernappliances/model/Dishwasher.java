/**
 * 
 */
package modernappliances.model;

/**
 * 
 */
public class Dishwasher extends Appliance {
	
    private String soundRatingOfDishwasher;
    private String feature;
    
	public Dishwasher(String[] part) {
		
		super(part[0],part[1],Integer.parseInt(part[2]),Integer.parseInt(part[3]),part[4],Double.parseDouble(part[5]));
		
		if (part[6] == null || part[6].trim().isEmpty()) {
			System.err.println("Warning! not appropriate value given!");
			this.feature = "Standard";
		}else {
			
			this.feature = part[6];
		}
		this.soundRatingOfDishwasher = validateSoundRating(part[7]);
	}
	/*
	 * try { String soundRating = part[7].trim();
	 * 
	 * if(soundRating == "Qt") { this.soundRatingOfDishwaher = "Quietest";
	 * 
	 * }else if(soundRating == "Qr") { this.soundRatingOfDishwaher = "Quiter";
	 * 
	 * }else if(soundRating == "Qu") { this.soundRatingOfDishwaher = "Quiet";
	 * 
	 * }else if(soundRating == "M") { this.soundRatingOfDishwaher = "Moderate";
	 * 
	 * } else { throw new IllegalArgumentException(
	 * "Sound rating should either be Qu or , not " + soundRating ); } }catch
	 * (NumberFormatException ex) { throw new IllegalArgumentException(
	 * "Battery voltage for vacuum must be a number. You typed " + part[7]); }
	 */

	private String validateSoundRating(String soundRating) {
		
		if (soundRating == null || soundRating.trim().isEmpty()) {
			throw new IllegalArgumentException("Not valid format .. Input must be Qu or Qr or M or Qt. You typed "+ soundRating);
		}
		return switch(soundRating.trim()) { 

		case "Qt" -> "Quietest";
		case "Qr" -> "Quieter";
		case "Qu" -> "Quiet";
		case "M"  -> "Moderate";
		default -> throw new IllegalArgumentException("Input must be Qu or Qr or M or Qt!");

		}; 
	}
	
	public String getSoundRatingOfDishwasher() {
		
		return soundRatingOfDishwasher;
	}

	public String getFeature() {
		
		return feature;
	}

	@Override
	public String toString() {

		return super.toString() + "\n" + "Feature: " + feature + "\n" + "SoundRating: " + soundRatingOfDishwasher + "\n";
	}
	
	@Override 
	public String toFileString() {	
		
		String soundRatingForFile = switch(soundRatingOfDishwasher.trim()) { 

		case "Quietest" -> "Qt";
		case "Quieter" -> "Qr";
		case "Quiet" -> "Qu";
		case "Moderate"  -> "M";
		default -> throw new IllegalArgumentException("Input must be Qu or Qr or M or Qt!");

		}; 
		
		return String.join(";", getItemNumber(),getBrand(), String.valueOf(getQuantity()),String.valueOf(getWattage()),
				getColor(), String.valueOf(getPrice()), feature, soundRatingForFile);
	}
	

}


