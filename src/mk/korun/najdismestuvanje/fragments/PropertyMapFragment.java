package mk.korun.najdismestuvanje.fragments;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class PropertyMapFragment extends SupportMapFragment  {
	public GoogleMap gmap;
	public SupportMapFragment instance;
	
	public PropertyMapFragment() {
		super();
		instance = newInstance();
		gmap = instance.getMap();
	}
	
}
