package mk.korun.najdismestuvanje.fragments;

import java.util.ArrayList;

import mk.korun.najdismestuvanje.models.Property;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PropertyMapFragment extends SupportMapFragment {
	private SupportMapFragment instance = null;
	private ArrayList<Marker> currentMarkers = new ArrayList<Marker>();

	public SupportMapFragment getInstance() {
		if (instance == null) {
			instance = newInstance();
		}
		return instance;
	}

	public SupportMapFragment getInstance(GoogleMapOptions gmOptions) {
		instance = newInstance(gmOptions);
		return instance;
	}

	public void updateMarkers(ArrayList<Property> properties) {
		if (instance == null)
			return;
		for (Property property : properties) {
			instance.getMap().addMarker(
				new MarkerOptions()
				.position(new LatLng(
						Float.parseFloat(property.latitude), 
						Float.parseFloat(property.longitude)
					)
				)
				.title(property.name)
			);
		}
	}
}
