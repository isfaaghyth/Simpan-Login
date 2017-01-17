package io.belajar.simpan.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import io.belajar.simpan.Interfaces.NetworkListener;

/**
 * Created by isfaaghyth on 17/1/17.
 */

public class NetworkPresenter {

    private static String TAG = "NetworkPresenter";

    private Context context;
    private NetworkListener listener;

    public NetworkPresenter(Context context, NetworkListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void sendRequest(String URL) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error :(");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("uname", listener.username());
                param.put("pass", listener.password());
                return param;
            }
        };
        queue.add(request);
    }
}
