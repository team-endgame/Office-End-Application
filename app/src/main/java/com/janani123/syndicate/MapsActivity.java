package com.janani123.syndicate;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Repo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static public double lat;
    static public double lang;
    Button Report;
    Firebase ref;
    Firebase mref;
    double values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Report = (Button) findViewById(R.id.button);

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();


                ref = new Firebase("https://synduser-aa827.firebaseio.com/Output/9790844562/Latitude");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        Double latitude = Double.parseDouble(value);
                        values = latitude;

                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }

                });


                mref = new Firebase("https://synduser-aa827.firebaseio.com/Output/9790844562/Longitude");
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String longvalue = dataSnapshot.getValue(String.class);
                        Double longitude = Double.parseDouble(longvalue);

                        LatLng sydney = new LatLng(values, longitude);
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Complaint regustered by 9790844562"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        //Creating a heatmap with predefined set of points taken as co ordinates.

                        float zoomLevel = 7.0f; //This goes up to 21


                        LatLng one = new LatLng(13.0979, 80.2306 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Bad Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(one, zoomLevel));

                        LatLng two = new LatLng(13.0979, 80.2306 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Moderate Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(two,zoomLevel));

                        LatLng three = new LatLng(13.0850, 80.2101 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Moderate Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(three,zoomLevel));

                        LatLng four = new LatLng(12.9249, 80.1000 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Pleasant Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(four,zoomLevel));

                        LatLng five = new LatLng(12.9801, 80.2184 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Pleasant Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(five,zoomLevel));

                        LatLng six = new LatLng(12.9830, 80.2594 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Moderate Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(six,zoomLevel));

                        LatLng seven = new LatLng(13.0213, 80.2231 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Happy Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seven,zoomLevel));

                        LatLng eight = new LatLng(13.0500, 80.2121 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Happy Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eight,zoomLevel));

                        LatLng nine = new LatLng(13.0694, 80.1948 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Bad Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nine,zoomLevel));

                        LatLng ten = new LatLng(13.0067, 80.2206 );
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Moderate Experience").icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ten,zoomLevel));










                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


    }

        }) ;
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

    }
}
