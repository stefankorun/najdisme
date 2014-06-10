package mk.korun.najdismestuvanje.zadaci;

import mk.korun.najdismestuvanje.R;
import android.content.Intent;
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
		
		Button requestBtn = (Button) view.findViewById(R.id.btnStartService);
		requestBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent serviceIntent = new Intent(getActivity(), TestIntentService.class);
				getActivity().startService(serviceIntent);
			}
		});
		return view;
	}
	
}
