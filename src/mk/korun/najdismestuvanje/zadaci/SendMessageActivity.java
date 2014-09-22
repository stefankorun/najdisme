package mk.korun.najdismestuvanje.zadaci;

import mk.korun.najdismestuvanje.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendMessageActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		
		// Event Listeners
		((Button) findViewById(R.id.btnSendSms)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edtPhone = (EditText) findViewById(R.id.edtPhone);
				EditText edtSmsContent = (EditText) findViewById(R.id.edtSmsContent);
				SmsManager.getDefault().sendTextMessage(edtPhone.getText().toString(), null, edtSmsContent.getText().toString(), null,null);
				
				Intent returnIntent = new Intent();
				returnIntent.putExtra("number", edtPhone.getText().toString());
				returnIntent.putExtra("message", edtSmsContent.getText().toString());
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
