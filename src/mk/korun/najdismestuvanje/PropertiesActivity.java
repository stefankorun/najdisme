package mk.korun.najdismestuvanje;

import java.util.ArrayList;
import java.util.Locale;

import mk.korun.najdismestuvanje.fragments.PropertyListFragment;
import mk.korun.najdismestuvanje.fragments.PropertyMapFragment;
import mk.korun.najdismestuvanje.models.Property;
import mk.korun.najdismestuvanje.net.PropertiesManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PropertiesActivity extends FragmentActivity {
	//Fragment manager
	FragmentManager fragmentManager;
	//Fragments
	private static PropertyMapFragment fragPropertyMap;
	private static PropertyListFragment fragPropertyList;
	//Bundle data
	Bundle savedInstanceState;
	
	//Network manager for properties (mesta)
	private PropertiesManager propertiesManager;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_properties);
		
		this.savedInstanceState = savedInstanceState;
		
		fragmentManager = getSupportFragmentManager();
		propertiesManager = new PropertiesManager(this);
	}
	@Override
	protected void onResume() {
		//Getting the container Layout from the loaded .xml file
		View v = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
		if(v.getTag().toString().equals("land_orientation")) {
			displayBothFragments();
		} else {
			displayMapFragment();
		}
		super.onResume();
	}
	@Override
	protected void onStop() {
		fragPropertyList = null;
		fragPropertyMap = null;
		super.onStop();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable("fragPropertyMapCamera", fragPropertyMap.getInstance().getMap().getCameraPosition());
		super.onSaveInstanceState(outState);
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
		createMapFragment();
		createListFragment();
		propertiesManager.updateData(
				"41.11016012099889", "41.12373917672242", "20.771201015625024", "20.837118984375024");
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.frlPropertiesMap, fragPropertyMap.getInstance());
		fragmentTransaction.replace(R.id.frlPropertyList, fragPropertyList);
		fragmentTransaction.commit();
		
		//fragmentManager ne e potreben ko ke imame 2 fragmenti vo prozorec
		fragmentManager = null;
	}
	private void displayMapFragment() {
		if(fragPropertyMap == null) createMapFragment();
		propertiesManager.updateData(
				"41.11016012099889", "41.12373917672242", "20.771201015625024", "20.837118984375024");
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		//Proverka dali na pocetok postojt fragPropertyList zs prvo e inicijalzirana samo mapava
		if(fragPropertyList != null) fragmentTransaction.hide(fragPropertyList);
		//Proverka dali e vekje dodaen fragPropertyMap vo frlPropertiesBoth
		if(fragmentManager.findFragmentById(fragPropertyMap.getInstance().getId()) == null)
			//TODO Pubav fix za ostanvenje na stariot fragment pri rotacija
			fragmentTransaction.replace(R.id.frlPropertiesBoth, fragPropertyMap.getInstance());
		else 
			fragmentTransaction.show(fragPropertyMap.getInstance());
		fragmentTransaction.commit();
	}
	private void displayListFragment() {
		if(fragPropertyList == null) createListFragment();
		propertiesManager.updateData(
				"41.11016012099889", "41.12373917672242", "20.771201015625024", "20.837118984375024");
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.hide(fragPropertyMap.getInstance());
		//Proverka dali e vekje dodaen fragPropertyMap vo frlPropertiesBoth
		if(fragmentManager.findFragmentById(fragPropertyList.getId()) == null)
			fragmentTransaction.add(R.id.frlPropertiesBoth, fragPropertyList);
		else 
			fragmentTransaction.show(fragPropertyList);
		fragmentTransaction.commit();
	}
	private void createMapFragment() {
		fragPropertyMap = new PropertyMapFragment();
		
		Geocoder gcoder = new Geocoder(this);
		Address adrFromGcoder = new Address(Locale.getDefault());
		
		String selectedLocation = getIntent().getStringExtra("location");
		
		try {
			adrFromGcoder = gcoder.getFromLocationName(selectedLocation, 2).get(0);
		} catch (Exception e) {
			//If getFromLocationName fails, set coordinates to Ohrid, Macedonia
			adrFromGcoder.setLatitude(41.123);
			adrFromGcoder.setLongitude(20.8);
			e.printStackTrace();
		}
		
		CameraPosition camPos;
		
		if(savedInstanceState != null && savedInstanceState.getParcelable("fragPropertyMapCamera") != null) {
			camPos = savedInstanceState.getParcelable("fragPropertyMapCamera");
		} else {
			camPos = CameraPosition.fromLatLngZoom(new LatLng(adrFromGcoder.getLatitude(), adrFromGcoder.getLongitude()), 12);
		}
		
		GoogleMapOptions gmOptions = new GoogleMapOptions();
		gmOptions.camera(camPos);
		fragPropertyMap.getInstance(gmOptions);
	}
	private void createListFragment() {
		fragPropertyList = new PropertyListFragment();
	}
	
	
	/* === STATIC FUNCIONS === */
	public static void updateFragmentListProperties(ArrayList<Property> properties) {
		if(fragPropertyList != null) { 
			// initialy fragPropertyList does not exists
			fragPropertyList.updateProperties(properties);
		}
		fragPropertyMap.updateMarkers(properties);
	}
	
}
