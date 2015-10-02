package borell.com.suino.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import borell.com.suino.DatesLinearLayout;
import borell.com.suino.R;
import borell.com.suino.adapter.CourseDatesAdapter;
import borell.com.suino.model.CourseDate;
import borell.com.suino.model.CourseDay;
import borell.com.suino.model.SuinoCourse;


public class CourseDatesFragment extends Fragment {
    private LinearLayout courseDatesListView;
    private CourseDatesAdapter adapter;
    private ArrayList<CourseDate> dates;
    private ArrayList<CourseDay>days;
    private RecyclerView.LayoutManager mLayoutManager;


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

        days.add(new CourseDay(1, 10, 12));
        days.add(new CourseDay(1, 14, 16));

        dates = new ArrayList<CourseDate>();

        dates.add(new CourseDate(1,2,3));
        dates.add(new CourseDate(1, 2, 3));

        courseDatesListView = (LinearLayout) getView().findViewById(R.id.rv_course_date_list);
        //LinearLayout ll = inflateLayout();
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), days));

        days = new ArrayList<CourseDay>();

        days.add(new CourseDay(2, 18, 20));

        courseDatesListView.addView(new DatesLinearLayout(getActivity(), days));

        days = new ArrayList<CourseDay>();


        days.add(new CourseDay(5,18,20));

        courseDatesListView.addView(new DatesLinearLayout(getActivity(), days));

//        courseDatesListView.addView(new DatesLinearLayout(getActivity()));



//        int viewHeight = 150 * days.size();
//        courseDatesListView.getLayoutParams().height = viewHeight;



    }

    private void setTestData(){
//        dates.add(new CourseDate(1,2,3));
//        dates.add(new CourseDate(1,2,3));



    }

    public void updateList(ArrayList<CourseDate> dates, ArrayList<CourseDay> days){


    }
}
