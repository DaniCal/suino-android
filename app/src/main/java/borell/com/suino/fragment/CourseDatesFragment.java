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

import borell.com.suino.view.createCourseView.DatesLinearLayout;
import borell.com.suino.R;
import borell.com.suino.model.SuinoEvent;


public class CourseDatesFragment extends Fragment {
    private LinearLayout courseDatesListView;
    private ArrayList<SuinoEvent> dates;


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
        this.updateList(this.dates);
    }

    public void updateList(ArrayList<SuinoEvent> dates){
        this.dates = dates;

        if(courseDatesListView == null){
            return;
        }

        courseDatesListView.removeAllViews();

        drawDatesList(dates);
    }
    private void drawDatesList(ArrayList<SuinoEvent> dates){
        ArrayList<SuinoEvent> tmpDates = new ArrayList<>();
        for(SuinoEvent date : dates){
            tmpDates.add(date);
        }

        while(tmpDates.size() > 0){
            courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADate(tmpDates, tmpDates.get(0).getStart()), true));
            tmpDates = removeCourseByDateHelper(tmpDates, tmpDates.get(0).getStart());
        }
    }
//    private void drawDaysList(ArrayList<CourseDay> days){
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.MONDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.TUESDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.WEDNESDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.THURSDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.FRIDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.SATURDAY)));
//        courseDatesListView.addView(new DatesLinearLayout(getActivity(), getCoursesOfADay(days, Calendar.SUNDAY)));
//    }
//
//    private ArrayList<CourseDay> getCoursesOfADay(ArrayList<CourseDay> days, int dayOfTheWeek){
//        ArrayList<CourseDay> courses = new ArrayList<>();
//        for(CourseDay day : days){
//            if(day.getDayOfTheWeek() == dayOfTheWeek){
//                courses.add(day);
//            }
//        }
//        return courses;
//    }

    private ArrayList<SuinoEvent> getCoursesOfADate(ArrayList<SuinoEvent> dates, Calendar date){
        ArrayList<SuinoEvent> courses = new ArrayList<>();
        for(SuinoEvent item : dates){
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

    private ArrayList<SuinoEvent> removeCourseByDateHelper(ArrayList<SuinoEvent> dates, Calendar date){

        for (Iterator<SuinoEvent> iterator = dates.iterator(); iterator.hasNext();) {
            SuinoEvent item = iterator.next();
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
