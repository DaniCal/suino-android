package borell.com.suino.fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;


public class MyMapFragment extends SupportMapFragment {

    String locationProvider = LocationManager.NETWORK_PROVIDER;

    LocationManager locationManager;

    Marker locationMarker;


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }


    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(locationListener);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initMap();

        return view;
    }

    @Override
    public void onAttach(final Activity activity){
        super.onAttach(activity);

    }



    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };





    private void initMap() {
        UiSettings settings = getMap().getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setTiltGesturesEnabled(false);
        settings.setZoomControlsEnabled(false);
        getMap().setMyLocationEnabled(true);
        

        LatLng latlng;
        Location location = locationManager.getLastKnownLocation(locationProvider);
        latlng = new LatLng(location.getLatitude(), location.getLongitude());
        setLatLng(latlng);

    }

    public LatLng getSelectLocation(){
        return getMap().getCameraPosition().target;
    }

    private void setLatLng(LatLng latlng){
        CameraPosition position = new CameraPosition(latlng, 16, 0 ,0 );
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        getMap().moveCamera(update);
    }
}
