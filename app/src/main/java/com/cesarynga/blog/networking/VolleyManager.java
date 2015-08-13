package com.cesarynga.blog.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ANDROID on 12/08/2015.
 */
public class VolleyManager {

    private static VolleyManager sInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyManager(Context context) {
        mContext = context;
        mRequestQueue =getRequestQueue();
    }

    public static synchronized VolleyManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyManager(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
