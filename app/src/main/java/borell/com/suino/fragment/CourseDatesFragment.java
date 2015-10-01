package borell.com.suino.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import borell.com.suino.R;
import borell.com.suino.adapter.CourseDatesAdapter;
import borell.com.suino.model.SuinoCourse;


public class CourseDatesFragment extends Fragment {
    private RecyclerView courseDatesListView;
    private CourseDatesAdapter adapter;


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
        View layout = inflater.inflate(R.layout.fragment_course_dates, container, false);
//        courseDatesListView = (RecyclerView) layout.findViewById(R.id.drawerList);

        return layout;
    }

    public void updateList(ArrayList<SuinoCourse.CourseDate> dates, ArrayList<SuinoCourse.CourseDay> days){
        adapter = new CourseDatesAdapter(getActivity(), dates, days);
        courseDatesListView.setAdapter(adapter);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
