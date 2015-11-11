package borell.com.suino.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class SuinoEvent {
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    private ArrayList<String> participants;
    private String id;
    private SuinoCourse course;

    public SuinoEvent(){

    }

    public SuinoEvent(long startStamp, long endStamp){
        this.start.setTimeInMillis(startStamp);
        this.end.setTimeInMillis(endStamp);
        this.participants = new ArrayList<>();
    }

    public SuinoEvent(int startStamp, int endStamp){
        this.start.setTimeInMillis(startStamp * 1000L);
        this.end.setTimeInMillis(endStamp * 1000L);
        this.participants = new ArrayList<>();
    }

    public SuinoEvent(int startStamp, int endStamp, String id){
        this.start.setTimeInMillis(startStamp * 1000L);
        this.end.setTimeInMillis(endStamp * 1000L);
        this.participants = new ArrayList<>();
        this.id = id;
    }

    public SuinoEvent(int startStamp, int endStamp, ArrayList<String> participants, String id){
        this(startStamp * 1000L, endStamp * 1000L);
        this.participants = participants;
        this.id = id;
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

    public void addParticipant(String participantId){
        participants.add(participantId);
    }

    public boolean removeParticipant(String participantId){
        return participants.remove(participantId);
    }

    public String createPostEventJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoEvent.class, new SuinoEventSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoEvent.class).toString();
    }

    public SuinoEvent deserializeFromSearchResult(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoEvent.class, new SuinoEventSerializer());

        return gsonBuilder.create().fromJson(json, SuinoEvent.class);
    }

    private class SuinoEventSerializer implements JsonSerializer<SuinoEvent>, JsonDeserializer<SuinoEvent> {
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


        @Override
        public SuinoEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            final JsonObject jsonObject = json.getAsJsonObject();

            final String id = jsonObject.get("_id").getAsString();
            final int start = jsonObject.get("start").getAsInt();
            final int end = jsonObject.get("end").getAsInt();
            return new SuinoEvent(start, end, id);
        }

    }

}