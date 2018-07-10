package com.myapps.claremacharia44.fadhili;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements
        OnMapReadyCallback,LocationListener {
    MapView mapView;
    private GoogleMap mMap;
    Double x, y;
    String loc_name;
    JsonArrayRequest request;
    private RequestQueue requestQueue;
    String JSON_URL = "https://fadhili.herokuapp.com/collectioncenter";
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    private static final int LOCATION_REQUEST_CODE = 101;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;



    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment =
                (SupportMapFragment)
                        getChildFragmentManager().findFragmentById(R.id.fadhiliMap);
        mapFragment.getMapAsync(this);

        markerRequest();

        return view;
    }


    private void markerRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        x = jsonObject.getDouble("collectionCenterLatitude");
                        y = jsonObject.getDouble("collectionCenterLongitude");
                        loc_name = jsonObject.getString("collectionCenterName");


                        Bitmap img = BitmapFactory.decodeResource(getResources(),
                                R.mipmap.maps_icon);
                        BitmapDescriptor bitmapDescriptor =
                                BitmapDescriptorFactory.fromBitmap(img);

                        LatLng collectionLoc = new LatLng(x, y);
                        mMap.addMarker(new MarkerOptions().
                                position(collectionLoc).
                                title(loc_name)
                                .icon(bitmapDescriptor));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(collectionLoc,15));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMaxZoomPreference(15.0f);
        mMap.setMaxZoomPreference(15.0f);


    }private void checkLocationandAddToMap() {
        //Checking if the user has granted the permission
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Requesting the Location permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = locationManager.getBestProvider(criteria, true);


        //Fetching the last known location using the Fus
        Location location = locationManager.getLastKnownLocation(bestProvider);


        if (location != null) {

            onLocationChanged(location);


        } else {
            //This is what you need:
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, (LocationListener) getActivity());
        }


    }



    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(),
               android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mMap.setMyLocationEnabled(true);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(latLng).title("You are Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(5000)// In meters
                .strokeColor(Color.BLUE);



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
