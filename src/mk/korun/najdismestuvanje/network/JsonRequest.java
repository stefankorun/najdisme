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
	private String baseUrl = "https://api.foursquare.com/v2/users/self?oauth_token=OMYLKKWRQL5WXLMTGS2DZWBP5C1EX14AFSWPUVIKO5FBPR3V&v=20140527";
	private RequestQueue queue;
	private JsonObjectRequest request;
	
	public JsonRequest(Context context) {
		queue = Volley.newRequestQueue(context);
		
		String searchQuerry = "";
		String url = baseUrl + searchQuerry;
		request = new JsonObjectRequest(Request.Method.GET, url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Log.d("JSON Response", arg0.optString("meta"));
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
