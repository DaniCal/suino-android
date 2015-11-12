package borell.com.suino.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import borell.com.suino.Http.HttpManager;
import borell.com.suino.R;
import borell.com.suino.activity.SearchCourseInterface;
import borell.com.suino.activity.UIInterface;

public class SearchButtonFragment  extends Fragment {

    Activity activity;
    SearchCourseInterface mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        activity = getActivity();

        return inflater.inflate(R.layout.fragment_search_filter_button, container, false);
    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (SearchCourseInterface) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initClickListener();
    }

    public void initClickListener(){
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showFilter();
            }
        });
    }

}
