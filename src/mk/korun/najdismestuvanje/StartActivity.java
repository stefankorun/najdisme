package mk.korun.najdismestuvanje;

import mk.korun.najdismestuvanje.fragments.LocationsFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
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
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void createFragmentLocationsResult() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frlSearchLocationsResults, locFragment);
		fragmentTransaction.commit();
		
	}
}
