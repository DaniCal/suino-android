package borell.com.suino.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class SuinoEvent {
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    private ArrayList<String> participants;
    private String _id;
    private SuinoCourse course;

    public SuinoEvent(long startStamp, long endStamp){
        this.start.setTimeInMillis(startStamp);
        this.end.setTimeInMillis(endStamp);
    }

    public SuinoEvent(Calendar start, Calendar end){
        this.start = start;
        this.end = end;
    }

    public SuinoEvent(int startStamp, int endStamp, ArrayList<String> participants, String id){
        this(startStamp * 1000L, endStamp * 1000L);
        this.participants = participants;
        this._id = id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public Calendar getStart(){
        return start;
    }

    public Calendar getEnd(){
        return end;
    }

    public SuinoCourse getCourse(){
        return course;
    }

    public String getCourseId(){
        return course.getId();
    }

    public void setCourse(SuinoCourse course){
        this.course = course;
    }

    protected long getStartAsUnixTimestamp(){
        return (start.getTimeInMillis()/1000L);
    }

    protected long getEndAsUnixTimestamp(){
        return (end.getTimeInMillis()/1000L);
    }

    public int numberOfParticipants(){
        return participants.size();
    }

    public JsonElement createPostEventJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoEvent.class, new SuinoCourseDateSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoEvent.class);
    }

    private class SuinoCourseDateSerializer implements JsonSerializer<SuinoEvent> {
        private static final String VALUE_COURSE_ID = "_course";
        private static final String VALUE_START = "start";
        private static final String VALUE_END = "end";

        @Override
        public JsonElement serialize(SuinoEvent src, Type typeOfSrc, JsonSerializationContext context) {


            final JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty(VALUE_COURSE_ID, getCourseId());
            jsonObject.addProperty(VALUE_START, getStartAsUnixTimestamp());
            jsonObject.addProperty(VALUE_END, getEndAsUnixTimestamp());

            return jsonObject;
        }
    }

}