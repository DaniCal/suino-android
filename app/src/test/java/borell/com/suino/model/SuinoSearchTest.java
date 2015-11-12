package borell.com.suino.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import borell.com.suino.BuildConfig;
import borell.com.suino.TestJson;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoSearchTest {



    @Test
    public void test_create_search_url(){

    }

    @Test
    public void test_replace_result(){
        SuinoSearch search = new SuinoSearch();
        search.replaceResult(TestJson.resultJson);
        assertEquals(3, search.getResultLength());
        assertNotNull(search.getResultAt(0));
        assertNotNull(search.getResultAt(0).getCourse());
        assertEquals(TestJson.validCourseId, search.getResultAt(0).getCourse().getId());
        assertNotNull(search.getResultAt(0).getEvent());
        assertEquals(TestJson.validEventId, search.getResultAt(0).getEvent().getId());
        assertNotNull(search.getResultAt(0).getTeacher());
        assertEquals(TestJson.validUserId, search.getResultAt(0).getTeacher().getId());
        assertNotNull(search.getResultAt(1));
        assertNotNull(search.getResultAt(1).getCourse());
        assertEquals(TestJson.validCourseId, search.getResultAt(1).getCourse().getId());
        assertNotNull(search.getResultAt(1).getEvent());
        assertEquals(TestJson.validEventId, search.getResultAt(1).getEvent().getId());
        assertNotNull(search.getResultAt(1).getTeacher());
        assertEquals(TestJson.validUserId, search.getResultAt(1).getTeacher().getId());
        assertNotNull(search.getResultAt(2));
        assertEquals(TestJson.validCourseId, search.getResultAt(2).getCourse().getId());
        assertNotNull(search.getResultAt(2).getCourse());
        assertNotNull(search.getResultAt(2).getEvent());
        assertEquals(TestJson.validEventId, search.getResultAt(2).getEvent().getId());
        assertNotNull(search.getResultAt(2).getTeacher());
        assertEquals(TestJson.validUserId, search.getResultAt(2).getTeacher().getId());

    }

    @Test
    public void test_reset_result(){

    }

    @Test
    public void test_add_result(){

    }

    @Test
    public void test_get_result_at(){

    }
}
