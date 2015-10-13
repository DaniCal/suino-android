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
import java.util.Iterator;

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

        ArrayList<CourseDate> tmpDates = new ArrayList<>();
        for(CourseDate date : dates){
            tmpDates.add(date);
        }

        while(tmpDates.size() > 0){
            courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADate(tmpDates, tmpDates.get(0).getStart()), true));
            tmpDates = removeCourseByDate(tmpDates, tmpDates.get(0).getStart());
        }


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

    private ArrayList<CourseDate> getCoursesOfADate(ArrayList<CourseDate> dates, Calendar date){
        ArrayList<CourseDate> courses = new ArrayList<>();
        for(CourseDate item : dates){
            Calendar itemCal = item.getStart();
            if(itemCal.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    itemCal.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                    itemCal.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
                    ){
                courses.add(item);
            }
        }
        return courses;
    }

    private ArrayList<CourseDate> removeCourseByDate(ArrayList<CourseDate> dates, Calendar date){

        for (Iterator<CourseDate> iterator = dates.iterator(); iterator.hasNext();) {
            CourseDate item = iterator.next();
            Calendar itemCal = item.getStart();
            if(itemCal.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    itemCal.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                    itemCal.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
                    ){
                iterator.remove();
            }
        }

//        for(CourseDate item : dates){
//            Calendar itemCal = item.getStart();
//            if(itemCal.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
//                    itemCal.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
//                    itemCal.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
//                    ){
//                dates.remove(item);
//            }
//        }
        return dates;
    }
}
