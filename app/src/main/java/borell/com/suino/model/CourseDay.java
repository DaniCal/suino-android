package borell.com.suino.model;

public class CourseDay{
    private int dayOfTheWeek;
    private int start;
    private int end;

    public CourseDay(int dayOfTheWeek, int start, int end){
        this.dayOfTheWeek = dayOfTheWeek;
        this.start = start;
        this.end = end;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}