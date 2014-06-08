package mk.korun.najdismestuvanje.net;

import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonRequest;

public class CustomJsonArrayRequest extends JsonRequest<JSONArray> {

	public CustomJsonArrayRequest(int method, String url, String requestBody,
			Listener<JSONArray> listener, ErrorListener errorListener) {
		super(method, url, requestBody, listener, errorListener);
		// TODO Auto-generated constructor stub
	}
	
	public CustomJsonArrayRequest(int method, String url, JSONObject jsonRequest,
	        Listener<JSONArray> listener, ErrorListener errorListener) {
	    super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), 
	        listener, errorListener);
	}

	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse arg0) {
		System.out.println("in parseNetworkResponse");
		arg0.data.toString();
		try {
			String resData = new String(arg0.data, "UTF-8");
			return Response.success(new JSONArray(resData), getCacheEntry());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Response.error(new ParseError(e));
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.error(new ParseError(e));
		}
	}	
}
