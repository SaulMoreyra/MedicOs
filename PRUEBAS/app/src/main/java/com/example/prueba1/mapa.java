package com.example.prueba1;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.prueba1.directioshelpers.FetchURL;
import com.example.prueba1.directioshelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class mapa extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;//ayuda a encontrar la ultima ubicacion conocida
    Location mCurrentLocation;//variable para almacenar la ubicacion actual
    MarkerOptions posActual, marca1;//para manejar las coordenadas de la posicion actual
    Polyline actualPolyline;//para dibujar la ruta
    //DatabaseReference mDatabase;//referencia a la BD de Firbase
    private Marker marcador;//Marcador para la ubicacion
    double lat = 0.0, lon = 0.0;//variable para guardar latitud y longitud de la posicion actual

    String name="";
    double lat_t=0.0,long_t=0.0;
    private int id_pedido;
    private String estado="asignado";
    private Button recibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        recibir = findViewById(R.id.recibido);

        lat_t = getIntent().getDoubleExtra("latitud_cliente",0.0);
        long_t = getIntent().getDoubleExtra("longitud_cliente",0.0);
        name = getIntent().getStringExtra("nombre");
        id_pedido = getIntent().getIntExtra("id_pedido",0);
        /**
         *  creacion de getFusedLocationProviderClient para obtener la
         * ubicacion y subirla a firebase
         */
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(MainActivity.tipo_cliente.equals("cliente")){
            Thread actualizacoord = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (!estado.equals("entregado")){
                        getRepartidorLocation();
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            });
            actualizacoord.start();
        }
        if(MainActivity.tipo_cliente.equals("repartidor")){
            recibir.setEnabled(false);
        }

        recibir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                recibirPedido();
                Intent intent = new Intent(mapa.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recibirPedido() {
        String URL = "api/auth/terminarpedido";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id_pedido", id_pedido);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, MainActivity.base_url+URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(LoginActivity.this, response,Toast.LENGTH_LONG).show();
                Log.i("VOLLEY", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String mensaje = jsonObject.getString("mensaje");
                    Log.i("VOLLEY", mensaje);
                    if(mensaje.equals("ok")) {
                        //Toast.makeText(MapsActivity.this,"repartidor actualziado ", Toast.LENGTH_LONG).show();

                    }else{
                        // Toast.makeText(MapsActivity.this,"repartidor no actualziado ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e("VOLLEY", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                //String statusCode = String.valueOf(response.statusCode);
                return super.parseNetworkResponse(response);
            }
        };
        VolleySingleton.getInstanciaVolley(mapa.this).addToRequestQueue(stringRequest);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        marca1 = new MarkerOptions().position(new LatLng(lat_t, long_t)).title(name+"cliente");
        mMap.addMarker(marca1);
        miUbicacion();
    }

    /**
     * "agregarMarcador" sirve para agregar el marcador al mapa, ademas crea un objeto de tipo
     * latlng donde se guardan las coordenadss
     */
    private void agregarMarcador(double lat, double lon) {
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null)
            marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()//se agrega el marcador y se confguran alguna cosas
                .position(coordenadas)
                .title("REPARTIDOR"));
        mMap.animateCamera(miUbicacion);

        /**
         * se llama el metodo para crear la URL con la cual se hace uso de la la API de direcciones
         * de google, y se calcula la ruta
         */
        String url = getUrl(marca1.getPosition(), marcador.getPosition(),"driving");
        new FetchURL(mapa.this).execute(url, "driving");
    }

    /**
     * "actualizarUbicacion" sirve para obtener las coordenadas de la ubicacion y luego las almacena
     * en las variables globales lat lon, comprobando primero que la ubicacion no sea igual a null
     * para evitar que se cierre la aplicacion por ultimo llama al metodo agregar marcador para
     * dibujarlo*/
    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }

    }

    /**
     * Se implementa un Objeto de tipo LocationListener, del cual usaremos el metodo
     * onLocationChanged el cual se ejecuta cada vez que se detecta un cambio en la ubicacion
     * cada que se ejecute llamamos el metodo actualizarUbicacion
     */
    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            if(MainActivity.tipo_cliente.equals("repartidor")){
                actualizarUbicacion(location);
                postUbicacionRepartidor();
            }

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    /**
     * Este metodo pide permisos al usuario para poder usar el GPS ya que es autorizado busca la
     * ultima ubicacion conocida y la guarda en un objeto de tipo Location y actualiza su ubicacion
     * en el mapa, esto lo hace cada 2 segundos. Este valor del iempo se puede ajustar
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void miUbicacion() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0,locListener);
    }

    /**
     * "getUrl" se encarga de armar la url para hacer uso de la API de direcciones de google
     */
    private String getUrl(LatLng origen, LatLng destino, String transporte) {
        //coordenadas origen
        String str_origen = "origin=" + origen.latitude + "," + origen.longitude;
        //coordenadas destino
        String str_destino = "destination=" + destino.latitude + "," + destino.longitude;
        //Modo de Transporte
        String mTransporte = "mode=" + transporte;
        String parametros = str_origen + "&" + str_destino + "&" + mTransporte;
        //String salida = "json";
        //URL Final
        String url = "https://maps.googleapis.com/maps/api/directions/json?" + parametros + "&key=" + "AIzaSyCbVLGH0WLL-J540al5hYCI5vO8wgnXdsI";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (actualPolyline != null)
            actualPolyline.remove();
        actualPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void postUbicacionRepartidor() {

        String URL = "api/auth/actualizarUbicacion";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id_pedido", id_pedido);
            jsonBody.put("latitud_repartidor",lat);
            jsonBody.put("longitud_repartidor",lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, MainActivity.base_url+URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(LoginActivity.this, response,Toast.LENGTH_LONG).show();
                Log.i("VOLLEY", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String mensaje = jsonObject.getString("mensaje");
                    Log.i("VOLLEY", mensaje);
                    if(mensaje.equals("ok")) {
                        //Toast.makeText(MapsActivity.this,"repartidor actualziado ", Toast.LENGTH_LONG).show();
                        // Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                        //startActivity(intent);
                    }else{
                        // Toast.makeText(MapsActivity.this,"repartidor no actualziado ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e("VOLLEY", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                //String statusCode = String.valueOf(response.statusCode);
                return super.parseNetworkResponse(response);
            }
        };
        VolleySingleton.getInstanciaVolley(mapa.this).addToRequestQueue(stringRequest);
    }

    public void getRepartidorLocation(){
        String URL = "api/auth/ubicacion_pedido/"+id_pedido;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,MainActivity.base_url+URL,null,   new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    double latitud = Double.parseDouble(response.getString("latitud_repartidor"));
                    double longitud = Double.parseDouble(response.getString("longitud_repartidor"));
                    estado = response.getString("status_pedido");
                    agregarMarcador(latitud, longitud);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }
        });
        VolleySingleton.getInstanciaVolley(mapa.this).addToRequestQueue(jsonObjectRequest);
    }

}
