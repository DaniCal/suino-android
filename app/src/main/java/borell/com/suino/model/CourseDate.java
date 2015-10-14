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


    public JsonElement createCourseDateJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CourseDate.class, new SuinoCourseDateSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, CourseDate.class);
    }

    private class SuinoCourseDateSerializer implements JsonSerializer<CourseDate> {
        private static final String VALUE_START = "start";
        private static final String VALUE_END = "end";
        private static final String VALUE_PARTICIPANTS = "participants";


        @Override
        public JsonElement serialize(CourseDate src, Type typeOfSrc, JsonSerializationContext context) {

            final JsonArray participants = new JsonArray();

            final JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty(VALUE_START, getStart().getTimeInMillis());
            jsonObject.addProperty(VALUE_END, getEnd().getTimeInMillis());
            jsonObject.add(VALUE_PARTICIPANTS, participants);

            return jsonObject;
        }
    }

}