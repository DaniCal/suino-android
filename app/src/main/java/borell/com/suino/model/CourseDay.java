package borell.com.suino.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
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

    public JsonElement createCourseDayJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CourseDay.class, new SuinoCourseDaySerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, CourseDay.class);
    }

    private class SuinoCourseDaySerializer implements JsonSerializer<CourseDay> {
        private static final String VALUE_START = "start";
        private static final String VALUE_END = "end";
        private static final String VALUE_DAY_OF_THE_WEEK = "dayOfTheWeek";


        @Override
        public JsonElement serialize(CourseDay src, Type typeOfSrc, JsonSerializationContext context) {

            final JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty(VALUE_START, getStart().getTimeInMillis());
            jsonObject.addProperty(VALUE_END, getEnd().getTimeInMillis());
            jsonObject.addProperty(VALUE_DAY_OF_THE_WEEK, getDayOfTheWeek());

            return jsonObject;
        }
    }
}