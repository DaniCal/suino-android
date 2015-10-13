package borell.com.suino.model;

import java.util.ArrayList;
import java.util.Calendar;

public class CourseDate{
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    private ArrayList<String> participants;

    public CourseDate(long startStamp, long endStamp){
        start.setTimeInMillis(startStamp);
        end.setTimeInMillis(endStamp);
        participants = new ArrayList<String>();
    }
    public Calendar getStart(){
        return start;
    }

    public Calendar getEnd(){
        return end;
    }
}