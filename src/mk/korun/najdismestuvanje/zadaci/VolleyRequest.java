package mk.korun.najdismestuvanje.zadaci;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyRequest {
	private String url = "https://dl.dropboxusercontent.com/u/57707756/CapTech_Volley_Blog/stringResponse.json";
	private RequestQueue queue;
	private StringRequest stringRequest;
	
	public VolleyRequest(Context context) {
		queue = Volley.newRequestQueue(context);
		
		stringRequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				Log.d("VolleyRequest response", arg0);
			}
			
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.d("VolleyRequest response", "ERROR");
			}
			
		});
	}
	
	public void send() {
		queue.add(stringRequest);
	}
}
