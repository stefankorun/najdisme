package mk.korun.najdismestuvanje;

import java.io.IOException;
import java.util.Locale;

import mk.korun.najdismestuvanje.fragments.PropertyMapFragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PropertiesActivity extends FragmentActivity {
	private PropertyMapFragment fragPropertyMap;
  
 
    @Override
    protected void onResume() {
        super.onResume();
        //initilizeMap();
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_properties);
		
		createMapFragment();
	}
	private void createMapFragment() {
		Geocoder gcoder = new Geocoder(this);
		Address adrFromGcoder = new Address(Locale.getDefault());
		
		/*LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		String locationProvider = LocationManager.NETWORK_PROVIDER;
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);*/
		
		try {
			adrFromGcoder = gcoder.getFromLocationName("Bitola, Macedonia", 2).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GoogleMapOptions gmOptions = new GoogleMapOptions();
		gmOptions.camera(CameraPosition.fromLatLngZoom(
				new LatLng(adrFromGcoder.getLatitude(), adrFromGcoder.getLongitude()), 11));
		
		fragPropertyMap = new PropertyMapFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frlPropertiesMap, fragPropertyMap.getInstance(gmOptions));
		fragmentTransaction.commit();
		
	}
	
	
	
	/**
     * function to load map. If map is not created it will create it for you
     * 
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
	*/
}
