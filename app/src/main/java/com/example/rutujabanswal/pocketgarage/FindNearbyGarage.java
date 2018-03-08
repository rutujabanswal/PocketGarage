package com.example.rutujabanswal.pocketgarage;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.rutujabanswal.pocketgarage.R.id.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindNearbyGarage extends Fragment implements OnMapReadyCallback {


    public FindNearbyGarage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_find_nearby_garage, container, false);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(map1);
        mapFragment.getMapAsync(this);

    return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map =googleMap;
        LatLng aurangabad = new LatLng(19.8762, 75.3433);
        map.addMarker(new MarkerOptions().position(aurangabad)
                .title("Marker in Aurangabad"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(aurangabad));
    }
}
