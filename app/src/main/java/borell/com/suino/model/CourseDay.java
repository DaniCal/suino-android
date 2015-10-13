package borell.com.suino.model;

import java.util.Calendar;

public class CourseDay{
    private int dayOfTheWeek;
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();

    public CourseDay(int dayOfTheWeek, int startStamp, int endStamp){
        this.dayOfTheWeek = dayOfTheWeek;
        start.setTimeInMillis(startStamp*1000L);
        end.setTimeInMillis(endStamp*1000L);
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