package borell.com.suino.model;

import java.util.ArrayList;


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
        days = new ArrayList<CourseDay>();
        dates = new ArrayList<CourseDate>();
    }


    public void addCourseDay(int day, int start, int end){
        CourseDay courseDay = new CourseDay(day, start, end);
        days.add(courseDay);
    }

    public void addCourseDate(int date, int start, int end){
        CourseDate courseDate = new CourseDate(date, start, end);
        dates.add(courseDate);
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


    public class CourseDay{
        private int dayOfTheWeek;
        private int start;
        private int end;

        public CourseDay(int dayOfTheWeek, int start, int end){
            this.dayOfTheWeek = dayOfTheWeek;
            this.start = start;
            this.end = end;
        }
    }

    public class CourseDate{
        private int date;
        private int start;
        private int end;
        private ArrayList<String> participants;

        public CourseDate(int date, int start, int end){
            this.date = date;
            this.start = start;
            this.end = end;
            participants = new ArrayList<String>();
        }
    }

}
