package borell.com.suino.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import borell.com.suino.R;
import borell.com.suino.activity.UIInterface;


public class CreateCourseFragment extends Fragment {

    private UIInterface mCallback;
    private Activity activity;

    public CreateCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_course, container, false);
    }
    @Override
    public void onAttach(final Activity activity){
//        mCallback = (UIInterface) activity;

        super.onAttach(activity);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
