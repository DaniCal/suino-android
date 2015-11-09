package borell.com.suino;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import borell.com.suino.model.SuinoCourse;
import borell.com.suino.model.SuinoEvent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoCourseTest{

    @Test
    public void test_if_course_is_valid(){
        SuinoCourse mSuinoCourse = new SuinoCourse();

        String validCategory = "sports";
        String validTag = "yoga";
        int level = 1;
        String validDescription = "This should be a very valid description";
        double validLongitude = 24.123;
        double validLatitude = 2.43434;
        boolean valid;
        boolean result;

        valid = mSuinoCourse.isValid();
        assertFalse(valid);

        mSuinoCourse.setCategory(validCategory);
        valid = mSuinoCourse.isValid();
        assertFalse(valid);

        mSuinoCourse.addKeyword(validTag);
        valid = mSuinoCourse.isValid();
        assertFalse(valid);

        mSuinoCourse.setLevel(level);
        valid = mSuinoCourse.isValid();
        assertFalse(valid);

        mSuinoCourse.setDescription(validDescription);
        valid = mSuinoCourse.isValid();
        assertFalse(valid);

        mSuinoCourse.setLocation(validLatitude, validLongitude);

        valid = mSuinoCourse.isValid();
        assertTrue(valid);
    }

    @Test
    public void test_category(){
        String validCategory = "sports";
        SuinoCourse mSuinoCourse = new SuinoCourse();
        mSuinoCourse.setCategory(validCategory);
        String result = mSuinoCourse.getCategory();
        assertEquals(result, validCategory);
    }

    @Test
    public void test_level(){
        int validLevel = 1;
        int invalidLevel_1 = 4;
        int invalidLevel_0 = 0;
        boolean validation;
        int result;

        SuinoCourse mSuinoCourse = new SuinoCourse();

        validation = mSuinoCourse.setLevel(invalidLevel_0);
        result = mSuinoCourse.getLevel();
        assertFalse(validation);
        assertNotEquals(invalidLevel_0, result);

        validation = mSuinoCourse.setLevel(invalidLevel_1);
        result = mSuinoCourse.getLevel();
        assertFalse(validation);
        assertNotEquals(invalidLevel_1, result);

        validation = mSuinoCourse.setLevel(validLevel);
        result = mSuinoCourse.getLevel();
        assertTrue(validation);
        assertEquals(validLevel, result);
    }

    @Test
    public void test_description(){
        String validDescription = "This should be a very valid description";
        String invalidDescription = "This is not valid";
        boolean validation;
        String result;

        SuinoCourse mSuinoCourse = new SuinoCourse();
        validation = mSuinoCourse.setDescription(invalidDescription);
        result = mSuinoCourse.getDescription();
        assertFalse(validation);
        assertNotEquals(invalidDescription, result);

        validation = mSuinoCourse.setDescription(validDescription);
        result = mSuinoCourse.getDescription();
        assertTrue(validation);
        assertEquals(validDescription, result);
    }

    @Test
    public void test_location(){
        double validLatitude = 22.3333;
        double validLongitude = 44.1231;

        double invalidLongitude = 180.01;
        double invalidLatitude = -90.2;

        boolean validation;
        double resultLongitude;
        double resultLatitude;

        SuinoCourse mSuinoCourse = new SuinoCourse();

        validation = mSuinoCourse.setLocation(invalidLatitude, validLongitude);
        resultLatitude = mSuinoCourse.getLatitude();
        resultLongitude = mSuinoCourse.getLongitude();
        assertFalse(validation);
        assertNotEquals(invalidLatitude, resultLatitude);
        assertNotEquals(validLongitude, resultLongitude);


        validation = mSuinoCourse.setLocation(validLatitude, invalidLongitude);
        resultLatitude = mSuinoCourse.getLatitude();
        resultLongitude = mSuinoCourse.getLongitude();
        assertFalse(validation);
        assertNotEquals(validLatitude, resultLatitude);
        assertNotEquals(invalidLongitude, resultLongitude);

        validation = mSuinoCourse.setLocation(validLatitude, validLongitude);
        resultLatitude = mSuinoCourse.getLatitude();
        resultLongitude = mSuinoCourse.getLongitude();
        assertTrue(validation);
        assertEquals(validLatitude, resultLatitude, 0);
        assertEquals(validLongitude, resultLongitude, 0);


    }

    @Test
    public void test_add_new_event(){
        SuinoCourse mSuinoCourse = new SuinoCourse();
        SuinoEvent newEvent = new SuinoEvent(1123123L, 123123L);
        int size;
        SuinoEvent result;
        mSuinoCourse.addSuinoEvent(newEvent);
        size = mSuinoCourse.getEvents().size();
        result = mSuinoCourse.getEvents().get(0);

        assertEquals(1, size);
        assertEquals(newEvent.getStart().getTimeInMillis(), result.getStart().getTimeInMillis());
        assertEquals(newEvent.getEnd().getTimeInMillis(), result.getEnd().getTimeInMillis());
    }

    @Test
    public void test_remove_event(){
        SuinoCourse mSuinoCourse = new SuinoCourse();
        SuinoEvent newEvent = new SuinoEvent(1123123L, 123123L);
        mSuinoCourse.addSuinoEvent(newEvent);

        boolean validation;

        validation = mSuinoCourse.removeEvent(1);
        assertFalse(validation);

        validation = mSuinoCourse.removeEvent(0);
        assertTrue(validation);

    }

    @Test
    public void test_add_new_keyword(){
        SuinoCourse mSuinoCourse = new SuinoCourse();
        String validKeyword = "beachvolley";
        mSuinoCourse.addKeyword(validKeyword);
        assertEquals(1, mSuinoCourse.getKeywords().size());
        assertEquals(validKeyword, mSuinoCourse.getKeywords().get(0));
    }

    @Test
    public void test_remove_keyword(){
        SuinoCourse mSuinoCourse = new SuinoCourse();
        String validKeyword = "beachvolley";
        mSuinoCourse.addKeyword(validKeyword);
        mSuinoCourse.removeKeyword(0);
        assertEquals(0, mSuinoCourse.getKeywords().size());
    }

    @Test
    public void test_price(){
        int validPrice = 10;
        int invalidPrice = 51;
        int defaultPrice = 15;
        SuinoCourse mSuinoCourse = new SuinoCourse();
        assertEquals(defaultPrice, mSuinoCourse.getPrice());

        mSuinoCourse.setPrice(invalidPrice);
        assertNotEquals(invalidPrice, mSuinoCourse.getPrice());

        mSuinoCourse.setPrice(validPrice);
        assertEquals(validPrice, mSuinoCourse.getPrice());
    }

    @Test
    public void test_groupSize(){

        int defaultGroupSize = 2;
        int invalidGroupSize = 21;
        int validGroupSize = 3;
        boolean validation;
        SuinoCourse mSuinoCourse = new SuinoCourse();

        assertEquals(defaultGroupSize, mSuinoCourse.getGroupSize());

        validation = mSuinoCourse.setGroupSize(invalidGroupSize);
        assertFalse(validation);
        assertNotEquals(invalidGroupSize, mSuinoCourse.getGroupSize());

        validation = mSuinoCourse.setGroupSize(validGroupSize);
        assertTrue(validation);
        assertEquals(validGroupSize, mSuinoCourse.getGroupSize());

        validation = mSuinoCourse.decreaseGroupSize();
        assertTrue(validation);
        assertEquals(validGroupSize - 1, mSuinoCourse.getGroupSize());

        mSuinoCourse.decreaseGroupSize();
        validation = mSuinoCourse.decreaseGroupSize();
        assertFalse(validation);
        assertEquals(1, mSuinoCourse.getGroupSize());
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

        String validJson = "{" +
                "\"category\":\"" + validCategory + "\"," +
                "\"description\":\"" + validDescription + "\"," +
                "\"groupSize\":" + validGroupSize + "," +
                "\"keywords\":" + "[\"" + validKeyword + "\"]," +
                "\"level\":" + + validLevel + "," +
                "\"latitude\":" + validLatitude+ "," +
                "\"longitude\":" + validLongitude+ "," +
                "\"price\":" + validPrice +
                "}";

        SuinoCourse mSuinoCourse = new SuinoCourse();
        mSuinoCourse.setCategory(validCategory);
        mSuinoCourse.addKeyword(validKeyword);
        mSuinoCourse.setLevel(validLevel);
        mSuinoCourse.setDescription(validDescription);
        mSuinoCourse.setLocation(validLatitude, validLongitude);
        mSuinoCourse.setGroupSize(validGroupSize);
        mSuinoCourse.setPrice(validPrice);

        String result = mSuinoCourse.createCreateCourseJson();
//        assertEquals(validJson.length(), result.length());
        assertEquals(validJson, result);

    }


}