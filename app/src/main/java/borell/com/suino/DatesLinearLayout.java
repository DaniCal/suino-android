package borell.com.suino;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import borell.com.suino.model.CourseDay;

/**
 * Created by daniellohse on 10/2/15.
 */
public class DatesLinearLayout extends LinearLayout {

    LayoutInflater inflater;



//    public DatesLinearLayout(Context context) {
//        super(context);
//
//    }

    public DatesLinearLayout(Context context, ArrayList<CourseDay> data) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.addView(inflateLayout(data));

    }

    public RelativeLayout inflateLayout(ArrayList<CourseDay> data){

        RelativeLayout itemView = (RelativeLayout) inflater.inflate(R.layout.row_course_day_dates, this, false);
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

    public LinearLayout inflateTimesLayout(LinearLayout ll, int start, int end){
        LinearLayout itemView = (LinearLayout) inflater.inflate(R.layout.row_course_time, ll, false);


        TextView tv_start = (TextView) itemView.findViewById(R.id.tv__time_start);
        tv_start.setText(" " + start + ":00 ");
        TextView tv_end = (TextView) itemView.findViewById(R.id.tv__time_end);
        tv_end.setText(" " +  end + ":00 ");
        return itemView;
    }

    private String getDayOfTheWeekString(int day){
        switch(day){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return "Error";
        }
    }



}
