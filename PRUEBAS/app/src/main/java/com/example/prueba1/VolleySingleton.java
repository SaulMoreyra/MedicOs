package com.example.prueba1;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.prueba1.ui.historial.HistorialFragment;


public class VolleySingleton {

    private static VolleySingleton instanciaVolley;
    private RequestQueue request;
    private static Context contexto;

    private VolleySingleton(Context context) {
        contexto = context;
        request = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstanciaVolley(Context context) {
        if(instanciaVolley == null){
            instanciaVolley = new VolleySingleton(context);
        }
        return instanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if(request == null){
            request = Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return request;
    }

    public <T> void addToRequestQueue(Request <T> request){
        getRequestQueue().add(request);
    }

}
