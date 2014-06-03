package mk.korun.najdismestuvanje.fragments;

import mk.korun.najdismestuvanje.models.Property;
import android.content.Context;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class PropertyListFragment extends ListFragment {
	private class PropertyListAdapter extends ArrayAdapter<Property> {

		public PropertyListAdapter(Context context, int resource) {
			super(context, resource);
			// TODO Auto-generated constructor stub
		}
		
	}
}
