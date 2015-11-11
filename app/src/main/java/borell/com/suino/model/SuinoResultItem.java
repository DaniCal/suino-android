package borell.com.suino.model;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;

public class SuinoResultItem{
    public SuinoCourse course;
    public SuinoEvent event;
    public SuinoUser teacher;

    public SuinoResultItem(){

    }

    public SuinoResultItem(SuinoCourse course, SuinoEvent event, SuinoUser teacher){
        this.course = course;
        this.event = event;
        this.teacher = teacher;
    }

    public SuinoResultItem deserializeFromSearchResult(String json){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoResultItem.class, new SuinoResultItemDeserializer());
        return gsonBuilder.create().fromJson(json, SuinoResultItem.class);
    }


    public SuinoCourse getCourse(){
        return this.course;
    }

    public SuinoEvent getEvent(){
        return this.event;
    }

    public SuinoUser getTeacher(){
        return this.teacher;
    }




    private class SuinoResultItemDeserializer implements JsonDeserializer<SuinoResultItem> {
        public SuinoResultItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            final JsonObject jsonObject = json.getAsJsonObject();
            final JsonObject eventObject = jsonObject.getAsJsonObject("event");
            final JsonObject courseObject = jsonObject.getAsJsonObject("course");
            final JsonObject userObject = jsonObject.getAsJsonObject("user");

            SuinoEvent event = new SuinoEvent().deserializeFromSearchResult(eventObject.toString());
            SuinoCourse course = new SuinoCourse().deserializeFromSearchResult(courseObject.toString());
            SuinoUser user = new SuinoUser().deserializeFromSearchResult(userObject.toString());



            SuinoResultItem item = new SuinoResultItem(course, event, user);

            return item;

        }
    }

}