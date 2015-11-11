package borell.com.suino.model;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

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
        this.result = deserializeSearchResult(json);
    }

    public int getResultLength(){
        return result.size();
    }

    public SuinoResultItem getResultAt(int position){
       return result.get(position);
    }


    private ArrayList<SuinoResultItem> deserializeSearchResult(String json){

        Type listType = new TypeToken<ArrayList<SuinoResultItem>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(listType, new SuinoSearchDeserializer());
        return gsonBuilder.create().fromJson(json, listType);
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

    private class SuinoSearchDeserializer implements JsonDeserializer<ArrayList<SuinoResultItem>> {
        public ArrayList<SuinoResultItem> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            final JsonArray jsonArray = json.getAsJsonArray();
            ArrayList<SuinoResultItem> list =  new ArrayList<SuinoResultItem>();

            for(int i = 0; i < jsonArray.size(); i++){
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                SuinoResultItem item = new SuinoResultItem().deserializeFromSearchResult(object.toString());
                list.add(item);
            }


            return list;

        }
    }
}
