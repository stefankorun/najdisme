package mk.korun.najdismestuvanje;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

public class PlacesActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places);
		
		int loc = getIntent().getIntExtra("location", 0);
		((TextView) findViewById(R.id.txvLocationNumber)).setText(Integer.toString(loc));
	}
	
	private void createTabs() {
	}
	
}
