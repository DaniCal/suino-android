package borell.com.suino.model;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SuinoSearch {

    ArrayList<SuinoResultItem> result;
    SuinoFilter filter;



    public void setCategoryFilter(String category){

    }

    public void setGroupFilter(int groupSize){

    }

    public void setLevelFilter(int level){

    }

    public void setPriceFilter(int price){

    }

    public void setMaxDistanceFilter(int maxDistance){

    }

    public void addKeyword(String keyword){

    }

    

    public void resetFilter(){

    }

    public String createSearchUrl(){
        return "";
    }

    public String createSearchJson(){
        return "";
    }

    public void resetResult(){

    }

    public void addResult(String json){

    }

    public void replaceResult(String json){

    }

    public int getResultLength(){
        return result.size();
    }

    public SuinoResultItem getResultAt(int position){
       return result.get(position);
    }


    private class SuinoFilter{
        String categoryFilter;
        int groupFilter;
        int levelFilter;
        int priceFilter;
        int maxDistance;
        ArrayList<String> keywords;

        public void resetFilter(){

        }
    }

    private class SuinoUserDeserializer implements JsonDeserializer<SuinoSearch> {
        public SuinoSearch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            final JsonObject jsonObject = json.getAsJsonObject();

//            final String id = jsonObject.get("_id").getAsString();


            return null;

        }
    }
}
