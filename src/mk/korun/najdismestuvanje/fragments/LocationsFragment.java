package mk.korun.najdismestuvanje.fragments;

import java.util.ArrayList;
import java.util.Locale;

import mk.korun.najdismestuvanje.PropertiesActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocationsFragment extends ListFragment {
	private ArrayList<String> locations;
	private ArrayList<String> filteredLocations;
	
	private ArrayAdapter<String> adapter;
	
	public LocationsFragment() {
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
	
	@SuppressWarnings("unchecked")
	public void filterLocations(String filter) {
		if(filter != "") {
			filter = filter.toLowerCase(Locale.getDefault());
			filteredLocations.clear();
			for (String s : locations) {
				if(s.toLowerCase(Locale.getDefault()).matches(".*" + filter + ".*")) {
					filteredLocations.add(s);
				}
			}
		} else {
			filteredLocations = (ArrayList<String>) locations.clone();
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, filteredLocations);
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
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
}
