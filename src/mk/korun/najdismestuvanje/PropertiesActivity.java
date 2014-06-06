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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PropertiesActivity extends FragmentActivity {
	private PropertyMapFragment fragPropertyMap;
	private PropertyListFragment fragPropertyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_properties);
		
		//Getting the container Layout from the loaded .xml file
		View v = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
		if(v.getTag().toString().equals("land_orientation")) {
			displayBothFragments();
		} else {
			displayMapFragment();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
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
	        	displayListFragment();
	            return true;
	        case R.id.action_showMap:
	        	displayMapFragment();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


	
	private void displayBothFragments() {
		if(fragPropertyMap == null)	createMapFragment();
		if(fragPropertyList == null) createListFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frlPropertiesMap, fragPropertyMap.getInstance());
		fragmentTransaction.replace(R.id.frlPropertyList, fragPropertyList);
		fragmentTransaction.commit();
		
	}
	private void displayMapFragment() {
		if(fragPropertyMap == null)	createMapFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frlPropertiesBoth, fragPropertyMap.getInstance());
		fragmentTransaction.commit();
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
				new LatLng(adrFromGcoder.getLatitude(), adrFromGcoder.getLongitude()), 12));
		fragPropertyMap.getInstance(gmOptions);
	}
	private void displayListFragment() {
		if(fragPropertyList == null) createListFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frlPropertiesBoth, fragPropertyList);
		fragmentTransaction.commit();
	}
	private void createListFragment() {
		fragPropertyList = new PropertyListFragment();
	}
	
}
