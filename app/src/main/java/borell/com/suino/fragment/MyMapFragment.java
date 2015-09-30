package borell.com.suino.fragment;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends SupportMapFragment {

    String locationProvider = LocationManager.NETWORK_PROVIDER;

    LocationManager locationManager;

    Marker locationMarker;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
//            setLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initMap();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }


    @Override
    public void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initMap();

        return view;
    }

    private void initMap() {
        UiSettings settings = getMap().getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setTiltGesturesEnabled(false);
        settings.setZoomControlsEnabled(false);


        LatLng latlng;
        Location location = locationManager.getLastKnownLocation(locationProvider);
        latlng = new LatLng(location.getLatitude(), location.getLongitude());
        setLatLng(latlng);


        locationMarker = getMap().addMarker(new MarkerOptions()
                .position(getMap().getCameraPosition().target));

    }

    public void setLatLng(LatLng latlng){
        CameraPosition position = new CameraPosition(latlng, 14, 0 ,0 );
        UiSettings settings = getMap().getUiSettings();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        getMap().moveCamera(update);
    }
}
