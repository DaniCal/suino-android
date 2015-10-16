package borell.com.suino.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import borell.com.suino.DatesLinearLayout;
import borell.com.suino.R;
import borell.com.suino.model.CourseDate;
import borell.com.suino.model.CourseDay;


public class CourseDatesFragment extends Fragment {
    private LinearLayout courseDatesListView;
    private ArrayList<CourseDate> dates;
    private ArrayList<CourseDay>days;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_dates, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        courseDatesListView = (LinearLayout) getView().findViewById(R.id.rv_course_date_list);
        updateList();
    }

    private void updateList(){
        this.updateList(this.dates, this.days);
    }

    public void updateList(ArrayList<CourseDate> dates, ArrayList<CourseDay> days){
        this.dates = dates;
        this.days = days;

        if(courseDatesListView == null){
            return;
        }

        courseDatesListView.removeAllViews();

        drawDaysList(days);
        drawDatesList(dates);
    }
    private void drawDatesList(ArrayList<CourseDate> dates){
        ArrayList<CourseDate> tmpDates = new ArrayList<>();
        for(CourseDate date : dates){
            tmpDates.add(date);
        }

        while(tmpDates.size() > 0){
            courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADate(tmpDates, tmpDates.get(0).getStart()), true));
            tmpDates = removeCourseByDateHelper(tmpDates, tmpDates.get(0).getStart());
        }
    }
    private void drawDaysList(ArrayList<CourseDay> days){
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

    private ArrayList<CourseDate> removeCourseByDateHelper(ArrayList<CourseDate> dates, Calendar date){

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
        return dates;
    }
}
