package com.example.rutujabanswal.pocketgarage.Model;


import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.rutujabanswal.pocketgarage.location.GetNearbyPlacesData;
import com.example.rutujabanswal.pocketgarage.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.rutujabanswal.pocketgarage.R.id.map1;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindNearbyGarage extends Fragment implements OnMapReadyCallback {


    private int PROXIMITY_RADIUS = 10000;
    public FindNearbyGarage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_nearby_garage, container, false);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(map1);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


 GoogleMap mMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        final double latitude = 19.8762;
        final double longitude = 75.3433;
        final String FuelStation = "gas_station";
        final String CarRepaire = "car_repair";
        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
          mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude,longitude)).zoom(14).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        final Button btnCarRepaire = (Button)getView().findViewById(R.id.btnCarRepair);
        final Button btnFuelStation = (Button)getView().findViewById(R.id.btnFuelStation);
        mMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                Log.d("onClick","MapClick");
                mMap.clear();
                String url = getUrl(latitude, longitude,FuelStation);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(getContext(),"Nearby Gas Stations", Toast.LENGTH_LONG).show();


            }
        });

        btnFuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(latitude, longitude, FuelStation);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(getContext(),"Nearby Fuel Stations", Toast.LENGTH_SHORT).show();
            }
        });

        btnCarRepaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(latitude, longitude, CarRepaire);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(getContext(),"Nearby Car Repair Shops", Toast.LENGTH_SHORT).show();

            }
        });



    }


    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }



}
