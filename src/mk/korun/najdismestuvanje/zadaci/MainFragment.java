package mk.korun.najdismestuvanje.zadaci;

import mk.korun.najdismestuvanje.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
	CommentsSqlDatabase commentsDb;

	@Override
	public void onResume() {
		super.onResume();
		commentsDb = new CommentsSqlDatabase(getActivity());
		commentsDb.open();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(
				mk.korun.najdismestuvanje.R.layout.fragment_main, container,
				false);

		Button btnStartService = (Button) view.findViewById(R.id.btnStartService);
		btnStartService.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent serviceIntent = new Intent(getActivity(),
						TestIntentService.class);
				getActivity().startService(serviceIntent);
			}
		});
		Button btnAddSqlComment = (Button) view.findViewById(R.id.btnAddSqlComment);
		btnAddSqlComment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				commentsDb.createComment("test", "komentar");
				Log.d("SQL USPESNO", commentsDb.getAllComments().toString());
			}
		});
		Button btnSendBroadcast = (Button) view.findViewById(R.id.btnSendBroadcast);
		btnSendBroadcast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("mk.korun.najdismestuvanje.TEST_RECIEVER");
				getActivity().sendBroadcast(intent);
			}
		});
		Button btnSendSms = (Button) view.findViewById(R.id.btnSendSms);
		btnSendSms.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SendMessageActivity.class);
				startActivity(i);
			}
		});
		return view;
	}

	@Override
	public void onPause() {
		commentsDb.close();
		super.onPause();
	}

}
