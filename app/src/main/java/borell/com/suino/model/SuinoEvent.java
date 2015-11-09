package borell.com.suino.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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

    public SuinoEvent(long startStamp, long endStamp){
        start.setTimeInMillis(startStamp);
        end.setTimeInMillis(endStamp);
        participants = new ArrayList<String>();
    }

    private SuinoCourse course;
    private String id;




    public SuinoEvent(int start, int end, SuinoCourse course, ArrayList<String> participants, String id){
        this(start, end);
        this.course = course;
        this. participants = participants;
        this.id = id;
    }

    public boolean isValid(){
        if(start != null && end != null){
            return true;
        }
        return false;
    }

    public SuinoCourse getCourse() {
        return course;
    }

    public void setCourse(SuinoCourse course) {
        this.course = course;
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


    public Calendar getStart(){
        return start;
    }

    public Calendar getEnd(){
        return end;
    }


    public JsonElement createCourseDateJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoEvent.class, new SuinoCourseDateSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoEvent.class);
    }

    private class SuinoCourseDateSerializer implements JsonSerializer<SuinoEvent> {
        private static final String VALUE_START = "start";
        private static final String VALUE_END = "end";
        private static final String VALUE_PARTICIPANTS = "participants";


        @Override
        public JsonElement serialize(SuinoEvent src, Type typeOfSrc, JsonSerializationContext context) {

            final JsonArray participants = new JsonArray();

            final JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty(VALUE_START, getStart().getTimeInMillis());
            jsonObject.addProperty(VALUE_END, getEnd().getTimeInMillis());
            jsonObject.add(VALUE_PARTICIPANTS, participants);

            return jsonObject;
        }
    }

}