package mk.korun.najdismestuvanje.models;

public class Property {
	public String name;
	public String type;
	public String description;
	public String latitude;
	public String longitude;
	
	public Property() {
		name = "Default Property";
		description = "Default Property Description";
		latitude = "";
		longitude = "";
	}
	public Property(String n, String d) {
		name = n;
		description = d;
	}
	
}
