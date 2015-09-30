package borell.com.suino.model;

import java.util.ArrayList;

/**
 * Created by daniellohse on 9/30/15.
 */
public class SuinoCourse {
    private String description;
    private String teacherFbId;
    private String teacherFirstName;
    private String teacherFbPictureLink;
    private int level;
    private int longitude;
    private int latitude;
    private String category;
    private ArrayList<String> tags;
    //private ArrayList<String> material;
    private int price;
    private int groupSize;
    private ArrayList<CourseDay> days;
    private ArrayList<CourseDate> dates;


    private class CourseDay{
        private int dayOfTheWeek;
        private int start;
        private int end;
    }

    private class CourseDate{
        private int date;
        private int start;
        private int end;
        private ArrayList<String> participants;
    }

}
