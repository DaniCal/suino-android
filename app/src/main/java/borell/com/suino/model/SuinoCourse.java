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


public class SuinoCourse {
    private String description;
    private int level;
    private double longitude;
    private double latitude;
    private String category;
    private ArrayList<String> tags;
    private int price;
    private int groupSize;
    private ArrayList<SuinoEvent> events;
    private SuinoUser teacherUser;

    private final int LEVEL_MIN = 1;
    private final int LEVEL_MAX = 3;
    private final int PRICE_MAX = 50;
    private final int PRICE_DEFAULT = 15;
    private final int GROUP_SIZE_MIN = 1;
    private final int GROUP_SIZE_MAX = 20;
    private final int GROUP_SIZE_DEFAULT= 2;
    private final int DESCRIPTION_MIN = 20;
    private final int DESCRIPTION_MAX = 200;
    private double LATITUDE_MAX = 90;
    private double LATITUDE_MIN = -90;
    private double LONGITUDE_MAX = 180;
    private double LONGITUDE_MIN = -180;


    public SuinoCourse(){

        longitude = -1;
        latitude = -1;
        events = new ArrayList<SuinoEvent>();
        tags = new ArrayList<String>();
        level = Integer.MIN_VALUE;
        price = PRICE_DEFAULT;
        groupSize = GROUP_SIZE_DEFAULT;
    }


    public void addSuinoEvent(SuinoEvent newEvent){
        events.add(newEvent);
    }

    public boolean removeEvent(int position){
        if(position >= events.size()){
            return false;
        }
        events.remove(0);
        return true;
    }

    public ArrayList<SuinoEvent> getEvents(){
        return events;
    }

    public void addKeyword(String tag){
        tags.add(tag);
    }

    public void removeKeyword(int position){
        tags.remove(position);
    }

    public ArrayList<String> getKeywords() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if(description.length() < DESCRIPTION_MIN || description.length() > DESCRIPTION_MAX){
            return false;
        }
        this.description = description;
        return true;
    }

    public int getLevel() {
        return level;
    }

    public boolean setLevel(int level) {
        if(level < LEVEL_MIN || level > LEVEL_MAX){
            return false;
        }
        this.level = level;
        return true;
    }

    public boolean setLocation(double latitude, double longitude){
        if(latitude > LATITUDE_MAX || latitude < LATITUDE_MIN || longitude > LONGITUDE_MAX || longitude < LONGITUDE_MIN){
            return false;
        }

        this.latitude = latitude;
        this.longitude = longitude;
        return true;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public boolean setPrice(int price) {
        if(price < 0 || price > PRICE_MAX){
            return false;
        }
        this.price = price;
        return true;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public boolean setGroupSize(int groupSize) {
        if(groupSize < GROUP_SIZE_MIN || groupSize > GROUP_SIZE_MAX){
            return false;
        }
        this.groupSize = groupSize;
        return true;
    }

    public boolean increaseGroupSize(){
        return setGroupSize(groupSize + 1);
    }

    public boolean decreaseGroupSize(){
        return setGroupSize(groupSize - 1);
    }

    public boolean isValid() {
        return getErrorMessage().length() <= 0;
    }

    public String getErrorMessage(){
        if(!checkCategory()){
            return "Please choose a category";
        }else if(!checkTags()){
            return "Please add at least one keyword";

        }else if(!checkLevel()){
            return "Please set the course level";

        }else if(!checkDescription()){
            return "Please specify the course description";

        }else if(!checkLocation()){
            return "Please select the course location";

        }

        return "";
    }

    private boolean checkCategory(){
        return !(category == null || category.length() == 0);
    }

    private boolean checkTags(){
        return !(tags == null || tags.size() == 0);
    }

    private boolean checkLevel(){
        return !(level < 1 || level > 3);
    }

    private boolean checkDescription(){
        return !(description == null || description.length() < 20);
    }

    private boolean checkLocation(){
        return !(latitude == -1 || longitude == -1);
    }

    public String createCreateCourseUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HttpConfig.HOST)
                .port(HttpConfig.PORT)
                .addPathSegment(HttpValues.PATH_CREATE_COURSE)
                .build();
        return url.toString();
    }

    public String createCreateCourseJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoCourse.class, new SuinoCourseSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoCourse.class).toString();
    }

    private class SuinoCourseSerializer implements JsonSerializer<SuinoCourse> {

        private static final String VALUE_CATEGORY = "category";
        private static final String VALUE_DESCRIPTION = "description";
        private static final String VALUE_GROUP_SIZE = "groupSize";
        private static final String VALUE_KEYWORDS = "keywords";
        private static final String VALUE_LEVEL = "level";
        private static final String VALUE_LATITUDE = "latitude";
        private static final String VALUE_LONGITUDE = "longitude";
        private static final String VALUE_PRICE = "price";


        @Override
        public JsonElement serialize(SuinoCourse src, Type typeOfSrc, JsonSerializationContext context) {

            final JsonArray keywords = new JsonArray();
            for(String keyword : getKeywords()){
                JsonPrimitive element = new JsonPrimitive(keyword);
                keywords.add(element);
            }

            final JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty(VALUE_CATEGORY, getCategory());
            jsonObject.addProperty(VALUE_DESCRIPTION, getDescription());
            jsonObject.addProperty(VALUE_GROUP_SIZE, getGroupSize());
            jsonObject.add(VALUE_KEYWORDS, keywords);
            jsonObject.addProperty(VALUE_LEVEL, getLevel());
            jsonObject.addProperty(VALUE_LATITUDE, getLatitude());
            jsonObject.addProperty(VALUE_LONGITUDE, getLongitude());
            jsonObject.addProperty(VALUE_PRICE, getPrice());

            return jsonObject;
        }
    }

}
