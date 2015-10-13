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
        courseDatesListView = (LinearLayout) getView().findViewById(R.id.rv_course_date_list);



    }

    public void updateList(ArrayList<CourseDate> dates, ArrayList<CourseDay> days){
        if(courseDatesListView.getChildCount() > 0) {
            courseDatesListView.removeAllViews();
        }
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.MONDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.TUESDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.WEDNESDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.THURSDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.FRIDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.SATURDAY)));
        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.SUNDAY)));
    }

    private ArrayList<CourseDay> getCoursesOfADay(ArrayList<CourseDay> days, int dayOfTheWeek){
        ArrayList<CourseDay> courses = new ArrayList<>();
        for(CourseDay day : days){
            if(day.getDayOfTheWeek() == dayOfTheWeek){
                courses.add(day);
            }
        }
        return courses;
    }
}
