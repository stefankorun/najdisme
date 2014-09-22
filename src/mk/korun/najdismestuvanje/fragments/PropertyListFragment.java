 package mk.korun.najdismestuvanje.fragments;

import java.util.ArrayList;

import mk.korun.najdismestuvanje.PropertyProfileActivity;
import mk.korun.najdismestuvanje.R;
import mk.korun.najdismestuvanje.models.Property;
import mk.korun.najdismestuvanje.zadaci.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PropertyListFragment extends ListFragment {
	private PropertyListAdapter adapter;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		adapter = new PropertyListAdapter();
		this.setListAdapter(adapter);
		
	
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(getActivity(), PropertyProfileActivity.class);
		i.putExtra("propertyName", adapter.properties.get(position).name);
		i.putExtra("propertyType", adapter.properties.get(position).type);
		i.putExtra("propertyDescription", adapter.properties.get(position).description);
		startActivity(i);
		super.onListItemClick(l, v, position, id);
	}
	
	
	public void updateProperties(ArrayList<Property> p) {
		adapter.properties = p;
		adapter.notifyDataSetChanged();
	}
	
	
	private class PropertyListAdapter extends BaseAdapter {
		ArrayList<Property> properties;
		
		public PropertyListAdapter() {
			properties = new ArrayList<Property>();
			properties.add(new Property("Loading", "Се вчитува"));
		}
		@Override
		public int getCount() {
			return properties.size();
		}
		@Override
		public Object getItem(int position) {
			return properties.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getActivity().getLayoutInflater();
			if(convertView == null) {
				convertView = inflater.inflate(R.layout.adapter_propertylist, parent, false);
			}
			TextView txvName = (TextView) convertView.findViewById(R.id.txvName);
			TextView txvDescription = (TextView) convertView.findViewById(R.id.txvDescription);
			txvName.setText(properties.get(position).name);
//			txvDescription.setText(properties.get(position).description);
			return convertView;
		}
	}
}
