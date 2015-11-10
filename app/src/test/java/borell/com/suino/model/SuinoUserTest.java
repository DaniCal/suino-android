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
public class SuinoUserTest {


    @Test
    public void test_construct_to_login(){
        String validFirstName = "Dani";
        String validLastName = "Lo";
        String validFbId = "123123123";
        String validPicture = "somelink.com/picture";
        String validEmail = "daniel.lohse@gmail.com";
        String validGender = "male";
        int validAge = 25;
        String validDeviceToken = "123b112j312b1j3c";

        SuinoUser user = new SuinoUser(validFirstName, validLastName, validFbId, validPicture, validEmail, validGender, validAge, validDeviceToken);

        assertEquals(validFirstName, user.getFbFirstName());
        assertEquals(validLastName, user.getFbLastName());
        assertEquals(validFbId, user.getFbId());
        assertEquals(validPicture, user.getFbPictureLink().toString());
        assertEquals(validEmail, user.getFbEmail());
        assertEquals(validGender, user.getFbGender());
        assertEquals(validAge, user.getAge());
        assertEquals(validDeviceToken, user.getDeviceToken());

    }


    @Test
    public void test_construct_from_search(){
        String validId = "1231242fsdf";
        String validFirstName = "Dani";
        String validPicture = "somelink.com/picture";
        int validAge = 25;

        SuinoUser user = new SuinoUser(validId, validFirstName, validPicture, validAge);

        assertEquals(validId, user.getId());
        assertEquals(validFirstName, user.getFbFirstName());
        assertEquals(validPicture, user.getFbPictureLink().toString());
        assertEquals(validAge, user.getAge());

    }

    @Test
    public void test_serialize_post(){

        String validFirstName = "Dani";
        String validLastName = "Lo";
        String validFbId = "123123123";
        String validPicture = "somelink.com/picture";
        String validEmail = "daniel.lohse@gmail.com";
        String validGender = "male";
        int validAge = 25;
        String validDeviceToken = "123b112j312b1j3c";

        SuinoUser user = new SuinoUser(validFirstName, validLastName, validFbId, validPicture, validEmail, validGender, validAge, validDeviceToken);

        String validJson = "{" +
                "\"fbId\":\"" + validFbId + "\"," +
                "\"fbName\":\"" + validFirstName + "\"," +
                "\"age\":" + validAge + "," +
                "\"gender\":\"" + validGender + "\"," +
                "\"fbPictureLink\":\"" + validPicture + "\"," +
                "\"email\":\"" + validEmail + "\"," +
                "\"deviceToken\":\"" + validDeviceToken + "\"," +
                "\"platform\":\"" + "android" + "\"" +
                "}";

        String result = user.createRegisterJson();
        assertEquals(validJson, result);
    }

    @Test
    public void test_deserialize_from_search(){
        String validId = "1231242fsdf";
        String validFirstName = "Dani";
        String validPicture = "somelink.com/picture";
        int validAge = 25;

        String validJson = "{" +
                "\"_id\":\"" + validId + "\"," +
                "\"fbName\":\"" + validFirstName + "\"," +
                "\"fbPictureLink\":\"" + validPicture + "\"," +
                "\"age\":" + validAge +
                "}";

        SuinoUser user = new SuinoUser().deserializeFromSearchResult(validJson);
        assertEquals(validId, user.getId());
        assertEquals(validFirstName, user.getFbFirstName());
        assertEquals(validPicture, user.getFbPictureLink().toString());
        assertEquals(validAge, user.getAge());

    }


}
