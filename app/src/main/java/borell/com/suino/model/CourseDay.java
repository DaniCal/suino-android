package borell.com.suino.model;

import java.util.Calendar;

public class CourseDay{
    private int dayOfTheWeek;
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();

    public CourseDay(int dayOfTheWeek, long startStamp, long endStamp){
        this.dayOfTheWeek = dayOfTheWeek;
        start.setTimeInMillis(startStamp);
        end.setTimeInMillis(endStamp);
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }
}