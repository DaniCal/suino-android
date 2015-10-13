package borell.com.suino.model;

import java.util.ArrayList;
import java.util.Calendar;

public class CourseDate{
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    private ArrayList<String> participants;

    public CourseDate(int startStamp, int endStamp){
        start.setTimeInMillis(startStamp*1000);
        end.setTimeInMillis(endStamp*1000);
        participants = new ArrayList<String>();
    }
}