package mk.korun.najdismestuvanje;

import java.io.IOException;
import java.util.Locale;

import mk.korun.najdismestuvanje.fragments.PropertyMapFragment;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

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
		manageFragments();
		
	}
	
	
	private void manageFragments() {
		DisplayMetrics dm1 = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm1);
		Log.d("Display metrics", dm1.toString());
		
		int screenSize = getResources().getConfiguration().screenLayout &
		        Configuration.SCREENLAYOUT_SIZE_MASK;

		String toastMsg;
		switch(screenSize) {
		    case Configuration.SCREENLAYOUT_SIZE_LARGE:
		        toastMsg = "Large screen";
		        break;
		    case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		        toastMsg = "Normal screen";
		        break;
		    case Configuration.SCREENLAYOUT_SIZE_SMALL:
		        toastMsg = "Small screen";
		        break;
		    default:
		        toastMsg = "Screen size is neither large, normal or small";
		}
		Log.d("screenSize", toastMsg);
	}
	
	private void createMapFragment() {
		Geocoder gcoder = new Geocoder(this);
		Address adrFromGcoder = new Address(Locale.getDefault());
		
		String selectedLocation = getIntent().getStringExtra("location");
		
		try {
			adrFromGcoder = gcoder.getFromLocationName(selectedLocation + ", Macedonia", 2).get(0);
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
}
