package borell.com.suino.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import borell.com.suino.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class SuinoFragment extends Fragment {

    public SuinoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suino, container, false);
    }
}