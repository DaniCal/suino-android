package borell.com.suino.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import borell.com.suino.R;
import borell.com.suino.fragment.CreateCourseFragment;
import borell.com.suino.fragment.SuinoFragment;


public class CreateCourseActivity extends AppCompatActivity implements CreateCourseInterface {
    private Toolbar mToolbar;
    private Button createCourse;
    private Activity activity;
    String locationProvider = LocationManager.NETWORK_PROVIDER;

    LocationManager locationManager;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            makeUseOfNewLocation(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };

    private void makeUseOfNewLocation(Location location) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_course);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Course");
        displayView(0);

        activity = this;
        createCourse = (Button) findViewById(R.id.button_create_course);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "TODO: Create Course", Toast.LENGTH_SHORT).show();
            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);



    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);}

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        //AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        //AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void showMap(){
        GoogleMapOptions options = new GoogleMapOptions();
        options.tiltGesturesEnabled(false);
        options.zoomControlsEnabled(false);
        LatLng latlng;
        Location location = locationManager.getLastKnownLocation(locationProvider);
        latlng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition position = new CameraPosition(latlng, 14, 0 ,0 );

        options.camera(position);






        SupportMapFragment mMapFragment = SupportMapFragment.newInstance(options);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_create_course, mMapFragment);
        fragmentTransaction.commit();

//        Marker marker = mMapFragment.getMap().addMarker(new MarkerOptions()
//                .position(latlng));

        
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CreateCourseFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_create_course, fragment);
            fragmentTransaction.commit();
        }
    }
}
