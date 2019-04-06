package com.example.android.minireddit.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aly on 3/20/2019.
 */
public class MySingleton {
    private static MySingleton instance;
    private RequestQueue requestQueue;

    private static Context ctx;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();


    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    /**
     * Gets request queue.
     *
     * @return the request queue
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Add to request queue.
     *
     * @param <T> the type parameter
     * @param req the req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
