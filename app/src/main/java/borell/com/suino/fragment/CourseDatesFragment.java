package borell.com.suino.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;

import borell.com.suino.DatesLinearLayout;
import borell.com.suino.R;
import borell.com.suino.adapter.CourseDatesAdapter;
import borell.com.suino.model.CourseDate;
import borell.com.suino.model.CourseDay;


public class CourseDatesFragment extends Fragment {
    private LinearLayout courseDatesListView;
    private ArrayList<CourseDate> dates;
    private ArrayList<CourseDay>days;


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

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        days = new ArrayList<CourseDay>();

        days.add(new CourseDay(Calendar.MONDAY, 1443875400, 1443880800));

        courseDatesListView = (LinearLayout) getView().findViewById(R.id.rv_course_date_list);
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), days));

    }

    public void updateList(ArrayList<CourseDate> dates, ArrayList<CourseDay> days){


    }
}
