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
public class SuinoEventTest {

    @Test
    public void test_construct_to_create(){
        long validStart = 1450792800;
        long validEnd = 1450796400;
        SuinoEvent mEvent_1 = new SuinoEvent(validStart * 1000L, validEnd * 1000L);
        assertEquals(validStart * 1000L, mEvent_1.getStart().getTimeInMillis());
        assertEquals(validEnd * 1000L, mEvent_1.getEnd().getTimeInMillis());
        assertEquals(validStart, mEvent_1.getStartAsUnixTimestamp());
        assertEquals(validEnd, mEvent_1.getEndAsUnixTimestamp());
        assertEquals(0, mEvent_1.numberOfParticipants());

        SuinoEvent mEvent_2 = new SuinoEvent((int)validStart, (int) validEnd);
        assertEquals(validStart * 1000L, mEvent_2.getStart().getTimeInMillis());
        assertEquals(validEnd * 1000L, mEvent_2.getEnd().getTimeInMillis());
        assertEquals(validStart, mEvent_2.getStartAsUnixTimestamp());
        assertEquals(validEnd, mEvent_2.getEndAsUnixTimestamp());
        assertEquals(0, mEvent_2.numberOfParticipants());
    }


    @Test
    public void test_construct_from_search(){
        long validStart = 1450792800;
        long validEnd = 1450796400;
        String id = "123123123";
        ArrayList<String> participants =  new ArrayList<>();
        participants.add("000000");
        participants.add("111111");

        SuinoEvent mEvent = new SuinoEvent((int)validStart, (int) validEnd, participants, id);
        assertEquals(validStart * 1000L, mEvent.getStart().getTimeInMillis());
        assertEquals(validEnd * 1000L, mEvent.getEnd().getTimeInMillis());
        assertEquals(validStart, mEvent.getStartAsUnixTimestamp());
        assertEquals(validEnd, mEvent.getEndAsUnixTimestamp());
        assertEquals(id, mEvent.getId());
        assertEquals(2, mEvent.numberOfParticipants());

    }

    @Test
    public void test_participants(){
        long validStart = 1450792800;
        long validEnd = 1450796400;
        SuinoEvent mEvent = new SuinoEvent(validStart, validEnd);
        mEvent.addParticipant("111111");

        assertEquals(1, mEvent.numberOfParticipants());
        mEvent.addParticipant("111111");
        assertEquals(2, mEvent.numberOfParticipants());
        mEvent.removeParticipant("111111");
        assertEquals(1, mEvent.numberOfParticipants());
    }

    @Test
    public void test_serialize_post_json(){
        String validCategory = "sports";
        int validLevel = 1;
        String validDescription = "This should be a very valid description";
        double validLatitude = 22.3333;
        double validLongitude = 44.1231;
        String validKeyword = "beachvolley";
        int validPrice = 10;
        int validGroupSize = 3;
        String validId = "4edd40c86762e0fb12000102";

        String courseJson = "{" +
                "\"_id\":\"" + validId + "\"," +
                "\"category\":\"" + validCategory + "\"," +
                "\"description\":\"" + validDescription + "\"," +
                "\"groupSize\":" + validGroupSize + "," +
                "\"keywords\":" + "[\"" + validKeyword + "\"]," +
                "\"level\":" + + validLevel + "," +
                "\"location\":[" + validLatitude + ","+ validLongitude + "]," +
                "\"price\":" + validPrice +
                "}";

        SuinoCourse course = new SuinoCourse().deserializeFromSearchResult(courseJson);
        int validStart = 1450792800;
        int validEnd = 1450796400;


        String validJson = "{" +
                "\"_course\":\"" + validId + "\"," +
                "\"start\":" + validStart + "," +
                "\"end\":" + validEnd +
                "}";


        SuinoEvent mEvent = new SuinoEvent(validStart, validEnd);
        mEvent.setCourse(course);
        String json = mEvent.createPostEventJson();
        assertEquals(validJson, json);
    }

    @Test
    public void test_deserialize_search_result(){
        int validStart = 1450792800;
        int validEnd = 1450796400;
        String validId = "4edd40c86762e0fb12000102";

        String validJson = "{" +
                "\"_id\":\"" + validId + "\"," +
                "\"start\":" + validStart + "," +
                "\"end\":" + validEnd +
                "}";

        SuinoEvent event = new SuinoEvent(1,2).deserializeFromSearchResult(validJson);
        assertEquals(validStart, event.getStartAsUnixTimestamp());
        assertEquals(validEnd, event.getEndAsUnixTimestamp());
        assertEquals(validId,event.getId());
    }
}