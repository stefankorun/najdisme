package mk.korun.najdismestuvanje.models;

public class Property {
	public String name;
	public String description;
	
	public Property() {
		name = "Default Property";
		description = "Default Property Description";
	}
	public Property(String n, String d) {
		name = n;
		description = d;
	}
	
}
