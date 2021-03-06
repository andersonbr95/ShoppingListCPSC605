package com.example.navbartest.ui.map;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.navbartest.R;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;


public class MapFragment extends Fragment {
    private GoogleMap mMap;
    private Spinner mapFragmentSpinner;
    private Button buttonFind;
    Location location;
    public ArrayList<String> shoppingList = new ArrayList<>();
    double currentLat = 0, currentLong = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //initialize the view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            shoppingList = bundle.getStringArrayList("list");

            //set adapter for spinner later
            mapFragmentSpinner = (Spinner) view.findViewById(R.id.sp_item_finder);
            buttonFind = view.findViewById(R.id.bt_find);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, shoppingList);
            adapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
            mapFragmentSpinner.setAdapter(adapter);
        }


        ///initialize the map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
        if (buttonFind != null) {
            buttonFind.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int i = mapFragmentSpinner.getSelectedItemPosition();

                    String url = "https://maps.googleapis.com/maps/api/" + "?location=" + currentLat + "," + currentLong +
                            "&radius=5000" + "&type=" + shoppingList.get(i)
                            + "&sensor=true" + "&key=" + getResources().getString(R.string.google_maps_key);
                    //Places API Key AIzaSyDMLXAoju3wBWny_GLYHWupHFBcfjBagXE already set be the value of google_map_key
                }
            });//end btfind click listener
        }
        //asynce map

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                //when map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //when clicked on map
                        currentLat = location.getLatitude();
                        currentLong = location.getLongitude();
                        //initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //set position of marker
                        markerOptions.position(latLng);
                        //set Title of marker
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        //remove all marker
                        googleMap.clear();
                        //animating zoom to the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        //add a marker on the mnap
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

}