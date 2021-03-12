package com.example.navbartest.ui.map;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.example.navbartest.R;

import android.location.Location;

import com.example.navbartest.SharedViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.ContentValues.TAG;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class MapFragment extends Fragment {
    private GoogleMap mMap;
    private Spinner mapFragmentSpinner;
    private Button buttonFind;
    Location location;
    LatLng currentLoc;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;
    public ArrayList<String> shoppingList = new ArrayList<>();
    double currentLat = 0, currentLong = 0;
    private SharedViewModel shared;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlacesClient placesClient;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //initialize the view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //initialize the map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
        //asynce map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                currentLat = 41.6050335;
                currentLong = -88.0827548;
                /* Tried adding places to map
                Places.initialize(getActivity().getApplicationContext(), getResources().getString(R.string.google_maps_key));
                placesClient = Places.createClient(getActivity());
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME);
                FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeFields).build();
                placesClient.findCurrentPlace(request); */

                currentLoc = new LatLng(currentLat, currentLong);
                mMap.addMarker(new MarkerOptions().position(currentLoc).title("Lewis University"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc));
                //when map is loaded
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //remove all markers first
                        mMap.clear();
                        //when clicked on map
                        currentLat = location.getLatitude();
                        currentLong = location.getLongitude();
                        //initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //set position of marker
                        markerOptions.position(latLng);
                        //set Title of marker
                        markerOptions.title(currentLat + ":" + currentLong);
                        //animating zoom to the marker
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        //add a marker on the map
                        mMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        shared = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        shared.getListData().observe(getViewLifecycleOwner(), ArrayList -> {
            shoppingList = shared.getList();
        });
        if (bundle != null) {
            shoppingList = bundle.getStringArrayList("list");

            //set adapter for spinner later
            mapFragmentSpinner = (Spinner) view.findViewById(R.id.sp_item_finder);
            buttonFind = view.findViewById(R.id.bt_find);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, shoppingList);
            adapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
            mapFragmentSpinner.setAdapter(adapter);
        }

        if (buttonFind != null) {
            buttonFind.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int i = mapFragmentSpinner.getSelectedItemPosition();

                    String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?=" + "?location=" + currentLat + "," + currentLong +
                            "&radius=500" + "&keyword=" + shoppingList.get(i)
                            + "&sensor=true" + "&key=" + getResources().getString(R.string.google_maps_key);
                    //Places API Key AIzaSyDMLXAoju3wBWny_GLYHWupHFBcfjBagXE already set be the value of google_map_key
                }
            });//end btfind click listener
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
}