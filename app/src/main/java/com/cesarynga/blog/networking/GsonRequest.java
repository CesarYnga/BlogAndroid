package com.cesarynga.blog.networking;

import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ANDROID on 12/08/2015.
 */
public class GsonRequest<T> extends Request<T> {

    private final Gson mGson = new Gson();
    private final Class<T> mClazz;
    private Object mBody;
    private final Response.Listener<T> mListener;

    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mClazz = clazz;
        mListener = listener;
    }

    public GsonRequest(int method, String url, Object body, Class<T> clazz, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mBody = body;
        mClazz = clazz;
        mListener = listener;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mBody == null ? null : mGson.toJson(mBody).getBytes();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        ArrayMap<String, String> headers = new ArrayMap<>();
        headers.put("Content-Type", "application/json;charset=utf-8");
        return headers;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            VolleyLog.d(json);
            return Response.success(
                    mGson.fromJson(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
