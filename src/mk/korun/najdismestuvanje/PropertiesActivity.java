package mk.korun.najdismestuvanje;

import mk.korun.najdismestuvanje.fragments.PropertyMapFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;

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
		
		fragPropertyMap = new PropertyMapFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frlPropertiesMap, fragPropertyMap.instance);
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
