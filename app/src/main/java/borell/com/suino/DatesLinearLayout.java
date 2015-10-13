package borell.com.suino;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import borell.com.suino.model.CourseDay;


public class DatesLinearLayout extends LinearLayout {

    LayoutInflater inflater;


    public DatesLinearLayout(Context context, ArrayList<CourseDay> data) {
        super(context);
        inflater = LayoutInflater.from(context);
        if(data != null && data.size() > 0){
            this.addView(inflateLayout(data));
        }
    }

    public CardView inflateLayout(ArrayList<CourseDay> data){

        CardView itemView = (CardView) inflater.inflate(R.layout.row_course_day_dates, this, false);
        TextView tv_day = (TextView) itemView.findViewById(R.id.tv_day_course_list);
        TextView tv_freq = (TextView) itemView.findViewById(R.id.tv_freq_course_list);
        tv_day.setText(getDayOfTheWeekString(data.get(0).getDayOfTheWeek()));
        tv_freq.setText("Weekly");

        LinearLayout rv = (LinearLayout) itemView.findViewById(R.id.rv_times);
        rv.setGravity(Gravity.END);

        for(CourseDay day : data){
            rv.addView(inflateTimesLayout(rv, day.getStart(), day.getEnd()));
        }
        return  itemView;
    }

    public LinearLayout inflateTimesLayout(LinearLayout ll, Calendar start, Calendar end){
        LinearLayout itemView = (LinearLayout) inflater.inflate(R.layout.row_course_time, ll, false);
        TextView tv_start = (TextView) itemView.findViewById(R.id.tv__time_start);
        tv_start.setText(getTime(start));
        TextView tv_end = (TextView) itemView.findViewById(R.id.tv__time_end);
        tv_end.setText(getTime(end));
        return itemView;
    }

    private String getDayOfTheWeekString(int day){
        switch(day){
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
            default:
                return "Error";
        }
    }

    private String getTime(Calendar time){

        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        String hourText = Integer.toString(hour);
        String minuteText = Integer.toString(minute);

        if(hour < 10){
            hourText = "0" + hour;
        }
        if(minute < 10) {
            minuteText = "0" + minute;
        }

        return " " + hourText + ":" + minuteText + " ";
    }

    private String getDate(int timestamp){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp*1000);
        return date.get(Calendar.DAY_OF_MONTH) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR);
    }



}
