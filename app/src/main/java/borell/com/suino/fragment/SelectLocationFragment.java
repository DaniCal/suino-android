package borell.com.suino.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;

public class SelectLocationFragment extends Fragment {
    private CreateCourseInterface mCallback;

    MyMapFragment mapFragment;
    CardView confirmLocation;
    ImageView iv_location;
    FrameLayout mapContainer;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        confirmLocation = (CardView) getView().findViewById(R.id.cv_confirmLocation);
        confirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapFragment != null) {
                    mCallback.onConfirmLocation(mapFragment.getSelectLocation());
                }
            }
        });

        initLocationImage();



    }

    private void initLocationImage(){
        mapContainer = (FrameLayout) getView().findViewById(R.id.container_map);
        iv_location = (ImageView) getView().findViewById(R.id.iv_location);
        ViewTreeObserver vto = iv_location.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                iv_location.getViewTreeObserver().removeOnPreDrawListener(this);

                iv_location.setY(iv_location.getY() - iv_location.getHeight()/2);
                return true;
            }
        });

    }

    @Override
    public void onAttach(final Activity activity){
        mCallback = (CreateCourseInterface) activity;
        super.onAttach(activity);
        displayMap();

    }

    private void displayMap() {

        mapFragment = new MyMapFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_map, mapFragment);
        fragmentTransaction.commit();

    }




}
