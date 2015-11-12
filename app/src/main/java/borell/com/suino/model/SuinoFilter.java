package borell.com.suino.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.squareup.okhttp.HttpUrl;

import java.lang.reflect.Type;
import java.util.ArrayList;

import borell.com.suino.Http.HttpConfig;
import borell.com.suino.Http.HttpValues;

/**
 * Created by daniellohse on 11/11/15.
 */
public class SuinoFilter {
    private double latitude;
    private double longitude;
    String categoryFilter;
    int groupFilter;
//    int levelFilter;
    int priceFilter;
    int maxDistance;
    ArrayList<Integer> levelFilter;
    ArrayList<String> keywords;

    public static final int DISTANCE_MAX = 50;
    public static final int DISTANCE_DEFAULT = 20;
    public static final int DISTANCE_MIN = 5;



    public SuinoFilter(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        categoryFilter = "";
        keywords = new ArrayList<>();
        groupFilter = -1;
        levelFilter = new ArrayList<>();
        priceFilter = -1;
        maxDistance = -1;
    }

    public String createSearchUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HttpConfig.HOST)
                .port(HttpConfig.PORT)
                .addPathSegment(HttpValues.PATH_SEARCH)
                .build();
        return url.toString();
    }
    public void setCategoryFilter(String category){
        this.categoryFilter = category;
    }

    public String getCategoryFilter(){
        return this.categoryFilter;
    }

    public void setGroupFilter(int groupSize){
        this.groupFilter = groupSize;
    }

    public int getGroupFilter(){
        return this.groupFilter;
    }

    public void addLevelFilter(int level){
        this.levelFilter.add(level);
    }

    public void removeLevelFilter(int level){
        this.levelFilter.remove(level);
    }

    public boolean isLevelSet(int level){
        return this.levelFilter.contains(level);
    }

    public ArrayList<Integer> getLevelFilter(){
        return this.levelFilter;
    }

    public void setPriceFilter(int price){
        this.priceFilter = price;
    }

    public int getPriceFilter(){
        return this.priceFilter;
    }

    public void setMaxDistanceFilter(int maxDistance){
        this.maxDistance = maxDistance;
    }

    public int getMaxDistance(){
        return this.maxDistance;
    }

    public void addKeyword(String keyword){
        this.keywords.add(keyword);
    }

    public ArrayList<String> getKeywords(){
        return this.keywords;
    }

    public void resetFilter(){

    }

    public String createSearchJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoFilter.class, new SuinoFilterSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoFilter.class).toString();    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private class SuinoFilterSerializer implements JsonSerializer<SuinoFilter> {

        @Override
        public JsonElement serialize(SuinoFilter src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty("latitude", getLatitude());
            jsonObject.addProperty("longitude", getLongitude());

            if(categoryFilter != ""){
                jsonObject.addProperty("category", getCategoryFilter());
            }

            if(keywords.size() != 0){
                JsonArray keywordsArray = new JsonArray();
                for (String item : getKeywords()){
                    keywordsArray.add(new JsonPrimitive(item));
                }
                jsonObject.add("keywords", keywordsArray);
            }

            if(groupFilter != -1){
                jsonObject.addProperty("groupSize", getGroupFilter());
            }

            if(levelFilter.size() != 0){
                JsonArray levelArray = new JsonArray();
                for (int item : levelFilter){
                    levelArray.add(new JsonPrimitive(item));
                }
                jsonObject.add("level", levelArray);
            }

            if(priceFilter != -1){
                jsonObject.addProperty("price", getPriceFilter());
            }

            if(maxDistance != -1){
                jsonObject.addProperty("maxDistance", getMaxDistance());
            }

            return jsonObject;
        }
    }

}
