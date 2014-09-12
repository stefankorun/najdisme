package mk.korun.najdismestuvanje.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mk.korun.najdismestuvanje.PropertiesActivity;
import mk.korun.najdismestuvanje.R;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocationsListFragment extends ListFragment {
	private ArrayList<String> locations;
	private ArrayList<String> filteredLocations;
	
	private ArrayAdapter<String> adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		adapter = new ArrayAdapter<String>(getActivity(), 
				R.layout.adapter_locationslist, filteredLocations);
		this.setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		this.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), PropertiesActivity.class);
				TextView tv = (TextView) view.findViewById(android.R.id.text1);
				intent.putExtra("location", tv.getText());
				startActivity(intent);
			}
		});
		super.onStart();
	}
	
	public LocationsListFragment() {
		locations = new ArrayList<String>();
		locations.add("Ohrid");
		locations.add("Kicea");
		locations.add("Kumanovo");
		locations.add("Bitola");
		locations.add("Skopje");
		locations.add("Resen");
		locations.add("Volino");
		locations.add("Cicki");
		filteredLocations = new ArrayList<String>(locations);
	}
	
	public void filterLocations(String filter) {
		// depricated - search in existing list of locations
		if(filter != "") {
			filter = filter.toLowerCase(Locale.getDefault());
			filteredLocations.clear();
			for (String s : locations) {
				if(s.toLowerCase(Locale.getDefault()).matches(".*" + filter + ".*")) {
					filteredLocations.add(s);
				}
			}
		} else {
			filteredLocations = new ArrayList<String>(locations);
		}
		adapter.notifyDataSetChanged();
	}
	
	
	public void searchForLocations(String filter) {
		filteredLocations.clear();
		filteredLocations.addAll(getLocationsBySearch(filter));
		adapter.notifyDataSetChanged();
	}

	private ArrayList<String> getLocationsBySearch(String searchString) {
		Log.d("getLocationsBySearch ||| with string: ", searchString);
		
		Geocoder gcoder = new Geocoder(getActivity());
		List<Address> addrList;
		ArrayList<String> retList = new ArrayList<String>();
		
		try {
			addrList = gcoder.getFromLocationName(searchString, 20);
			Log.d("getLocationsBySearch |||  gcoder.getFromLocationName", "gcoder successful");
			for (Address addr : addrList) {
				Log.d("getLocationsBySearch |||  retList.add", " " + addr.toString());
				retList.add(addr.getAddressLine(0) + ", " + addr.getAddressLine(1));
			}
		} catch (Exception e) {
			//If getFromLocationName fails, set error message
			Log.d("getLocationsBySearch |||  gcoder.getFromLocationName", "gcoder UNsuccessful");
			retList.add("ERROR LOADING CITY NAMES");
			e.printStackTrace();
		}
		return retList;
	}
}
