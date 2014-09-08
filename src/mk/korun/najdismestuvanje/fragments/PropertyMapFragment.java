package mk.korun.najdismestuvanje.fragments;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class PropertyMapFragment extends SupportMapFragment {
	private SupportMapFragment instance;

	public GoogleMap gmap;

	public PropertyMapFragment() {
		super();
		instance = null;
	}

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

	@Override
	public void onResume() {
		gmap = getMap();
		super.onResume();
	}
}
