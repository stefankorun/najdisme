package mk.korun.najdismestuvanje.net;

import java.util.ArrayList;

import mk.korun.najdismestuvanje.PropertiesActivity;
import mk.korun.najdismestuvanje.models.Property;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PropertiesManager {
	private ArrayList<Property> properties;
	private Context context;
	
	private String baseUrl = "http://najdismestuvanje.x10.mx/ajaxServises/getProfileJson.php?";
//	private String testUrl = "http://najdismestuvanje.x10.mx/stefantest/android_req_data.php";
	private RequestQueue queue;
	private JSONObject requestData;
	private JSONArray responseData;

	
	public PropertiesManager(Context context) {
		this.context = context;
		queue = Volley.newRequestQueue(context);
		requestData = new JSONObject();
		properties = new ArrayList<Property>();
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	private void updateList(JSONArray responseData) {
		this.responseData = responseData;
		
		properties.clear();
		for(int i = 0; i < responseData.length(); ++i) {
			JSONObject jsonTemp = (JSONObject) responseData.opt(i);
			
			Property p = new Property();
			p.name = jsonTemp.optString("name");
			p.description = jsonTemp.optString("opis");
			p.latitude = jsonTemp.optString("latitude");
			p.longitude = jsonTemp.optString("longtitude");
			
			properties.add(p);
		}
		PropertiesActivity.updateFragmentListProperties(properties);
	}
	public void updateData(String lat1, String lat2, String lon1, String lon2) {
		try {
			requestData.putOpt("lat1", "41.11016012099889");
			requestData.putOpt("lat2", "41.12373917672242");
			requestData.putOpt("long1", "20.771201015625024");
			requestData.putOpt("long2", "20.837118984375024");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String finalUrl = baseUrl 
				+ "lat1=" + lat1
				+ "&lat2=" + lat2
				+ "&long1=" + lon1
				+ "&long2=" + lon2;
		
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(finalUrl, new Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray arg0) {
				responseData = arg0;
				updateList(arg0);
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.d("JSON Error Response", arg0.toString());
			}
		});
		
		queue.add(jsonArrayRequest);
	}
	
}
