package mk.korun.najdismestuvanje.network;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class JsonRequest {
	private String baseUrl = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	private RequestQueue queue;
	private JsonObjectRequest request;
	
	public JsonRequest(Context context) {
		queue = Volley.newRequestQueue(context);
		
		String searchQuerry = "korun";
		String url = baseUrl + searchQuerry;
		request = new JsonObjectRequest(Request.Method.GET, url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Log.d("JSON Response", arg0.toString());
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void send() {
		queue.add(request);
	}
	
}
