package mk.korun.najdismestuvanje;

import java.util.Locale;

import mk.korun.najdismestuvanje.fragments.PropertyListFragment;
import mk.korun.najdismestuvanje.fragments.PropertyMapFragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PropertiesActivity extends FragmentActivity {
	private PropertyMapFragment fragPropertyMap;
	private PropertyListFragment fragPropertyList;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_properties);
		
		fragmentManager = getSupportFragmentManager();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_properties_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_showList:
	        	createListFragment();
	            return true;
	        case R.id.action_showMap:
	        	createMapFragment();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	protected void onResume() {
		createListFragment();
		super.onResume();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}
	
	private void createMapFragment() {
		fragPropertyMap = new PropertyMapFragment();
		
		Geocoder gcoder = new Geocoder(this);
		Address adrFromGcoder = new Address(Locale.getDefault());
		
		String selectedLocation = getIntent().getStringExtra("location");
		
		try {
			adrFromGcoder = gcoder.getFromLocationName(selectedLocation + ", Macedonia", 2).get(0);
		} catch (Exception e) {
			//If getFromLocationName fails, set coordinates to Ohrid, Macedonia
			adrFromGcoder.setLatitude(41.123);
			adrFromGcoder.setLongitude(20.8);
			e.printStackTrace();
		}
		
		GoogleMapOptions gmOptions = new GoogleMapOptions();
		gmOptions.camera(CameraPosition.fromLatLngZoom(
				new LatLng(adrFromGcoder.getLatitude(), adrFromGcoder.getLongitude()), 11));
		
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.hide(fragPropertyList);
		fragmentTransaction.add(R.id.frlPropertiesBoth, fragPropertyMap.getInstance(gmOptions));
		fragmentTransaction.commit();
	}
	
	private void createListFragment() {
		fragPropertyList = new PropertyListFragment();

		fragmentTransaction = fragmentManager.beginTransaction();
		if(fragPropertyMap != null) fragmentTransaction.hide(fragPropertyMap.getInstance());
		fragmentTransaction.add(R.id.frlPropertiesBoth, fragPropertyList);
		fragmentTransaction.commit();
		
	}
	
}
