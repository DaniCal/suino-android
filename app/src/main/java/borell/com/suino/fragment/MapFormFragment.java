package borell.com.suino.fragment;

import android.app.Activity;
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


public class MapFormFragment extends SupportMapFragment {
    CameraUpdate update;

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();

    }
    private void initMap() {
        UiSettings settings = getMap().getUiSettings();
        settings.setMyLocationButtonEnabled(false);
        settings.setTiltGesturesEnabled(false);
        settings.setZoomControlsEnabled(false);
        settings.setRotateGesturesEnabled(false);
        settings.setScrollGesturesEnabled(false);
        settings.setTiltGesturesEnabled(false);
        settings.setZoomGesturesEnabled(false);
        settings.setAllGesturesEnabled(false);
        if(update != null){
            getMap().moveCamera(update);

        }

    }

    @Override
    public void onAttach(final Activity activity){
        super.onAttach(activity);

    }


    public void updateCamera(LatLng latLng) {
        if(latLng != null){
            CameraPosition position = new CameraPosition(latLng, 14, 0 ,0 );
            this.update = CameraUpdateFactory.newCameraPosition(position);
        }
    }
}
