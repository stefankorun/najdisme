package mk.korun.najdismestuvanje.fragments;

import mk.korun.najdismestuvanje.R;
import mk.korun.najdismestuvanje.network.VolleyRequest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(mk.korun.najdismestuvanje.R.layout.fragment_test, container, false);
		
		/* Adding font to view
		 * 
		TextView txt_naslov = (TextView) view.findViewById(R.id.title);
		Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Light.ttf"); 
		txt_naslov.setTypeface(type);
		*/
		
		Button requestBtn = (Button) view.findViewById(R.id.requestBtn);
		requestBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				VolleyRequest req = new VolleyRequest(getActivity());
				for(int i = 0; i < 5; ++i)
					req.send();
			}
		});
		
		
		return view;
	}
}
