package mk.korun.najdismestuvanje.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        return inflater.inflate(mk.korun.najdismestuvanje.R.layout.fragment_test, container, false);
	}
}
