package borell.com.suino.model;

import java.util.ArrayList;

public class CourseDate{
    private int date;
    private int start;
    private int end;
    private ArrayList<String> participants;

    public CourseDate(int date, int start, int end){
        this.date = date;
        this.start = start;
        this.end = end;
        participants = new ArrayList<String>();
    }
}