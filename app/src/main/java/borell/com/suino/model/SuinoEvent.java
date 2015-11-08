package borell.com.suino.model;

import java.util.ArrayList;

public class SuinoEvent {
    private SuinoCourse course;
    private ArrayList<SuinoUser> participants;
    private long start;
    private long end;
    private String id;

    public SuinoEvent(int start, int end){
        this.start = start;
        this.end = end;
    }

    public SuinoEvent(int start, int end, SuinoCourse course, ArrayList<SuinoUser> participants, String id){
        this(start, end);
        this.course = course;
        this. participants = participants;
        this.id = id;
    }

    public SuinoCourse getCourse() {
        return course;
    }

    public void setCourse(SuinoCourse course) {
        this.course = course;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int numParticipants(){
        return participants.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
