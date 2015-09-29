package borell.com.suino.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import borell.com.suino.R;
import borell.com.suino.activity.UIInterface;


/**
 * A placeholder fragment containing a simple view.
 */
public class SuinoFragment extends Fragment {

    Button loginButton;
    Button createCourse;
    private UIInterface mCallback;

    public SuinoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suino, container, false);
    }
    @Override
    public void onAttach(Activity activity){
        mCallback = (UIInterface) activity;

        super.onAttach(activity);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginButton = (Button) getView().findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onShowLogin();
            }
        });

        createCourse = (Button) getView().findViewById((R.id.button_create_course_test));
        createCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCallback.onCreateCourse();
            }
        });
    }
}
