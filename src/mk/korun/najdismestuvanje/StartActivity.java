package mk.korun.najdismestuvanje;

import mk.korun.najdismestuvanje.fragments.LocationsFragment;
import mk.korun.najdismestuvanje.net.PropertiesManager;
import mk.korun.najdismestuvanje.zadaci.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends FragmentActivity {
	LocationsFragment locFragment = new LocationsFragment();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		createFragmentLocationsResult();
		
		((EditText) findViewById(R.id.edtSearchLocations)).addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				locFragment.filterLocations(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});
	}
	
	private void createFragmentLocationsResult() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frlSearchLocationsResults, locFragment);
		fragmentTransaction.commit();
		
	}
	
	
	public void gotoTestActivity(View v) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
}
