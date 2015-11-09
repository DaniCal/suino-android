package borell.com.suino.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import borell.com.suino.BuildConfig;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoEventTest {

    @Test
    public void test_construct(){
        assertFalse(true);
    }

    @Test
    public void test_id(){
        assertFalse(true);
    }

    @Test
    public void test_course_reference(){
        assertFalse(true);
    }

    @Test
    public void test_participants(){
        assertFalse(true);
    }

    @Test
    public void test_serialize_post_json(){
        assertFalse(true);
    }

    @Test
    public void test_deserialize_get_json(){
        assertFalse(true);
    }
}