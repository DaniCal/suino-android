package borell.com.suino.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import borell.com.suino.BuildConfig;
import borell.com.suino.TestJson;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoResultItemTest {
    @Test
    public void test_deserialize(){

        SuinoResultItem item = new SuinoResultItem().deserializeFromSearchResult(TestJson.resultItemJson);

        assertEquals(TestJson.validCourseId, item.getCourse().getId());
        assertEquals(TestJson.validCategory, item.getCourse().getCategory());
        assertEquals(TestJson.validLevel, item.getCourse().getLevel());
        assertEquals(TestJson.validDescription, item.getCourse().getDescription());
        assertEquals(TestJson.validLatitude, item.getCourse().getLatitude(),0 );
        assertEquals(TestJson.validLongitude, item.getCourse().getLongitude(), 0);
        assertEquals(TestJson.validKeyword, item.getCourse().getKeywords().get(0));
        assertEquals(TestJson.validPrice, item.getCourse().getPrice());
        assertEquals(TestJson.validGroupSize, item.getCourse().getGroupSize());

        assertEquals(TestJson.validEventId, item.getEvent().getId());
        assertEquals(TestJson.validStart, item.getEvent().getStartAsUnixTimestamp());
        assertEquals(TestJson.validEnd, item.getEvent().getEndAsUnixTimestamp());

        assertEquals(TestJson.validUserId, item.getTeacher().getId());
        assertEquals(TestJson.validFirstName, item.getTeacher().getFbFirstName());
        assertEquals(TestJson.validPicture, item.getTeacher().getFbPictureLink().toString());
        assertEquals(TestJson.validAge, item.getTeacher().getAge());

    }
}
