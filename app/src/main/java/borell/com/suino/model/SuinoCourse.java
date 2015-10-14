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
    private String teacherFbId;
    private String teacherFirstName;
    private String teacherFbPictureLink;
    private int level;
    private double longitude;
    private double latitude;
    private String category;
    private ArrayList<String> tags;
    //private ArrayList<String> material;
    private int price;
    private int groupSize;
    private ArrayList<CourseDay> days;
    private ArrayList<CourseDate> dates;

    public SuinoCourse(String teacherFbId, String teacherFirstName, String teacherFbPictureLink){
        this.teacherFbId = teacherFbId;
        this.teacherFirstName = teacherFirstName;
        this.teacherFbPictureLink = teacherFbPictureLink;
        longitude = -1;
        latitude = -1;
        days = new ArrayList<CourseDay>();
        dates = new ArrayList<CourseDate>();
    }


    public void addCourseDay(int day, long start, long end){
        CourseDay courseDay = new CourseDay(day, start, end);
        days.add(courseDay);
    }

    public void addCourseDate(long start, long end){
        CourseDate courseDate = new CourseDate(start, end);
        dates.add(courseDate);
    }

    public ArrayList<CourseDay> getDays(){
        return days;
    }

    public ArrayList<CourseDate> getDates(){
        return dates;
    }



    public void addTag(String tag){
        tags.add(tag);
    }

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }


    public boolean isValid() {
        return getErrorMessage().length() <= 0;
    }

    public String getErrorMessage(){
        if(!checkCategory()){
            return "Please choose a category";
//        }else if(!checkTags()){
//            return "Please add at least one tag";

        }else if(!checkLevel()){
            return "Please set the course level";

        }else if(!checkDescription()){
            return "Please specify the course description";

        }else if(!checkLocation()){
            return "Please select the course location";

        }else if(!checkDates()){
            return "Please add at least one day, when the course will take place";
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

    private boolean checkDates(){
        return !(dates.size() == 0 && days.size() == 0);
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

        private static final String VALUE_TITLE = "title";
        private static final String VALUE_DESCRIPTION = "description";
        private static final String VALUE_TEACHER_DB_ID = "teacherFbId";
        private static final String VALUE_TEACHER_FIRST_NAME = "teacherFirstName";
        private static final String VALUE_TEACHER_FB_PICTURE_LINK = "teacherFbPictureLink";
        private static final String VALUE_LEVEL = "level";
        private static final String VALUE_LOCATION = "location";
        private static final String VALUE_LONGITUDE = "longitude";
        private static final String VALUE_LATITUDE = "latitude";

        private static final String VALUE_CATEGORY = "category";
        private static final String VALUE_TAGS = "tags";
        private static final String VALUE_PRICE = "price";
        private static final String VALUE_GROUP_SIZE = "groupSize";
        private static final String VALUE_AVAILABILITY = "availability";




        @Override
        public JsonElement serialize(SuinoCourse src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject locationObject = new JsonObject();
            locationObject.addProperty(VALUE_LONGITUDE, getLongitude());
            locationObject.addProperty(VALUE_LATITUDE, getLatitude());

            final JsonArray tagArray = new JsonArray();
            for(String tag : tags){
                JsonPrimitive element = new JsonPrimitive(tag);
                tagArray.add(element);
            }


            final JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty(VALUE_DESCRIPTION, getDescription());
            jsonObject.addProperty(VALUE_TEACHER_DB_ID, teacherFbId);
            jsonObject.addProperty(VALUE_TEACHER_FIRST_NAME, teacherFirstName);
            jsonObject.addProperty(VALUE_TEACHER_FB_PICTURE_LINK, teacherFbPictureLink);
            jsonObject.addProperty(VALUE_LEVEL, getLevel());
            jsonObject.addProperty(VALUE_CATEGORY, getCategory());
            jsonObject.addProperty(VALUE_PRICE, getPrice());
            jsonObject.addProperty(VALUE_GROUP_SIZE, getGroupSize());
            jsonObject.add(VALUE_LOCATION, locationObject);
            jsonObject.add(VALUE_TAGS, tagArray);







            return jsonObject;
        }
    }

}
