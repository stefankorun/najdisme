package mk.korun.najdismestuvanje;

import mk.korun.najdismestuvanje.fragments.LocationsListFragment;
import mk.korun.najdismestuvanje.zadaci.MainActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends FragmentActivity {
	LocationsListFragment locFragment = new LocationsListFragment();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		createFragmentLocationsResult();
		
		// Event Listeners
		((EditText) findViewById(R.id.edtSearchLocations)).addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				//locFragment.searchForLocations(s.toString());
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
		((Button) findViewById(R.id.btnSearchGeocoder)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et = (EditText) findViewById(R.id.edtSearchLocations);
				locFragment.searchForLocations(et.getText().toString());
//				locFragment.testNotifyAdapter();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(!isNetworkAvailable()) {
			prepareNoNetworkAlertDialog().show();
		}
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
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	private AlertDialog prepareNoNetworkAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("This application requires internet connectivity.\n\nTurn WiFi on?").setTitle("No internet access");
		
		// Add the buttons
		builder.setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
		           }
		       });
		builder.setNegativeButton("Exit app", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   finish();
		        	   System.exit(0);
		           }
		       });

		// Create the AlertDialog
		return builder.create();
	}
}
