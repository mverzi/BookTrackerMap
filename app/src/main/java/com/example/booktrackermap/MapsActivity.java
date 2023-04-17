package com.example.booktrackermap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.booktrackermap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    // Array of bookstore locations
    private LatLng[] bookstoreLocations = {
            new LatLng(33.75977, -84.35054), // A Cappella Books
            new LatLng(33.76830, -84.29233), // Charis Books & More
            new LatLng(33.86693, -84.30974), // Atlanta Vintage Books
            new LatLng(33.77684, -84.30250), // Posman Books
            new LatLng(33.74623, -84.34773), // Bookish Atlanta
            new LatLng(33.75872, -84.38172), // For Keeps Bookstore
            new LatLng(33.76870, -84.34133), // BiblioTech
            new LatLng(33.81942, -84.31438), // Tall Tales Book Shop Inc
            new LatLng(33.74480, -84.41365), // 44th and 3rd Bookseller
            new LatLng(33.78456, -84.35352), // Virginia Highland Books
            new LatLng(33.79112, -84.32743), // Emory Bookstore
            new LatLng(33.84082, -84.38494), // Barnes and Noble
            new LatLng(33.79416, -84.30563), // Eagle Eye Book Shop
            new LatLng(33.78075, -84.29430), // Little Shop of Stories

            // Add more bookstore locations here
    };

    private String[] bookstoreNames = {
            "A Cappella Books",
            "Charis Books & More",
            "Atlanta Vintage Books",
            "Posman Books",
            "Bookish Atlanta",
            "For Keeps Bookstore",
            "BiblioTech",
            "Tall Tales Book Shop Inc",
            "44th and 3rd Bookseller",
            "Virginia Highland Books",
            "Emory Bookstore",
            "Barnes and Noble",
            "Eagle Eye Book Shop",
            "Little Shop of Stories"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; //my Google map instance

        // Add a marker and title for each bookstore location
        for (int i = 0; i < bookstoreLocations.length; i++) {
            LatLng bookstoreLatLng = bookstoreLocations[i];
            String bookstoreTitle = bookstoreNames[i];
            mMap.addMarker(new MarkerOptions().position(bookstoreLatLng).title(bookstoreTitle));
        }


        // Move the camera to show all the markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng location : bookstoreLocations) {
            builder.include(location);
        }

        LatLngBounds australiaBounds = new LatLngBounds(
                new LatLng(33.65340, -84.44805), // SW bounds
                new LatLng(33.89844, -84.27840)  // NE bounds
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(australiaBounds.getCenter(), 10));
    }

    public void onMapLoaded() {
        // Create a LatLngBounds object that encompasses the markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(33.8479, -84.3628)); // Barnes & Noble
        builder.include(new LatLng(33.8674, -84.3094)); // Books-A-Million
        LatLngBounds bounds = builder.build();

        // Set the camera position to show the markers on the map
        int padding = 200; // in pixels
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }
}