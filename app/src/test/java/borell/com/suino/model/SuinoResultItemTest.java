package borell.com.suino.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import borell.com.suino.BuildConfig;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoResultItemTest {
    @Test
    public void test_deserialize(){
        String validCategory = "sports";
        int validLevel = 1;
        String validDescription = "This should be a very valid description";
        double validLatitude = 22.3333;
        double validLongitude = 44.1231;
        String validKeyword = "beachvolley";
        int validPrice = 10;
        int validGroupSize = 3;
        String validCourseId = "4edd40c86762e0fb12000102";

        String courseJson = "{" +
                "\"_id\":\"" + validCourseId + "\"," +
                "\"category\":\"" + validCategory + "\"," +
                "\"description\":\"" + validDescription + "\"," +
                "\"groupSize\":" + validGroupSize + "," +
                "\"keywords\":" + "[\"" + validKeyword + "\"]," +
                "\"level\":" + + validLevel + "," +
                "\"location\":[" + validLatitude + ","+ validLongitude + "]," +
                "\"price\":" + validPrice +
                "}";


        String validUserId = "1231242fsdf";
        String validFirstName = "Dani";
        String validPicture = "somelink.com/picture";
        int validAge = 25;
        String userJson = "{" +
                "\"_id\":\"" + validUserId + "\"," +
                "\"fbName\":\"" + validFirstName + "\"," +
                "\"fbPictureLink\":\"" + validPicture + "\"," +
                "\"age\":" + validAge +
                "}";

        String validEventId = "4edd40c86762e0fb12000102";
        int validStart = 1450792800;
        int validEnd = 1450796400;

        String eventJson = "{" +
                "\"_id\":\"" + validEventId + "\"," +
                "\"start\":" + validStart + "," +
                "\"end\":" + validEnd +
                "}";

        String json = "{" +
                "\"event\":" + eventJson + "," +
                "\"course\":" + courseJson + "," +
                "\"user\":" + userJson +
                "}";


        SuinoResultItem item = new SuinoResultItem().deserializeFromSearchResult(json);
        assertEquals(validCourseId, item.getCourse().getId());
        assertEquals(validCategory, item.getCourse().getCategory());
        assertEquals(validLevel, item.getCourse().getLevel());
        assertEquals(validDescription, item.getCourse().getDescription());
        assertEquals(validLatitude, item.getCourse().getLatitude(),0 );
        assertEquals(validLongitude, item.getCourse().getLongitude(), 0);
        assertEquals(validKeyword, item.getCourse().getKeywords().get(0));
        assertEquals(validPrice, item.getCourse().getPrice());
        assertEquals(validGroupSize, item.getCourse().getGroupSize());

        assertEquals(validEventId, item.getEvent().getId());
        assertEquals(validStart, item.getEvent().getStartAsUnixTimestamp());
        assertEquals(validEnd, item.getEvent().getEndAsUnixTimestamp());

        assertEquals(validUserId, item.getTeacher().getId());
        assertEquals(validFirstName, item.getTeacher().getFbFirstName());
        assertEquals(validPicture, item.getTeacher().getFbPictureLink().toString());
        assertEquals(validAge, item.getTeacher().getAge());

    }
}
